package commercadona.supermarket.infraestructure.rest.contract;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page {
    private int size;
    private int number;
    private int totalElements;
    private int totalPages;
}
