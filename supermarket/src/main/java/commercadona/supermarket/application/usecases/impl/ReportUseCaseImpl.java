package commercadona.supermarket.application.usecases.impl;


import org.springframework.stereotype.Service;
import commercadona.supermarket.application.mapper.ReportMapper;
import commercadona.supermarket.application.usecases.ReportUseCase;
import commercadona.supermarket.domain.service.ReportService;
import commercadona.supermarket.infraestructure.dtos.response.ReportResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.ReportRemainingResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportUseCaseImpl implements ReportUseCase {
    

    private final ReportService reportService;
    private final ReportMapper reportMapper;



    public ReportUseCaseImpl(ReportService reportService, ReportMapper reportMapper){
        this.reportService = reportService;
        this.reportMapper = reportMapper;
    }
    

    @Override
    public ReportResponseDTO getStoreReport(Long idStore) {
        return reportMapper.mapReportToReportDTO(reportService.getStoreReport(idStore));
    }


    @Override
    public ReportRemainingResponseDTO getStoreReportRemaining(Long idStore) {
         return reportMapper.mapReportRemainingToReportRemainingDTO(reportService.getStoreReportRemaining(idStore));
    }
    
}
