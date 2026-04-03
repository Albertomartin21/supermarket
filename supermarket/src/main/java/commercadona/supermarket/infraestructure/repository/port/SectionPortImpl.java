package commercadona.supermarket.infraestructure.repository.port;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.SectionMapper;
import commercadona.supermarket.domain.model.Section;
import commercadona.supermarket.domain.repository.SectionPort;
import commercadona.supermarket.infraestructure.repository.SectionRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SectionPortImpl implements SectionPort {
    private SectionRepository sectionRepository;
    private SectionMapper sectionMapper;

    public SectionPortImpl(SectionRepository sectionRepository, SectionMapper sectionMapper) {
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
    }

    @Override
    public List<Section> getSectionList() {
        return sectionRepository.findAll().stream()
            .map(section -> sectionMapper.mapSectionEntityToSection(section))
            .collect(Collectors.toList());
    }
}
