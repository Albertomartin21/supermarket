package commercadona.supermarket.domain.repository;

import commercadona.supermarket.domain.model.Store;

public interface StorePort {
    Store getStoreById(Long id);
}
