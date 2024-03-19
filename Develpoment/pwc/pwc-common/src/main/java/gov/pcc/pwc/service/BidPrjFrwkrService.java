package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.domain.BidPrjFrwkr;
import gov.pcc.pwc.domain.BidPrjFrwkrPrimaryKey;
import gov.pcc.pwc.repository.BidPrjFrwkrRepository;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.criteria.BidPrjFrwkrCriteria;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import gov.pcc.pwc.service.dto.BidPrjFrwkrDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import gov.pcc.pwc.service.mapper.BidPrjFrwkrMapper;
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
 * Service Implementation for managing {@link BidPrjFrwkr}.
 */
@Service
@Transactional
public class BidPrjFrwkrService {

    private final Logger log = LoggerFactory.getLogger(BidPrjFrwkrService.class);

    private final BidPrjFrwkrRepository bidPrjFrwkrRepository;

    private final BidPrjFrwkrMapper bidPrjFrwkrMapper;

    public BidPrjFrwkrService(BidPrjFrwkrRepository bidPrjFrwkrRepository, BidPrjFrwkrMapper bidPrjFrwkrMapper) {
        this.bidPrjFrwkrRepository = bidPrjFrwkrRepository;
        this.bidPrjFrwkrMapper = bidPrjFrwkrMapper;
    }

    /**
     * Save a bidPrjFrwkr.
     *
     * @param bidPrjFrwkrDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjFrwkrDTO save(BidPrjFrwkrDTO bidPrjFrwkrDTO) {
        log.debug("Request to save BidPrjFrwkr : {}", bidPrjFrwkrDTO);
        BidPrjFrwkr bidPrjFrwkr = bidPrjFrwkrMapper.toEntity(bidPrjFrwkrDTO);
        bidPrjFrwkr = bidPrjFrwkrRepository.save(bidPrjFrwkr);
        return bidPrjFrwkrMapper.toDto(bidPrjFrwkr);
    }

    /**
     * Partially update a bidPrjFrwkr.
     *
     * @param bidPrjFrwkrDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjFrwkrDTO> partialUpdate(BidPrjFrwkrDTO bidPrjFrwkrDTO) {
        log.debug("Request to partially update BidPrjFrwkr : {}", bidPrjFrwkrDTO);

        return bidPrjFrwkrRepository
            .findById(bidPrjFrwkrDTO.getId())
            .map(existingBidPrjFrwkr -> {
                bidPrjFrwkrMapper.partialUpdate(existingBidPrjFrwkr, bidPrjFrwkrDTO);

                return existingBidPrjFrwkr;
            })
            .map(bidPrjFrwkrRepository::save)
            .map(bidPrjFrwkrMapper::toDto);
    }

    /**
     * Get all the bidPrjFrwkrs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjFrwkrDTO> findAll() {
        log.debug("Request to get all BidPrjFrwkrs");
        return bidPrjFrwkrRepository.findAll().stream().map(bidPrjFrwkrMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjFrwkr by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjFrwkrDTO> findOne(BidPrjFrwkrPrimaryKey id) {
        log.debug("Request to get BidPrjFrwkr : {}", id);
        return bidPrjFrwkrRepository.findById(id).map(bidPrjFrwkrMapper::toDto);
    }

    /**
     * Delete the bidPrjFrwkr by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjFrwkrPrimaryKey id) {
        log.debug("Request to delete BidPrjFrwkr : {}", id);
        bidPrjFrwkrRepository.deleteById(id);
    }

    /**
     * Find the list of BidPrjFrwkrDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidPrjFrwkrDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjFrwkrDTO> findByCriteria(BidPrjFrwkrCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "wkut" });
        }
        final Page<BidPrjFrwkr> bidPrjFrwkrs = bidPrjFrwkrRepository.findAll(criteria, criteria.toPageable());
        return bidPrjFrwkrs.map(bidPrjFrwkrMapper::toDto);
    }


}
