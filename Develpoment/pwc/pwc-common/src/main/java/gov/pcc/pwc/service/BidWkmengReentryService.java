package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidWkmengReentry;
import gov.pcc.pwc.repository.BidWkmengReentryRepository;
import gov.pcc.pwc.service.dto.BidWkmengReentryDTO;
import gov.pcc.pwc.service.mapper.BidWkmengReentryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidWkmengReentry}.
 */
@Service
@Transactional
public class BidWkmengReentryService {

    private final Logger log = LoggerFactory.getLogger(BidWkmengReentryService.class);

    private final BidWkmengReentryRepository bidWkmengReentryRepository;

    private final BidWkmengReentryMapper bidWkmengReentryMapper;

    public BidWkmengReentryService(BidWkmengReentryRepository bidWkmengReentryRepository, BidWkmengReentryMapper bidWkmengReentryMapper) {
        this.bidWkmengReentryRepository = bidWkmengReentryRepository;
        this.bidWkmengReentryMapper = bidWkmengReentryMapper;
    }

    /**
     * Save a bidWkmengReentry.
     *
     * @param bidWkmengReentryDTO the entity to save.
     * @return the persisted entity.
     */
    public BidWkmengReentryDTO save(BidWkmengReentryDTO bidWkmengReentryDTO) {
        log.debug("Request to save BidWkmengReentry : {}", bidWkmengReentryDTO);
        BidWkmengReentry bidWkmengReentry = bidWkmengReentryMapper.toEntity(bidWkmengReentryDTO);
        bidWkmengReentry = bidWkmengReentryRepository.save(bidWkmengReentry);
        return bidWkmengReentryMapper.toDto(bidWkmengReentry);
    }

    /**
     * Partially update a bidWkmengReentry.
     *
     * @param bidWkmengReentryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidWkmengReentryDTO> partialUpdate(BidWkmengReentryDTO bidWkmengReentryDTO) {
        log.debug("Request to partially update BidWkmengReentry : {}", bidWkmengReentryDTO);

        return bidWkmengReentryRepository
            .findById(bidWkmengReentryDTO.getId())
            .map(existingBidWkmengReentry -> {
                bidWkmengReentryMapper.partialUpdate(existingBidWkmengReentry, bidWkmengReentryDTO);

                return existingBidWkmengReentry;
            })
            .map(bidWkmengReentryRepository::save)
            .map(bidWkmengReentryMapper::toDto);
    }

    /**
     * Get all the bidWkmengReentries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidWkmengReentryDTO> findAll() {
        log.debug("Request to get all BidWkmengReentries");
        return bidWkmengReentryRepository
            .findAll()
            .stream()
            .map(bidWkmengReentryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidWkmengReentry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidWkmengReentryDTO> findOne(String id) {
        log.debug("Request to get BidWkmengReentry : {}", id);
        return bidWkmengReentryRepository.findById(id).map(bidWkmengReentryMapper::toDto);
    }

    /**
     * Delete the bidWkmengReentry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete BidWkmengReentry : {}", id);
        bidWkmengReentryRepository.deleteById(id);
    }
}
