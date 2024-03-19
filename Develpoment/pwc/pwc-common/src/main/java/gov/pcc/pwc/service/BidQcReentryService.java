package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidQcReentry;
import gov.pcc.pwc.repository.BidQcReentryRepository;
import gov.pcc.pwc.service.dto.BidQcReentryDTO;
import gov.pcc.pwc.service.mapper.BidQcReentryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidQcReentry}.
 */
@Service
@Transactional
public class BidQcReentryService {

    private final Logger log = LoggerFactory.getLogger(BidQcReentryService.class);

    private final BidQcReentryRepository bidQcReentryRepository;

    private final BidQcReentryMapper bidQcReentryMapper;

    public BidQcReentryService(BidQcReentryRepository bidQcReentryRepository, BidQcReentryMapper bidQcReentryMapper) {
        this.bidQcReentryRepository = bidQcReentryRepository;
        this.bidQcReentryMapper = bidQcReentryMapper;
    }

    /**
     * Save a bidQcReentry.
     *
     * @param bidQcReentryDTO the entity to save.
     * @return the persisted entity.
     */
    public BidQcReentryDTO save(BidQcReentryDTO bidQcReentryDTO) {
        log.debug("Request to save BidQcReentry : {}", bidQcReentryDTO);
        BidQcReentry bidQcReentry = bidQcReentryMapper.toEntity(bidQcReentryDTO);
        bidQcReentry = bidQcReentryRepository.save(bidQcReentry);
        return bidQcReentryMapper.toDto(bidQcReentry);
    }

    /**
     * Partially update a bidQcReentry.
     *
     * @param bidQcReentryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidQcReentryDTO> partialUpdate(BidQcReentryDTO bidQcReentryDTO) {
        log.debug("Request to partially update BidQcReentry : {}", bidQcReentryDTO);

        return bidQcReentryRepository
            .findById(bidQcReentryDTO.getId())
            .map(existingBidQcReentry -> {
                bidQcReentryMapper.partialUpdate(existingBidQcReentry, bidQcReentryDTO);

                return existingBidQcReentry;
            })
            .map(bidQcReentryRepository::save)
            .map(bidQcReentryMapper::toDto);
    }

    /**
     * Get all the bidQcReentries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidQcReentryDTO> findAll() {
        log.debug("Request to get all BidQcReentries");
        return bidQcReentryRepository.findAll().stream().map(bidQcReentryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidQcReentry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidQcReentryDTO> findOne(String id) {
        log.debug("Request to get BidQcReentry : {}", id);
        return bidQcReentryRepository.findById(id).map(bidQcReentryMapper::toDto);
    }

    /**
     * Delete the bidQcReentry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete BidQcReentry : {}", id);
        bidQcReentryRepository.deleteById(id);
    }
}
