package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjRatecf;
import gov.pcc.pwc.domain.BidPrjRatecfPrimaryKey;
import gov.pcc.pwc.domain.BidPrjWksorc;
import gov.pcc.pwc.repository.BidPrjRatecfRepository;
import gov.pcc.pwc.service.criteria.BidPrjRatecfCriteria;
import gov.pcc.pwc.service.criteria.BidPrjWksorcCriteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import gov.pcc.pwc.service.dto.BidPrjRatecfDTO;
import gov.pcc.pwc.service.dto.BidPrjWksorcDTO;
import gov.pcc.pwc.service.mapper.BidPrjRatecfMapper;
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
 * Service Implementation for managing {@link BidPrjRatecf}.
 */
@Service
@Transactional
public class BidPrjRatecfService {

    private final Logger log = LoggerFactory.getLogger(BidPrjRatecfService.class);

    private final BidPrjRatecfRepository bidPrjRatecfRepository;

    private final BidPrjRatecfMapper bidPrjRatecfMapper;

    public BidPrjRatecfService(BidPrjRatecfRepository bidPrjRatecfRepository, BidPrjRatecfMapper bidPrjRatecfMapper) {
        this.bidPrjRatecfRepository = bidPrjRatecfRepository;
        this.bidPrjRatecfMapper = bidPrjRatecfMapper;
    }

    /**
     * Save a bidPrjRatecf.
     *
     * @param bidPrjRatecfDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjRatecfDTO save(BidPrjRatecfDTO bidPrjRatecfDTO) {
        log.debug("Request to save BidPrjRatecf : {}", bidPrjRatecfDTO);
        BidPrjRatecf bidPrjRatecf = bidPrjRatecfMapper.toEntity(bidPrjRatecfDTO);
        bidPrjRatecf = bidPrjRatecfRepository.save(bidPrjRatecf);
        return bidPrjRatecfMapper.toDto(bidPrjRatecf);
    }

    /**
     * Partially update a bidPrjRatecf.
     *
     * @param bidPrjRatecfDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjRatecfDTO> partialUpdate(BidPrjRatecfDTO bidPrjRatecfDTO) {
        log.debug("Request to partially update BidPrjRatecf : {}", bidPrjRatecfDTO);

        return bidPrjRatecfRepository
            .findById(bidPrjRatecfDTO.getId())
            .map(existingBidPrjRatecf -> {
                bidPrjRatecfMapper.partialUpdate(existingBidPrjRatecf, bidPrjRatecfDTO);

                return existingBidPrjRatecf;
            })
            .map(bidPrjRatecfRepository::save)
            .map(bidPrjRatecfMapper::toDto);
    }

    /**
     * Get all the bidPrjRatecfs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjRatecfDTO> findAll() {
        log.debug("Request to get all BidPrjRatecfs");
        return bidPrjRatecfRepository.findAll().stream().map(bidPrjRatecfMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjRatecf by id.
     *
     * @param bidPrjRatecfPrimaryKey the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjRatecfDTO> findOne(BidPrjRatecfPrimaryKey bidPrjRatecfPrimaryKey) {
        log.debug("Request to get BidPrjRatecf : {}", bidPrjRatecfPrimaryKey);
        return bidPrjRatecfRepository.findById(bidPrjRatecfPrimaryKey).map(bidPrjRatecfMapper::toDto);
    }

    /**
     * Delete the bidPrjRatecf by id.
     *
     * @param bidPrjRatecfPrimaryKey the id of the entity.
     */
    public void delete(BidPrjRatecfPrimaryKey bidPrjRatecfPrimaryKey) {
        log.debug("Request to delete BidPrjRatecf : {}", bidPrjRatecfPrimaryKey);
        bidPrjRatecfRepository.deleteById(bidPrjRatecfPrimaryKey);
    }



    /**
     * Find the list of BidPrjSegmsetDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidPrjSegmsetDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjRatecfDTO> findByCriteria(BidPrjRatecfCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "wkut" });
        }
        final Page<BidPrjRatecfDTO> bidPrjRatecfs = bidPrjRatecfRepository.findName(criteria);
        return bidPrjRatecfs;
    }

    public BidPrjRatecfDTO findByPk(String wkut, String prjno){
        return bidPrjRatecfRepository.findByPk(wkut,prjno);
    }


    public List<AdmAgentDTO>  findOrgId(String userId){
      return bidPrjRatecfRepository.findOrgId(userId);
    };



}
