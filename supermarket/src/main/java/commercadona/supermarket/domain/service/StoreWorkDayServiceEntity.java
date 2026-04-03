package commercadona.supermarket.domain.service;

import java.time.LocalDate;
import java.util.List;

import commercadona.supermarket.domain.model.StoreWorkDay;

public interface StoreWorkDayServiceEntity {

   StoreWorkDay getStoreWorkDayByIdStoreAndDate(Long idStore, LocalDate date);
   StoreWorkDay saveStoreWorkDay (StoreWorkDay storeWorkDay);
   List<StoreWorkDay> getAllStoreWorkDay();
   void saveAllStoreWorkDay(List<StoreWorkDay> listStoreWorkDays);

}
