package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngEbillingCheckDtl;
import gov.pcc.pwc.repository.EngEbillingCheckDtlRepository;
import gov.pcc.pwc.service.dto.EngEbillingCheckDtlDTO;
import gov.pcc.pwc.service.mapper.EngEbillingCheckDtlMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngEbillingCheckDtl}.
 */
@Service
@Transactional
public class EngEbillingCheckDtlService {

    private final Logger log = LoggerFactory.getLogger(EngEbillingCheckDtlService.class);

    private final EngEbillingCheckDtlRepository engEbillingCheckDtlRepository;

    private final EngEbillingCheckDtlMapper engEbillingCheckDtlMapper;

    public EngEbillingCheckDtlService(
        EngEbillingCheckDtlRepository engEbillingCheckDtlRepository,
        EngEbillingCheckDtlMapper engEbillingCheckDtlMapper
    ) {
        this.engEbillingCheckDtlRepository = engEbillingCheckDtlRepository;
        this.engEbillingCheckDtlMapper = engEbillingCheckDtlMapper;
    }

    /**
     * Save a engEbillingCheckDtl.
     *
     * @param engEbillingCheckDtlDTO the entity to save.
     * @return the persisted entity.
     */
    public EngEbillingCheckDtlDTO save(EngEbillingCheckDtlDTO engEbillingCheckDtlDTO) {
        log.debug("Request to save EngEbillingCheckDtl : {}", engEbillingCheckDtlDTO);
        EngEbillingCheckDtl engEbillingCheckDtl = engEbillingCheckDtlMapper.toEntity(engEbillingCheckDtlDTO);
        engEbillingCheckDtl = engEbillingCheckDtlRepository.save(engEbillingCheckDtl);
        return engEbillingCheckDtlMapper.toDto(engEbillingCheckDtl);
    }

    /**
     * Partially update a engEbillingCheckDtl.
     *
     * @param engEbillingCheckDtlDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngEbillingCheckDtlDTO> partialUpdate(EngEbillingCheckDtlDTO engEbillingCheckDtlDTO) {
        log.debug("Request to partially update EngEbillingCheckDtl : {}", engEbillingCheckDtlDTO);

        return engEbillingCheckDtlRepository
            .findById(engEbillingCheckDtlDTO.getEngEbillingCheckId())
            .map(existingEngEbillingCheckDtl -> {
                engEbillingCheckDtlMapper.partialUpdate(existingEngEbillingCheckDtl, engEbillingCheckDtlDTO);

                return existingEngEbillingCheckDtl;
            })
            .map(engEbillingCheckDtlRepository::save)
            .map(engEbillingCheckDtlMapper::toDto);
    }

    /**
     * Get all the engEbillingCheckDtls.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngEbillingCheckDtlDTO> findAll() {
        log.debug("Request to get all EngEbillingCheckDtls");
        return engEbillingCheckDtlRepository
            .findAll()
            .stream()
            .map(engEbillingCheckDtlMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engEbillingCheckDtl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngEbillingCheckDtlDTO> findOne(String id) {
        log.debug("Request to get EngEbillingCheckDtl : {}", id);
        return engEbillingCheckDtlRepository.findById(id).map(engEbillingCheckDtlMapper::toDto);
    }

    /**
     * Delete the engEbillingCheckDtl by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngEbillingCheckDtl : {}", id);
        engEbillingCheckDtlRepository.deleteById(id);
    }
}
