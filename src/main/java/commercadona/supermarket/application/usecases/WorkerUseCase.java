package commercadona.supermarket.application.usecases;

import java.util.List;

import commercadona.supermarket.infraestructure.dtos.request.WorkerRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.WorkerResponseDTO;

public interface WorkerUseCase {
    
    List<WorkerResponseDTO> getListWorkers();

    List<WorkerResponseDTO> getListWorkersByIdStore(Long idStore);

    WorkerResponseDTO getWorkerByDni(String dni);

    List<WorkerResponseDTO> getWorkersByName(String name, String lastNames);

    WorkerResponseDTO createWorker(WorkerRequestDTO workerDTO);

    WorkerResponseDTO updateWorker(String dni, WorkerRequestDTO workerDTO);

    WorkerResponseDTO deleteWorker(String dni);

    WorkerResponseDTO assignWorker(String dni,String section,int hours);

    WorkerResponseDTO unassignWorker(String dni, String section);
}
