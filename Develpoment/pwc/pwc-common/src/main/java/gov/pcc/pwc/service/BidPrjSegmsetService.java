package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.domain.BidPrjSegmset;
import gov.pcc.pwc.domain.BidPrjSegmsetPrimaryKey;
import gov.pcc.pwc.repository.BidPrjSegmsetRepository;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.criteria.BidPrjSegmsetCriteria;
import gov.pcc.pwc.service.dto.BidCcutDTO;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import gov.pcc.pwc.service.dto.BidPrjSegmsetDTO;
import gov.pcc.pwc.service.mapper.BidPrjSegmsetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BidPrjSegmset}.
 */
@Service
@Transactional
public class BidPrjSegmsetService {

    private final Logger log = LoggerFactory.getLogger(BidPrjSegmsetService.class);

    private final BidPrjSegmsetRepository bidPrjSegmsetRepository;

    private final BidPrjSegmsetMapper bidPrjSegmsetMapper;

    public BidPrjSegmsetService(BidPrjSegmsetRepository bidPrjSegmsetRepository, BidPrjSegmsetMapper bidPrjSegmsetMapper) {
        this.bidPrjSegmsetRepository = bidPrjSegmsetRepository;
        this.bidPrjSegmsetMapper = bidPrjSegmsetMapper;
    }

    /**
     * Save a bidPrjSegmset.
     *
     * @param bidPrjSegmsetDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjSegmsetDTO save(BidPrjSegmsetDTO bidPrjSegmsetDTO) {
        log.debug("Request to save BidPrjSegmset : {}", bidPrjSegmsetDTO);
        BidPrjSegmset bidPrjSegmset = bidPrjSegmsetMapper.toEntity(bidPrjSegmsetDTO);
        bidPrjSegmset = bidPrjSegmsetRepository.save(bidPrjSegmset);
        return bidPrjSegmsetMapper.toDto(bidPrjSegmset);
    }

    /**
     * Partially update a bidPrjSegmset.
     *
     * @param bidPrjSegmsetDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjSegmsetDTO> partialUpdate(BidPrjSegmsetDTO bidPrjSegmsetDTO) {
        log.debug("Request to partially update BidPrjSegmset : {}", bidPrjSegmsetDTO);

        return bidPrjSegmsetRepository
            .findById(bidPrjSegmsetDTO.getId())
            .map(existingBidPrjSegmset -> {
                bidPrjSegmsetMapper.partialUpdate(existingBidPrjSegmset, bidPrjSegmsetDTO);

                return existingBidPrjSegmset;
            })
            .map(bidPrjSegmsetRepository::save)
            .map(bidPrjSegmsetMapper::toDto);
    }

    /**
     * Get all the bidPrjSegmsets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjSegmsetDTO> findAll() {
        log.debug("Request to get all BidPrjSegmsets");
        return bidPrjSegmsetRepository.findAll().stream().map(bidPrjSegmsetMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjSegmset by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjSegmsetDTO> findOne(BidPrjSegmsetPrimaryKey id) {
        log.debug("Request to get BidPrjSegmset : {}", id);
        return bidPrjSegmsetRepository.findById(id).map(bidPrjSegmsetMapper::toDto);
    }

    /**
     * Delete the bidPrjSegmset by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjSegmsetPrimaryKey id) {
        log.debug("Request to delete BidPrjSegmset : {}", id);
        bidPrjSegmsetRepository.deleteById(id);
    }



    /**
     * Find the list of BidPrjSegmsetDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidPrjSegmsetDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjSegmsetDTO> findByCriteria(BidPrjSegmsetCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "wkut" });
        }
        final Page<BidPrjSegmset> bidPrjSegmsets = bidPrjSegmsetRepository.findAll(criteria, criteria.toPageable());
        return bidPrjSegmsets.map(bidPrjSegmsetMapper::toDto);
    }


}
