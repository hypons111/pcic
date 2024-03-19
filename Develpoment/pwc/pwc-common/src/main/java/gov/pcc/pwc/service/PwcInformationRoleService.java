package gov.pcc.pwc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.pcc.pwc.domain.PwcInformationRole;
import gov.pcc.pwc.repository.PwcInformationRoleRepository;
import gov.pcc.pwc.service.dto.PwcInformationRoleDTO;
import gov.pcc.pwc.service.mapper.PwcInformationRoleMapper;

/**
 * Service Implementation for managing {@link PwcInformationRole}.
 */
@Service
@Transactional
public class PwcInformationRoleService {

    private final Logger log = LoggerFactory.getLogger(PwcInformationRoleService.class);

    private final PwcInformationRoleRepository pwcInformationRoleRepository;

    private final PwcInformationRoleMapper pwcInformationRoleMapper;

    public PwcInformationRoleService(
        PwcInformationRoleRepository pwcInformationRoleRepository,
        PwcInformationRoleMapper pwcInformationRoleMapper
    ) {
        this.pwcInformationRoleRepository = pwcInformationRoleRepository;
        this.pwcInformationRoleMapper = pwcInformationRoleMapper;
    }

    /**
     * Save a pwcInformationRole.
     *
     * @param pwcInformationRoleDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcInformationRoleDTO save(PwcInformationRoleDTO pwcInformationRoleDTO) {
        log.debug("Request to save PwcInformationRole : {}", pwcInformationRoleDTO);
        PwcInformationRole pwcInformationRole = pwcInformationRoleMapper.toEntity(pwcInformationRoleDTO);
        pwcInformationRole = pwcInformationRoleRepository.save(pwcInformationRole);
        return pwcInformationRoleMapper.toDto(pwcInformationRole);
    }

    /**
     * Partially update a pwcInformationRole.
     *
     * @param pwcInformationRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwcInformationRoleDTO> partialUpdate(PwcInformationRoleDTO pwcInformationRoleDTO) {
        log.debug("Request to partially update PwcInformationRole : {}", pwcInformationRoleDTO);

        return pwcInformationRoleRepository
            .findById(pwcInformationRoleDTO.getProInformationRoleId())
            .map(
                existingPwcInformationRole -> {
                    pwcInformationRoleMapper.partialUpdate(existingPwcInformationRole, pwcInformationRoleDTO);
                    return existingPwcInformationRole;
                }
            )
            .map(pwcInformationRoleRepository::save)
            .map(pwcInformationRoleMapper::toDto);
    }

    /**
     * Get all the pwcInformationRoles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcInformationRoleDTO> findAll() {
        log.debug("Request to get all PwcInformationRoles");
        return pwcInformationRoleRepository
            .findAll()
            .stream()
            .map(pwcInformationRoleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwcInformationRole by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwcInformationRoleDTO> findOne(Long id) {
        log.debug("Request to get PwcInformationRole : {}", id);
        return pwcInformationRoleRepository.findById(id).map(pwcInformationRoleMapper::toDto);
    }

    /**
     * Delete the pwcInformationRole by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwcInformationRole : {}", id);
        pwcInformationRoleRepository.deleteById(id);
    }
}
