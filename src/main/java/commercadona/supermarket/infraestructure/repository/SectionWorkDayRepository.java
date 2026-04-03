package commercadona.supermarket.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import commercadona.supermarket.infraestructure.entity.SectionWorkDayEntity;

@Repository
public interface SectionWorkDayRepository extends JpaRepository<SectionWorkDayEntity, Long> {
    
}
