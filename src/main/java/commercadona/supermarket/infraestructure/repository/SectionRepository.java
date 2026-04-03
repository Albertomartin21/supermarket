package commercadona.supermarket.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import commercadona.supermarket.infraestructure.entity.SectionEntity;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, String> {
    
}
