package commercadona.supermarket.infraestructure.rest.contract;

import lombok.Data;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreResponseRest {
    private List<StoreDataRest> content;
    private Page page;
}
