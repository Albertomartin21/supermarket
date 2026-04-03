package commercadona.supermarket.domain.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreWorkDay {
    
    private Long id;

    private LocalDate date;

    private Long idStore;
    
    private List<SectionWorkDay> listSections;
}
