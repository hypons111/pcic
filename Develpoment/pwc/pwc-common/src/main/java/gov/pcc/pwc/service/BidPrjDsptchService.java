package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjDsptch;
import gov.pcc.pwc.domain.BidPrjDsptchPrimaryKey;
import gov.pcc.pwc.domain.BidPrjStc;
import gov.pcc.pwc.domain.BidPrjStcPrimaryKey;
import gov.pcc.pwc.repository.BidPrjDsptchRepository;
import gov.pcc.pwc.service.criteria.BidPrjDsptchCriteria;
import gov.pcc.pwc.service.criteria.BidPrjPay2Criteria;
import gov.pcc.pwc.service.dto.BidPrjDsptchDTO;
import gov.pcc.pwc.service.dto.BidPrjPay2DTO;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;
import gov.pcc.pwc.service.mapper.BidPrjDsptchMapper;

import java.math.BigDecimal;
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
 * Service Implementation for managing {@link BidPrjDsptch}.
 */
@Service
@Transactional
public class BidPrjDsptchService {

    private final Logger log = LoggerFactory.getLogger(BidPrjDsptchService.class);

    private final BidPrjDsptchRepository bidPrjDsptchRepository;

    private final BidPrjDsptchMapper bidPrjDsptchMapper;

    public BidPrjDsptchService(BidPrjDsptchRepository bidPrjDsptchRepository, BidPrjDsptchMapper bidPrjDsptchMapper) {
        this.bidPrjDsptchRepository = bidPrjDsptchRepository;
        this.bidPrjDsptchMapper = bidPrjDsptchMapper;
    }

    /**
     * Save a bidPrjDsptch.
     *
     * @param bidPrjDsptchDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjDsptchDTO save(BidPrjDsptchDTO bidPrjDsptchDTO) {
        log.debug("Request to save BidPrjDsptch : {}", bidPrjDsptchDTO);
        // 找出最大的DSP_NO，並累加
        BidPrjDsptchCriteria criteria = new BidPrjDsptchCriteria();
        criteria.setWkut(bidPrjDsptchDTO.getWkut());
        criteria.setPrjno(bidPrjDsptchDTO.getPrjno());
        criteria.setSortDirection(Sort.Direction.DESC);
        Page<BidPrjDsptchDTO> page = this.findAllBidPrjDsptchByCriteria(criteria);

        if(page != null && page.getContent().size() > 0){
            List<BidPrjDsptchDTO> listDTO = page.getContent();
            int num = 0;
            for(BidPrjDsptchDTO item : listDTO){
                // 取出最大的NO
                if(Integer.parseInt(item.getDspNo()) > num){
                    num = Integer.parseInt(item.getDspNo());
                }
                String dspNo = 1 + num + "";
                bidPrjDsptchDTO.setDspNo(dspNo);
            }
        }else {
            bidPrjDsptchDTO.setDspNo("1");
        }

        BidPrjDsptch bidPrjDsptch = bidPrjDsptchMapper.toEntity(bidPrjDsptchDTO);
        bidPrjDsptch = bidPrjDsptchRepository.save(bidPrjDsptch);
        return bidPrjDsptchMapper.toDto(bidPrjDsptch);
    }

    /**
     * Partially update a bidPrjDsptch.
     *
     * @param bidPrjDsptchDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjDsptchDTO> partialUpdate(BidPrjDsptchDTO bidPrjDsptchDTO) {
        log.debug("Request to partially update BidPrjDsptch : {}", bidPrjDsptchDTO);

        return bidPrjDsptchRepository
            .findById(bidPrjDsptchDTO.getId())
            .map(existingBidPrjDsptch -> {
                bidPrjDsptchMapper.partialUpdate(existingBidPrjDsptch, bidPrjDsptchDTO);

                return existingBidPrjDsptch;
            })
            .map(bidPrjDsptchRepository::save)
            .map(bidPrjDsptchMapper::toDto);
    }

    /**
     * Get all the bidPrjDsptches.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjDsptchDTO> findAll() {
        log.debug("Request to get all BidPrjDsptches");
        return bidPrjDsptchRepository.findAll().stream().map(bidPrjDsptchMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjDsptch by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjDsptchDTO> findOne(BidPrjDsptchPrimaryKey id) {
        log.debug("Request to get BidPrjDsptch : {}", id);
        return bidPrjDsptchRepository.findById(id).map(bidPrjDsptchMapper::toDto);
    }

    /**
     * Delete the bidPrjDsptch by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjDsptchPrimaryKey id) {
        log.debug("Request to delete BidPrjDsptch : {}", id);
        bidPrjDsptchRepository.deleteById(id);
    }

    /**
     * Get BidPrjDsptch By Criteria
     * */

    public Page<BidPrjDsptchDTO> findAllBidPrjDsptchByCriteria(BidPrjDsptchCriteria criteria){
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "dspNo" });
        }
        final Page<BidPrjDsptchDTO> bidPrjDsptchDTOS = bidPrjDsptchRepository.findAll(criteria,criteria.toPageable()).map(bidPrjDsptchMapper::toDto);
        return bidPrjDsptchDTOS;
    }
    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String>  validData(BidPrjDsptchDTO bidPrjDsptchDTO, String fromStatus){
        List<String> retrunArray = new ArrayList<>();

        BidPrjDsptchPrimaryKey bidPrjDsptchPrimaryKey = new BidPrjDsptchPrimaryKey(bidPrjDsptchDTO.getWkut(),bidPrjDsptchDTO.getPrjno(),bidPrjDsptchDTO.getDspNo());
        BidPrjDsptch bidPrjDsptch = bidPrjDsptchRepository.findById(bidPrjDsptchPrimaryKey).orElse(null);

        if(bidPrjDsptch != null  && fromStatus.equals("create")){
            retrunArray.add("已有資料無法新增");
        }


//       必填欄位在後端皆要做驗證
        if(bidPrjDsptchDTO.getWkut() == null ){
            retrunArray.add("執行單位為必填");
        }
        if(bidPrjDsptchDTO.getPrjno() == null ){
            retrunArray.add("標案編號為必填");
        }
        if(bidPrjDsptchDTO.getDspSdat() == null ){
            retrunArray.add("請輸入「通知日期」(ADM-0001-W)");
        }
        if(bidPrjDsptchDTO.getDspSdat() == null ){
            retrunArray.add("請輸入「通知日期」(ADM-0001-W)");
        }
        if(bidPrjDsptchDTO.getDspSpeed() == null ){
            retrunArray.add("請輸入「速別」(ADM-0001-W) ");
        }

        if(bidPrjDsptchDTO.getDspItem() == null ){
            retrunArray.add("請輸入「施工通知事項」(ADM-0001-W) ");
        }
        if(bidPrjDsptchDTO.getDspPmon() == null ){
            retrunArray.add("請輸入「概估金額」(ADM-0001-W) ");
        }
        if(bidPrjDsptchDTO.getDspPedat() == null ){
            retrunArray.add("請輸入「預定完成日期」(ADM-0001-W) ");
        }

        if(bidPrjDsptchDTO.getDspAmon() == null && bidPrjDsptchDTO.getDspF2dat() != null ){
            retrunArray.add("實作計價金額未填 ");
        }

        if(bidPrjDsptchDTO.getCtSum() != null && bidPrjDsptchDTO.getCtSumF() == null) {
            if (bidPrjDsptchDTO.getDspPmon() != null && bidPrjDsptchDTO.getCtSum() != null) {
                if (bidPrjDsptchDTO.getDspPmon().compareTo(bidPrjDsptchDTO.getCtSum()) == 1) {
                    retrunArray.add("「概估金額」明顯不合理(不應大於契約金額) (BID-00xx-W)");
                }
                if (bidPrjDsptchDTO.getDspPmon().compareTo(bidPrjDsptchDTO.getCtSum().multiply(BigDecimal.valueOf(0.7))) == 1) {
                    retrunArray.add("一張派工單概估金額明顯不合理(不應大於契約金額 * 0.7) (BID-00xx-W)");
                }
            }
        }

        if(bidPrjDsptchDTO.getCtSumF() != null) {
            if (bidPrjDsptchDTO.getDspPmon() != null && bidPrjDsptchDTO.getCtSumF() != null) {
                if (bidPrjDsptchDTO.getDspPmon().compareTo(bidPrjDsptchDTO.getCtSumF()) == 1) {
                    retrunArray.add("「概估金額」明顯不合理(不應大於契約金額) (BID-00xx-W)");
                }
                if (bidPrjDsptchDTO.getDspPmon().compareTo(bidPrjDsptchDTO.getCtSumF().multiply(BigDecimal.valueOf(0.7))) == 1) {
                    retrunArray.add("一張派工單概估金額明顯不合理(不應大於契約金額 * 0.7) (BID-00xx-W)");
                }
            }
        }

        return retrunArray;
    }
}
