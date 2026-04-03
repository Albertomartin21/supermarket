package commercadona.supermarket.domain.repository;

import java.time.LocalDate;
import java.util.List;

import commercadona.supermarket.domain.model.StoreWorkDay;

public interface StoreWorkDayPort {
    StoreWorkDay getStoreWorkDayByIdStoreAndDate(Long idStore, LocalDate localDate);
    StoreWorkDay saveStoreWorkDay(StoreWorkDay storeWorkDay);
    List<StoreWorkDay> getAllStoreWorkDay();
    void saveAllStoreWorkDay(List<StoreWorkDay> listStoreWorkDays);
}
