package commercadona.supermarket.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store {
    private Long id;
    private String name;
    List<Worker> listWorkers;
    List<Section> listSection;
    private String address;
    private String city;
}
