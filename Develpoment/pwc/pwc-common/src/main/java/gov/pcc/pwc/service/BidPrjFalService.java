package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjFal;
import gov.pcc.pwc.domain.BidPrjFalPrimaryKey;
import gov.pcc.pwc.repository.BidPrjFalRepository;
import gov.pcc.pwc.service.dto.BidPrjFalDTO;
import gov.pcc.pwc.service.mapper.BidPrjFalMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjFal}.
 */
@Service
@Transactional
public class BidPrjFalService {

    private final Logger log = LoggerFactory.getLogger(BidPrjFalService.class);

    private final BidPrjFalRepository bidPrjFalRepository;

    private final BidPrjFalMapper bidPrjFalMapper;

    public BidPrjFalService(BidPrjFalRepository bidPrjFalRepository, BidPrjFalMapper bidPrjFalMapper) {
        this.bidPrjFalRepository = bidPrjFalRepository;
        this.bidPrjFalMapper = bidPrjFalMapper;
    }

    /**
     * Save a bidPrjFal.
     *
     * @param bidPrjFalDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjFalDTO save(BidPrjFalDTO bidPrjFalDTO) {
        log.debug("Request to save BidPrjFal : {}", bidPrjFalDTO);
        BidPrjFal bidPrjFal = bidPrjFalMapper.toEntity(bidPrjFalDTO);
        bidPrjFal = bidPrjFalRepository.save(bidPrjFal);
        return bidPrjFalMapper.toDto(bidPrjFal);
    }

    /**
     * Partially update a bidPrjFal.
     *
     * @param bidPrjFalDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjFalDTO> partialUpdate(BidPrjFalDTO bidPrjFalDTO) {
        log.debug("Request to partially update BidPrjFal : {}", bidPrjFalDTO);

        return bidPrjFalRepository
            .findById(bidPrjFalDTO.getId())
            .map(existingBidPrjFal -> {
                bidPrjFalMapper.partialUpdate(existingBidPrjFal, bidPrjFalDTO);

                return existingBidPrjFal;
            })
            .map(bidPrjFalRepository::save)
            .map(bidPrjFalMapper::toDto);
    }

    /**
     * Get all the bidPrjFals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjFalDTO> findAll() {
        log.debug("Request to get all BidPrjFals");
        return bidPrjFalRepository.findAll().stream().map(bidPrjFalMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjFal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjFalDTO> findOne(BidPrjFalPrimaryKey id) {
        log.debug("Request to get BidPrjFal : {}", id);
        return bidPrjFalRepository.findById(id).map(bidPrjFalMapper::toDto);
    }

    /**
     * Delete the bidPrjFal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjFalPrimaryKey id) {
        log.debug("Request to delete BidPrjFal : {}", id);
        bidPrjFalRepository.deleteById(id);
    }

    public List<BidPrjFalDTO> findPrjMonthAllFal(String wkut, String prjno, String year, String month){
        return bidPrjFalRepository.findPrjMonthAllFal(wkut,prjno,year,month);
    }
}
