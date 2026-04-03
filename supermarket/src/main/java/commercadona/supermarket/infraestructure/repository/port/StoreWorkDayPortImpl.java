package commercadona.supermarket.infraestructure.repository.port;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import commercadona.supermarket.application.mapper.StoreWorkDayMapper;
import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.domain.repository.StoreWorkDayPort;
import commercadona.supermarket.infraestructure.repository.StoreWorkDayRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreWorkDayPortImpl implements StoreWorkDayPort {
    private StoreWorkDayRepository storeWorkDayRepository;
    private StoreWorkDayMapper storeWorkDayMapper;

    public StoreWorkDayPortImpl(StoreWorkDayRepository storeWorkDayRepository, StoreWorkDayMapper storeWorkDayMapper) {
        this.storeWorkDayRepository = storeWorkDayRepository;
        this.storeWorkDayMapper = storeWorkDayMapper;
    }

    @Override
    public StoreWorkDay getStoreWorkDayByIdStoreAndDate(Long idStore, LocalDate localDate) {
         return storeWorkDayRepository.findByIdStoreAndDate(idStore, localDate)
            .map(store -> storeWorkDayMapper.mapStoreWorkDayEntityToStoreWordDay(store))
            .orElse(null);
    }

    @Override
    public StoreWorkDay saveStoreWorkDay(StoreWorkDay storeWorkDay) {
         return Optional.of(storeWorkDay)
            .map(storeDomain -> storeWorkDayMapper.mapStoreWorkDayToStoreWordDayEntity(storeDomain))
            .map(storeEntity -> storeWorkDayRepository.save(storeEntity))
            .map(storeEntity -> storeWorkDayMapper.mapStoreWorkDayEntityToStoreWordDay(storeEntity)) 
            .orElse(null);
    }

    @Override
    public List<StoreWorkDay> getAllStoreWorkDay() {
        return storeWorkDayRepository.getAllList().stream()
            .map(store -> storeWorkDayMapper.mapStoreWorkDayEntityToStoreWordDay(store))
            .collect(Collectors.toList());
    }

    @Override
    public void saveAllStoreWorkDay(List<StoreWorkDay> listStoreWorkDays) {
          var listEntities = listStoreWorkDays.stream()
                .map(storeWorkDays -> storeWorkDayMapper.mapStoreWorkDayToStoreWordDayEntity(storeWorkDays))
                .toList();
          storeWorkDayRepository.saveAll(listEntities);
    }
}
