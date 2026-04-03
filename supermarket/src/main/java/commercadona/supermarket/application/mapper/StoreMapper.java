package commercadona.supermarket.application.mapper;


import commercadona.supermarket.domain.model.Store;
import commercadona.supermarket.domain.model.StoreData;
import commercadona.supermarket.infraestructure.dtos.response.StoreResponseDTO;
import commercadona.supermarket.infraestructure.entity.StoreEntity;
import commercadona.supermarket.infraestructure.rest.contract.StoreDataRest;

public interface StoreMapper {
    Store mapStoreEntityToStore(StoreEntity storeEntity);
    StoreData mapStoreDataRestToStoreData(StoreDataRest storDataRest);
    StoreResponseDTO mapStoreToStoreDTO(Store store);
}
