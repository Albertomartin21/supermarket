package commercadona.supermarket.domain.model;

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
public class Report {
    private String name;
    private Map<String, List<ReportWorker>> sectionMap;
    private String address;
    private String city;
}
