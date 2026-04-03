package commercadona.supermarket.domain.repository;

import java.util.List;

import commercadona.supermarket.domain.model.Section;

public interface SectionPort {
    List<Section> getSectionList();
}
