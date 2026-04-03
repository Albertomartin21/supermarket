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
public class ReportRemainingResponseDTO { 
    private String name;
    private Map<String, Integer> sectionMap;
    private String address;
    private String city;
}
