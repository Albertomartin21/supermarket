package commercadona.supermarket.infraestructure.dtos.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionResponseDTO {
    private String section;
    private Set<String> skills;
}
