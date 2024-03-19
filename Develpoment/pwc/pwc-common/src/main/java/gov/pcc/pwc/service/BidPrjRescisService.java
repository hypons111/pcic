package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjRescis;
import gov.pcc.pwc.domain.BidPrjRescisPrimaryKey;
import gov.pcc.pwc.repository.BidPrjRescisRepository;
import gov.pcc.pwc.service.criteria.BidPrjRescisCriteria;
import gov.pcc.pwc.service.dto.BidPrjRescisDTO;
import gov.pcc.pwc.service.mapper.BidPrjRescisMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjRescis}.
 */
@Service
@Transactional
public class BidPrjRescisService {

    private final Logger log = LoggerFactory.getLogger(BidPrjRescisService.class);

    private final BidPrjRescisRepository bidPrjRescisRepository;

    private final BidPrjRescisMapper bidPrjRescisMapper;

    public BidPrjRescisService(BidPrjRescisRepository bidPrjRescisRepository, BidPrjRescisMapper bidPrjRescisMapper) {
        this.bidPrjRescisRepository = bidPrjRescisRepository;
        this.bidPrjRescisMapper = bidPrjRescisMapper;
    }

    /**
     * Save a bidPrjRescis.
     *
     * @param bidPrjRescisDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjRescisDTO save(BidPrjRescisDTO bidPrjRescisDTO) {
        log.debug("Request to save BidPrjRescis : {}", bidPrjRescisDTO);
        BidPrjRescis bidPrjRescis = bidPrjRescisMapper.toEntity(bidPrjRescisDTO);
        bidPrjRescis = bidPrjRescisRepository.save(bidPrjRescis);
        return bidPrjRescisMapper.toDto(bidPrjRescis);
    }

    /**
     * Partially update a bidPrjRescis.
     *
     * @param bidPrjRescisDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjRescisDTO> partialUpdate(BidPrjRescisDTO bidPrjRescisDTO) {
        log.debug("Request to partially update BidPrjRescis : {}", bidPrjRescisDTO);

        return bidPrjRescisRepository
            .findById(bidPrjRescisDTO.getId())
            .map(existingBidPrjRescis -> {
                bidPrjRescisMapper.partialUpdate(existingBidPrjRescis, bidPrjRescisDTO);

                return existingBidPrjRescis;
            })
            .map(bidPrjRescisRepository::save)
            .map(bidPrjRescisMapper::toDto);
    }

    /**
     * Get all the bidPrjRescis.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjRescisDTO> findAll() {
        log.debug("Request to get all BidPrjRescis");
        return bidPrjRescisRepository.findAll().stream().map(bidPrjRescisMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjRescis by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjRescisDTO> findOne(BidPrjRescisPrimaryKey id) {
        log.debug("Request to get BidPrjRescis : {}", id);
        return bidPrjRescisRepository.findById(id).map(bidPrjRescisMapper::toDto);
    }

    /**
     * Delete the bidPrjRescis by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjRescisPrimaryKey id) {
        log.debug("Request to delete BidPrjRescis : {}", id);
        bidPrjRescisRepository.deleteById(id);
    }

    public Optional<BidPrjRescisDTO> getLatestBidPrjRescis(BidPrjRescisCriteria criteria){
        criteria.setSortBy(new String[]{"stopDate"});
        return bidPrjRescisRepository.findAll(criteria, criteria.toPageable().getSort()).stream().findFirst().map(bidPrjRescisMapper::toDto);
    }
}
