package gov.pcc.pwc.service;

import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.BidPrjSafe;
import gov.pcc.pwc.domain.BidPrjSafePrimaryKey;
import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.repository.BidPrjSafeRepository;
import gov.pcc.pwc.service.criteria.BidPrjSafeCriteria;
import gov.pcc.pwc.service.criteria.CmsCompCriteria;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import gov.pcc.pwc.service.dto.CmsCompDTO;
import gov.pcc.pwc.service.mapper.BidPrjSafeMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjSafe}.
 */
@Service
@Transactional
public class BidPrjSafeService {

    private final Logger log = LoggerFactory.getLogger(BidPrjSafeService.class);

    private final BidPrjSafeRepository bidPrjSafeRepository;

    private final BidPrjSafeMapper bidPrjSafeMapper;

    public BidPrjSafeService(BidPrjSafeRepository bidPrjSafeRepository, BidPrjSafeMapper bidPrjSafeMapper) {
        this.bidPrjSafeRepository = bidPrjSafeRepository;
        this.bidPrjSafeMapper = bidPrjSafeMapper;
    }

    /**
     * Save a bidPrjSafe.
     *
     * @param bidPrjSafeDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjSafeDTO save(BidPrjSafeDTO bidPrjSafeDTO) {
        log.debug("Request to save BidPrjSafe : {}", bidPrjSafeDTO);
        BidPrjSafe bidPrjSafe = bidPrjSafeMapper.toEntity(bidPrjSafeDTO);
        bidPrjSafe = bidPrjSafeRepository.save(bidPrjSafe);
        return bidPrjSafeMapper.toDto(bidPrjSafe);
    }

    /**
     * Partially update a bidPrjSafe.
     *
     * @param bidPrjSafeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjSafeDTO> partialUpdate(BidPrjSafeDTO bidPrjSafeDTO) {
        log.debug("Request to partially update BidPrjSafe : {}", bidPrjSafeDTO);

        return bidPrjSafeRepository
            .findById(bidPrjSafeDTO.getId())
            .map(existingBidPrjSafe -> {
                bidPrjSafeMapper.partialUpdate(existingBidPrjSafe, bidPrjSafeDTO);

                return existingBidPrjSafe;
            })
            .map(bidPrjSafeRepository::save)
            .map(bidPrjSafeMapper::toDto);
    }

    /**
     * Get all the bidPrjSafes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjSafeDTO> findAll() {
        log.debug("Request to get all BidPrjSafes");
        return bidPrjSafeRepository.findAll().stream().map(bidPrjSafeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjSafe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjSafeDTO> findOne(BidPrjSafePrimaryKey id) {
        log.debug("Request to get BidPrjSafe : {}", id);
        return bidPrjSafeRepository.findById(id).map(bidPrjSafeMapper::toDto);
    }

    /**
     * Delete the bidPrjSafe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjSafePrimaryKey id) {
        log.debug("Request to delete BidPrjSafe : {}", id);
        bidPrjSafeRepository.deleteById(id);
    }

    /**
     * Find the list of BidPrjSafe by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidPrjSafeDTO.
     */
//    工安事件狀況列表 bid-prj-safe : bid-aad-002-info
    @Transactional(readOnly = true)
    public Page<BidPrjSafeDTO> findAllListByCriteria(BidPrjSafeCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "SDATE" });
        }
        final Page<BidPrjSafeDTO> bidPrjSafes = bidPrjSafeRepository.findAllBidSafeKey(criteria);
        return bidPrjSafes;
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String>  validData(BidPrjSafeDTO bidPrjSafeDTO, String fromStatus){
        List<String> retrunArray = new ArrayList<>();

        BidPrjSafe bidPrjSafe = bidPrjSafeRepository.findById(new BidPrjSafePrimaryKey(bidPrjSafeDTO.getWkut(),bidPrjSafeDTO.getPrjno(),bidPrjSafeDTO.getSdate())).orElse(null);
//      僅新增需要判斷
        if(fromStatus.equals("create")){
            if(bidPrjSafe != null){
                retrunArray.add("發生日期已有工安事件狀況");
            }
        }

//       必填欄位在後端皆要做驗證
        if(bidPrjSafeDTO.getSdate() == null ){
            retrunArray.add("發生日期為必填");
        }
        if(bidPrjSafeDTO.getWkut() == null ){
            retrunArray.add("執行單位為必填");
        }
        if(bidPrjSafeDTO.getPrjno() == null ){
            retrunArray.add("標案編號為必填");
        }
        if(bidPrjSafeDTO.getPrmNo() == null ){
            retrunArray.add("提報文號為必填");
        }


        if(!(bidPrjSafeDTO.getStype().equals("20"))){
            if(bidPrjSafeDTO.getDiedNum().add(bidPrjSafeDTO.getHurtNum()).equals(0)){
                retrunArray.add("傷害+死亡人數不得為0");
            }
        }

        return retrunArray;
    }

    /**
     * 根據需求: PK(sDate 需要能夠被修改)
     * 流程如下: 查詢原資料 ->　刪除原資料　-> 新增新資料
     *要做@Transactional 處理
     * */

    @Transactional(propagation = Propagation.REQUIRES_NEW ,rollbackFor = {Exception.class})
    public BidPrjSafeDTO update(BidPrjSafeDTO bidPrjSafeDTO){
        log.debug("Request to save BidPrjSafe : {}", bidPrjSafeDTO);

        // 查詢原資料
        try{
            BidPrjSafeDTO bidPrjSafeDTO1 = bidPrjSafeRepository.findById(bidPrjSafeDTO.getId()).map(bidPrjSafeMapper::toDto).get();
            bidPrjSafeRepository.deleteById(bidPrjSafeDTO1.getId());
        }
        catch (Exception e) {
            String[] err = new String[]{"資料錯誤"};
            throw new BidValidationFailException("bidValidError", "資料檢核錯誤", err);
        }

        BidPrjSafePrimaryKey bidPrjSafePrimaryKeyNew = new BidPrjSafePrimaryKey(bidPrjSafeDTO.getWkut(),bidPrjSafeDTO.getPrjno(),bidPrjSafeDTO.getSdateNew());
        bidPrjSafeDTO.setId(bidPrjSafePrimaryKeyNew);

        List<String> validResult = this.validData(bidPrjSafeDTO,"modify");
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        BidPrjSafe bidPrjSafe = bidPrjSafeMapper.toEntity(bidPrjSafeDTO);
        bidPrjSafe = bidPrjSafeRepository.save(bidPrjSafe);
        return bidPrjSafeMapper.toDto(bidPrjSafe);
    }

    /**
     *
     * @param criteria wkut和prjno
     * @return  List<BidPrjSafeDTO>
     */
    public List<BidPrjSafeDTO> findPrjSafeDTOListByCriteria(BidPrjSafeCriteria criteria){
        criteria.setSortBy(new String[]{"sdate"});
        criteria.setSortDirection(Sort.Direction.DESC);
        return bidPrjSafeRepository.findAll(criteria,criteria.toPageable().getSort()).stream().map(bidPrjSafeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

}
