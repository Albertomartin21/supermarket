package commercadona.supermarket.infraestructure.entity;


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
public class WorkerEntity {
    @Id
    private String dni;
    private String name;
    private String lastNames;
    private int maxHours;
    private int assignedHours; 
    private Long idStore;
    @ElementCollection
    @CollectionTable(name = "sections_workers",joinColumns = @JoinColumn(name = "worker_dni"))
    @MapKeyColumn(name = "section")
    @Column(name = "hours")
    private Map<String, Integer> mapSections;
}