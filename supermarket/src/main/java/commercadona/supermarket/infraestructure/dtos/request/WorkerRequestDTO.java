package commercadona.supermarket.infraestructure.dtos.request;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerRequestDTO {
    private String dni;
    private String name;
    private String lastNames;
    private Map<String, Integer> mapSection;
    private String maxHours;
    Long idStore;
}
