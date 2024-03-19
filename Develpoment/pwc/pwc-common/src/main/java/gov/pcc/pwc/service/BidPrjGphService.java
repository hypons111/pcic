package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjGph;
import gov.pcc.pwc.domain.BidPrjGphPrimaryKey;
import gov.pcc.pwc.repository.BidPrjGphRepository;
import gov.pcc.pwc.service.criteria.BidPrjGphCriteria;
import gov.pcc.pwc.service.dto.BidPrjGphDTO;
import gov.pcc.pwc.service.mapper.BidPrjGphMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjGph}.
 */
@Service
@Transactional
public class BidPrjGphService {

    private final Logger log = LoggerFactory.getLogger(BidPrjGphService.class);

    private final BidPrjGphRepository bidPrjGphRepository;

    private final BidPrjGphMapper bidPrjGphMapper;

    public BidPrjGphService(BidPrjGphRepository bidPrjGphRepository, BidPrjGphMapper bidPrjGphMapper) {
        this.bidPrjGphRepository = bidPrjGphRepository;
        this.bidPrjGphMapper = bidPrjGphMapper;
    }

    /**
     * Save a bidPrjGph.
     *
     * @param bidPrjGphDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjGphDTO save(BidPrjGphDTO bidPrjGphDTO) {
        log.debug("Request to save BidPrjGph : {}", bidPrjGphDTO);
        BidPrjGph bidPrjGph = bidPrjGphMapper.toEntity(bidPrjGphDTO);
        bidPrjGph = bidPrjGphRepository.save(bidPrjGph);
        return bidPrjGphMapper.toDto(bidPrjGph);
    }

    /**
     * Partially update a bidPrjGph.
     *
     * @param bidPrjGphDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjGphDTO> partialUpdate(BidPrjGphDTO bidPrjGphDTO) {
        log.debug("Request to partially update BidPrjGph : {}", bidPrjGphDTO);

        return bidPrjGphRepository
            .findById(bidPrjGphDTO.getId())
            .map(existingBidPrjGph -> {
                bidPrjGphMapper.partialUpdate(existingBidPrjGph, bidPrjGphDTO);

                return existingBidPrjGph;
            })
            .map(bidPrjGphRepository::save)
            .map(bidPrjGphMapper::toDto);
    }

    /**
     * Get all the bidPrjGphs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjGphDTO> findAll() {
        log.debug("Request to get all BidPrjGphs");
        return bidPrjGphRepository.findAll().stream().map(bidPrjGphMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjGph by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjGphDTO> findOne(BidPrjGphPrimaryKey id) {
        log.debug("Request to get BidPrjGph : {}", id);
        return bidPrjGphRepository.findById(id).map(bidPrjGphMapper::toDto);
    }

    /**
     * Get project all bidPrjGph by project id.
     *
     * @param criteria the id of the project.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjGphDTO> findPages(BidPrjGphCriteria criteria) {
        log.debug("Request to get BidPrjGph by project : {} ", criteria.toString());
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "seq" });
        }
        return bidPrjGphRepository.findAll(criteria, criteria.toPageable()).map(bidPrjGphMapper::toDto);
    }

    @Transactional(readOnly = true)
    public int findMaxSeq(BidPrjGphCriteria criteria) {
        return bidPrjGphRepository.findMaxSeq(criteria);
    }

    /**
     * Delete the bidPrjGph by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjGphPrimaryKey id) {
        log.debug("Request to delete BidPrjGph : {}", id);
        bidPrjGphRepository.deleteById(id);
    }
}
