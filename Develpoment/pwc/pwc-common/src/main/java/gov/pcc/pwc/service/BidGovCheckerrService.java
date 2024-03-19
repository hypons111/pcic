package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidGovCheckerr;
import gov.pcc.pwc.domain.BidGovCheckerrPrimaryKey;
import gov.pcc.pwc.repository.BidGovCheckerrRepository;
import gov.pcc.pwc.service.criteria.BidGovCheckerrCriteria;
import gov.pcc.pwc.service.dto.BidGovCheckerrDTO;
import gov.pcc.pwc.service.mapper.BidGovCheckerrMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidGovCheckerr}.
 */
@Service
@Transactional
public class BidGovCheckerrService {

    private final Logger log = LoggerFactory.getLogger(BidGovCheckerrService.class);

    private final BidGovCheckerrRepository bidGovCheckerrRepository;

    private final BidGovCheckerrMapper bidGovCheckerrMapper;

    public BidGovCheckerrService(BidGovCheckerrRepository bidGovCheckerrRepository, BidGovCheckerrMapper bidGovCheckerrMapper) {
        this.bidGovCheckerrRepository = bidGovCheckerrRepository;
        this.bidGovCheckerrMapper = bidGovCheckerrMapper;
    }

    /**
     * Save a bidGovCheckerr.
     *
     * @param bidGovCheckerrDTO the entity to save.
     * @return the persisted entity.
     */
    public BidGovCheckerrDTO save(BidGovCheckerrDTO bidGovCheckerrDTO) {
        log.debug("Request to save BidGovCheckerr : {}", bidGovCheckerrDTO);
        BidGovCheckerr bidGovCheckerr = bidGovCheckerrMapper.toEntity(bidGovCheckerrDTO);
        bidGovCheckerr = bidGovCheckerrRepository.save(bidGovCheckerr);
        return bidGovCheckerrMapper.toDto(bidGovCheckerr);
    }

    /**
     * Partially update a bidGovCheckerr.
     *
     * @param bidGovCheckerrDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidGovCheckerrDTO> partialUpdate(BidGovCheckerrDTO bidGovCheckerrDTO) {
        log.debug("Request to partially update BidGovCheckerr : {}", bidGovCheckerrDTO);

        return bidGovCheckerrRepository
            .findById(bidGovCheckerrDTO.getId())
            .map(existingBidGovCheckerr -> {
                bidGovCheckerrMapper.partialUpdate(existingBidGovCheckerr, bidGovCheckerrDTO);

                return existingBidGovCheckerr;
            })
            .map(bidGovCheckerrRepository::save)
            .map(bidGovCheckerrMapper::toDto);
    }

    /**
     * Get all the bidGovCheckerrs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidGovCheckerrDTO> findAll() {
        log.debug("Request to get all BidGovCheckerrs");
        return bidGovCheckerrRepository
            .findAll()
            .stream()
            .map(bidGovCheckerrMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public List<BidGovCheckerrDTO> findAllByCriteria(BidGovCheckerrCriteria criteria) {
        log.debug("Request to get all BidGovCheckerrs : {}", criteria);
        return bidGovCheckerrRepository.findByWkutAndPrjnoAndCheckDate(criteria.getWkut(), criteria.getPrjno(), criteria.getCheckDate())
                .stream()
                .map(bidGovCheckerrMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidGovCheckerr by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidGovCheckerrDTO> findOne(BidGovCheckerrPrimaryKey id) {
        log.debug("Request to get BidGovCheckerr : {}", id);
        return bidGovCheckerrRepository.findById(id).map(bidGovCheckerrMapper::toDto);
    }

    /**
     * Delete the bidGovCheckerr by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidGovCheckerrPrimaryKey id) {
        log.debug("Request to delete BidGovCheckerr : {}", id);
        bidGovCheckerrRepository.deleteById(id);
    }
}
