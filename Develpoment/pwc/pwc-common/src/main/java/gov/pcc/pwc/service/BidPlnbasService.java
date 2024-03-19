package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPlnbas;
import gov.pcc.pwc.domain.BidPlnbasPrimaryKey;
import gov.pcc.pwc.repository.BidPlnbasRepository;
import gov.pcc.pwc.service.criteria.BidPlnbasCriteria;
import gov.pcc.pwc.service.criteria.BidUnit10Criteria;
import gov.pcc.pwc.service.dto.BidPlnbasDTO;
import gov.pcc.pwc.service.dto.BidPlnunitbDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import gov.pcc.pwc.service.mapper.BidPlnbasMapper;
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
 * Service Implementation for managing {@link BidPlnbas}.
 */
@Service
@Transactional
public class BidPlnbasService {

    private final Logger log = LoggerFactory.getLogger(BidPlnbasService.class);

    private final BidPlnbasRepository bidPlnbasRepository;

    private final BidPlnbasMapper bidPlnbasMapper;

    public BidPlnbasService(BidPlnbasRepository bidPlnbasRepository, BidPlnbasMapper bidPlnbasMapper) {
        this.bidPlnbasRepository = bidPlnbasRepository;
        this.bidPlnbasMapper = bidPlnbasMapper;
    }

    /**
     * Save a bidPlnbas.
     *
     * @param bidPlnbasDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPlnbasDTO save(BidPlnbasDTO bidPlnbasDTO) {
        log.debug("Request to save BidPlnbas : {}", bidPlnbasDTO);
        BidPlnbas bidPlnbas = bidPlnbasMapper.toEntity(bidPlnbasDTO);
        bidPlnbas = bidPlnbasRepository.save(bidPlnbas);
        return bidPlnbasMapper.toDto(bidPlnbas);
    }

    /**
     * Partially update a bidPlnbas.
     *
     * @param bidPlnbasDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPlnbasDTO> partialUpdate(BidPlnbasDTO bidPlnbasDTO) {
        log.debug("Request to partially update BidPlnbas : {}", bidPlnbasDTO);

        return bidPlnbasRepository
            .findById(bidPlnbasDTO.getId())
            .map(existingBidPlnbas -> {
                bidPlnbasMapper.partialUpdate(existingBidPlnbas, bidPlnbasDTO);

                return existingBidPlnbas;
            })
            .map(bidPlnbasRepository::save)
            .map(bidPlnbasMapper::toDto);
    }

    /**
     * Get all the bidPlnbas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPlnbasDTO> findAll() {
        log.debug("Request to get all BidPlnbas");
        return bidPlnbasRepository.findAll().stream().map(bidPlnbasMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPlnbas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPlnbasDTO> findOne(BidPlnbasPrimaryKey id) {
        log.debug("Request to get BidPlnbas : {}", id);
        return bidPlnbasRepository.findById(id).map(bidPlnbasMapper::toDto);
    }

    /**
     * Delete the bidPlnbas by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPlnbasPrimaryKey id) {
        log.debug("Request to delete BidPlnbas : {}", id);
        bidPlnbasRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<BidPlnbasDTO> findAllByCriteria(BidPlnbasCriteria criteria) {
        log.debug("Request to get BidPlnunitb by criteria : {} ", criteria.toString());
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "plncode", "plnno" });
        }
        return bidPlnbasRepository.findAllByCriteria(criteria);
    }
}
