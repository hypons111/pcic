package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.domain.BidPrjEnviPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.domain.VwBidProject;
import gov.pcc.pwc.repository.BidPrjEnviRepository;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.dto.BidCcutDTO;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import gov.pcc.pwc.service.mapper.BidPrjEnviMapper;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjEnvi}.
 */
@Service
@Transactional
public class BidPrjEnviService {

    private final Logger log = LoggerFactory.getLogger(BidPrjEnviService.class);

    private final BidPrjEnviRepository bidPrjEnviRepository;

    private final BidPrjEnviMapper bidPrjEnviMapper;

    public BidPrjEnviService(BidPrjEnviRepository bidPrjEnviRepository, BidPrjEnviMapper bidPrjEnviMapper) {
        this.bidPrjEnviRepository = bidPrjEnviRepository;
        this.bidPrjEnviMapper = bidPrjEnviMapper;
    }

    /**
     * Save a bidPrjEnvi.
     *
     * @param bidPrjEnviDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjEnviDTO save(BidPrjEnviDTO bidPrjEnviDTO) {
        validData(bidPrjEnviDTO);

            log.debug("Request to save BidPrjEnvi : {}", bidPrjEnviDTO);
        BidPrjEnvi bidPrjEnvi = bidPrjEnviMapper.toEntity(bidPrjEnviDTO);
        bidPrjEnvi = bidPrjEnviRepository.save(bidPrjEnvi);
        return bidPrjEnviMapper.toDto(bidPrjEnvi);
    }

    /**
     * Partially update a bidPrjEnvi.
     *
     * @param bidPrjEnviDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjEnviDTO> partialUpdate(BidPrjEnviDTO bidPrjEnviDTO) {
        log.debug("Request to partially update BidPrjEnvi : {}", bidPrjEnviDTO);

        return bidPrjEnviRepository
            .findById(bidPrjEnviDTO.getId())
            .map(existingBidPrjEnvi -> {
                bidPrjEnviMapper.partialUpdate(existingBidPrjEnvi, bidPrjEnviDTO);

                return existingBidPrjEnvi;
            })
            .map(bidPrjEnviRepository::save)
            .map(bidPrjEnviMapper::toDto);
    }

    /**
     * Get all the bidPrjEnvis.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjEnviDTO> findAll() {
        log.debug("Request to get all BidPrjEnvis");
        return bidPrjEnviRepository.findAll().stream().map(bidPrjEnviMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjEnvi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjEnviDTO> findOne(BidPrjEnviPrimaryKey id) {
        log.debug("Request to get BidPrjEnvi : {}", id);
        return bidPrjEnviRepository.findById(id).map(bidPrjEnviMapper::toDto);
    }

    /**
     * Delete the bidPrjEnvi by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjEnviPrimaryKey id) {
        log.debug("Request to delete BidPrjEnvi : {}", id);
        bidPrjEnviRepository.deleteById(id);
    }


    public List<BidPrjEnviDTO> findByPk(String wkut, String prjno){
        return bidPrjEnviRepository.findByPk(wkut,prjno);
    }


    /**
     * Find the list of BidPrjEnviDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidPrjEnviDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjEnviDTO> findByCriteria(BidPrjEnviCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "wkut" });
        }
        final Page<BidPrjEnvi> bidPrjEnvis = bidPrjEnviRepository.findAll(criteria, criteria.toPageable());
        return bidPrjEnvis.map(bidPrjEnviMapper::toDto);
    }



        //impl
//    /**
//     * Find the list of BidPrjEnviDTO by criteria.
//     *
//     * @param criteria the criteria.
//     * @return the list of BidPrjEnviDTO.
//     */
//    @Transactional(readOnly = true)
//    public Page<BidPrjEnviDTO> findByCriteria(BidPrjEnviCriteria criteria) {
//        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
//            criteria.setSortBy(new String[] { "wkut" });
//        }
//        final Page<BidPrjEnviDTO> bidPrjEnvi = bidPrjEnviRepository.findAll(criteria);
//        return bidPrjEnvi;
//    }


    public List<BidCcutDTO> findctUnitByPk(BidPrjEnviCriteria criteria){
        return bidPrjEnviRepository.findctUnitByPk(criteria);
    }



    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validData(BidPrjEnviDTO bidPrjEnviDTO){
        List<String> retrunArray = new ArrayList<>();
        BidPrjEnvi comparedProject = bidPrjEnviRepository.findById(new BidPrjEnviPrimaryKey(bidPrjEnviDTO.getWkut(), bidPrjEnviDTO.getPrjno(), bidPrjEnviDTO.getSdate())).orElse(null);
        if(comparedProject != null){
            retrunArray.add("裁處日期已有環保裁罰狀況");
        }
        return retrunArray;
    }

    public List<BidPrjEnviDTO> findDTOListByCriteria(BidPrjEnviCriteria criteria){
        criteria.setSortBy(new String[]{"sdate"});
        criteria.setSortDirection(Sort.Direction.DESC);
        return bidPrjEnviRepository.findAll(criteria, criteria.toPageable().getSort()).stream().map(bidPrjEnviMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }


}
