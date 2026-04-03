package commercadona.supermarket.application.mapper.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.StoreWorkDayMapper;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.domain.model.SectionWorkDay;
import commercadona.supermarket.domain.model.StoreWorkDay;
import commercadona.supermarket.infraestructure.entity.SectionEntity;
import commercadona.supermarket.infraestructure.entity.SectionWorkDayEntity;
import commercadona.supermarket.infraestructure.entity.StoreWorkDayEntity;

@Service
public class StoreWorkDayMapperImpl implements StoreWorkDayMapper{

    @Override
    public StoreWorkDay mapStoreWorkDayEntityToStoreWordDay(StoreWorkDayEntity storeWorkDayEntity) {
        return StoreWorkDay.builder()
            .id(storeWorkDayEntity.getId())
            .date(storeWorkDayEntity.getDate())
            .idStore(storeWorkDayEntity.getIdStore())
            .listSections(
                    Optional.ofNullable(storeWorkDayEntity.getListSections())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(this::mapSectionWorkDayEntityToSectionWorkDay)
                    .toList()
            )
            .build();
    }

     @Override
    public StoreWorkDayEntity mapStoreWorkDayToStoreWordDayEntity(StoreWorkDay storeWorkDay) {
        return StoreWorkDayEntity.builder()
            .id(storeWorkDay.getId())
            .date(storeWorkDay.getDate())
            .idStore(storeWorkDay.getIdStore())
            .listSections(
                    Optional.ofNullable(storeWorkDay.getListSections())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(this::mapSectionWorkDayToSectionWorkDayEntity)
                    .toList()
            )
            .build();
    }



    private SectionWorkDay mapSectionWorkDayEntityToSectionWorkDay(SectionWorkDayEntity sectionWorkDayEntity) {
        SectionWorkDay sectionWorkDay = SectionWorkDay.builder()
                .id(sectionWorkDayEntity.getId())
                .section(Section.valueOf(sectionWorkDayEntity.getSection().getSection()))
                .assignedHours(sectionWorkDayEntity.getAssignedHours())
                .requiredHours(sectionWorkDayEntity.getRequiredHours())
                .build();
        sectionWorkDay.getSection().setSkillsList(sectionWorkDayEntity.getSection().getSkillsList());
        return sectionWorkDay;
    }

      private SectionWorkDayEntity mapSectionWorkDayToSectionWorkDayEntity(SectionWorkDay sectionWorkDay) {
        SectionEntity sectionEntity = SectionEntity.builder()
                                        .section(sectionWorkDay.getSection().name())
                                        .skillsList(sectionWorkDay.getSection().getSkillsList())
                                        .build();
        return SectionWorkDayEntity.builder()
                .id(sectionWorkDay.getId())
                .section(sectionEntity)
                .assignedHours(sectionWorkDay.getAssignedHours())
                .requiredHours(sectionWorkDay.getRequiredHours())
                .build();
    }
    
}
