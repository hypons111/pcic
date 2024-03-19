package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjPe;
import gov.pcc.pwc.repository.BidPrjPeRepository;
import gov.pcc.pwc.service.dto.BidPrjPeDTO;
import gov.pcc.pwc.service.mapper.BidPrjPeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjPe}.
 */
@Service
@Transactional
public class BidPrjPeService {

    private final Logger log = LoggerFactory.getLogger(BidPrjPeService.class);

    private final BidPrjPeRepository bidPrjPeRepository;

    private final BidPrjPeMapper bidPrjPeMapper;

    public BidPrjPeService(BidPrjPeRepository bidPrjPeRepository, BidPrjPeMapper bidPrjPeMapper) {
        this.bidPrjPeRepository = bidPrjPeRepository;
        this.bidPrjPeMapper = bidPrjPeMapper;
    }

    /**
     * Save a bidPrjPe.
     *
     * @param bidPrjPeDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjPeDTO save(BidPrjPeDTO bidPrjPeDTO) {
        log.debug("Request to save BidPrjPe : {}", bidPrjPeDTO);
        BidPrjPe bidPrjPe = bidPrjPeMapper.toEntity(bidPrjPeDTO);
        bidPrjPe = bidPrjPeRepository.save(bidPrjPe);
        return bidPrjPeMapper.toDto(bidPrjPe);
    }

    /**
     * Partially update a bidPrjPe.
     *
     * @param bidPrjPeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjPeDTO> partialUpdate(BidPrjPeDTO bidPrjPeDTO) {
        log.debug("Request to partially update BidPrjPe : {}", bidPrjPeDTO);

        return bidPrjPeRepository
            .findById(bidPrjPeDTO.getId())
            .map(existingBidPrjPe -> {
                bidPrjPeMapper.partialUpdate(existingBidPrjPe, bidPrjPeDTO);

                return existingBidPrjPe;
            })
            .map(bidPrjPeRepository::save)
            .map(bidPrjPeMapper::toDto);
    }

    /**
     * Get all the bidPrjPes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjPeDTO> findAll() {
        log.debug("Request to get all BidPrjPes");
        return bidPrjPeRepository.findAll().stream().map(bidPrjPeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjPe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjPeDTO> findOne(String id) {
        log.debug("Request to get BidPrjPe : {}", id);
        return bidPrjPeRepository.findById(id).map(bidPrjPeMapper::toDto);
    }

    /**
     * Delete the bidPrjPe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete BidPrjPe : {}", id);
        bidPrjPeRepository.deleteById(id);
    }
}
