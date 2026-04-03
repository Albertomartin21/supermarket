package commercadona.supermarket.application.usecases.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.WorkerMapper;
import commercadona.supermarket.application.usecases.WorkerUseCase;
import commercadona.supermarket.domain.service.WorkerService;
import commercadona.supermarket.infraestructure.dtos.request.WorkerRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.WorkerResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WorkerUseCaseImpl implements WorkerUseCase {

    private final WorkerService workerService;
    private final WorkerMapper workerMapper;



    public WorkerUseCaseImpl(WorkerService workerService, WorkerMapper workerMapper){
        this.workerService = workerService;
        this.workerMapper = workerMapper;
    }



    @Override
    public List<WorkerResponseDTO> getListWorkers() {
        return workerService.getListWorkers().stream()
            .map(worker -> workerMapper.mapWorkerToWorkerResponseDTO(worker))
            .collect(Collectors.toList());
    }

    @Override
    public List<WorkerResponseDTO> getListWorkersByIdStore(Long idStore) {
        return workerService.getListWorkersByIdStore(idStore).stream()
            .map(worker -> workerMapper.mapWorkerToWorkerResponseDTO(worker))
            .collect(Collectors.toList());
    }

    @Override
    public WorkerResponseDTO getWorkerByDni(String dni) {
         return workerMapper.mapWorkerToWorkerResponseDTO(workerService.getWorkerByDni(dni));
    }



    @Override
    public List<WorkerResponseDTO> getWorkersByName(String name, String lastNames) {
        return workerService.getWorkersByName(name, lastNames).stream()
            .map(worker -> workerMapper.mapWorkerToWorkerResponseDTO(worker))
            .collect(Collectors.toList());
    }

    @Override
    public WorkerResponseDTO deleteWorker(String dni) {
        return workerMapper.mapWorkerToWorkerResponseDTO(workerService.deleteWorker(dni));
    }



    @Override
    public WorkerResponseDTO createWorker(WorkerRequestDTO workerDTO) {
      var worker = workerService.createWorker(workerMapper.mapWorkerRequestDTOToWorker(workerDTO));
      return workerMapper.mapWorkerToWorkerResponseDTO(worker);
    }



    @Override
    public WorkerResponseDTO updateWorker(String dni, WorkerRequestDTO workerDTO) {
        var worker = workerService.updateWorker(dni,workerMapper.mapWorkerRequestDTOToWorker(workerDTO));
        return workerMapper.mapWorkerToWorkerResponseDTO(worker);
    }



    @Override
    public WorkerResponseDTO assignWorker(String dni, String section, int hours) {
        return workerMapper.mapWorkerToWorkerResponseDTO(workerService.assignWorker(dni, section, hours));
    }



    @Override
    public WorkerResponseDTO unassignWorker(String dni, String section) {
         return workerMapper.mapWorkerToWorkerResponseDTO(workerService.unassignWorker(dni, section));
    }


    


}