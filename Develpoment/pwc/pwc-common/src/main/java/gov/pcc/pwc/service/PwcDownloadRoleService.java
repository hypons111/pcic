package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.PwcDownloadRole;
import gov.pcc.pwc.repository.PwcDownloadRoleRepository;
import gov.pcc.pwc.service.dto.PwcDownloadRoleDTO;
import gov.pcc.pwc.service.mapper.PwcDownloadRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PwcDownloadRole}.
 */
@Service
@Transactional
public class PwcDownloadRoleService {

    private final Logger log = LoggerFactory.getLogger(PwcDownloadRoleService.class);

    private final PwcDownloadRoleRepository pwcDownloadRoleRepository;

    private final PwcDownloadRoleMapper pwcDownloadRoleMapper;

    public PwcDownloadRoleService(PwcDownloadRoleRepository pwcDownloadRoleRepository, PwcDownloadRoleMapper pwcDownloadRoleMapper) {
        this.pwcDownloadRoleRepository = pwcDownloadRoleRepository;
        this.pwcDownloadRoleMapper = pwcDownloadRoleMapper;
    }

    /**
     * Save a pwcDownloadRole.
     *
     * @param pwcDownloadRoleDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcDownloadRoleDTO save(PwcDownloadRoleDTO pwcDownloadRoleDTO) {
        log.debug("Request to save PwcDownloadRole : {}", pwcDownloadRoleDTO);
        PwcDownloadRole pwcDownloadRole = pwcDownloadRoleMapper.toEntity(pwcDownloadRoleDTO);
        pwcDownloadRole = pwcDownloadRoleRepository.save(pwcDownloadRole);
        return pwcDownloadRoleMapper.toDto(pwcDownloadRole);
    }

    /**
     * Partially update a pwcDownloadRole.
     *
     * @param pwcDownloadRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwcDownloadRoleDTO> partialUpdate(PwcDownloadRoleDTO pwcDownloadRoleDTO) {
        log.debug("Request to partially update PwcDownloadRole : {}", pwcDownloadRoleDTO);

        return pwcDownloadRoleRepository
            .findById(pwcDownloadRoleDTO.getProDownloadRoleId())
            .map(
                existingPwcDownloadRole -> {
                    pwcDownloadRoleMapper.partialUpdate(existingPwcDownloadRole, pwcDownloadRoleDTO);
                    return existingPwcDownloadRole;
                }
            )
            .map(pwcDownloadRoleRepository::save)
            .map(pwcDownloadRoleMapper::toDto);
    }

    /**
     * Get all the pwcDownloadRoles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcDownloadRoleDTO> findAll() {
        log.debug("Request to get all PwcDownloadRoles");
        return pwcDownloadRoleRepository
            .findAll()
            .stream()
            .map(pwcDownloadRoleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwcDownloadRole by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwcDownloadRoleDTO> findOne(Long id) {
        log.debug("Request to get PwcDownloadRole : {}", id);
        return pwcDownloadRoleRepository.findById(id).map(pwcDownloadRoleMapper::toDto);
    }

    /**
     * Delete the pwcDownloadRole by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwcDownloadRole : {}", id);
        pwcDownloadRoleRepository.deleteById(id);
    }
}
