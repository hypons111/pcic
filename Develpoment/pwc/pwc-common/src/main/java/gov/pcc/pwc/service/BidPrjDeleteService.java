package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjDelete;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.repository.BidPrjDeleteRepository;
import gov.pcc.pwc.service.dto.BidPrjDeleteDTO;
import gov.pcc.pwc.service.mapper.BidPrjDeleteMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjDelete}.
 */
@Service
@Transactional
public class BidPrjDeleteService {

    private final Logger log = LoggerFactory.getLogger(BidPrjDeleteService.class);

    private final BidPrjDeleteRepository bidPrjDeleteRepository;

    private final BidPrjDeleteMapper bidPrjDeleteMapper;

    public BidPrjDeleteService(BidPrjDeleteRepository bidPrjDeleteRepository, BidPrjDeleteMapper bidPrjDeleteMapper) {
        this.bidPrjDeleteRepository = bidPrjDeleteRepository;
        this.bidPrjDeleteMapper = bidPrjDeleteMapper;
    }

    /**
     * Save a bidPrjDelete.
     *
     * @param bidPrjDeleteDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjDeleteDTO save(BidPrjDeleteDTO bidPrjDeleteDTO) {
        log.debug("Request to save BidPrjDelete : {}", bidPrjDeleteDTO);
        BidPrjDelete bidPrjDelete = bidPrjDeleteMapper.toEntity(bidPrjDeleteDTO);
        bidPrjDelete = bidPrjDeleteRepository.save(bidPrjDelete);
        return bidPrjDeleteMapper.toDto(bidPrjDelete);
    }

    /**
     * Partially update a bidPrjDelete.
     *
     * @param bidPrjDeleteDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjDeleteDTO> partialUpdate(BidPrjDeleteDTO bidPrjDeleteDTO) {
        log.debug("Request to partially update BidPrjDelete : {}", bidPrjDeleteDTO);

        return bidPrjDeleteRepository
            .findById(bidPrjDeleteDTO.getId())
            .map(existingBidPrjDelete -> {
                bidPrjDeleteMapper.partialUpdate(existingBidPrjDelete, bidPrjDeleteDTO);

                return existingBidPrjDelete;
            })
            .map(bidPrjDeleteRepository::save)
            .map(bidPrjDeleteMapper::toDto);
    }

    /**
     * Get all the bidPrjDeletes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjDeleteDTO> findAll() {
        log.debug("Request to get all BidPrjDeletes");
        return bidPrjDeleteRepository.findAll().stream().map(bidPrjDeleteMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjDelete by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjDeleteDTO> findOne(BidProjectPrimaryKey id) {
        log.debug("Request to get BidPrjDelete : {}", id);
        return bidPrjDeleteRepository.findById(id).map(bidPrjDeleteMapper::toDto);
    }

    /**
     * Delete the bidPrjDelete by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidProjectPrimaryKey id) {
        log.debug("Request to delete BidPrjDelete : {}", id);
        bidPrjDeleteRepository.deleteById(id);
    }
}
