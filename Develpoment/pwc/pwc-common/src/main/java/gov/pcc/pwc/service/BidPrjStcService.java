package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjPay2;
import gov.pcc.pwc.domain.BidPrjPay2PrimaryKey;
import gov.pcc.pwc.domain.BidPrjStc;
import gov.pcc.pwc.domain.BidPrjStcPrimaryKey;
import gov.pcc.pwc.repository.BidPrjStcRepository;
import gov.pcc.pwc.service.criteria.BidPrjStcCriteria;
import gov.pcc.pwc.service.dto.BidPrjPay2DTO;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;
import gov.pcc.pwc.service.mapper.BidPrjStcMapper;

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
 * Service Implementation for managing {@link BidPrjStc}.
 */
@Service
@Transactional
public class BidPrjStcService {

    private final Logger log = LoggerFactory.getLogger(BidPrjStcService.class);

    private final BidPrjStcRepository bidPrjStcRepository;

    private final BidPrjStcMapper bidPrjStcMapper;

    public BidPrjStcService(BidPrjStcRepository bidPrjStcRepository, BidPrjStcMapper bidPrjStcMapper) {
        this.bidPrjStcRepository = bidPrjStcRepository;
        this.bidPrjStcMapper = bidPrjStcMapper;
    }

    /**
     * Save a bidPrjStc.
     *
     * @param bidPrjStcDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjStcDTO save(BidPrjStcDTO bidPrjStcDTO) {
        log.debug("Request to save BidPrjStc : {}", bidPrjStcDTO);
        BidPrjStc bidPrjStc = bidPrjStcMapper.toEntity(bidPrjStcDTO);
        bidPrjStc = bidPrjStcRepository.save(bidPrjStc);
        return bidPrjStcMapper.toDto(bidPrjStc);
    }

    /**
     * Partially update a bidPrjStc.
     *
     * @param bidPrjStcDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjStcDTO> partialUpdate(BidPrjStcDTO bidPrjStcDTO) {
        log.debug("Request to partially update BidPrjStc : {}", bidPrjStcDTO);

        return bidPrjStcRepository
            .findById(bidPrjStcDTO.getId())
            .map(existingBidPrjStc -> {
                bidPrjStcMapper.partialUpdate(existingBidPrjStc, bidPrjStcDTO);

                return existingBidPrjStc;
            })
            .map(bidPrjStcRepository::save)
            .map(bidPrjStcMapper::toDto);
    }

    /**
     * Get all the bidPrjStcs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjStcDTO> findAll() {
        log.debug("Request to get all BidPrjStcs");
        return bidPrjStcRepository.findAll().stream().map(bidPrjStcMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjStc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjStcDTO> findOne(BidPrjStcPrimaryKey id) {
        log.debug("Request to get BidPrjStc : {}", id);
        return bidPrjStcRepository.findById(id).map(bidPrjStcMapper::toDto);
    }

    /**
     * Delete the bidPrjStc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjStcPrimaryKey id) {
        log.debug("Request to delete BidPrjStc : {}", id);
        bidPrjStcRepository.deleteById(id);
    }

    /**
     * find BidPrjStcDTO By Criteria
     * */
    public Page<BidPrjStcDTO> findBidPrjStcDTOByCriteria(BidPrjStcCriteria criteria){
        log.debug("criteria to get BidPrjStcDTO List : {}", criteria);
        return bidPrjStcRepository.findAll(criteria,criteria.toPageable()).map(bidPrjStcMapper::toDto);
    }

    public List<BidPrjStcDTO> findTableDTOByCriteria(BidPrjStcCriteria criteria){
        log.debug("criteria to get BidPrjStcDTO List : {}", criteria);
        return bidPrjStcRepository.findBidAae013DTOByCriteria(criteria);
    }
    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String>  validData(BidPrjStcDTO bidPrjStcDTO, String fromStatus){
        List<String> retrunArray = new ArrayList<>();

        BidPrjStcPrimaryKey bidPrjStcPrimaryKey = new BidPrjStcPrimaryKey(bidPrjStcDTO.getWkut(),bidPrjStcDTO.getPrjno(),bidPrjStcDTO.getCheckDate());
        BidPrjStc bidPrjStc = bidPrjStcRepository.findById(bidPrjStcPrimaryKey).orElse(null);

        if(bidPrjStc != null  && fromStatus.equals("create")){
            retrunArray.add("已有資料無法新增");
        }


//       必填欄位在後端皆要做驗證
        if(bidPrjStcDTO.getWkut() == null ){
            retrunArray.add("執行單位為必填");
        }
        if(bidPrjStcDTO.getPrjno() == null ){
            retrunArray.add("標案編號為必填");
        }

        if(bidPrjStcDTO.getCheckDate() == null ){
            retrunArray.add("請輸入「檢核日期」(ADM-0001-W)");
        }

        if(bidPrjStcDTO.getAdmFileId() == null ){
            retrunArray.add("請選取檔案並上傳(BID-0009-W)");
        }

        if (bidPrjStcDTO.getYndA01() == null){
            retrunArray.add("項目1「檢核結果」未填報(BID-0010-W)");
        }else {
            if (bidPrjStcDTO.getYndA01().equals("N") && bidPrjStcDTO.getResonA01() == null) {
                retrunArray.add("項目1等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA01().equals("N") && bidPrjStcDTO.getDateA01() == null){
                retrunArray.add("項目1等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA02() == null){
            retrunArray.add("項目2「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA02().equals("N") && bidPrjStcDTO.getResonA02() == null){
                retrunArray.add("項目2等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA02().equals("N") && bidPrjStcDTO.getDateA02() == null){
                retrunArray.add("項目2等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA03() == null){
            retrunArray.add("項目3「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA03().equals("N") && bidPrjStcDTO.getResonA03() == null){
                retrunArray.add("項目3等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA03().equals("N") && bidPrjStcDTO.getDateA03() == null){
                retrunArray.add("項目3等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA04() == null){
            retrunArray.add("項目4「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA04().equals("N") && bidPrjStcDTO.getResonA04() == null){
                retrunArray.add("項目4等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA04().equals("N") && bidPrjStcDTO.getDateA04() == null){
                retrunArray.add("項目4等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }

        }

        if (bidPrjStcDTO.getYndA05() == null){
            retrunArray.add("項目5「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA05().equals("N") && bidPrjStcDTO.getResonA05() == null){
                retrunArray.add("項目5等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA05().equals("N") && bidPrjStcDTO.getDateA05() == null){
                retrunArray.add("項目5等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA06() == null){
            retrunArray.add("項目6「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA06().equals("N") && bidPrjStcDTO.getResonA06() == null){
                retrunArray.add("項目6等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA06().equals("N") && bidPrjStcDTO.getDateA06() == null){
                retrunArray.add("項目6等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA07() == null){
            retrunArray.add("項目7「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA07().equals("N") && bidPrjStcDTO.getResonA07() == null){
                retrunArray.add("項目7等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA07().equals("N") && bidPrjStcDTO.getDateA07() == null){
                retrunArray.add("項目7等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA08() == null){
            retrunArray.add("項目8「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA08().equals("N") && bidPrjStcDTO.getResonA08() == null){
                retrunArray.add("項目8等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA08().equals("N") && bidPrjStcDTO.getDateA08() == null){
                retrunArray.add("項目8等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA09() == null){
            retrunArray.add("項目9「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA09().equals("N") && bidPrjStcDTO.getResonA09() == null){
                retrunArray.add("項目9等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA09().equals("N") && bidPrjStcDTO.getDateA09() == null){
                retrunArray.add("項目9等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA10() == null){
            retrunArray.add("項目10「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA10().equals("N") && bidPrjStcDTO.getResonA10() == null){
                retrunArray.add("項目10等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA10().equals("N") && bidPrjStcDTO.getDateA10() == null){
                retrunArray.add("項目10等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA11() == null){
            retrunArray.add("項目11「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA11().equals("N") && bidPrjStcDTO.getResonA11() == null){
                retrunArray.add("項目11等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA11().equals("N") && bidPrjStcDTO.getDateA11() == null){
                retrunArray.add("項目11等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA12() == null){
            retrunArray.add("項目12「檢核結果」未填報(BID-0010-W)");
        }else{
            if(bidPrjStcDTO.getYndA12().equals("N") && bidPrjStcDTO.getResonA12() == null){
                retrunArray.add("項目12等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA12().equals("N") && bidPrjStcDTO.getDateA12() == null){
                retrunArray.add("項目12等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA13() == null){
            retrunArray.add("項目13「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA13().equals("N") && bidPrjStcDTO.getResonA13() == null){
                retrunArray.add("項目13等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA13().equals("N") && bidPrjStcDTO.getDateA13() == null){
                retrunArray.add("項目13等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA14() == null){
            retrunArray.add("項目14「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA14().equals("N") && bidPrjStcDTO.getResonA14() == null){
                retrunArray.add("項目14等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA14().equals("N") && bidPrjStcDTO.getDateA14() == null){
                retrunArray.add("項目14等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }

        if (bidPrjStcDTO.getYndA15() == null){
            retrunArray.add("項目15「檢核結果」未填報(BID-0010-W)");
        }else {
            if(bidPrjStcDTO.getYndA15().equals("N") && bidPrjStcDTO.getResonA15() == null){
                retrunArray.add("項目15等於「未完成」，但「檢討結果及原因備案」未填報(BID-0011-W)");
            }
            if(bidPrjStcDTO.getYndA15().equals("N") && bidPrjStcDTO.getDateA15() == null){
                retrunArray.add("項目15等於「未完成」，但「預定完成日期」未填報(BID-0012-W)");
            }
        }


        return retrunArray;
    }
}
