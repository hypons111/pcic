package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjQc;
import gov.pcc.pwc.domain.BidPrjQcPrimaryKey;
import gov.pcc.pwc.repository.BidPrjQcRepository;
import gov.pcc.pwc.service.dto.BidPrjQcDTO;
import gov.pcc.pwc.service.mapper.BidPrjQcMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjQc}.
 */
@Service
@Transactional
public class BidPrjQcService {

    private final Logger log = LoggerFactory.getLogger(BidPrjQcService.class);

    private final BidPrjQcRepository bidPrjQcRepository;

    private final BidPrjQcMapper bidPrjQcMapper;

    public BidPrjQcService(BidPrjQcRepository bidPrjQcRepository, BidPrjQcMapper bidPrjQcMapper) {
        this.bidPrjQcRepository = bidPrjQcRepository;
        this.bidPrjQcMapper = bidPrjQcMapper;
    }

    /**
     * Save a bidPrjQc.
     *
     * @param bidPrjQcDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjQcDTO save(BidPrjQcDTO bidPrjQcDTO) {
        log.debug("Request to save BidPrjQc : {}", bidPrjQcDTO);
        BidPrjQc bidPrjQc = bidPrjQcMapper.toEntity(bidPrjQcDTO);
        bidPrjQc = bidPrjQcRepository.save(bidPrjQc);
        return bidPrjQcMapper.toDto(bidPrjQc);
    }

    /**
     * Partially update a bidPrjQc.
     *
     * @param bidPrjQcDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjQcDTO> partialUpdate(BidPrjQcDTO bidPrjQcDTO) {
        log.debug("Request to partially update BidPrjQc : {}", bidPrjQcDTO);

        return bidPrjQcRepository
            .findById(bidPrjQcDTO.getPrjQcKey())
            .map(existingBidPrjQc -> {
                bidPrjQcMapper.partialUpdate(existingBidPrjQc, bidPrjQcDTO);

                return existingBidPrjQc;
            })
            .map(bidPrjQcRepository::save)
            .map(bidPrjQcMapper::toDto);
    }

    /**
     * Get all the bidPrjQcs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjQcDTO> findAll() {
        log.debug("Request to get all BidPrjQcs");
        return bidPrjQcRepository.findAll().stream().map(bidPrjQcMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjQc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjQcDTO> findOne(BidPrjQcPrimaryKey id) {
        log.debug("Request to get BidPrjQc : {}", id);
        return bidPrjQcRepository.findById(id).map(bidPrjQcMapper::toDto);
    }

    /**
     * Delete the bidPrjQc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjQcPrimaryKey id) {
        log.debug("Request to delete BidPrjQc : {}", id);
        bidPrjQcRepository.deleteById(id);
    }

    /**
     *find BidPrjQc by wkut and prjno
     **/
    public List<BidPrjQcDTO> findPrjQcList(BidPrjQcDTO bidPrjQcDTO) {
        log.debug("Request to get all BidPrjQcs");
        return bidPrjQcRepository.findBidPrjQc(bidPrjQcDTO);
    }

}
