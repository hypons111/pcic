package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.PwbUnitRole;
import gov.pcc.pwc.repository.PwbUnitRoleRepository;
import gov.pcc.pwc.service.dto.AdmRoleDTO;
import gov.pcc.pwc.service.dto.PwbUnitRoleDTO;
import gov.pcc.pwc.service.mapper.PwbUnitRoleMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PwbUnitRole}.
 */
@Service
@Transactional
public class PwbUnitRoleService {

    private final Logger log = LoggerFactory.getLogger(PwbUnitRoleService.class);

    private final PwbUnitRoleRepository pwbUnitRoleRepository;

    private final PwbUnitRoleMapper pwbUnitRoleMapper;

    public PwbUnitRoleService(PwbUnitRoleRepository pwbUnitRoleRepository, PwbUnitRoleMapper pwbUnitRoleMapper) {
        this.pwbUnitRoleRepository = pwbUnitRoleRepository;
        this.pwbUnitRoleMapper = pwbUnitRoleMapper;
    }

    /**
     * Save a pwbUnitRole.
     *
     * @param pwbUnitRoleDTO the entity to save.
     * @return the persisted entity.
     */
    public PwbUnitRoleDTO save(PwbUnitRoleDTO pwbUnitRoleDTO) {
        log.debug("Request to save PwbUnitRole : {}", pwbUnitRoleDTO);
        PwbUnitRole pwbUnitRole = pwbUnitRoleMapper.toEntity(pwbUnitRoleDTO);
        pwbUnitRole = pwbUnitRoleRepository.save(pwbUnitRole);
        return pwbUnitRoleMapper.toDto(pwbUnitRole);
    }

    /**
     * Partially update a pwbUnitRole.
     *
     * @param pwbUnitRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwbUnitRoleDTO> partialUpdate(PwbUnitRoleDTO pwbUnitRoleDTO) {
        log.debug("Request to partially update PwbUnitRole : {}", pwbUnitRoleDTO);

        return pwbUnitRoleRepository
            .findById(pwbUnitRoleDTO.getId())
            .map(existingPwbUnitRole -> {
                pwbUnitRoleMapper.partialUpdate(existingPwbUnitRole, pwbUnitRoleDTO);

                return existingPwbUnitRole;
            })
            .map(pwbUnitRoleRepository::save)
            .map(pwbUnitRoleMapper::toDto);
    }

    /**
     * Get all the pwbUnitRoles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwbUnitRoleDTO> findAll() {
        log.debug("Request to get all PwbUnitRoles");
        return pwbUnitRoleRepository.findAll().stream().map(pwbUnitRoleMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwbUnitRole by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwbUnitRoleDTO> findOne(Long id) {
        log.debug("Request to get PwbUnitRole : {}", id);
        return pwbUnitRoleRepository.findById(id).map(pwbUnitRoleMapper::toDto);
    }

    /**
     * Delete the pwbUnitRole by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwbUnitRole : {}", id);
        pwbUnitRoleRepository.deleteById(id);
    }

    /**
     * Find all role for front-end roleOpts
     *
     * @return the list of PwbUnitRoleDTO.
     */

    @Transactional(readOnly = true)
    public List<AdmRoleDTO> findAllRoleOpts(String unitId) {
        return pwbUnitRoleRepository.findAllRoleOpts(unitId);
    }
}
