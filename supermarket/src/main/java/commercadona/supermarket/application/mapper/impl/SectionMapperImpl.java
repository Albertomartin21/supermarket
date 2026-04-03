package commercadona.supermarket.application.mapper.impl;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.SectionMapper;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.infraestructure.entity.SectionEntity;

@Service
public class SectionMapperImpl implements SectionMapper{

    @Override
    public Section mapSectionEntityToSection(SectionEntity sectionEntity) {
        Section section = Section.valueOf(sectionEntity.getSection()); 
        section.setSkillsList(sectionEntity.getSkillsList());
        return section;
    }
    
}
