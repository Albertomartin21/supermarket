package commercadona.supermarket.domain.cron;

import java.time.LocalDate;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import commercadona.supermarket.domain.model.SectionWorkDay;
import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.domain.model.Worker;
import commercadona.supermarket.domain.service.StoreWorkDayServiceEntity;
import commercadona.supermarket.domain.service.WorkerService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CronDaily {

    private StoreWorkDayServiceEntity storeWorkDayService;
    private WorkerService workerService;

    public CronDaily(StoreWorkDayServiceEntity storeWorkDayService, WorkerService workerService){
        this.storeWorkDayService = storeWorkDayService;
        this.workerService = workerService;
    }

    
     @Scheduled(cron = "${scheduler.reset-sections-cron}")
     public void cronJob() {
        log.info("Iniciando cron de reseteo diario");

        List<StoreWorkDay> storeWorkList = storeWorkDayService.getAllStoreWorkDay();
        List<StoreWorkDay> newStoreWorkList = getDailySectionWorkDay(storeWorkList);
        storeWorkDayService.saveAllStoreWorkDay(newStoreWorkList);
        List<Worker> listWorkers =  workerService.getListWorkers();
        List<Worker> listStorageWorkers = listWorkers.stream()
            .map(worker -> {
                Worker copy = worker.toBuilder().build();
                worker.setHoursAssignedDay(0);
                worker.getMapSection().clear();
                return copy;
            })
            .toList();
        workerService.saveAll(listWorkers);
        workerService.saveStorageWorker(listStorageWorkers);

        log.info("Terminado cron reseteo diario");
    }

    private List<StoreWorkDay> getDailySectionWorkDay(List<StoreWorkDay> storeWorkDayList){
        return storeWorkDayList.stream()
            .map(storeWorkDay -> 
                    StoreWorkDay.builder()
                        .date(LocalDate.now())
                        .idStore(storeWorkDay.getIdStore())
                        .listSections(resetListSection(storeWorkDay.getListSections()))
                        .build()
                )
            .toList();
    }

    private List<SectionWorkDay> resetListSection(List<SectionWorkDay> listSectionWorkDay){
         return listSectionWorkDay.stream()
            .map(sectionWorkDay -> SectionWorkDay.builder()
                    .section(sectionWorkDay.getSection())
                    .assignedHours(0)  
                    .requiredHours(sectionWorkDay.getRequiredHours())
                    .build()
            )
            .toList();
    }
}
