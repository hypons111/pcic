package gov.pcc.pwc.service;

import gov.pcc.pwc.common.enums.eng.EngDocNoTypeEnum;
import gov.pcc.pwc.domain.EngDocNo;
import gov.pcc.pwc.domain.EngDocNoPK;
import gov.pcc.pwc.repository.EngDocNoRepository;
import gov.pcc.pwc.service.dto.EngDocNoDTO;
import gov.pcc.pwc.service.mapper.EngDocNoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngDocNo}.
 */
@Service
@Transactional
public class EngDocNoService {

    private final Logger log = LoggerFactory.getLogger(EngDocNoService.class);

    private final EngDocNoRepository engDocNoRepository;

    private final EngDocNoMapper engDocNoMapper;

    public EngDocNoService(EngDocNoRepository engDocNoRepository, EngDocNoMapper engDocNoMapper) {
        this.engDocNoRepository = engDocNoRepository;
        this.engDocNoMapper = engDocNoMapper;
    }

    /**
     * Save a engDocNo.
     *
     * @param engDocNoDTO the entity to save.
     * @return the persisted entity.
     */
    public EngDocNoDTO save(EngDocNoDTO engDocNoDTO) {
        log.debug("Request to save EngDocNo : {}", engDocNoDTO);
        EngDocNo engDocNo = engDocNoMapper.toEntity(engDocNoDTO);
        engDocNo = engDocNoRepository.save(engDocNo);
        return engDocNoMapper.toDto(engDocNo);
    }

    /**
     * Get all the engDocNos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngDocNoDTO> findAll() {
        log.debug("Request to get all EngDocNos");
        return engDocNoRepository.findAll().stream().map(engDocNoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engDocNo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngDocNoDTO> findOne(EngDocNoPK id) {
        log.debug("Request to get EngDocNo : {}", id);
        return engDocNoRepository.findById(id).map(engDocNoMapper::toDto);
    }

    /**
     * Delete the engDocNo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(EngDocNoPK id) {
        log.debug("Request to delete EngDocNo : {}", id);
        engDocNoRepository.deleteById(id);
    }

    public String getDocNo(String engDocNoType) {
        log.debug("Request to get docNo : {}", engDocNoType);

        List<EngDocNo> list = engDocNoRepository.findByEngDocNoTypeOrderByEngDocNoNo(engDocNoType);
        if(!list.isEmpty()) {
            engDocNoRepository.delete(list.get(0));
            return list.get(0).getEngDocNoNo();
        }else {
            return null;
        }
    }
}
