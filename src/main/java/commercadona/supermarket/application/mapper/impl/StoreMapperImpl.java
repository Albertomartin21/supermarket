package commercadona.supermarket.application.mapper.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.StoreMapper;
import commercadona.supermarket.application.mapper.WorkerMapper;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.domain.model.Store;
import commercadona.supermarket.domain.model.StoreData;
import commercadona.supermarket.infraestructure.dtos.response.SectionResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.StoreResponseDTO;
import commercadona.supermarket.infraestructure.entity.StoreEntity;
import commercadona.supermarket.infraestructure.rest.contract.StoreDataRest;

@Service
public class StoreMapperImpl implements StoreMapper {

    private final WorkerMapper workerMapper;

    public StoreMapperImpl(WorkerMapper workerMapper){
        this.workerMapper = workerMapper;
    }

    @Override
    public Store mapStoreEntityToStore(StoreEntity storeEntity) {
        return Store.builder()
            .id(storeEntity.getId())
            .name(storeEntity.getName())
            .listWorkers(Optional.ofNullable(storeEntity.getListWorkers())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(workerMapper::mapWorkerEntityToWorker)
                        .toList())
            .build();
    }

    @Override
    public StoreData mapStoreDataRestToStoreData(StoreDataRest storeDataRest) {
        return StoreData.builder()
            .id(storeDataRest.getId())
            .city(storeDataRest.getCity())
            .description(storeDataRest.getDescription())
            .address(storeDataRest.getAddress())
            .build();
    }

    @Override
    public StoreResponseDTO mapStoreToStoreDTO(Store store) {
        return StoreResponseDTO.builder()
            .id(store.getId())
            .city(store.getCity())
            .address(store.getAddress())
            .name(store.getName())
            .sectionList(mapSectionToSectionDTO(store.getListSection()))
            .build();
    }

    private List<SectionResponseDTO> mapSectionToSectionDTO(List<Section> sectionList) {
    return sectionList.stream()
                  .map(section -> SectionResponseDTO.builder()
                                            .section(section.name())
                                            .skills(section.getSkillsList())
                                            .build())
                  .toList();
     }
    
}
