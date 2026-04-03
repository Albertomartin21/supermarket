package commercadona.supermarket.infraestructure.dtos.response;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponseDTO { 
    private String name;
    private Map<String, List<ReportWorkerResponseDTO>> sectionMap;
    private String address;
    private String city;
}
