package commercadona.supermarket.application.mapper;

import commercadona.supermarket.domain.model.Report;
import commercadona.supermarket.domain.model.ReportRemaining;
import commercadona.supermarket.infraestructure.dtos.response.ReportResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.ReportRemainingResponseDTO;

public interface ReportMapper {
    ReportResponseDTO mapReportToReportDTO(Report report);
    ReportRemainingResponseDTO mapReportRemainingToReportRemainingDTO(ReportRemaining reportRemaining);
}
