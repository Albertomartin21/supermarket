package commercadona.supermarket.domain.repository;

import java.util.List;

import commercadona.supermarket.domain.model.Worker;

public interface WorkerPort {
    List<Worker> getAllWorkers();

    List<Worker> getListWorkersByIdStore(Long idStore);
    
    Worker getWorkerByDni(String dni);
    
    List<Worker> getWorkersByName(String name, String lastNames);

    Worker createWorker(Worker worker);

    Worker updateWorker(Worker worker);

    Worker deleteWorker(String dni);

    void saveAll(List<Worker> listWorkers);
    
    void saveStorageWorker(List<Worker> listWorkers);
}
