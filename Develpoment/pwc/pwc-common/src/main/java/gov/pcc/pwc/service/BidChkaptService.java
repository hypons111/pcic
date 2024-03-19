package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidChkapt;
import gov.pcc.pwc.domain.BidChkaptPrimaryKey;
import gov.pcc.pwc.repository.BidChkaptRepository;
import gov.pcc.pwc.service.criteria.BidChkaptCriteria;
import gov.pcc.pwc.service.dto.BidChkaptDTO;
import gov.pcc.pwc.service.mapper.BidChkaptMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidChkapt}.
 */
@Service
@Transactional
public class BidChkaptService {

    private final Logger log = LoggerFactory.getLogger(BidChkaptService.class);

    private final BidChkaptRepository bidChkaptRepository;

    private final BidChkaptMapper bidChkaptMapper;

    public BidChkaptService(BidChkaptRepository bidChkaptRepository, BidChkaptMapper bidChkaptMapper) {
        this.bidChkaptRepository = bidChkaptRepository;
        this.bidChkaptMapper = bidChkaptMapper;
    }

    /**
     * Save a bidChkapt.
     *
     * @param bidChkaptDTO the entity to save.
     * @return the persisted entity.
     */
    public BidChkaptDTO save(BidChkaptDTO bidChkaptDTO) {
        log.debug("Request to save BidChkapt : {}", bidChkaptDTO);
        BidChkapt bidChkapt = bidChkaptMapper.toEntity(bidChkaptDTO);
        bidChkapt = bidChkaptRepository.save(bidChkapt);
        return bidChkaptMapper.toDto(bidChkapt);
    }

    /**
     * Partially update a bidChkapt.
     *
     * @param bidChkaptDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidChkaptDTO> partialUpdate(BidChkaptDTO bidChkaptDTO) {
        log.debug("Request to partially update BidChkapt : {}", bidChkaptDTO);

        return bidChkaptRepository
                .findById(bidChkaptDTO.getId())
                .map(existingBidChkapt -> {
                    bidChkaptMapper.partialUpdate(existingBidChkapt, bidChkaptDTO);

                    return existingBidChkapt;
                })
                .map(bidChkaptRepository::save)
                .map(bidChkaptMapper::toDto);
    }

    /**
     * Get all the bidChkapts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidChkaptDTO> findAll() {
        log.debug("Request to get all BidChkapts");
        return bidChkaptRepository.findAll().stream().map(bidChkaptMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidChkapt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidChkaptDTO> findOne(BidChkaptPrimaryKey id) {
        log.debug("Request to get BidChkapt : {}", id);
        return bidChkaptRepository.findById(id).map(bidChkaptMapper::toDto);
    }

    /**
     * Delete the bidChkapt by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidChkaptPrimaryKey id) {
        log.debug("Request to delete BidChkapt : {}", id);
        bidChkaptRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<BidChkaptDTO> findLastestOne(BidChkaptCriteria criteria) {
        log.debug("Request to get latest BidChkapt : {}",criteria);
        criteria.setSortBy(new String[]{"idate"});
        return bidChkaptRepository.findAll(criteria, criteria.toPageable()).stream().map(bidChkaptMapper::toDto).findFirst();
    }

}
