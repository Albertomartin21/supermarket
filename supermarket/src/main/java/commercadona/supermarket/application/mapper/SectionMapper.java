package commercadona.supermarket.application.mapper;

import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.infraestructure.entity.SectionEntity;

public interface SectionMapper {
    Section mapSectionEntityToSection(SectionEntity sectionEntity);
}
