package commercadona.supermarket.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import commercadona.supermarket.infraestructure.entity.WorkerEntity;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, String> {
    List<WorkerEntity> findByNameAndLastNamesIgnoreCase(String name, String lastNames);

    List<WorkerEntity> findByIdStore(Long idStore);
}
