package commercadona.supermarket.infraestructure.dtos.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerResponseDTO {
    private String dni;
    private String name;
    private String lastNames;
    private Map<String, Integer> mapSection;
    private String maxHours;
    Long idStore;
}
