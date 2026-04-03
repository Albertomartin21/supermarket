package commercadona.supermarket.application.usecases.impl;

import org.springframework.stereotype.Service;
import commercadona.supermarket.application.mapper.StoreMapper;
import commercadona.supermarket.application.usecases.StoreUseCase;
import commercadona.supermarket.domain.service.StoreService;
import commercadona.supermarket.infraestructure.dtos.response.StoreResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreUseCaseImpl implements StoreUseCase{
    
    private final StoreService storeService;
    private final StoreMapper storeMapper;



    public StoreUseCaseImpl(StoreService storeService, StoreMapper storeMapper){
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }



    @Override
    public StoreResponseDTO getStoreData(Long idStore) {
        return storeMapper.mapStoreToStoreDTO(storeService.getStoreData(idStore));
    }
}
