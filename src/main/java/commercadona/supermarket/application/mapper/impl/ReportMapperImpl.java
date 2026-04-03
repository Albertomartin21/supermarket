package commercadona.supermarket.application.mapper.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.ReportMapper;
import commercadona.supermarket.domain.model.Report;
import commercadona.supermarket.domain.model.ReportRemaining;
import commercadona.supermarket.domain.model.ReportWorker;
import commercadona.supermarket.infraestructure.dtos.response.ReportResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.ReportRemainingResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.ReportWorkerResponseDTO;

@Service
public class ReportMapperImpl implements ReportMapper{

    @Override
    public ReportResponseDTO mapReportToReportDTO(Report report) {
        return ReportResponseDTO.builder()
        .name(report.getName())
        .sectionMap(mapSectionMap(report.getSectionMap()))
        .address(report.getAddress())
        .city(report.getCity())
        .build();
    }

    
    @Override
    public ReportRemainingResponseDTO mapReportRemainingToReportRemainingDTO(ReportRemaining reportRemaining) {
        return ReportRemainingResponseDTO.builder()
        .name(reportRemaining.getName())
        .sectionMap(reportRemaining.getSectionMap())
        .address(reportRemaining.getAddress())
        .city(reportRemaining.getCity())
        .build();
    }

    private Map<String, List<ReportWorkerResponseDTO>> mapSectionMap(Map<String, List<ReportWorker>> sectionMap) {
            return sectionMap.entrySet().stream()
                    .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> mapReportWorkerToReportWorkerDTO(entry.getValue())
                ));
    }

    private List<ReportWorkerResponseDTO> mapReportWorkerToReportWorkerDTO(List<ReportWorker> workers) {
    return workers.stream()
                  .map(worker -> ReportWorkerResponseDTO.builder()
                                    .name(worker.getName())
                                    .hours(worker.getHours())
                                    .build())
                  .toList();
}
   
}
