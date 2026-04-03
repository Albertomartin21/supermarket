package commercadona.supermarket.domain.service;

import commercadona.supermarket.domain.model.Report;
import commercadona.supermarket.domain.model.ReportRemaining;

public interface ReportService {
    Report getStoreReport(Long idStore);
    ReportRemaining getStoreReportRemaining(Long idStore);
}
