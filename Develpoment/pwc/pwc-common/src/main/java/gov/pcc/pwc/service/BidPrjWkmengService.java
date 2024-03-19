package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjWkmeng;
import gov.pcc.pwc.repository.BidPrjWkmengRepository;
import gov.pcc.pwc.service.dto.BidPrjWkmengDTO;
import gov.pcc.pwc.service.mapper.BidPrjWkmengMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjWkmeng}.
 */
@Service
@Transactional
public class BidPrjWkmengService {

    private final Logger log = LoggerFactory.getLogger(BidPrjWkmengService.class);

    private final BidPrjWkmengRepository bidPrjWkmengRepository;

    private final BidPrjWkmengMapper bidPrjWkmengMapper;

    public BidPrjWkmengService(BidPrjWkmengRepository bidPrjWkmengRepository, BidPrjWkmengMapper bidPrjWkmengMapper) {
        this.bidPrjWkmengRepository = bidPrjWkmengRepository;
        this.bidPrjWkmengMapper = bidPrjWkmengMapper;
    }

    /**
     * Save a bidPrjWkmeng.
     *
     * @param bidPrjWkmengDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjWkmengDTO save(BidPrjWkmengDTO bidPrjWkmengDTO) {
        log.debug("Request to save BidPrjWkmeng : {}", bidPrjWkmengDTO);
        BidPrjWkmeng bidPrjWkmeng = bidPrjWkmengMapper.toEntity(bidPrjWkmengDTO);
        bidPrjWkmeng = bidPrjWkmengRepository.save(bidPrjWkmeng);
        return bidPrjWkmengMapper.toDto(bidPrjWkmeng);
    }

    /**
     * Partially update a bidPrjWkmeng.
     *
     * @param bidPrjWkmengDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjWkmengDTO> partialUpdate(BidPrjWkmengDTO bidPrjWkmengDTO) {
        log.debug("Request to partially update BidPrjWkmeng : {}", bidPrjWkmengDTO);

        return bidPrjWkmengRepository
            .findById(bidPrjWkmengDTO.getId())
            .map(existingBidPrjWkmeng -> {
                bidPrjWkmengMapper.partialUpdate(existingBidPrjWkmeng, bidPrjWkmengDTO);

                return existingBidPrjWkmeng;
            })
            .map(bidPrjWkmengRepository::save)
            .map(bidPrjWkmengMapper::toDto);
    }

    /**
     * Get all the bidPrjWkmengs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjWkmengDTO> findAll() {
        log.debug("Request to get all BidPrjWkmengs");
        return bidPrjWkmengRepository.findAll().stream().map(bidPrjWkmengMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjWkmeng by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjWkmengDTO> findOne(String id) {
        log.debug("Request to get BidPrjWkmeng : {}", id);
        return bidPrjWkmengRepository.findById(id).map(bidPrjWkmengMapper::toDto);
    }

    /**
     * Delete the bidPrjWkmeng by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete BidPrjWkmeng : {}", id);
        bidPrjWkmengRepository.deleteById(id);
    }
}
