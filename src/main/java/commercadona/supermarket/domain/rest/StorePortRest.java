package commercadona.supermarket.domain.rest;

import commercadona.supermarket.domain.model.StoreData;

public interface StorePortRest {
    StoreData geStoreDataById(Long idStore);
}
