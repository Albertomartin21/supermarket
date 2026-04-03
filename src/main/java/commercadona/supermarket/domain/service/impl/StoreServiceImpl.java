package commercadona.supermarket.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import commercadona.supermarket.domain.exception.StoreNotFoundErrorException;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.domain.model.Store;
import commercadona.supermarket.domain.model.StoreData;
import commercadona.supermarket.domain.repository.SectionPort;
import commercadona.supermarket.domain.repository.StorePort;
import commercadona.supermarket.domain.rest.StorePortRest;
import commercadona.supermarket.domain.service.StoreService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreServiceImpl implements StoreService{
    private final StorePortRest storePortRest;
    private final SectionPort sectionPort;
    private final StorePort storePort;


    public StoreServiceImpl(StorePortRest storePortRest, SectionPort sectionPort, StorePort storePort){
        this.storePortRest = storePortRest;
        this.sectionPort = sectionPort;
        this.storePort = storePort;
    }

    @Override
    public Store getStoreData(Long storeId) {
        List<Section> section = sectionPort.getSectionList();
        StoreData storeData = getStoreDataById(storeId);
        Store store = getStoreById(storeId);
        
        return Store.builder()
                    .id(storeId)
                    .name(store.getName())
                    .address(storeData.getAddress())
                    .city(storeData.getCity())
                    .listSection(section)
                    .build();
    }

    @Override
    public Store getStoreById(Long storeId) {
        return Optional.ofNullable(storePort.getStoreById(storeId))
             .orElseThrow(() -> new StoreNotFoundErrorException(String.format("Error al buscar, no se ha encontrado la tienda: [%s]", storeId)));
    }
  
    private StoreData getStoreDataById(Long storeId) {
        return Optional.ofNullable(storePortRest.geStoreDataById(storeId))
             .orElseThrow(() -> new StoreNotFoundErrorException(String.format("Error al buscar, no se ha encontrado los datos de la tienda: [%s]", storeId)));
    }
}
