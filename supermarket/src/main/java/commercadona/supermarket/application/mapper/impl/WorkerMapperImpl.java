package commercadona.supermarket.application.mapper.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.WorkerMapper;
import commercadona.supermarket.domain.model.Worker;
import commercadona.supermarket.infraestructure.dtos.request.WorkerRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.WorkerResponseDTO;
import commercadona.supermarket.infraestructure.entity.WorkerDayStorageEntity;
import commercadona.supermarket.infraestructure.entity.WorkerEntity;

@Service
public class WorkerMapperImpl implements WorkerMapper{

    @Override
    public WorkerResponseDTO mapWorkerToWorkerResponseDTO(Worker worker) {
        return WorkerResponseDTO.builder()
                .dni(worker.getDni())
                .name(worker.getName())
                .lastNames(worker.getLastNames())
                .mapSection(worker.getMapSection())
                .maxHours(String.valueOf(worker.getMaxHours()))
                .idStore(worker.getIdStore())
                .build();
    }

    @Override
    public Worker mapWorkerRequestDTOToWorker(WorkerRequestDTO workerdto) {
        return Worker.builder()
                .dni(workerdto.getDni())
                .name(workerdto.getName())
                .lastNames(workerdto.getLastNames())
                .mapSection(workerdto.getMapSection())
                .maxHours(Integer.parseInt(workerdto.getMaxHours()))
                .idStore(workerdto.getIdStore())
                .build();
    }

      @Override
      public Worker mapWorkerEntityToWorker(WorkerEntity workerEntity) {
       return Worker.builder()
                .dni(workerEntity.getDni())
                .name(workerEntity.getName())
                .lastNames(workerEntity.getLastNames())
                .mapSection(workerEntity.getMapSections())
                .maxHours(workerEntity.getMaxHours())
                .hoursAssignedDay(workerEntity.getAssignedHours())
                .idStore(workerEntity.getIdStore())
                .build();
      }

      @Override
      public WorkerEntity mapWorkerToWorkerEntity(Worker worker) {
         return WorkerEntity.builder()
                .dni(worker.getDni())
                .name(worker.getName())
                .lastNames(worker.getLastNames())
                .mapSections(worker.getMapSection())
                .maxHours(worker.getMaxHours())
                .idStore(worker.getIdStore())
                .assignedHours(worker.getHoursAssignedDay())
                .build();
      }

      @Override
      public WorkerDayStorageEntity mapWorkerToWorkerStorageEntity(Worker worker) {
        return WorkerDayStorageEntity.builder()
                .dni(worker.getDni())
                .name(worker.getName())
                .lastNames(worker.getLastNames())
                .mapSections(worker.getMapSection())
                .idStore(worker.getIdStore())
                .localDate(LocalDate.now().minusDays(1))
                .build();
      }
    
}
