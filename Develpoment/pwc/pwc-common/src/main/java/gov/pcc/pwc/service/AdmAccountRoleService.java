package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmAccountRole;
import gov.pcc.pwc.repository.AdmAccountRoleRepository;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmAccountRoleDTO;
import gov.pcc.pwc.service.mapper.AdmAccountRoleMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AdmAccountRole}.
 */
@Service
@Transactional
public class AdmAccountRoleService {

    private final Logger log = LoggerFactory.getLogger(AdmAccountRoleService.class);

    private final AdmAccountRoleRepository admAccountRoleRepository;

    private final AdmAccountRoleMapper admAccountRoleMapper;

    public AdmAccountRoleService(AdmAccountRoleRepository admAccountRoleRepository, AdmAccountRoleMapper admAccountRoleMapper) {
        this.admAccountRoleRepository = admAccountRoleRepository;
        this.admAccountRoleMapper = admAccountRoleMapper;
    }

    /**
     * Save a admAccountRole.
     *
     * @param admAccountRoleDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmAccountRoleDTO save(AdmAccountRoleDTO admAccountRoleDTO) {
        log.debug("Request to save AdmAccountRole : {}", admAccountRoleDTO);
        AdmAccountRole admAccountRole = admAccountRoleMapper.toEntity(admAccountRoleDTO);
        admAccountRole = admAccountRoleRepository.save(admAccountRole);
        return admAccountRoleMapper.toDto(admAccountRole);
    }

    /**
     * Partially update a admAccountRole.
     *
     * @param admAccountRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AdmAccountRoleDTO> partialUpdate(AdmAccountRoleDTO admAccountRoleDTO) {
        log.debug("Request to partially update AdmAccountRole : {}", admAccountRoleDTO);

        return admAccountRoleRepository
            .findById(admAccountRoleDTO.getId())
            .map(existingAdmAccountRole -> {
                admAccountRoleMapper.partialUpdate(existingAdmAccountRole, admAccountRoleDTO);

                return existingAdmAccountRole;
            })
            .map(admAccountRoleRepository::save)
            .map(admAccountRoleMapper::toDto);
    }

    /**
     * Get all the admAccountRoles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmAccountRoleDTO> findAll() {
        log.debug("Request to get all AdmAccountRoles");
        return admAccountRoleRepository
            .findAll()
            .stream()
            .map(admAccountRoleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one admAccountRole by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdmAccountRoleDTO> findOne(Long id) {
        log.debug("Request to get AdmAccountRole : {}", id);
        return admAccountRoleRepository.findById(id).map(admAccountRoleMapper::toDto);
    }

    /**
     * Delete the admAccountRole by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdmAccountRole : {}", id);
        admAccountRoleRepository.deleteById(id);
    }

    /**
     * Get few admAccountContactDTO
     *
     * @param admAccountContactDTO the id of the entity.
     * @return the userIds.
     */
    @Transactional(readOnly = true)
    public List<String> findAccountRoleId(AdmAccountContactDTO admAccountContactDTO) {
        log.debug("Request to get AdmAccountRole : {}", admAccountContactDTO.getUserId());
        return admAccountRoleRepository.findAccountRoleId(admAccountContactDTO);
    }
}
