package commercadona.supermarket.application.mapper;

import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.infraestructure.entity.StoreWorkDayEntity;

public interface StoreWorkDayMapper {
    StoreWorkDay mapStoreWorkDayEntityToStoreWordDay(StoreWorkDayEntity storeWorkDayEntity);
    StoreWorkDayEntity mapStoreWorkDayToStoreWordDayEntity(StoreWorkDay storeWorkDayEntity);
}
