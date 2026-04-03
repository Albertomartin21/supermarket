package commercadona.supermarket.application.mapper;

import commercadona.supermarket.domain.model.Worker;
import commercadona.supermarket.infraestructure.dtos.request.WorkerRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.WorkerResponseDTO;
import commercadona.supermarket.infraestructure.entity.WorkerDayStorageEntity;
import commercadona.supermarket.infraestructure.entity.WorkerEntity;

public interface WorkerMapper {

    WorkerResponseDTO mapWorkerToWorkerResponseDTO(Worker worker);
    Worker mapWorkerRequestDTOToWorker(WorkerRequestDTO workerdto);
    Worker mapWorkerEntityToWorker(WorkerEntity workerEntity);
    WorkerEntity mapWorkerToWorkerEntity(Worker workerEntity);
    WorkerDayStorageEntity mapWorkerToWorkerStorageEntity(Worker worker);
}
