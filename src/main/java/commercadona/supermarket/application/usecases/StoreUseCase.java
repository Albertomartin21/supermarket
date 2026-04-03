package commercadona.supermarket.application.usecases;

import commercadona.supermarket.infraestructure.dtos.response.StoreResponseDTO;

public interface StoreUseCase {
    StoreResponseDTO getStoreData(Long idStore);
}
