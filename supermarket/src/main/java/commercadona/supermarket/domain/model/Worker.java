package commercadona.supermarket.domain.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Worker {
    private String dni;
    private String name;
    private String lastNames;
    private int maxHours; 
    private int hoursAssignedDay; 
    private Long idStore;
    private Map<String, Integer> mapSection;
}

