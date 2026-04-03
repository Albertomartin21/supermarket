package commercadona.supermarket.domain.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.domain.repository.StoreWorkDayPort;
import commercadona.supermarket.domain.service.StoreWorkDayServiceEntity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreWorkDayServiceImpl implements StoreWorkDayServiceEntity{

    private final StoreWorkDayPort storeWorkDayPort;

    public StoreWorkDayServiceImpl(StoreWorkDayPort storeWorkDayPort){
        this.storeWorkDayPort = storeWorkDayPort;
    }

    @Override
    public StoreWorkDay getStoreWorkDayByIdStoreAndDate(Long idStore, LocalDate date) {
          return storeWorkDayPort.getStoreWorkDayByIdStoreAndDate(idStore, date);
    }

    @Override
    public StoreWorkDay saveStoreWorkDay(StoreWorkDay storeWorkDay) {
        return storeWorkDayPort.saveStoreWorkDay(storeWorkDay);
    }

    @Override
    public List<StoreWorkDay> getAllStoreWorkDay() {
         return storeWorkDayPort.getAllStoreWorkDay();
    }

    @Override
    public void saveAllStoreWorkDay(List<StoreWorkDay> listStoreWorkDays) {
        storeWorkDayPort.saveAllStoreWorkDay(listStoreWorkDays);
    }
    
}
