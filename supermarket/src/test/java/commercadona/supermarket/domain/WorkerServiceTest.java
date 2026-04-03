package commercadona.supermarket.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import commercadona.supermarket.domain.exception.WorkerErrorException;
import commercadona.supermarket.domain.exception.WorkerNotFoundErrorException;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.domain.model.SectionWorkDay;
import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.domain.model.Worker;
import commercadona.supermarket.domain.repository.WorkerPort;
import commercadona.supermarket.domain.service.StoreWorkDayServiceEntity;
import commercadona.supermarket.domain.service.WorkerService;
import commercadona.supermarket.domain.service.impl.WorkerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class WorkerServiceTest {

    private WorkerService workerService;

    @Mock 
    private WorkerPort workerPort;

    @Mock 
    private StoreWorkDayServiceEntity storeWorkDayService;

    @BeforeEach
    void setUp(){
        workerService = new WorkerServiceImpl(workerPort, storeWorkDayService);
    }


    @Test
    void shouldGetAllWorkers() {
        List<Worker> listWorker= new ArrayList<>();
        int sizeList = 5;
        for(int i = 0;i<sizeList;i++){
            listWorker.add(generateWorker("dni_"+i, "name_"+i, "lastName_"+i, "section_"+i, 8));
        }
        when(workerPort.getAllWorkers()).thenReturn(listWorker);
        var result = workerService.getListWorkers();
        
        assertNotNull(result);
        assertEquals(sizeList, result.size());
        verify(workerPort).getAllWorkers();
    }

    @Test
    void shouldGetAllWorkersEmpty() {
        List<Worker> listWorker= new ArrayList<>();
        when(workerPort.getAllWorkers()).thenReturn(listWorker);
        var result = workerService.getListWorkers();
        
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(workerPort).getAllWorkers();
    }

    @Test
    void shouldGetWorkerByDni() {
        String dni = "0123456789A";
        Worker worker = generateWorker(dni, "name", "lastName", Section.DROGUERIA.name(), 8);
        when(workerPort.getWorkerByDni(dni)).thenReturn(worker);
        var result = workerService.getWorkerByDni(dni);
        
        assertNotNull(result);
        assertEquals(dni, result.getDni());
        verify(workerPort).getWorkerByDni(dni);
    }

    @Test
    void shouldNotFoundWorkerByDni() {
        String dni = "0123456789A";
        when(workerPort.getWorkerByDni(dni)).thenReturn(null);
    
        assertThrows(WorkerNotFoundErrorException.class, () -> {
            workerService.getWorkerByDni(dni);
        });
        verify(workerPort).getWorkerByDni(dni);
    }


    @Test
    void shouldGetWorkerByName() {
        String name = "name";
        String lastName = "lastName";
        List<Worker> listWorker= new ArrayList<>();
        listWorker.add(generateWorker("dni", name, lastName, Section.DROGUERIA.name(), 8));
        when(workerPort.getWorkersByName(name, lastName)).thenReturn(listWorker);
        var result = workerService.getWorkersByName(name, lastName);
        
        assertNotNull(result);
        assertEquals(name, result.get(0).getName());
        assertEquals(lastName, result.get(0).getLastNames());
        verify(workerPort).getWorkersByName(name, lastName);
    }

    @Test
    void shouldNotFoundGetWorkerByName() {
        String name = "name";
        String lastName = "lastName";
        List<Worker> listWorker= new ArrayList<>();
        listWorker.add(generateWorker("dni", name, lastName, Section.DROGUERIA.name(), 8));
        when(workerPort.getWorkersByName(name, lastName)).thenReturn(listWorker);
        var result = workerService.getWorkersByName(name, lastName);
        
        assertNotNull(result);
        assertEquals(name, result.get(0).getName());
        assertEquals(lastName, result.get(0).getLastNames());
        verify(workerPort).getWorkersByName(name, lastName);
    }

    @Test
    void shouldCreateWorker() {
        String dni = "0123456789A";
        Worker worker = generateWorker(dni, "name", "lastName", Section.DROGUERIA.name(), 8);
        when(workerPort.createWorker(worker)).thenReturn(worker);
        var result = workerService.createWorker(worker);
        
        assertNotNull(result);
        assertEquals(worker.getDni(), result.getDni());
        assertEquals(worker.getName(), result.getName());
        assertEquals(worker.getLastNames(), result.getLastNames());
        assertEquals(worker.getMapSection(), result.getMapSection());
        assertEquals(worker.getMaxHours(), result.getMaxHours());
        verify(workerPort).createWorker(worker);
    }

    
    @Test
    void shouldCreateWorkerAndthrowException() {
        String dni = "0123456789A";
        Worker worker = generateWorker(dni, "name", "lastName", Section.DROGUERIA.name(), 8);
        when(workerPort.createWorker(worker)).thenReturn(null);
    
        assertThrows(WorkerErrorException.class, () -> {
            workerService.createWorker(worker);
        });
        verify(workerPort).createWorker(worker);
    }

    @Test
    void shouldDeleteWorker() {
        String dni = "0123456789A";
        Worker worker = generateWorker(dni, "name", "lastName", Section.DROGUERIA.name(), 8);
        when(workerPort.getWorkerByDni(dni)).thenReturn(worker);
        when(workerPort.deleteWorker(dni)).thenReturn(worker);
        var result = workerService.deleteWorker(dni);
        
        assertNotNull(result);
        assertEquals(worker.getDni(), result.getDni());
        assertEquals(worker.getName(), result.getName());
        assertEquals(worker.getLastNames(), result.getLastNames());
        assertEquals(worker.getMapSection(), result.getMapSection());
        assertEquals(worker.getMaxHours(), result.getMaxHours());
        verify(workerPort).deleteWorker(dni);
    }


    @Test
    void shouldDeleteWorkerAndthrowException() {
        String dni = "0123456789A";
        when(workerPort.getWorkerByDni(dni)).thenReturn(null);
    
        assertThrows(WorkerNotFoundErrorException.class, () -> {
            workerService.deleteWorker(dni);
        });

        verify(workerPort, never()).deleteWorker(dni);
    }

    @Test
    void shouldUpdateWorker() {
        String dni = "0123456789A";
        Worker worker = generateWorker(dni, "name", "lastName", Section.DROGUERIA.name(), 8);
        Worker workerUpdate = generateWorker(dni, "nameUpdate", "lastNameUpdate", "sectionUpdate", 8);
        StoreWorkDay storeWorkDayApi = StoreWorkDay.builder().build();
        when(workerPort.updateWorker(any(Worker.class))).thenReturn(workerUpdate);
        when(workerPort.getWorkerByDni(dni)).thenReturn(worker);
        when(storeWorkDayService.getStoreWorkDayByIdStoreAndDate(worker.getIdStore(), LocalDate.now()))
                    .thenReturn(storeWorkDayApi);
        var result = workerService.updateWorker(dni, worker);
        
        assertNotNull(result);
        assertEquals(workerUpdate.getDni(), result.getDni());
        assertEquals(workerUpdate.getName(), result.getName());
        assertEquals(workerUpdate.getLastNames(), result.getLastNames());
        assertEquals(workerUpdate.getMapSection(), result.getMapSection());
        assertEquals(workerUpdate.getMaxHours(), result.getMaxHours());
        verify(workerPort).updateWorker(any(Worker.class));
    }


    
    @Test
    void shouldAssignWorker() {
        String dni = "0123456789A";
        String section =  Section.DROGUERIA.name();
        int hours = 4;
        Worker worker = generateWorker(dni, "name", "lastName", section, 8);
        StoreWorkDay storeWorkDay = generateStoreWorkerDay(worker.getIdStore());
        when(workerPort.getWorkerByDni(dni)).thenReturn(worker);
        when(workerPort.updateWorker(any(Worker.class))).thenReturn(worker);
        when(storeWorkDayService.getStoreWorkDayByIdStoreAndDate(worker.getIdStore(),LocalDate.now())).thenReturn(storeWorkDay);

        var result = workerService.assignWorker(dni, Section.CAJAS.name(), hours);
        
        assertNotNull(result);
        verify(workerPort, times(2)).getWorkerByDni(dni);
        verify(workerPort).updateWorker(any(Worker.class));
        verify(storeWorkDayService, times(2)).getStoreWorkDayByIdStoreAndDate(worker.getIdStore(),LocalDate.now());
    }


    @Test
    void shouldUnassignWorker() {
        String dni = "0123456789A";
        String section =  Section.DROGUERIA.name();
        Worker worker = generateWorker(dni, "name", "lastName", section, 8);
        StoreWorkDay storeWorkDay = generateStoreWorkerDay(worker.getIdStore());
        when(workerPort.getWorkerByDni(dni)).thenReturn(worker);
        when(workerPort.updateWorker(any(Worker.class))).thenReturn(worker);
        when(storeWorkDayService.getStoreWorkDayByIdStoreAndDate(worker.getIdStore(),LocalDate.now())).thenReturn(storeWorkDay);

        var result = workerService.unassignWorker(dni, section);
        
        assertNotNull(result);
        assertEquals(result.getMapSection().size(), 0);
        assertFalse(result.getMapSection().containsKey(section));
        storeWorkDay.getListSections().stream()
            .filter(sect -> sect.equals(section))
            .forEach(sect -> assertEquals(sect.getAssignedHours(), -2));
        verify(workerPort, times(2)).getWorkerByDni(dni);
        verify(workerPort).updateWorker(any(Worker.class));
        verify(storeWorkDayService, times(2)).getStoreWorkDayByIdStoreAndDate(worker.getIdStore(),LocalDate.now());
    }

    

    @Test
    void shouldUpdateWorkerAndthrowException() {
        String dni = "0123456789A";
        Worker worker = generateWorker(dni, "name", "lastName", Section.DROGUERIA.name(), 8);
        when(workerPort.getWorkerByDni(dni)).thenReturn(null);
    
        assertThrows(WorkerNotFoundErrorException.class, () -> {
            workerService.updateWorker(dni, worker);
        });
        verify(workerPort, never()).updateWorker(worker);
    }

    private Worker generateWorker(String dni, String name, String lastName, String section, int maxHours){
         return Worker.builder()
            .dni(dni)
            .name(name)
            .lastNames(lastName)
            .mapSection(new HashMap<>(Map.of(section, 2)))
            .maxHours(maxHours)
            .hoursAssignedDay(0)
            .idStore(1L)
            .build();
    }

    private StoreWorkDay generateStoreWorkerDay(Long idStore){
         return StoreWorkDay.builder()
            .date(LocalDate.now())
            .idStore(idStore)
            .listSections(Arrays.stream(Section.values())  
                        .map(this::generateSectionWorkDay)                 
                        .toList())
            .build();
    }

    private SectionWorkDay generateSectionWorkDay(Section section){
         return SectionWorkDay.builder()
            .section(section)
            .assignedHours(0)
            .requiredHours(8)
            .build();
    }
}