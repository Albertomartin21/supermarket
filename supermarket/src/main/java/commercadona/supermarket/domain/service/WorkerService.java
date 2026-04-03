package commercadona.supermarket.domain.service;

import java.util.List;

import commercadona.supermarket.domain.model.Worker;

public interface WorkerService {
    
    List<Worker> getListWorkers();

    List<Worker> getListWorkersByIdStore(Long idStore);

    Worker getWorkerByDni(String dni);

    List<Worker> getWorkersByName(String name, String lastNames);

    Worker createWorker(Worker worker);

    Worker updateWorker(String dni, Worker worker);

    Worker deleteWorker(String dni);

    Worker assignWorker(String dni,String section,int hours);

    Worker unassignWorker(String dni, String section);

    void saveAll(List<Worker> listWorkers);

    void saveStorageWorker(List<Worker> listWorkers);
}
