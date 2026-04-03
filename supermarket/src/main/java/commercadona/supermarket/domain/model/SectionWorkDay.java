package commercadona.supermarket.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionWorkDay {

    private Long id;

    private Section section;

    private Integer assignedHours;

    private Integer requiredHours;
}
