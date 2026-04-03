package commercadona.supermarket.infraestructure.entity;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SectionEntity {
    
    @Id
    private String section;

    @ElementCollection
    @CollectionTable(name = "section_skills", joinColumns = @JoinColumn(name = "section_id"))
    @Column(name = "skills")
    private Set<String> skillsList;
    
}
