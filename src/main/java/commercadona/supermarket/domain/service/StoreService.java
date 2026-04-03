package commercadona.supermarket.domain.service;

import commercadona.supermarket.domain.model.Store;

public interface StoreService {
    Store getStoreData(Long id);
    Store getStoreById(Long id);
}
