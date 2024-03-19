package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidOversee;
import gov.pcc.pwc.repository.BidOverseeRepository;
import gov.pcc.pwc.service.criteria.BidOverseeCriteria;
import gov.pcc.pwc.service.dto.BidOverseeDTO;
import gov.pcc.pwc.service.mapper.BidOverseeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidOversee}.
 */
@Service
@Transactional
public class BidOverseeService {

    private final Logger log = LoggerFactory.getLogger(BidOverseeService.class);

    private final BidOverseeRepository bidOverseeRepository;

    private final BidOverseeMapper bidOverseeMapper;

    public BidOverseeService(BidOverseeRepository bidOverseeRepository, BidOverseeMapper bidOverseeMapper) {
        this.bidOverseeRepository = bidOverseeRepository;
        this.bidOverseeMapper = bidOverseeMapper;
    }

    /**
     * Save a bidOversee.
     *
     * @param bidOverseeDTO the entity to save.
     * @return the persisted entity.
     */
    public BidOverseeDTO save(BidOverseeDTO bidOverseeDTO) {
        log.debug("Request to save BidOversee : {}", bidOverseeDTO);
        BidOversee bidOversee = bidOverseeMapper.toEntity(bidOverseeDTO);
        bidOversee = bidOverseeRepository.save(bidOversee);
        return bidOverseeMapper.toDto(bidOversee);
    }

    /**
     * Partially update a bidOversee.
     *
     * @param bidOverseeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidOverseeDTO> partialUpdate(BidOverseeDTO bidOverseeDTO) {
        log.debug("Request to partially update BidOversee : {}", bidOverseeDTO);

        return bidOverseeRepository
            .findById(bidOverseeDTO.getSeqNo())
            .map(existingBidOversee -> {
                bidOverseeMapper.partialUpdate(existingBidOversee, bidOverseeDTO);

                return existingBidOversee;
            })
            .map(bidOverseeRepository::save)
            .map(bidOverseeMapper::toDto);
    }

    /**
     * Get all the bidOversees.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidOverseeDTO> findAll() {
        log.debug("Request to get all BidOversees");
        return bidOverseeRepository.findAll().stream().map(bidOverseeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidOversee by id.
     *
     * @param seqNo the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidOverseeDTO> findOne(String seqNo) {
        log.debug("Request to get BidOversee : {}", seqNo);
        return bidOverseeRepository.findById(seqNo).map(bidOverseeMapper::toDto);
    }

    /**
     * Delete the bidOversee by id.
     *
     * @param seqNo the id of the entity.
     */
    public void delete(String seqNo) {
        log.debug("Request to delete BidOversee : {}", seqNo);
        bidOverseeRepository.deleteById(seqNo);
    }

    @Transactional(readOnly = true)
    public List<BidOverseeDTO> findListByCriteria(BidOverseeCriteria criteria){
        criteria.setSortBy(new String[]{"seqDate"});
        criteria.setSortDirection(Sort.Direction.DESC);
        return  bidOverseeRepository.findAll(criteria,criteria.toPageable().getSort()).stream().map(bidOverseeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
