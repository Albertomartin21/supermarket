package commercadona.supermarket.domain.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import commercadona.supermarket.domain.exception.StoreNotFoundErrorException;
import commercadona.supermarket.domain.model.Report;
import commercadona.supermarket.domain.model.ReportRemaining;
import commercadona.supermarket.domain.model.ReportWorker;
import commercadona.supermarket.domain.model.Store;
import commercadona.supermarket.domain.model.StoreData;
import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.domain.rest.StorePortRest;
import commercadona.supermarket.domain.service.ReportService;
import commercadona.supermarket.domain.service.StoreService;
import commercadona.supermarket.domain.service.StoreWorkDayServiceEntity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceImp implements ReportService {

    private final StoreService storeService;
    private final StoreWorkDayServiceEntity storeWorkDayService;
    private final StorePortRest storePortRest;

    public ReportServiceImp(StoreService storeService, StoreWorkDayServiceEntity storeWorkDayService, StorePortRest storePortRest){
        this.storeService = storeService;
        this.storeWorkDayService = storeWorkDayService;
        this.storePortRest = storePortRest;
    }

    @Override
    public Report getStoreReport(Long idStore) {
        Store store = storeService.getStoreById(idStore);
        Map<String, List<ReportWorker>> mapSections = generateMapReport(store);
        StoreData storeData = getStoreDataById(idStore);
        return Report.builder()
            .name(store.getName())
            .sectionMap(mapSections)
            .address(storeData.getAddress())
            .city(storeData.getCity())
            .build();
    }

    @Override
    public ReportRemaining getStoreReportRemaining(Long idStore) {
        Store store = storeService.getStoreById(idStore);
        Map<String, Integer> mapSections = generateMapRemaining(store);
        StoreData storeData = getStoreDataById(idStore);
        return ReportRemaining.builder()
            .name(store.getName())
            .sectionMap(mapSections)
            .address(storeData.getAddress())
            .city(storeData.getCity())
            .build();
    }
    


    private Map<String, List<ReportWorker>> generateMapReport(Store store){
        Map<String, List<ReportWorker>> mapSections = new HashMap<>();
         Optional.ofNullable(store.getListWorkers())
            .orElse(Collections.emptyList())
            .forEach(worker -> {
                worker.getMapSection().forEach((section, hours) ->
                {
                    List<ReportWorker> listReportWorkers = Optional.ofNullable(mapSections.get(section))
                        .orElseGet(ArrayList::new);
                    ReportWorker reportWorker = ReportWorker.builder()
                        .name(worker.getName())
                        .hours(hours)    
                        .build();
                    listReportWorkers.add(reportWorker);
                    mapSections.put(section, listReportWorkers);
                }
                );;
            });

        return mapSections;
    }


    private Map<String, Integer> generateMapRemaining(Store store){
        StoreWorkDay storeWorkDay = storeWorkDayService.getStoreWorkDayByIdStoreAndDate(store.getId(), LocalDate.now());
        Map<String, Integer> mapSections = new HashMap<>();
        Optional.ofNullable(storeWorkDay.getListSections())
            .orElse(Collections.emptyList())
            .forEach(sectionWorkDay -> {
                int hoursRemaining = sectionWorkDay.getRequiredHours() - sectionWorkDay.getAssignedHours();
                if(hoursRemaining>0)
                    mapSections.put(sectionWorkDay.getSection().name(), hoursRemaining);
                }
        );
        return mapSections;
    }

     private StoreData getStoreDataById(Long storeId) {
        return Optional.ofNullable(storePortRest.geStoreDataById(storeId))
             .orElseThrow(() -> new StoreNotFoundErrorException(String.format("Error al buscar, no se ha encontrado los datos de la tienda: [%s]", storeId)));
    }
}
