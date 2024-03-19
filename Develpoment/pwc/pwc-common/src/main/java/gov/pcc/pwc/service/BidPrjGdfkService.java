package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjGdfk;
import gov.pcc.pwc.domain.BidPrjGdfkPrimaryKey;
import gov.pcc.pwc.domain.BidPrjWksorc;
import gov.pcc.pwc.repository.BidPrjGdfkRepository;
import gov.pcc.pwc.service.criteria.BidPrjGdfkCriteria;
import gov.pcc.pwc.service.criteria.BidPrjWksorcCriteria;
import gov.pcc.pwc.service.dto.BidPrjGdfkDTO;
import gov.pcc.pwc.service.dto.BidPrjWksorcDTO;
import gov.pcc.pwc.service.mapper.BidPrjGdfkMapper;
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
 * Service Implementation for managing {@link BidPrjGdfk}.
 */
@Service
@Transactional
public class BidPrjGdfkService {

    private final Logger log = LoggerFactory.getLogger(BidPrjGdfkService.class);

    private final BidPrjGdfkRepository bidPrjGdfkRepository;

    private final BidPrjGdfkMapper bidPrjGdfkMapper;

    public BidPrjGdfkService(BidPrjGdfkRepository bidPrjGdfkRepository, BidPrjGdfkMapper bidPrjGdfkMapper) {
        this.bidPrjGdfkRepository = bidPrjGdfkRepository;
        this.bidPrjGdfkMapper = bidPrjGdfkMapper;
    }

    /**
     * Save a bidPrjGdfk.
     *
     * @param bidPrjGdfkDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjGdfkDTO save(BidPrjGdfkDTO bidPrjGdfkDTO) {
        log.debug("Request to save BidPrjGdfk : {}", bidPrjGdfkDTO);
        BidPrjGdfk bidPrjGdfk = bidPrjGdfkMapper.toEntity(bidPrjGdfkDTO);
        bidPrjGdfk = bidPrjGdfkRepository.save(bidPrjGdfk);
        return bidPrjGdfkMapper.toDto(bidPrjGdfk);
    }

    /**
     * Partially update a bidPrjGdfk.
     *
     * @param bidPrjGdfkDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjGdfkDTO> partialUpdate(BidPrjGdfkDTO bidPrjGdfkDTO) {
        log.debug("Request to partially update BidPrjGdfk : {}", bidPrjGdfkDTO);

        return bidPrjGdfkRepository
            .findById(bidPrjGdfkDTO.getId())
            .map(existingBidPrjGdfk -> {
                bidPrjGdfkMapper.partialUpdate(existingBidPrjGdfk, bidPrjGdfkDTO);

                return existingBidPrjGdfk;
            })
            .map(bidPrjGdfkRepository::save)
            .map(bidPrjGdfkMapper::toDto);
    }

    /**
     * Get all the bidPrjGdfks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjGdfkDTO> findAll() {
        log.debug("Request to get all BidPrjGdfks");
        return bidPrjGdfkRepository.findAll().stream().map(bidPrjGdfkMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjGdfk by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjGdfkDTO> findOne(BidPrjGdfkPrimaryKey id) {
        log.debug("Request to get BidPrjGdfk : {}", id);
        return bidPrjGdfkRepository.findById(id).map(bidPrjGdfkMapper::toDto);
    }

    /**
     * Delete the bidPrjGdfk by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjGdfkPrimaryKey id) {
        log.debug("Request to delete BidPrjGdfk : {}", id);
        bidPrjGdfkRepository.deleteById(id);
    }


    /**
     * Find the list of BidPrjSegmsetDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidPrjSegmsetDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjGdfkDTO> findByCriteria(BidPrjGdfkCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "wkut" });
        }
        final Page<BidPrjGdfk> bidPrjGdfks = bidPrjGdfkRepository.findAll(criteria, criteria.toPageable());
        return bidPrjGdfks.map(bidPrjGdfkMapper::toDto);
    }
}
