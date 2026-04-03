package commercadona.supermarket.infraestructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commercadona.supermarket.infraestructure.dtos.response.ReportResponseDTO;
import commercadona.supermarket.application.usecases.ReportUseCase;
import commercadona.supermarket.infraestructure.dtos.response.ReportRemainingResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/reports")
public class ReportsController {
    
       private ReportUseCase reportUseCase;

       public ReportsController(ReportUseCase reportUseCase){
        this.reportUseCase = reportUseCase;
    }


    @Operation(summary = "Obtener el reporte de la tienda por el identificador")
    @GetMapping("/{storeId}")
    public ReportResponseDTO getStoreReport(@PathVariable Long storeId){
        log.info("Iniciando el reporte de la tienda {}", storeId);
        var report = reportUseCase.getStoreReport(storeId);
        log.info("Terminado el reporte de la tienda {}", storeId);
        return report;
    }

    @GetMapping("/remaining/{storeId}")
    public ReportRemainingResponseDTO getStoreReportRemaining(@PathVariable Long storeId){
        log.info("Iniciando el reporte de la tienda restante {}", storeId);
        var report = reportUseCase.getStoreReportRemaining(storeId);
        log.info("Terminado el reporte de la tienda restante {}", storeId);
        return report;
    }
}
