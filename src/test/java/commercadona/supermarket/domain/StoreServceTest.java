package commercadona.supermarket.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import commercadona.supermarket.domain.exception.StoreNotFoundErrorException;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.domain.model.Store;
import commercadona.supermarket.domain.model.StoreData;
import commercadona.supermarket.domain.repository.SectionPort;
import commercadona.supermarket.domain.repository.StorePort;
import commercadona.supermarket.domain.rest.StorePortRest;
import commercadona.supermarket.domain.service.StoreService;
import commercadona.supermarket.domain.service.impl.StoreServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StoreServceTest {
    
    private StoreService storeService;

    @Mock 
    private StorePortRest storePortRest;

    @Mock 
    private SectionPort sectionPort;

    @Mock 
    private StorePort storePort;
    
    @BeforeEach
    void setUp(){
        storeService = new StoreServiceImpl(storePortRest, sectionPort, storePort);
    }

    @Test
    void shouldGetStoreData(){
        Long storeId = 1L;
        List<Section> sectionList = List.of(Section.HORNO, Section.CAJAS);
        Store storeMock = Store.builder().id(storeId).name("store").build();
        StoreData storeDataMock = new StoreData();
        storeDataMock.setAddress("Calle Falsa 123");
        storeDataMock.setCity("Madrid");

        when(sectionPort.getSectionList()).thenReturn(sectionList);
        when(storePort.getStoreById(storeId)).thenReturn(storeMock);
        when(storePortRest.geStoreDataById(storeId)).thenReturn(storeDataMock);
        
        var storeResult = storeService.getStoreData(storeId);

        assertNotNull(storeResult);
        assertEquals(storeId, storeResult.getId());
        assertEquals("store", storeResult.getName());
        assertEquals("Calle Falsa 123", storeResult.getAddress());
        assertEquals("Madrid", storeResult.getCity());
        assertEquals(sectionList, storeResult.getListSection());

        verify(sectionPort, times(1)).getSectionList();
        verify(storePort, times(1)).getStoreById(storeId);
        verify(storePortRest, times(1)).geStoreDataById(storeId);
    }

    @Test
    void testGetStoreData_StoreNotFound() {
        Long storeId = 2L;

        assertThrows(StoreNotFoundErrorException.class,
            () -> storeService.getStoreData(storeId));
    }

    @Test
    void testGetStoreData_StoreDataNotFound() {
        Long storeId = 3L;

        when(storePortRest.geStoreDataById(storeId)).thenReturn(null);

        assertThrows(StoreNotFoundErrorException.class,
            () -> storeService.getStoreData(storeId));
    }
}
