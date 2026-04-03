package commercadona.supermarket.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import commercadona.supermarket.infraestructure.entity.StoreEntity;
import commercadona.supermarket.infraestructure.entity.WorkerEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<WorkerEntity> findByNameIgnoreCase(String name);
}
