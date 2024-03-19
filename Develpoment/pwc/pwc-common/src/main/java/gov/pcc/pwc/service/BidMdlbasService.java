package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidMdlbas;
import gov.pcc.pwc.domain.BidMdlbasPrimaryKey;
import gov.pcc.pwc.repository.BidMdlbasRepository;
import gov.pcc.pwc.service.criteria.BidMdlbasCriteria;
import gov.pcc.pwc.service.dto.BidMdlbasDTO;
import gov.pcc.pwc.service.mapper.BidMdlbasMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidMdlbas}.
 */
@Service
@Transactional
public class BidMdlbasService {

    private final Logger log = LoggerFactory.getLogger(BidMdlbasService.class);

    private final BidMdlbasRepository bidMdlbasRepository;

    private final BidMdlbasMapper bidMdlbasMapper;

    public BidMdlbasService(BidMdlbasRepository bidMdlbasRepository, BidMdlbasMapper bidMdlbasMapper) {
        this.bidMdlbasRepository = bidMdlbasRepository;
        this.bidMdlbasMapper = bidMdlbasMapper;
    }

    /**
     * Save a bidMdlbas.
     *
     * @param bidMdlbasDTO the entity to save.
     * @return the persisted entity.
     */
    public BidMdlbasDTO save(BidMdlbasDTO bidMdlbasDTO) {
        log.debug("Request to save BidMdlbas : {}", bidMdlbasDTO);
        BidMdlbas bidMdlbas = bidMdlbasMapper.toEntity(bidMdlbasDTO);
        bidMdlbas = bidMdlbasRepository.save(bidMdlbas);
        return bidMdlbasMapper.toDto(bidMdlbas);
    }

    /**
     * Partially update a bidMdlbas.
     *
     * @param bidMdlbasDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidMdlbasDTO> partialUpdate(BidMdlbasDTO bidMdlbasDTO) {
        log.debug("Request to partially update BidMdlbas : {}", bidMdlbasDTO);

        return bidMdlbasRepository
            .findById(bidMdlbasDTO.getId())
            .map(existingBidMdlbas -> {
                bidMdlbasMapper.partialUpdate(existingBidMdlbas, bidMdlbasDTO);

                return existingBidMdlbas;
            })
            .map(bidMdlbasRepository::save)
            .map(bidMdlbasMapper::toDto);
    }

    /**
     * Get all the bidMdlbas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidMdlbasDTO> findAll() {
        log.debug("Request to get all BidMdlbas");
        return bidMdlbasRepository.findAll().stream().map(bidMdlbasMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidMdlbas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidMdlbasDTO> findOne(BidMdlbasPrimaryKey id) {
        log.debug("Request to get BidMdlbas : {}", id);
        return bidMdlbasRepository.findById(id).map(bidMdlbasMapper::toDto);
    }

    /**
     * Delete the bidMdlbas by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidMdlbasPrimaryKey id) {
        log.debug("Request to delete BidMdlbas : {}", id);
        bidMdlbasRepository.deleteById(id);
    }
    /**
     * Find NOTY_BUDG By Criteria
     * */
    public List<BidMdlbasDTO> findDisNoByCriteria(BidMdlbasCriteria criteria) {
        log.debug("Request to find BidMdlbasDTO : {}", criteria);
        criteria.setSortBy(new String[]{"disNo"});
        return bidMdlbasRepository.findNotyBudgByCriteria(criteria);
    }
}
