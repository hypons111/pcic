package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.BidPrjSubcoRepository;
import gov.pcc.pwc.service.criteria.BidPrjSubcoCriteria;
import gov.pcc.pwc.service.criteria.BidProjectUnitCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.mapper.BidPrjSubcoMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjSubco}.
 */
@Service
@Transactional
public class BidPrjSubcoService {

    private final Logger log = LoggerFactory.getLogger(BidPrjSubcoService.class);

    private final BidPrjSubcoRepository bidPrjSubcoRepository;

    private final BidPrjSubcoMapper bidPrjSubcoMapper;

    public BidPrjSubcoService(
            BidPrjSubcoRepository bidPrjSubcoRepository,
            BidPrjSubcoMapper bidPrjSubcoMapper
    ) {
        this.bidPrjSubcoRepository = bidPrjSubcoRepository;
        this.bidPrjSubcoMapper = bidPrjSubcoMapper;
    }

    /**
     * Save a bidPrjSubco.
     *
     * @param bidPrjSubcoDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjSubcoDTO save(BidPrjSubcoDTO bidPrjSubcoDTO) {
        log.debug("Request to save BidPrjSubco : {}", bidPrjSubcoDTO);
        BidPrjSubco bidPrjSubco = bidPrjSubcoMapper.toEntity(bidPrjSubcoDTO);
        bidPrjSubco = bidPrjSubcoRepository.save(bidPrjSubco);
        return bidPrjSubcoMapper.toDto(bidPrjSubco);
    }

    /**
     * Partially update a bidPrjSubco.
     *
     * @param bidPrjSubcoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjSubcoDTO> partialUpdate(BidPrjSubcoDTO bidPrjSubcoDTO) {
        log.debug("Request to partially update BidPrjSubco : {}", bidPrjSubcoDTO);

        return bidPrjSubcoRepository
                .findById(bidPrjSubcoDTO.getId())
                .map(existingBidPrjSubco -> {
                    bidPrjSubcoMapper.partialUpdate(existingBidPrjSubco, bidPrjSubcoDTO);

                    return existingBidPrjSubco;
                })
                .map(bidPrjSubcoRepository::save)
                .map(bidPrjSubcoMapper::toDto);
    }

    /**
     * Get all the bidPrjSubcos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjSubcoDTO> findAll() {
        log.debug("Request to get all BidPrjSubcos");
        return bidPrjSubcoRepository
                .findAll()
                .stream()
                .map(bidPrjSubcoMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjSubco by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjSubcoDTO> findOne(BidPrjSubcoPrimaryKey id) {
        log.debug("Request to get BidPrjSubco : {}", id);
        return bidPrjSubcoRepository.findById(id).map(bidPrjSubcoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<BidPrjSubco> findByWkutAndPrjnoAndSubcoNo(String wkut,String prjno,String subcoNo) {
        log.debug("Request to get wkut : {}", wkut);
        log.debug("Request to get prjno : {}", prjno);
        return bidPrjSubcoRepository.findByWkutAndPrjnoAndSubcoNo(wkut,prjno,subcoNo);
    }

    /**
     * Delete the bidPrjSubco by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjSubcoPrimaryKey id) {
        log.debug("Request to delete BidPrjSubco : {}", id);
        bidPrjSubcoRepository.deleteById(id);
    }

    /**
     * find BidPrjQc by wkut and prjno
     **/
    public List<BidPrjSubcoDTO> findPrjSubcosList(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        log.debug("Request to get all bidPrjStopwkPrimaryKey");
        return bidPrjSubcoRepository.findBidPrjSubco(bidPrjSubcoPrimaryKey);
    }

    public List<BidPrjSubcoDTO> gatAllMet(BidProjectPrimaryKey bidProjectPrimaryKey) {
        log.debug("Request to get all bidPrjStopwkPrimaryKey");
        return bidPrjSubcoRepository.gatAllMet(bidProjectPrimaryKey);
    }

    public List<BidProjectDTO> findProjectList(BidProjectDTO bidProjectDTO) {
        log.debug("Request to get all bidProjectDTO");
        return bidPrjSubcoRepository.findBidProject(bidProjectDTO);
    }

    public List<BidProjectUnitCriteria> findBidCcntNamesList(String wkut, String prjno) {
        log.debug("Request to get all String wkut, String prjno");
        return bidPrjSubcoRepository.findBidCcntNames(wkut, prjno);
    }

    public List<BidPrjSubcoDTO> bidProjectAndProjectUnitList(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        log.debug("Request to get all bidPrjStopwkPrimaryKey");
        return bidPrjSubcoRepository.findBidProjectAndProjectUnit(bidPrjSubcoPrimaryKey);
    }

    public List<BidProjectDTO> gatAllProject(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        log.debug("Request to get all bidPrjStopwkPrimaryKey");
        return bidPrjSubcoRepository.gatAllProject(bidPrjSubcoPrimaryKey);
    }

     public int findBidPrjSubcoCountList(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        log.debug("Request to get all bidPrjStopwkPrimaryKey");
        return bidPrjSubcoRepository.findBidPrjSubcoCount(bidPrjSubcoPrimaryKey);
    }

    public List<BidPrjSubcoDTO> insertBidPrjSubcoList(BidPrjSubcoDTO bidPrjSubcoDTO) {
        log.debug("Request to get all bidPrjSubcoDTO");
        return bidPrjSubcoRepository.insertBidPrjSubco(bidPrjSubcoDTO);
    }


    /**
     * Get project all bidPrjModify by project id.
     *
     * @param criteria the id of the project.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjSubcoDTO> findProjectAllSubco(BidPrjSubcoCriteria criteria) {
        log.debug("Request to get BidPrjModify by project : {} ", criteria.toString());
        return bidPrjSubcoRepository.findAll(criteria, criteria.toPageable()).map(bidPrjSubcoMapper::toDto);
    }

}
