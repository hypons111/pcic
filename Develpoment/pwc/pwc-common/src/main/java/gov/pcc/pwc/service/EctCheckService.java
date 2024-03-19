package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EctCheck;
import gov.pcc.pwc.repository.EctCheckRepository;
import gov.pcc.pwc.service.dto.EctCheckDTO;
import gov.pcc.pwc.service.mapper.EctCheckMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Service Implementation for managing {@link EctCheck}.
 */
@Service
@Transactional
public class EctCheckService {

    private final Logger log = LoggerFactory.getLogger(EctCheckService.class);

    private final EctCheckRepository ectCheckRepository;

    private final EctCheckMapper ectCheckMapper;

    public EctCheckService(EctCheckRepository ectCheckRepository, EctCheckMapper ectCheckMapper) {
        this.ectCheckRepository = ectCheckRepository;
        this.ectCheckMapper = ectCheckMapper;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> uploadCertPdf(MultipartHttpServletRequest request) {
        final Map<String, Object> rs = ectCheckRepository.uploadCertPdf(request);
        log.debug("service-uploadCertPdf" + rs.toString());
        return rs;
    }

    /**
     * Save a ectCheck.
     *
     * @param ectCheckDTO the entity to save.
     * @return the persisted entity.
     */
    public EctCheckDTO save(EctCheckDTO ectCheckDTO) {
        log.debug("Request to save EctCheck : {}", ectCheckDTO);
        EctCheck ectCheck = ectCheckMapper.toEntity(ectCheckDTO);
        ectCheck = ectCheckRepository.save(ectCheck);
        return ectCheckMapper.toDto(ectCheck);
    }

    /**
     * Partially update a ectCheck.
     *
     * @param ectCheckDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EctCheckDTO> partialUpdate(EctCheckDTO ectCheckDTO) {
        log.debug("Request to partially update EctCheck : {}", ectCheckDTO);

        return ectCheckRepository
            .findById(ectCheckDTO.getEctCheckId())
            .map(existingEctCheck -> {
                ectCheckMapper.partialUpdate(existingEctCheck, ectCheckDTO);

                return existingEctCheck;
            })
            .map(ectCheckRepository::save)
            .map(ectCheckMapper::toDto);
    }

    /**
     * Get all the ectChecks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EctCheckDTO> findAll() {
        log.debug("Request to get all EctChecks");
        return ectCheckRepository.findAll().stream().map(ectCheckMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ectCheck by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EctCheckDTO> findOne(Long id) {
        log.debug("Request to get EctCheck : {}", id);
        return ectCheckRepository.findById(id).map(ectCheckMapper::toDto);
    }

    /**
     * Delete the ectCheck by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EctCheck : {}", id);
        ectCheckRepository.deleteById(id);
    }
}
