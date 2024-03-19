package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.BidPrjPayRepository;
import gov.pcc.pwc.repository.VwBidProjectRepository;
import gov.pcc.pwc.service.criteria.BidPrjPayCriteria;
import gov.pcc.pwc.service.dto.BidPrjPayDTO;
import gov.pcc.pwc.service.mapper.BidPrjPayMapper;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BidPrjPay}.
 */
@Service
@Transactional
public class BidPrjPayService {

    private final Logger log = LoggerFactory.getLogger(BidPrjPayService.class);

    private final BidPrjPayRepository bidPrjPayRepository;

    private final VwBidProjectRepository vwBidProjectRepository;

    private final BidPrjPayMapper bidPrjPayMapper;

    public BidPrjPayService(BidPrjPayRepository bidPrjPayRepository, VwBidProjectRepository vwBidProjectRepository, BidPrjPayMapper bidPrjPayMapper) {
        this.bidPrjPayRepository = bidPrjPayRepository;
        this.vwBidProjectRepository = vwBidProjectRepository;
        this.bidPrjPayMapper = bidPrjPayMapper;
    }
    /**
     * Save a bidPrjPay.
     *
     * @param bidPrjPayDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjPayDTO save(BidPrjPayDTO bidPrjPayDTO) {
        log.debug("Request to save BidPrjPay : {}", bidPrjPayDTO);
        BidPrjPay bidPrjPay = bidPrjPayMapper.toEntity(bidPrjPayDTO);
        bidPrjPay = bidPrjPayRepository.save(bidPrjPay);
        return bidPrjPayMapper.toDto(bidPrjPay);
    }

    /**
     * Partially update a bidPrjPayDTO.
     *
     * @param bidPrjPayDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjPayDTO> partialUpdate(BidPrjPayDTO bidPrjPayDTO) {
        log.debug("Request to partially update BidPrjModify : {}", bidPrjPayDTO);

        return bidPrjPayRepository
                .findById(bidPrjPayDTO.getId())
                .map(existingBidPrjPay -> {
                    bidPrjPayMapper.partialUpdate(existingBidPrjPay, bidPrjPayDTO);

                    return existingBidPrjPay;
                })
                .map(bidPrjPayRepository::save)
                .map(bidPrjPayMapper::toDto);
    }

    /**
     * Get all the bidPrjPays.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjPayDTO> findAll() {
        log.debug("Request to get all BidPrjModifies");
        return bidPrjPayRepository.findAll().stream().map(bidPrjPayMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjPay by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjPayDTO> findOne(BidPrjPayPrimaryKey id) {
        log.debug("Request to get BidPrjPay : {}", id);
        return bidPrjPayRepository.findById(id).map(bidPrjPayMapper::toDto);
    }

    /**
     * Get project all bidPrjPay by project id.
     *
     * @param criteria the id of the project.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjPayDTO> findPages(BidPrjPayCriteria criteria) {
        log.debug("Request to get BidPrjPay by project : {} ", criteria.toString());
        return bidPrjPayRepository.findAll(criteria, criteria.toPageable()).map(bidPrjPayMapper::toDto);
    }

    /**
     * Get project all bidPrjPay by project id.
     *
     * @param criteria the id of the project.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<BidPrjPayDTO> findAll(BidPrjPayCriteria criteria) {
        log.debug("Request to get BidPrjPay by project : {} ", criteria.toString());
        return bidPrjPayRepository
                .findAll(criteria, Sort.by(Sort.Direction.ASC, "monNo"))
                .stream()
                .map(bidPrjPayMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Delete the bidPrjPay by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjPayPrimaryKey id) {
        log.debug("Request to delete BidPrjPay : {}", id);
        bidPrjPayRepository.deleteById(id);
    }

    public int findMaxMonNo(BidPrjPayCriteria criteria) {
        return bidPrjPayRepository.findMaxMonNo(criteria);
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validData(BidPrjPayDTO bidPrjPayDTO){
        List<String> retrunArray = new ArrayList<>();
        VwBidProject comparedProject = vwBidProjectRepository.findById(new BidProjectPrimaryKey(bidPrjPayDTO.getWkut(), bidPrjPayDTO.getPrjno())).orElse(null);
        if (comparedProject == null) {
            retrunArray.add("查無該標案資料");
            return retrunArray;
        }
        // 「契約金額／決標金額」：若「契約金額」＜＞空值則使用「契約金額」，若「契約金額」＝空值則使用「決標金額」。
        BigDecimal ctSum = comparedProject.getCtSumF();
        if (ctSum == null) {
            ctSum = comparedProject.getCtSum();
            if (ctSum == null) {
                retrunArray.add("查無「契約金額／決標金額」");
                return retrunArray;
            }
        }
        // (1) 若「應付總金額」＝空值，警示訊息：請輸入「應付總金額」(ADM-0001-W)
        if (bidPrjPayDTO.getPayMonT() == null) {
            retrunArray.add("請輸入「應付總金額」(ADM-0001-W)");
        }
        // (2) 若「應付總金額」＞NVL「契約金額,決標金額」，警示訊息：「應付總金額」明顯不合理(不應大於契約金額) (BID-0031-W)
        if (bidPrjPayDTO.getPayMonT() != null && bidPrjPayDTO.getPayMonT().compareTo(ctSum) > 0) {
            retrunArray.add("「應付總金額」明顯不合理(不應大於契約金額) (BID-0031-W)");
        }
        // (3) 若「截至上次已付總金額」＝空值，警示訊息：請輸入「截至上次已付總金額」(ADM-0001-W)
        if (bidPrjPayDTO.getPayMonU() == null) {
            retrunArray.add("請輸入「截至上次已付總金額」(ADM-0001-W)");
        }
        // (4) 若「截至上次已付總金額」＞NVL「契約金額,決標金額」，警示訊息：「截至上次已付總金額」明顯不合理(不應大於契約金額) (BID-0032-W)
        if (bidPrjPayDTO.getPayMonU() != null && bidPrjPayDTO.getPayMonU().compareTo(ctSum) > 0) {
            retrunArray.add("「截至上次已付總金額」明顯不合理(不應大於契約金額) (BID-0032-W)");
        }
        // (5) 若「本次請付金額」＜＞空值 且「本次請付日期」＝空值，警示訊息：已填「本次請付金額」但「本次請付日期」未填報 (BID-0033-W)
        if (bidPrjPayDTO.getTrueMon() != null && bidPrjPayDTO.getTrueDate() == null) {
            retrunArray.add("已填「本次請付金額」但「本次請付日期」未填報 (BID-0033-W)");
        }
        // (6) 若「本次請付日期」＜＞空值 且「本次請付金額」＝空值，警示訊息：已填「本次請付日期」但「本次請付金額」未填報 (BID-0034-W)
        if (bidPrjPayDTO.getTrueDate() != null && bidPrjPayDTO.getTrueMon() == null) {
            retrunArray.add("已填「本次請付日期」但「本次請付金額」未填報 (BID-0034-W)");
        }
        // (7) 若「本次請付金額」＞NVL「契約金額,決標金額」，警示訊息：「本次請付金額」明顯不合理(不應大於契約金額) (BID-0035-W)
        if (bidPrjPayDTO.getTrueMon() != null && bidPrjPayDTO.getTrueMon().compareTo(ctSum) > 0) {
            retrunArray.add("「本次請付金額」明顯不合理(不應大於契約金額) (BID-0035-W)");
        }
        // (8) 若「本次廠商請款單據送達機關金額」＝空值，警示訊息：請輸入「本次廠商請款單據送達機關金額」(ADM-0001-W)
        if (bidPrjPayDTO.getTenMon() == null) {
            retrunArray.add("請輸入「本次廠商請款單據送達機關金額」(ADM-0001-W)");
        }
        // (9) 若「本次廠商請款單據送達機關金額」＜＞空值 且「本次廠商請款單據送達機關日期」＝空值，警示訊息：已填「本次廠商請款單據送達機關金額」但「本次廠商請款單據送達機關日期」未填報 (BID-0037-W)
        if (bidPrjPayDTO.getTenMon() != null && bidPrjPayDTO.getTenDate() == null) {
            retrunArray.add("已填「本次廠商請款單據送達機關金額」但「本次廠商請款單據送達機關日期」未填報 (BID-0037-W)");
        }
        // (10) 若「本次實際付款金額」＜＞空值 且「本次實際付款日期」＝空值，警示訊息：已填「本次實際付款金額」但「本次實際付款日期」未填報 (BID-0037-W)
        if (bidPrjPayDTO.getPayMon() != null && bidPrjPayDTO.getPayDate() == null) {
            retrunArray.add("已填「本次實際付款金額」但「本次實際付款日期」未填報 (BID-0037-W)");
        }
        // (11) 若「本次實際付款日期」＜＞空值 且「本次實際付款金額」＝空值，警示訊息：已填「本次實際付款日期」但「本次實際付款金額」未填報 (BID-0038-W)
        if (bidPrjPayDTO.getPayDate() != null && bidPrjPayDTO.getPayMon() == null) {
            retrunArray.add("已填「本次實際付款日期」但「本次實際付款金額」未填報 (BID-0038-W)");
        }
        // (12) 若「本次實際付款日期」＞系統日期，警示訊息：「本次實際付款日期」不可是尚未到達之日期 (BID-0039-W)
        if (LocalDate.now().isBefore(bidPrjPayDTO.getPayDate())) {
            retrunArray.add("「本次實際付款日期」不可是尚未到達之日期 (BID-0039-W)");
        }
        // (13) 若「本次實際付款日期」＜＞空值 且「本次廠商請款單據送達機關日期」＝空值，警示訊息：已填「本次實際付款日期」但「本次廠商請款單據送達機關日期」未填報 (BID-0040-W)
        if (bidPrjPayDTO.getPayDate() != null && bidPrjPayDTO.getTenDate() == null) {
            retrunArray.add("已填「本次實際付款日期」但「本次廠商請款單據送達機關日期」未填報 (BID-0040-W)");
        }
        // (14) 若「本次實際付款金額」＞NVL「契約金額,決標金額」，警示訊息：「本次實際付款金額」明顯不合理(不應大於契約金額) (BID-0060-W)
        if (bidPrjPayDTO.getPayMon() != null && bidPrjPayDTO.getPayMon().compareTo(ctSum) > 0) {
            retrunArray.add("「本次實際付款金額」明顯不合理(不應大於契約金額) (BID-0060-W)");
        }
        // (15) 若「累計已付總金額」＞NVL「契約金額,決標金額」，警示訊息：「累計已付總金額」明顯不合理(不應大於契約金額) (BID-0061-W)
        if (bidPrjPayDTO.getPayMonS() != null && bidPrjPayDTO.getPayMonS().compareTo(ctSum) > 0) {
            retrunArray.add("「累計已付總金額」明顯不合理(不應大於契約金額) (BID-0061-W)");
        }
        // (16) 若「付款項目」＝空值，警示訊息：請輸入「付款項目」(ADM-0001-W)
        if (StringUtils.isBlank(bidPrjPayDTO.getPayRemark())) {
            retrunArray.add("請輸入「付款項目」(ADM-0001-W)");
        }
        return retrunArray;
    }

}
