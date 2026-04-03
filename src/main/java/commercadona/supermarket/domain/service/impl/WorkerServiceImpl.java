package commercadona.supermarket.domain.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import commercadona.supermarket.domain.exception.SectionErrorException;
import commercadona.supermarket.domain.exception.WorkerErrorException;
import commercadona.supermarket.domain.exception.WorkerNotFoundErrorException;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.domain.model.Worker;
import commercadona.supermarket.domain.repository.WorkerPort;
import commercadona.supermarket.domain.service.StoreWorkDayServiceEntity;
import commercadona.supermarket.domain.service.WorkerService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WorkerServiceImpl implements WorkerService {

    private final WorkerPort workerPort;

    private final StoreWorkDayServiceEntity storeWorkDayService;



    public WorkerServiceImpl(WorkerPort workerPort, StoreWorkDayServiceEntity storeWorkDayService){
        this.workerPort = workerPort;
        this.storeWorkDayService = storeWorkDayService;
    }

    @Override
    public List<Worker> getListWorkers() {
        return workerPort.getAllWorkers();
    }

     @Override
    public List<Worker> getListWorkersByIdStore(Long idStore) {
        return workerPort.getListWorkersByIdStore(idStore);
    }

    @Override
    public Worker getWorkerByDni(String dni) {
        return Optional.ofNullable(workerPort.getWorkerByDni(dni))
             .orElseThrow(() -> new WorkerNotFoundErrorException(String.format("Error al buscar, no se ha encontrado al trabajador con dni: [%s]", dni)));
    }

    @Override
    public List<Worker> getWorkersByName(String name, String lastNames) {
         return workerPort.getWorkersByName(name, lastNames);
    }

    @Override
    public Worker createWorker(Worker worker) {
        worker.getMapSection().forEach((key ,value) -> checkSection(key));
        worker.setHoursAssignedDay(0);
        return Optional.ofNullable(workerPort.createWorker(worker))
            .orElseThrow(() -> new WorkerErrorException(String.format("Error al crear el trabajador con dni [%s]", worker.getDni())));
    }

    @Override
    public Worker updateWorker(String dni, Worker worker) {
        worker.getMapSection().forEach((key ,value) -> checkSection(key));
        Worker workerDatabase = getWorkerByDni(dni);
        Long idStoreWorkerApi = Optional.ofNullable(worker.getIdStore())
                               .orElse(workerDatabase.getIdStore());

        StoreWorkDay storeWorkDayApi = storeWorkDayService.getStoreWorkDayByIdStoreAndDate(idStoreWorkerApi,
             LocalDate.now());

        StoreWorkDay storeWorkDayDatabase = 
            (workerDatabase.getIdStore() != idStoreWorkerApi)
            ? storeWorkDayService.getStoreWorkDayByIdStoreAndDate(workerDatabase.getIdStore(), LocalDate.now())
            : storeWorkDayApi;
        
        workerDatabase.getMapSection().forEach((section ,hours) -> removeSectionHours(section, storeWorkDayDatabase, workerDatabase));
        worker.getMapSection().forEach((section ,hours) -> checkSectionHours(section, hours, storeWorkDayApi));
        workerDatabase.setName(worker.getName());
        workerDatabase.setLastNames(worker.getLastNames());
        workerDatabase.setMaxHours(worker.getMaxHours());
        workerDatabase.setHoursAssignedDay(worker.getHoursAssignedDay());
        workerDatabase.setIdStore(idStoreWorkerApi);
        workerDatabase.setMapSection(worker.getMapSection());

        storeWorkDayService.saveStoreWorkDay(storeWorkDayApi);
        if(workerDatabase.getIdStore() != idStoreWorkerApi)
            storeWorkDayService.saveStoreWorkDay(storeWorkDayDatabase);
          return Optional.ofNullable(workerPort.updateWorker(workerDatabase))
            .orElseThrow(() -> new WorkerErrorException(String.format("Error al crear el trabajador con dni [%s]", worker.getDni())));
   
    }

    @Override
    public Worker deleteWorker(String dni) {
        Worker workerDatabase = getWorkerByDni(dni);
        StoreWorkDay storeWorkDayApi = storeWorkDayService.getStoreWorkDayByIdStoreAndDate(workerDatabase.getIdStore(),
             LocalDate.now());
        workerDatabase.getMapSection().forEach((section ,hours) -> removeSectionHours(section, storeWorkDayApi, workerDatabase));
        storeWorkDayService.saveStoreWorkDay(storeWorkDayApi);
        return workerPort.deleteWorker(dni);
    }

    @Override
    public Worker assignWorker(String dni, String section, int hours) {
        checkSection(section);
        Worker worker = getWorkerByDni(dni);
        StoreWorkDay storeWorkDay = storeWorkDayService.getStoreWorkDayByIdStoreAndDate(worker.getIdStore(), LocalDate.now());
        checkSectionHours(section, hours, storeWorkDay);
        checkWorkerHours(hours, worker);
        worker.setHoursAssignedDay(worker.getHoursAssignedDay() + hours);
        worker.getMapSection().put(section, hours);
        updateWorker(dni, worker);
        storeWorkDayService.saveStoreWorkDay(storeWorkDay);
        return worker;
    }

    @Override
    public Worker unassignWorker(String dni, String section) {
        checkSection(section);
        Worker worker = getWorkerByDni(dni);
        StoreWorkDay storeWorkDay = storeWorkDayService.getStoreWorkDayByIdStoreAndDate(worker.getIdStore(), LocalDate.now());
        removeSectionHours(section, storeWorkDay, worker);
        worker.setHoursAssignedDay(worker.getHoursAssignedDay() - worker.getMapSection().get(section));
        worker.getMapSection().remove(section);
        updateWorker(dni, worker);
        storeWorkDayService.saveStoreWorkDay(storeWorkDay);
        return worker;
    }

    private void checkSection(String section){
        Optional.ofNullable(section)
            .map(String::toUpperCase)
            .filter(sect -> Section.valueOf(sect) != null)
            .orElseThrow(() -> new SectionErrorException(String.format("Error con la seccion, [%s] no es una seccion valida",
             section)));
    }

    private void checkSectionHours(String section, int hours, StoreWorkDay storeWorkDay){
       Optional.ofNullable(storeWorkDay)
            .map(swd -> swd.getListSections())
            .orElse(Collections.emptyList())
            .stream() 
            .filter(sect -> sect.getSection().name().equals(section))
            .findFirst()
            .ifPresent(sect -> {
                int currentHours = sect.getRequiredHours() - sect.getAssignedHours();
                int result = currentHours - hours;
                 if(result>=0){
                    log.info("HORAS ASIGNADAS {} EN SECCION {}",sect.getAssignedHours() + hours, section);
                    sect.setAssignedHours(sect.getAssignedHours() + hours);
                 }
                else
                    throw new SectionErrorException(
                String.format("Error con la seccion [%s], se ha pasado el limite de horas diarias, quedan [%s]", section,
                currentHours));
            });
    }    

     private void checkWorkerHours(int hours, Worker worker){
        if(worker.getHoursAssignedDay() + hours > hours)
            throw new WorkerErrorException(
        String.format("Error el trabajador [%s], se ha pasado el limite de horas diarias, quedan [%s]",worker.getDni(),
         worker.getMaxHours() - worker.getHoursAssignedDay()));
    } 

     private void removeSectionHours(String section, StoreWorkDay storeWorkDay, Worker worker){
         Optional.ofNullable(storeWorkDay)
            .map(swd -> swd.getListSections())
            .orElse(Collections.emptyList())
            .stream()
            .filter(sect -> sect.getSection().name().equals(section))
            .findFirst()
            .ifPresent(sect -> {
               sect.setAssignedHours(sect.getAssignedHours() - worker.getMapSection().get(section));
            });
    }

     @Override
     public void saveAll(List<Worker> listWorkers) {
        workerPort.saveAll(listWorkers);
     }

     @Override
     public void saveStorageWorker(List<Worker> listWorkers) {
        workerPort.saveStorageWorker(listWorkers);
     }    
}
