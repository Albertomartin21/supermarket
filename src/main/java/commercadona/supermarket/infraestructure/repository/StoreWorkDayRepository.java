package commercadona.supermarket.infraestructure.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import commercadona.supermarket.infraestructure.entity.StoreWorkDayEntity;

@Repository
public interface StoreWorkDayRepository extends JpaRepository<StoreWorkDayEntity, Long> {

    @Query("SELECT s FROM StoreWorkDayEntity s LEFT JOIN FETCH s.listSections WHERE s.idStore = :idStore AND s.date = :localDate")
    Optional<StoreWorkDayEntity> findByIdStoreAndDate(Long idStore, LocalDate localDate);
    
    @Query("SELECT s FROM StoreWorkDayEntity s LEFT JOIN FETCH s.listSections")
    List<StoreWorkDayEntity> getAllList();
}
