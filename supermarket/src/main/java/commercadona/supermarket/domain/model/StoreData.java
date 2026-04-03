package commercadona.supermarket.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreData {
    private Integer id;
    private String description;
    private String address;
    private String city;
}