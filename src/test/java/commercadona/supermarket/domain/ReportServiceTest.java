package commercadona.supermarket.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.domain.model.Store;
import commercadona.supermarket.domain.model.StoreData;
import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.domain.rest.StorePortRest;
import commercadona.supermarket.domain.service.ReportService;
import commercadona.supermarket.domain.service.StoreService;
import commercadona.supermarket.domain.service.StoreWorkDayServiceEntity;
import commercadona.supermarket.domain.service.impl.ReportServiceImp;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    private ReportService reportService;
    
    @Mock 
    private  StoreService storeService;

    @Mock 
    private StoreWorkDayServiceEntity storeWorkDayService;

    @Mock 
    private StorePortRest storePortRest;

    
    @BeforeEach
    void setUp(){
        reportService = new ReportServiceImp(storeService, storeWorkDayService, storePortRest);
    }
    @Test
    void shouldGetStoreReport(){
        Long idStore = 1L;
        Store store = generateStore(idStore);
        StoreData storeData = StoreData.builder().address("address").city("city").build();
 
        when(storeService.getStoreById(idStore)).thenReturn(store);
        when(storePortRest.geStoreDataById(idStore)).thenReturn(storeData);
      
        var report = reportService.getStoreReport(idStore);

        // Asserts
        assertNotNull(report);
        verify(storeService).getStoreById(idStore);
        verify(storePortRest).geStoreDataById(idStore);
    }

    @Test
    void shouldGetStoreReportRemaining(){
        Long idStore = 1L;
        Store store = generateStore(idStore);
        StoreData storeData = StoreData.builder().address("address").city("city").build();
        StoreWorkDay storeWorkDayApi = StoreWorkDay.builder().build();

        when(storeWorkDayService.getStoreWorkDayByIdStoreAndDate(idStore, LocalDate.now()))
                    .thenReturn(storeWorkDayApi);
        when(storeService.getStoreById(idStore)).thenReturn(store);
        when(storePortRest.geStoreDataById(idStore)).thenReturn(storeData);
      
        var report = reportService.getStoreReportRemaining(idStore);

        // Asserts
        assertNotNull(report);
        verify(storeService).getStoreById(idStore);
        verify(storeService).getStoreById(idStore);
        verify(storePortRest).geStoreDataById(idStore);
        verify(storeWorkDayService).getStoreWorkDayByIdStoreAndDate(idStore, LocalDate.now());
    }

    private Store generateStore(Long idStore){
        List<Section> sectionList = new ArrayList<>();
        return Store.builder()
                .name("store")
                .id(idStore)
                .listSection(sectionList)
                .build();
    }
}
