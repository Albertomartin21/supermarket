package commercadona.supermarket.application.usecases;

import commercadona.supermarket.infraestructure.dtos.response.ReportResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.ReportRemainingResponseDTO;

public interface ReportUseCase {
    ReportResponseDTO getStoreReport(Long idStore);
    ReportRemainingResponseDTO getStoreReportRemaining(Long idStore);
}
