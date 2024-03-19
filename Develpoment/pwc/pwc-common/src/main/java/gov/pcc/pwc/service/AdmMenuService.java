package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmMenu;
import gov.pcc.pwc.repository.AdmMenuRepository;
import gov.pcc.pwc.service.dto.AdmMenuDTO;
import gov.pcc.pwc.service.mapper.AdmMenuMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AdmMenu}.
 */
@Service
@Transactional
public class AdmMenuService {

    private final Logger log = LoggerFactory.getLogger(AdmMenuService.class);

    private final AdmMenuRepository admMenuRepository;

    private final AdmMenuMapper admMenuMapper;

    public AdmMenuService(AdmMenuRepository admMenuRepository, AdmMenuMapper admMenuMapper) {
        this.admMenuRepository = admMenuRepository;
        this.admMenuMapper = admMenuMapper;
    }

    @Transactional(readOnly = true)
    public List<AdmMenuDTO> getMenu(Boolean openMenu) {
        log.debug("Request to get menu");
        return makeMenuTree(admMenuRepository
                .findAll()
                .stream()
                .filter(item -> item.getOpenMenu() == openMenu)
                .map(admMenuMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new))
                , null);
    }

    private List<AdmMenuDTO> makeMenuTree(List<AdmMenuDTO> admMenuDTOS, Long id) {
        final List<AdmMenuDTO> children = admMenuDTOS.stream()
                .filter(item -> Objects.equals(item.getParentId(), id))
                .collect(Collectors.toList());
        final List<AdmMenuDTO> successor = admMenuDTOS.stream()
                .filter(item -> !Objects.equals(item.getParentId(), id))
                .collect(Collectors.toList());
        children.forEach(linkedItem ->
                makeMenuTree(successor, linkedItem.getId()).forEach(
                        childrenItem -> linkedItem.getChildren().add(childrenItem)));

        return children;
    }
}
