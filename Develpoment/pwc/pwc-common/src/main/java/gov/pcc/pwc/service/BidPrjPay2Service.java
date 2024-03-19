package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjPay2;
import gov.pcc.pwc.domain.BidPrjPay2PrimaryKey;
import gov.pcc.pwc.repository.BidPrjPay2Repository;
import gov.pcc.pwc.service.criteria.BidPrjPay2Criteria;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
import gov.pcc.pwc.service.dto.BidPrjPay2DTO;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import gov.pcc.pwc.service.mapper.BidPrjPay2Mapper;

import java.util.ArrayList;
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
 * Service Implementation for managing {@link BidPrjPay2}.
 */
@Service
@Transactional
public class BidPrjPay2Service {

    private final Logger log = LoggerFactory.getLogger(BidPrjPay2Service.class);

    private final BidPrjPay2Repository bidPrjPay2Repository;

    private final BidPrjPay2Mapper bidPrjPay2Mapper;

    public BidPrjPay2Service(BidPrjPay2Repository bidPrjPay2Repository, BidPrjPay2Mapper bidPrjPay2Mapper) {
        this.bidPrjPay2Repository = bidPrjPay2Repository;
        this.bidPrjPay2Mapper = bidPrjPay2Mapper;
    }

    /**
     * Save a bidPrjPay2.
     *
     * @param bidPrjPay2DTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjPay2DTO save(BidPrjPay2DTO bidPrjPay2DTO) {
        log.debug("Request to save BidPrjPay2 : {}", bidPrjPay2DTO);
        BidPrjPay2 bidPrjPay2 = bidPrjPay2Mapper.toEntity(bidPrjPay2DTO);
        bidPrjPay2 = bidPrjPay2Repository.save(bidPrjPay2);
        return bidPrjPay2Mapper.toDto(bidPrjPay2);
    }

    /**
     * Partially update a bidPrjPay2.
     *
     * @param bidPrjPay2DTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjPay2DTO> partialUpdate(BidPrjPay2DTO bidPrjPay2DTO) {
        log.debug("Request to partially update BidPrjPay2 : {}", bidPrjPay2DTO);

        return bidPrjPay2Repository
            .findById(bidPrjPay2DTO.getId())
            .map(existingBidPrjPay2 -> {
                bidPrjPay2Mapper.partialUpdate(existingBidPrjPay2, bidPrjPay2DTO);

                return existingBidPrjPay2;
            })
            .map(bidPrjPay2Repository::save)
            .map(bidPrjPay2Mapper::toDto);
    }

    /**
     * Get all the bidPrjPay2s.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjPay2DTO> findAll() {
        log.debug("Request to get all BidPrjPay2s");
        return bidPrjPay2Repository.findAll().stream().map(bidPrjPay2Mapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjPay2 by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjPay2DTO> findOne(BidPrjPay2PrimaryKey id) {
        log.debug("Request to get BidPrjPay2 : {}", id);
        return bidPrjPay2Repository.findById(id).map(bidPrjPay2Mapper::toDto);
    }

    /**
     * Delete the bidPrjPay2 by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjPay2PrimaryKey id) {
        log.debug("Request to delete BidPrjPay2 : {}", id);
        bidPrjPay2Repository.deleteById(id);
    }

    /**
     * Get BidPrjPay2 By Criteria
     * */
    public Page<BidPrjPay2DTO> findAllBidPrjPay2ByCriteria(BidPrjPay2Criteria criteria){
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "year" });
        }
        final Page<BidPrjPay2DTO> bidPrjPay2DTOS = bidPrjPay2Repository.findBidPrjPay2ByCriteria(criteria);
        return bidPrjPay2DTOS;
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String>  validData(BidPrjPay2DTO bidPrjPay2DTO, String fromStatus){
        List<String> retrunArray = new ArrayList<>();

        BidPrjPay2PrimaryKey bidPrjPay2PrimaryKey = new BidPrjPay2PrimaryKey(bidPrjPay2DTO.getWkut(),bidPrjPay2DTO.getPrjno(),bidPrjPay2DTO.getYear(),bidPrjPay2DTO.getMonth());
        BidPrjPay2 bidPrjPay2 = bidPrjPay2Repository.findById(bidPrjPay2PrimaryKey).orElse(null);

        if(bidPrjPay2 != null  && fromStatus.equals("create")){
                retrunArray.add("已有資料無法新增");
        }


//       必填欄位在後端皆要做驗證
        if(bidPrjPay2DTO.getWkut() == null ){
            retrunArray.add("執行單位為必填");
        }
        if(bidPrjPay2DTO.getPrjno() == null ){
            retrunArray.add("標案編號為必填");
        }

        if(bidPrjPay2DTO.getYear() == null ){
            retrunArray.add("請輸入「請撥年度」(ADM-0001-W)");
        }

        if(bidPrjPay2DTO.getMonth() == null ){
            retrunArray.add("請輸入「請撥月份」(ADM-0001-W)");
        }

        if(bidPrjPay2DTO.getTrueMon() == null ){
            retrunArray.add("請輸入「本次請撥金額」(ADM-0001-W)");
        }

        if(bidPrjPay2DTO.getPayMonU() == null ){
            retrunArray.add("請輸入「截至上次已撥總金額」(ADM-0001-W) ");
        }

        if(bidPrjPay2DTO.getTrueMon() != null && bidPrjPay2DTO.getPayMonT() != null){
            if(bidPrjPay2DTO.getTenMon().compareTo(bidPrjPay2DTO.getPayMonT()) == 1  ){
                retrunArray.add("「本次請撥金額」明顯不合理(大於應撥總金額) (BID-0168-W) ");
            }
        }

        if(bidPrjPay2DTO.getPayMon() != null && bidPrjPay2DTO.getPayDate() == null){
            retrunArray.add("已填「本次實際撥款金額」但「本次實際撥款日期」未填報(BID-00169-W)");
        }

        if(bidPrjPay2DTO.getPayMon() == null && bidPrjPay2DTO.getPayDate() != null){
            retrunArray.add("已填「本次實際撥款日期」但「本次實際撥款金額」未填報(BID-0170-W) ");
        }

        return retrunArray;
    }


}
