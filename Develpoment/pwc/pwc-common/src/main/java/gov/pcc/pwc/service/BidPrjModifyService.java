package gov.pcc.pwc.service;

import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.BidPrjModifyRepository;
import gov.pcc.pwc.repository.VwBidProjectRepository;
import gov.pcc.pwc.service.criteria.BidPrjModifyCriteria;
import gov.pcc.pwc.service.dto.BidPrjDeleteDTO;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.mapper.BidPrjModifyMapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Service Implementation for managing {@link BidPrjModify}.
 */
@Service
@Transactional
public class BidPrjModifyService {

    private final Logger log = LoggerFactory.getLogger(BidPrjModifyService.class);

    private final BidPrjModifyRepository bidPrjModifyRepository;

    private final VwBidProjectRepository vwBidProjectRepository;

    private final BidPrjModifyMapper bidPrjModifyMapper;

    private final BidProjectService bidProjectService;

    public BidPrjModifyService(BidPrjModifyRepository bidPrjModifyRepository, VwBidProjectRepository vwBidProjectRepository, BidPrjModifyMapper bidPrjModifyMapper, BidProjectService bidProjectService) {
        this.bidPrjModifyRepository = bidPrjModifyRepository;
        this.vwBidProjectRepository = vwBidProjectRepository;
        this.bidPrjModifyMapper = bidPrjModifyMapper;
        this.bidProjectService = bidProjectService;
    }

    /**
     * Save a bidPrjModify.
     *
     * @param bidPrjModifyDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjModifyDTO save(BidPrjModifyDTO bidPrjModifyDTO) {
        log.debug("Request to save BidPrjModify : {}", bidPrjModifyDTO);
        BidPrjModify bidPrjModify = bidPrjModifyMapper.toEntity(bidPrjModifyDTO);
        bidPrjModify = bidPrjModifyRepository.save(bidPrjModify);
        return bidPrjModifyMapper.toDto(bidPrjModify);
    }

    /**
     * Partially update a bidPrjModify.
     *
     * @param bidPrjModifyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjModifyDTO> partialUpdate(BidPrjModifyDTO bidPrjModifyDTO) {
        log.debug("Request to partially update BidPrjModify : {}", bidPrjModifyDTO);

        return bidPrjModifyRepository
                .findById(bidPrjModifyDTO.getId())
                .map(existingBidPrjModify -> {
                    bidPrjModifyMapper.partialUpdate(existingBidPrjModify, bidPrjModifyDTO);

                    return existingBidPrjModify;
                })
                .map(bidPrjModifyRepository::save)
                .map(bidPrjModifyMapper::toDto);
    }

    /**
     * Get all the bidPrjModifies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjModifyDTO> findAll() {
        log.debug("Request to get all BidPrjModifies");
        return bidPrjModifyRepository.findAll().stream().map(bidPrjModifyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjModify by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjModifyDTO> findOne(BidPrjModifyPrimaryKey id) {
        log.debug("Request to get BidPrjModify : {}", id);
        return bidPrjModifyRepository.findById(id).map(bidPrjModifyMapper::toDto);
    }

    /**
     * Get project all bidPrjModify by project id.
     *
     * @param criteria the id of the project.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjModifyDTO> findProjectAllModify(BidPrjModifyCriteria criteria) {
        log.debug("Request to get BidPrjModify by project : {} ", criteria.toString());
        criteria.setSortDirection(Sort.Direction.DESC);
        return bidPrjModifyRepository.findAll(criteria, criteria.toPageable()).map(bidPrjModifyMapper::toDto);
    }


    @Transactional(readOnly = true)
    public List<BidPrjModifyDTO> findProjectAllModifyList(BidPrjModifyCriteria criteria) {
        log.debug("Request to get BidPrjModify by project : {} ", criteria.toString());
        return bidPrjModifyRepository.findAll(criteria).stream().map(bidPrjModifyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Delete the bidPrjModify by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjModifyPrimaryKey id) {
        log.debug("Request to delete BidPrjModify : {}", id);
        bidPrjModifyRepository.deleteById(id);
    }

    /**
     * 取的該project 最新一筆 修改資料
     *
     * @param pk project的主鍵
     * @return modifyDTO
     */

    @Transactional(readOnly = true)
    public Optional<BidPrjModifyDTO> findProjectLatestModdify(BidProjectPrimaryKey pk) {
        BidPrjModifyCriteria modifyCriteria = new BidPrjModifyCriteria(pk.getWkut(), pk.getPrjno());
        modifyCriteria.setSortBy(new String[]{"chgDate"});
        modifyCriteria.setSortDirection(Sort.Direction.DESC);
        log.debug("Request to get BidPrjModify by project : {} ", modifyCriteria);
        return bidPrjModifyRepository.findAll(modifyCriteria, modifyCriteria.toPageable()).map(bidPrjModifyMapper::toDto).get().findFirst();
    }

    /**
     * 整個邏輯
     * 1. 新增或更新一筆 prj modify，若是更新會有檢核
     * 2. 儲存modify資料
     * 3. 從modify 拉出 newAmt > 0的最新一筆該project的資料， 如果有的話更新project
     * 4. 從modify 拉出 newDate > 0的最新一筆該project的資料， 如果有的話更新project
     * 5. 把更新的project存進DB
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public BidPrjModifyDTO mergeProjectAndSavePrjModify(BidPrjModifyDTO bidPrjModifyDTO, String mergePrjno, String userName) throws Exception {

        /*
         * 1. 新增或更新一筆 prj modify，若是更新會有檢核
         * 2. 儲存modify資料
         */
        String oldPrjno = bidPrjModifyDTO.getPrjno();
        bidPrjModifyDTO.setPrjno(mergePrjno);

        Optional<BidPrjModify> checkModifyData = bidPrjModifyRepository.findById(new BidPrjModifyPrimaryKey(bidPrjModifyDTO.getWkut(), bidPrjModifyDTO.getPrjno(), bidPrjModifyDTO.getChgDate()));


        //宣告空的 bidPrjModify 做return用
        BidPrjModifyDTO returnBidPrjModifyDTO = null;

        //判斷modify newdat是否需要設為空值  If (newdat.equals(CPRJ.CE_DATE)){newdat=''}
        //因為這個project之後要刪掉，故取名deleteBidProject
        BidProjectDTO deleteBidProject = bidProjectService.findOne(new BidProjectPrimaryKey(bidPrjModifyDTO.getWkut(), oldPrjno)).orElse(null);
        Optional<BidProjectDTO> bidProject = bidProjectService.findOne(new BidProjectPrimaryKey(bidPrjModifyDTO.getWkut(), bidPrjModifyDTO.getPrjno()));

        if (deleteBidProject != null && deleteBidProject.getCeDate() != null && deleteBidProject.getCeDate().equals(bidPrjModifyDTO.getNewDate())) {
            bidPrjModifyDTO.setNewDate(null);
        }


        //若PRJMDY已有資料，進行UPDATE
        //UPDATE 前判斷 IF MERGE_NAME 非NULL AND CPRJMDY. MERGE_NO 非NULL 報錯
        if (checkModifyData.isPresent()) {
            if (StringUtils.isNotBlank(checkModifyData.get().getMergeName()) && StringUtils.isNotBlank(checkModifyData.get().getMergeNo())) {
                throw new BidValidationFailException("bidValidError", "資料檢核錯誤", new String[]{"欲併入之母標案" + bidProject.get().getName() + "\n" +
                        "  於" + checkModifyData.get().getChgDate() + "之由決標轉入後併案之變更設計併入資料已存在!!\n" +
                        "  請查詢變更設計已填報資料！\n" +
                        "  不可重複併案！如確認為同一天變更設計二案以上，請配合調整變更日期為前後日期，並於備註欄位加說明。\n"});

            } else {
                bidPrjModifyDTO.setUpdateUser(userName);
                bidPrjModifyDTO.setUpdateDate(Instant.now());
                returnBidPrjModifyDTO = this.save(bidPrjModifyDTO);

                bidPrjModifyDTO.setUpdateDate(Instant.now());
                bidPrjModifyDTO.setUpdateUser(userName);

            }
        } else {
            //新增資料
            bidPrjModifyDTO.setCreateDate(Instant.now());
            bidPrjModifyDTO.setCreateUser(userName);
            returnBidPrjModifyDTO = this.save(bidPrjModifyDTO);
        }

        /*
         *  3. 從modify 拉出 newAmt > 0的最新一筆該project的資料， 如果有的話更新project
         *  4. 從modify 拉出 newDate > 0的最新一筆該project的資料， 如果有的話更新project
         */


        List<BidPrjModifyDTO> amtModifys = bidPrjModifyRepository.findAll(this.queryModifyWithCondition(bidPrjModifyDTO.getWkut(), bidPrjModifyDTO.getPrjno(), "newAmt"))
                .stream().map(bidPrjModifyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));

        List<BidPrjModifyDTO> newDateModifys = bidPrjModifyRepository.findAll(this.queryModifyWithCondition(bidPrjModifyDTO.getWkut(), bidPrjModifyDTO.getPrjno(), "newDate"))
                .stream().map(bidPrjModifyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));

        if (bidProject.isPresent()) {
            BidProjectDTO updateProject = bidProject.get();

            if (!amtModifys.isEmpty()) {
                BidPrjModifyDTO amtModify = amtModifys.get(0);
                updateProject.setCtSumF(amtModify.getNewAmt());
                updateProject.setAeDate(null);
                updateProject.setAokDate(null);
            }

            if (!newDateModifys.isEmpty()) {
                BidPrjModifyDTO newDateModify = newDateModifys.get(0);
                updateProject.setCeDateF(newDateModify.getNewDate());
            }
            updateProject.setUpdateUser(userName);
            updateProject.setUpdateDate(Instant.now());
            bidProjectService.save(updateProject);
        }

        //刪掉project資料表裡的 被併案的project，新增delete data

        BidPrjDeleteDTO deleteBidDTO = bidProjectService.projectDTOtoPrjDeleteDTO(deleteBidProject);
        bidProjectService.deleteAndInsert(deleteBidDTO);

        //結束
        return returnBidPrjModifyDTO;
    }


    /**
     * 建立查詢需求 new Amt or new Date
     */
    public Specification<BidPrjModify> queryModifyWithCondition(String wkut, String prjno, String queryCondition) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<BidPrjModify> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Set<Predicate> predicates = new HashSet<>();
                predicates.add(criteriaBuilder.equal(root.get("wkut"), wkut));
                predicates.add(criteriaBuilder.equal(root.get("prjno"), prjno));
                if ("newAmt".equals(queryCondition)) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("newAmt"), BigDecimal.valueOf(0)));
                } else if ("newDate".equals(queryCondition)) {
                    predicates.add(criteriaBuilder.isNotNull(root.get("newAmt")));

                }

                query.orderBy(criteriaBuilder.desc(root.get("chgDate")));

                return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
            }
        };
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validData(BidPrjModifyDTO bidPrjModifyDTO) {
        List<String> retrunArray = new ArrayList<>();
        VwBidProject comparedProject = vwBidProjectRepository.findById(new BidProjectPrimaryKey(bidPrjModifyDTO.getWkut(), bidPrjModifyDTO.getPrjno())).orElse(null);
        if (comparedProject == null) {
            retrunArray.add("查無該標案資料");
            return retrunArray;
        }
        if (bidPrjModifyDTO.getChgDate() == null) {
            retrunArray.add("請輸入「變更日期」(ADM-0001-W) 。");
        }
        //if(bidPrjModifyDTO.getChgDate().isAfter(LocalDate.now())){ 這樣寫可能會有null pointer exception
        if (LocalDate.now().isBefore(bidPrjModifyDTO.getChgDate())) {
            retrunArray.add("「變更日期」不可是尚未到達之日期(BID-0013-W) 。");
        }
        if (bidPrjModifyDTO.getMdyPress() == null || bidPrjModifyDTO.getMdyPress().length() == 0) {
            retrunArray.add("請輸入「程序」(ADM-0001-W)");
        }
        if (bidPrjModifyDTO.getOkNo() == null || bidPrjModifyDTO.getOkNo().length() == 0) {
            retrunArray.add("請輸入「核准變更文號」(ADM-0001-W) 。");
        }
        if (bidPrjModifyDTO.getMdyReson() == null || bidPrjModifyDTO.getMdyReson().length() == 0) {
            retrunArray.add("請輸入「變更原因」(ADM-0001-W) 。");
        }
        if (bidPrjModifyDTO.getMdyRemark() == null || bidPrjModifyDTO.getMdyRemark().length() == 0) {
            retrunArray.add("請輸入「原因說明」(ADM-0001-W) 。");
        }
        if (bidPrjModifyDTO.getChgRate() == null || bidPrjModifyDTO.getChgRate().equals(BigDecimal.valueOf(0))) {
            retrunArray.add("請輸入「變更前母案進度」(ADM-0001-W) 。");
        }
        //若「前次修正後總價」＞發包預算(BDGT1)*10
        if (bidPrjModifyDTO.getOldAmt().compareTo(comparedProject.getBdgt1().multiply(BigDecimal.valueOf(10))) > 0) {
            retrunArray.add("「前次修正後總價」不合理 (經費單位為千元) (BID-0014-W) 。");
        }
        //若「本次修正後總價」＞發包預算(BDGT1)*10
        if (comparedProject.getBdgt1() != null && bidPrjModifyDTO.getNewAmt().compareTo(comparedProject.getBdgt1().multiply(BigDecimal.valueOf(10))) > 0) {
            retrunArray.add("「本次修正後總價」不合理 (經費單位為千元) (BID-0015-W) 。");
        }
        if (bidPrjModifyDTO.getNewDate() != null && bidPrjModifyDTO.getOldDate() == null) {
            retrunArray.add("「本次修正後預定完工日」有填但「前次修正後預定完工日」未填報(BID-0016-W) 。");
        }

        if (bidPrjModifyDTO.getNewDate() != null && bidPrjModifyDTO.getnDays() == 0) {
            retrunArray.add("本次修正期程有展延但「展延天數」未填報(BID-0017-W) 。");
        }
        //若「本次修正後總價」＞決標金額(CT_SUM)*1.2 且 「行政責任檢討情形」＝空值
        if (comparedProject.getCtSum() != null) {
            if (bidPrjModifyDTO.getNewAmt().compareTo(comparedProject.getCtSum().multiply(BigDecimal.valueOf(1.2))) > 0 && bidPrjModifyDTO.getRemark1() != null && bidPrjModifyDTO.getRemark1().length() == 0) {
                retrunArray.add("「本次修正後總價」超過原合約２成但「行政責任檢討情形」未填報(BID-0018-W)");
            }
        }


        return retrunArray;
    }

    /**
     * 驗證 bid adc 001功能
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validDataForMergeProject(BidPrjModifyDTO bidPrjModifyDTO, String mergePrjno) {
        List<String> retrunArray = new ArrayList<>();
        BidProjectDTO comparedProject = bidProjectService.findOne(new BidProjectPrimaryKey(bidPrjModifyDTO.getWkut(), bidPrjModifyDTO.getPrjno())).orElse(null);
        BidProjectDTO mergeProject = bidProjectService.findOne(new BidProjectPrimaryKey(bidPrjModifyDTO.getWkut(), mergePrjno)).orElse(null);
        if (comparedProject == null || mergeProject == null) {
            retrunArray.add("查無該標案資料");
            return retrunArray;
        }
        if (bidPrjModifyDTO.getChgDate() == null) {
            retrunArray.add("請輸入「變更日期」(ADM-0001-W) 。");
        }
        if (LocalDate.now().isBefore(bidPrjModifyDTO.getChgDate())) {
            retrunArray.add("「變更日期」不可是尚未到達之日期(BID-0013-W) 。");
        }
        if (bidPrjModifyDTO.getOkNo() == null || bidPrjModifyDTO.getOkNo().length() == 0) {
            retrunArray.add("請輸入「核准變更文號」(ADM-0001-W) 。");
        }
        if (bidPrjModifyDTO.getMdyReson() == null || bidPrjModifyDTO.getMdyReson().length() == 0) {
            retrunArray.add("請輸入「變更原因」(ADM-0001-W) 。");
        }
        if (bidPrjModifyDTO.getMdyRemark() == null || bidPrjModifyDTO.getMdyRemark().length() == 0) {
            retrunArray.add("請輸入「原因說明」(ADM-0001-W) 。");
        }
        if (bidPrjModifyDTO.getChgRate() == null || bidPrjModifyDTO.getChgRate().equals(BigDecimal.valueOf(0))) {
            retrunArray.add("請輸入「變更前母案進度」(ADM-0001-W) 。");
        }

        if (bidPrjModifyDTO.getNewDate() != null && bidPrjModifyDTO.getOldDate() == null) {
            retrunArray.add("「本次修正後預定完工日」有填但「前次修正後預定完工日」未填報(BID-0016-W)");
        }

        if (bidPrjModifyDTO.getNewDate() != null && bidPrjModifyDTO.getnDays() == 0) {
            retrunArray.add("本次修正期程有展延但「展延天數」未填報(BID-0017-W) 。");
        }

        if (mergeProject.getCtSum().compareTo(new BigDecimal(10)) > 0
                && bidPrjModifyDTO.getNewAmt().compareTo(comparedProject.getCtSum().multiply(new BigDecimal(1.2))) > 0) {
            retrunArray.add("「本次修正後總價」不合理 (經費單位為千元)(BID-0015-W) 。");
        }

        if (mergeProject.getCtSum() != null && comparedProject.getCtSum0() != null
                && mergeProject.getCtSum().multiply(new BigDecimal(0.5)).compareTo(comparedProject.getCtSum0()) < 0
                && (!"13".equals(bidPrjModifyDTO.getMdyReson()) || !"16".equals(bidPrjModifyDTO.getMdyReson()))) {
            retrunArray.add("依採購法第二十二條第1項第六款規定，本案已逾原主契約金額百分之五十，不宜併案。\n" +
                    "如為採購法第二十二條第1項第七款規定之後續擴充，應於前案結束後以新案列管，不應併案。\n" +
                    "如為其他條款規定，請回前頁於備註欄說明。");
        }

        return retrunArray;
    }

}
