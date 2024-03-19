package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPlnunitb;
import gov.pcc.pwc.repository.BidPlnunitbRepository;
import gov.pcc.pwc.service.criteria.BidPlnunitbCriteria;
import gov.pcc.pwc.service.criteria.BidUnit10Criteria;
import gov.pcc.pwc.service.dto.BidPlnunitbDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import gov.pcc.pwc.service.mapper.BidPlnunitbMapper;
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
 * Service Implementation for managing {@link BidPlnunitb}.
 */
@Service
@Transactional
public class BidPlnunitbService {

    private final Logger log = LoggerFactory.getLogger(BidPlnunitbService.class);

    private final BidPlnunitbRepository bidPlnunitbRepository;

    private final BidPlnunitbMapper bidPlnunitbMapper;

    public BidPlnunitbService(BidPlnunitbRepository bidPlnunitbRepository, BidPlnunitbMapper bidPlnunitbMapper) {
        this.bidPlnunitbRepository = bidPlnunitbRepository;
        this.bidPlnunitbMapper = bidPlnunitbMapper;
    }

    /**
     * Save a bidPlnunitb.
     *
     * @param bidPlnunitbDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPlnunitbDTO save(BidPlnunitbDTO bidPlnunitbDTO) {
        log.debug("Request to save BidPlnunitb : {}", bidPlnunitbDTO);
        BidPlnunitb bidPlnunitb = bidPlnunitbMapper.toEntity(bidPlnunitbDTO);
        bidPlnunitb = bidPlnunitbRepository.save(bidPlnunitb);
        return bidPlnunitbMapper.toDto(bidPlnunitb);
    }

    /**
     * Partially update a bidPlnunitb.
     *
     * @param bidPlnunitbDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPlnunitbDTO> partialUpdate(BidPlnunitbDTO bidPlnunitbDTO) {
        log.debug("Request to partially update BidPlnunitb : {}", bidPlnunitbDTO);

        return bidPlnunitbRepository
            .findById(bidPlnunitbDTO.getId())
            .map(existingBidPlnunitb -> {
                bidPlnunitbMapper.partialUpdate(existingBidPlnunitb, bidPlnunitbDTO);

                return existingBidPlnunitb;
            })
            .map(bidPlnunitbRepository::save)
            .map(bidPlnunitbMapper::toDto);
    }

    /**
     * Get all the bidPlnunitbs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPlnunitbDTO> findAll() {
        log.debug("Request to get all BidPlnunitbs");
        return bidPlnunitbRepository.findAll().stream().map(bidPlnunitbMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPlnunitb by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPlnunitbDTO> findOne(Long id) {
        log.debug("Request to get BidPlnunitb : {}", id);
        return bidPlnunitbRepository.findById(id).map(bidPlnunitbMapper::toDto);
    }

    /**
     * Delete the bidPlnunitb by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BidPlnunitb : {}", id);
        bidPlnunitbRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BidPlnunitbDTO> findAllByCriteria(BidPlnunitbCriteria criteria) {
        log.debug("Request to get BidPlnunitb by criteria : {} ", criteria.toString());
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "unicode", "plnname" });
        }
        return bidPlnunitbRepository.findAllByCriteria(criteria);
    }
}
