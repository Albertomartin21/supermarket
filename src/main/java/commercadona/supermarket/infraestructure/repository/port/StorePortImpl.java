package commercadona.supermarket.infraestructure.repository.port;


import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.StoreMapper;
import commercadona.supermarket.domain.model.Store;
import commercadona.supermarket.domain.repository.StorePort;
import commercadona.supermarket.infraestructure.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorePortImpl implements StorePort {
    private StoreRepository storeRepository;
    private StoreMapper storeMapper;

    public StorePortImpl(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    public Store getStoreById(Long id) {
        return storeRepository.findById(id) 
            .map(store -> storeMapper.mapStoreEntityToStore(store))
            .orElse(null);
    }
    
}
