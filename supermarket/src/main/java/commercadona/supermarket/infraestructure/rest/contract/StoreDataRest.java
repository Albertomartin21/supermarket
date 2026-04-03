package commercadona.supermarket.infraestructure.rest.contract;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDataRest {
        private int id;
        private String description;
        private String address;
        private String city;
    }
