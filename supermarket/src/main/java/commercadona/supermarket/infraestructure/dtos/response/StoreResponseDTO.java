package commercadona.supermarket.infraestructure.dtos.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private List<SectionResponseDTO> sectionList;
}
