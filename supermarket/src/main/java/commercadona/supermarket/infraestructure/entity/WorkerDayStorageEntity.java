package commercadona.supermarket.infraestructure.entity;

import java.time.LocalDate;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WorkerDayStorageEntity {
    @Id
    private String dni;
    private String name;
    private String lastNames;
    private Long idStore;
    private LocalDate localDate;
    @ElementCollection
    @CollectionTable(name = "sections_storage_workers",joinColumns = @JoinColumn(name = "dni"))
    @MapKeyColumn(name = "section")
    @Column(name = "hours")
    private Map<String, Integer> mapSections;
}