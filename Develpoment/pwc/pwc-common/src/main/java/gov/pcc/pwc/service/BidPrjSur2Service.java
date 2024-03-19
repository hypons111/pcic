package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjPay2;
import gov.pcc.pwc.domain.BidPrjPay2PrimaryKey;
import gov.pcc.pwc.domain.BidPrjSur2;
import gov.pcc.pwc.domain.BidPrjSur2PrimaryKey;
import gov.pcc.pwc.repository.BidPrjSur2Repository;
import gov.pcc.pwc.service.criteria.BidPrjSur2Criteria;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import gov.pcc.pwc.service.dto.BidPrjSur2DTO;
import gov.pcc.pwc.service.mapper.BidPrjSur2Mapper;

import java.math.BigDecimal;
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
 * Service Implementation for managing {@link BidPrjSur2}.
 */
@Service
@Transactional
public class BidPrjSur2Service {

    private final Logger log = LoggerFactory.getLogger(BidPrjSur2Service.class);

    private final BidPrjSur2Repository bidPrjSur2Repository;

    private final BidPrjSur2Mapper bidPrjSur2Mapper;

    public BidPrjSur2Service(BidPrjSur2Repository bidPrjSur2Repository, BidPrjSur2Mapper bidPrjSur2Mapper) {
        this.bidPrjSur2Repository = bidPrjSur2Repository;
        this.bidPrjSur2Mapper = bidPrjSur2Mapper;
    }

    /**
     * Save a bidPrjSur2.
     *
     * @param bidPrjSur2DTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjSur2DTO save(BidPrjSur2DTO bidPrjSur2DTO) {
        log.debug("Request to save BidPrjSur2 : {}", bidPrjSur2DTO);
        BigDecimal amnta = bidPrjSur2DTO.getAmnta();
        BigDecimal amntb = bidPrjSur2DTO.getAmntb();
        BigDecimal amnt = amnta.add(amntb);
        bidPrjSur2DTO.setAmnt(amnt);

        BidPrjSur2 bidPrjSur2 = bidPrjSur2Mapper.toEntity(bidPrjSur2DTO);
        bidPrjSur2 = bidPrjSur2Repository.save(bidPrjSur2);
        return bidPrjSur2Mapper.toDto(bidPrjSur2);
    }

    /**
     * Partially update a bidPrjSur2.
     *
     * @param bidPrjSur2DTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjSur2DTO> partialUpdate(BidPrjSur2DTO bidPrjSur2DTO) {
        log.debug("Request to partially update BidPrjSur2 : {}", bidPrjSur2DTO);

        return bidPrjSur2Repository
            .findById(bidPrjSur2DTO.getId())
            .map(existingBidPrjSur2 -> {
                bidPrjSur2Mapper.partialUpdate(existingBidPrjSur2, bidPrjSur2DTO);

                return existingBidPrjSur2;
            })
            .map(bidPrjSur2Repository::save)
            .map(bidPrjSur2Mapper::toDto);
    }

    /**
     * Get all the bidPrjSur2s.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjSur2DTO> findAll() {
        log.debug("Request to get all BidPrjSur2s");
        return bidPrjSur2Repository.findAll().stream().map(bidPrjSur2Mapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjSur2 by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjSur2DTO> findOne(BidPrjSur2PrimaryKey id) {
        log.debug("Request to get BidPrjSur2 : {}", id);
        return bidPrjSur2Repository.findById(id).map(bidPrjSur2Mapper::toDto);
    }

    /**
     * Delete the bidPrjSur2 by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjSur2PrimaryKey id) {
        log.debug("Request to delete BidPrjSur2 : {}", id);
        bidPrjSur2Repository.deleteById(id);
    }

    /**
     * Find bidPrjSur2DTO by Criteria
     * */
    public Page<BidPrjSur2DTO> findAllByCriteria(BidPrjSur2Criteria bidPrjSur2Criteria){
        if (bidPrjSur2Criteria.getSortBy() != null && bidPrjSur2Criteria.getSortBy().length == 0) {
            bidPrjSur2Criteria.setSortBy(new String[] { "createDate" });
        }
        final Page<BidPrjSur2DTO> bidPrjSur2s = bidPrjSur2Repository.findByCriteria(bidPrjSur2Criteria);
        return bidPrjSur2s;
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String>  validData(BidPrjSur2DTO bidPrjSur2DTO, String fromStatus){
        List<String> retrunArray = new ArrayList<>();

        BidPrjSur2PrimaryKey bidPrjSur2PrimaryKey = new BidPrjSur2PrimaryKey(bidPrjSur2DTO.getWkut(),bidPrjSur2DTO.getPrjno(),bidPrjSur2DTO.getDisNo());
        BidPrjSur2 bidPrjSur2 = bidPrjSur2Repository.findById(bidPrjSur2PrimaryKey).orElse(null);

        if(bidPrjSur2 != null  && fromStatus.equals("create")){
            retrunArray.add("已有資料無法新增");
        }

        //       必填欄位在後端皆要做驗證
        if(bidPrjSur2DTO.getWkut() == null){
            retrunArray.add("執行單位為必填");
        }

        if(bidPrjSur2DTO.getPrjno() == null){
            retrunArray.add("標案編號為必填");
        }

        if(bidPrjSur2DTO.getDisNo() == null){
            retrunArray.add("請輸入「預算編號」(ADM-0001-W)。");
        }

        if(bidPrjSur2DTO.getAmnta() == null){
            retrunArray.add("請輸入「使用地方災害準備金數」(ADM-0001-W)");
        }
        if(bidPrjSur2DTO.getAmntb() == null){
            retrunArray.add("請輸入「使用中央補助款數」(ADM-0001-W)");
        }




        return retrunArray;
    }

}
