package commercadona.supermarket.infraestructure.repository.port;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.WorkerMapper;
import commercadona.supermarket.domain.model.Worker;
import commercadona.supermarket.domain.repository.WorkerPort;
import commercadona.supermarket.infraestructure.entity.WorkerDayStorageEntity;
import commercadona.supermarket.infraestructure.repository.WorkerRepository;
import commercadona.supermarket.infraestructure.repository.WorkerStorageRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WorkerPortImpl implements WorkerPort {

    
    private WorkerRepository workerRepository;
    private WorkerStorageRepository workerStorageRepository;
    private WorkerMapper mapperService;

    public WorkerPortImpl(WorkerRepository workerRepository, WorkerMapper mapperService,
         WorkerStorageRepository workerStorageRepository) {
        this.workerRepository = workerRepository;
        this.mapperService = mapperService;
        this.workerStorageRepository = workerStorageRepository;
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll().stream()
            .map(worker -> mapperService.mapWorkerEntityToWorker(worker))
            .collect(Collectors.toList());
    }

    @Override
    public List<Worker> getListWorkersByIdStore(Long idStore) {
        return workerRepository.findByIdStore(idStore).stream()
            .map(worker -> mapperService.mapWorkerEntityToWorker(worker))
            .collect(Collectors.toList());
    }

    @Override
    public Worker getWorkerByDni(String dni) {
          return workerRepository.findById(dni) 
            .map(worker -> mapperService.mapWorkerEntityToWorker(worker))
            .orElse(null);
    }

    @Override
    public List<Worker> getWorkersByName(String name, String lastNames) {
          return workerRepository.findByNameAndLastNamesIgnoreCase(name, lastNames).stream()
            .map(worker -> mapperService.mapWorkerEntityToWorker(worker))
            .collect(Collectors.toList());
    }

    @Override
    public Worker createWorker(Worker worker) {
        return Optional.of(worker)
            .map(workerDomain -> mapperService.mapWorkerToWorkerEntity(workerDomain))
            .map(workerEntity -> workerRepository.save(workerEntity))
            .map(savedEntity -> mapperService.mapWorkerEntityToWorker(savedEntity)) 
            .orElse(null);
    }

    @Override
    public Worker updateWorker(Worker worker) {
         return Optional.of(worker)
            .map(workerDomain -> mapperService.mapWorkerToWorkerEntity(workerDomain))
            .map(workerEntity -> workerRepository.save(workerEntity))
            .map(savedEntity -> mapperService.mapWorkerEntityToWorker(savedEntity)) 
            .orElse(null);
    }

    @Override
    public Worker deleteWorker(String dni) {
            return workerRepository.findById(dni)
            .map(workerEntity -> {
                workerRepository.delete(workerEntity);
                return mapperService.mapWorkerEntityToWorker(workerEntity);
            })
            .orElse(null);
    }

    @Override
    public void saveAll(List<Worker> listWorkers) {
        var listEntities = listWorkers.stream()
                .map(worker -> mapperService.mapWorkerToWorkerEntity(worker))
                .toList();
        workerRepository.saveAll(listEntities);
    }

    @Override
    public void saveStorageWorker(List<Worker> listWorkers) {

        var listEntities = listWorkers.stream()
                .map(worker -> mapperService.mapWorkerToWorkerStorageEntity(worker))
                .toList();
        workerStorageRepository.saveAll(listEntities);
    }
}
