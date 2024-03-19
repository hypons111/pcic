package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidCheckerr;
import gov.pcc.pwc.repository.BidCheckerrRepository;
import gov.pcc.pwc.service.dto.BidCheckerrDTO;
import gov.pcc.pwc.service.mapper.BidCheckerrMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidCheckerr}.
 */
@Service
@Transactional
public class BidCheckerrService {

    private final Logger log = LoggerFactory.getLogger(BidCheckerrService.class);

    private final BidCheckerrRepository bidCheckerrRepository;

    private final BidCheckerrMapper bidCheckerrMapper;

    public BidCheckerrService(BidCheckerrRepository bidCheckerrRepository, BidCheckerrMapper bidCheckerrMapper) {
        this.bidCheckerrRepository = bidCheckerrRepository;
        this.bidCheckerrMapper = bidCheckerrMapper;
    }

    /**
     * Save a bidCheckerr.
     *
     * @param bidCheckerrDTO the entity to save.
     * @return the persisted entity.
     */
    public BidCheckerrDTO save(BidCheckerrDTO bidCheckerrDTO) {
        log.debug("Request to save BidCheckerr : {}", bidCheckerrDTO);
        BidCheckerr bidCheckerr = bidCheckerrMapper.toEntity(bidCheckerrDTO);
        bidCheckerr = bidCheckerrRepository.save(bidCheckerr);
        return bidCheckerrMapper.toDto(bidCheckerr);
    }

    /**
     * Partially update a bidCheckerr.
     *
     * @param bidCheckerrDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidCheckerrDTO> partialUpdate(BidCheckerrDTO bidCheckerrDTO) {
        log.debug("Request to partially update BidCheckerr : {}", bidCheckerrDTO);

        return bidCheckerrRepository
            .findById(bidCheckerrDTO.getCode())
            .map(existingBidCheckerr -> {
                bidCheckerrMapper.partialUpdate(existingBidCheckerr, bidCheckerrDTO);

                return existingBidCheckerr;
            })
            .map(bidCheckerrRepository::save)
            .map(bidCheckerrMapper::toDto);
    }

    /**
     * Get all the bidCheckerrs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidCheckerrDTO> findAll() {
        log.debug("Request to get all BidCheckerrs");
        return bidCheckerrRepository.findAll().stream().map(bidCheckerrMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidCheckerr by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidCheckerrDTO> findOne(String id) {
        log.debug("Request to get BidCheckerr : {}", id);
        return bidCheckerrRepository.findById(id).map(bidCheckerrMapper::toDto);
    }

    /**
     * Delete the bidCheckerr by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete BidCheckerr : {}", id);
        bidCheckerrRepository.deleteById(id);
    }
}
