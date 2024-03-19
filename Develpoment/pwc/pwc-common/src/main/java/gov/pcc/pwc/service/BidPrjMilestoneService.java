package gov.pcc.pwc.service;

import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.BidPrjMilestoneRepository;
import gov.pcc.pwc.service.criteria.BidPrjMilestoneCriteria;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.mapper.BidPrjMilestoneMapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import liquibase.exception.DatabaseException;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjMilestone}.
 */
@Service
@Transactional
public class BidPrjMilestoneService {

    private final Logger log = LoggerFactory.getLogger(BidPrjMilestoneService.class);

    private final BidPrjMilestoneRepository bidPrjMilestoneRepository;

    private final BidPrjMilestoneMapper bidPrjMilestoneMapper;

    private final BidProjectService bidProjectService;

    public BidPrjMilestoneService(BidPrjMilestoneRepository bidPrjMilestoneRepository, BidPrjMilestoneMapper bidPrjMilestoneMapper, BidProjectService bidProjectService) {
        this.bidPrjMilestoneRepository = bidPrjMilestoneRepository;
        this.bidPrjMilestoneMapper = bidPrjMilestoneMapper;
        this.bidProjectService = bidProjectService;
    }

    /**
     * Save a bidPrjMilestone.
     *
     * @param bidPrjMilestoneDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjMilestoneDTO save(BidPrjMilestoneDTO bidPrjMilestoneDTO) {
        log.debug("Request to save BidPrjMilestone : {}", bidPrjMilestoneDTO);
        BidPrjMilestone bidPrjMilestone = bidPrjMilestoneMapper.toEntity(bidPrjMilestoneDTO);
        bidPrjMilestone = bidPrjMilestoneRepository.save(bidPrjMilestone);
        return bidPrjMilestoneMapper.toDto(bidPrjMilestone);
    }

    /**
     * Insert a bidPrjMilestone.
     * save = save and update
     * insert 用sql寫的純粹insert 語句，發生衝突丟出錯誤讓前端顯示
     *
     * @param bidPrjMilestoneDTO the entity to insert.
     * @return the persisted entity.
     */

    public void insert(BidPrjMilestoneDTO bidPrjMilestoneDTO) {
        log.debug("Request to save BidPrjMilestone : {}", bidPrjMilestoneDTO);

        // 找出最後一筆，找到最大的序號+1
        BidPrjMilestoneCriteria criteria = new BidPrjMilestoneCriteria();
        criteria.setWkut(bidPrjMilestoneDTO.getWkut());
        criteria.setPrjno(bidPrjMilestoneDTO.getPrjno());
        criteria.setSortDirection(Sort.Direction.DESC);
        Page<BidPrjMilestoneDTO> page = this.findByCriteria(criteria);

        // 需要檢查預定開工日期120天限制
        // 預定開工日: BidProject.csDate
        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey();
        bidProjectPrimaryKey.setWkut(bidPrjMilestoneDTO.getWkut());
        bidProjectPrimaryKey.setPrjno(bidPrjMilestoneDTO.getPrjno());
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).orElse(null);

        if (page != null && page.getContent().size() > 0) {
            // 當不是第一次新增時
            List<BidPrjMilestoneDTO> listDTO = page.getContent();
            int num = 0;
            for (BidPrjMilestoneDTO item : listDTO) {
                // 取出最大的NO
                if (Integer.parseInt(item.getMilestoneNo()) > num) {
                    num = Integer.parseInt(item.getMilestoneNo());
                }
            }
            String milestoneNo = 1 + num + "";
            bidPrjMilestoneDTO.setMilestoneNo(milestoneNo);

            // 第N>1次檢查
            if (bidPrjMilestoneDTO.getApplyDate().isAfter(listDTO.get(0).getApplyDate())) {
                if (ChronoUnit.DAYS.between(listDTO.get(0).getApplyDate(), bidPrjMilestoneDTO.getApplyDate()) > 120) {
                    throw new BidValidationFailException("bidValidError", "資料檢核錯誤", new String[]{"填報日期不得超過間隔120天"});
                }
            }

        } else {
            bidPrjMilestoneDTO.setMilestoneNo("1");
            if (bidProjectDTO.getCsDate() != null) {
                // 第一次新增 檢查
                if (ChronoUnit.DAYS.between(bidPrjMilestoneDTO.getApplyDate(), bidProjectDTO.getCsDate()) > 120) {
                    throw new BidValidationFailException("bidValidError", "資料檢核錯誤", new String[]{"填報日期不得超過間隔120天"});
                }
            }else {
                throw new BidValidationFailException("bidValidError", "資料檢核錯誤", new String[]{"基本資料(BID-AAA-002)之基本資料4之「預定開工日期」未填報(BID-0275-W)"});
            }


        }

        bidPrjMilestoneRepository.insertBidPrjMilestone(bidPrjMilestoneDTO);
    }

    public void fromStcInsert(BidPrjStcDTO bidPrjStcDTO, String MILESTONE_NAME, LocalDate ESTIMATE_DATE, String REMARK) {

        BidPrjMilestoneDTO bidPrjMilestoneDTO = new BidPrjMilestoneDTO();
        BidPrjMilestonePrimaryKey bidPrjMilestonePrimaryKey = new BidPrjMilestonePrimaryKey(bidPrjStcDTO.getWkut(), bidPrjStcDTO.getPrjno(), "1");
        bidPrjMilestoneDTO.setWkut(bidPrjStcDTO.getWkut());
        bidPrjMilestoneDTO.setPrjno(bidPrjStcDTO.getPrjno());
        bidPrjMilestoneDTO.setApplyDate(bidPrjStcDTO.getCheckDate());
        bidPrjMilestoneDTO.setMilestoneName(MILESTONE_NAME);
        bidPrjMilestoneDTO.setMilestoneType("2");
        bidPrjMilestoneDTO.setMilestoneStatus("N");
        bidPrjMilestoneDTO.setEstimateDate(ESTIMATE_DATE);
        bidPrjMilestoneDTO.setRemark(REMARK);
        bidPrjMilestoneDTO.setCreateDate(bidPrjStcDTO.getCreateDate());
        bidPrjMilestoneDTO.setCreateUser(bidPrjStcDTO.getCreateUser());
        bidPrjMilestoneDTO.setId(bidPrjMilestonePrimaryKey);
        bidPrjMilestoneDTO.setUpdateDate(bidPrjStcDTO.getCreateDate());

        this.insert(bidPrjMilestoneDTO);
    }

    @Transactional
    public void fromStcUpdate(BidPrjMilestoneCriteria criteria, BidPrjStcDTO bidPrjStcDTO, String MILESTONE_NAME, LocalDate ESTIMATE_DATE, String REMARK, String MILESTONE_STATUS) {
        criteria.setMilestoneName(MILESTONE_NAME);
        if (this.findOneByCriteria(criteria).orElse(null) == null) {
            if (MILESTONE_STATUS.equals("N")) {
                this.fromStcInsert(bidPrjStcDTO, MILESTONE_NAME, ESTIMATE_DATE, REMARK);
            }
        } else {
            BidPrjMilestoneDTO bidPrjMilestoneDTO = this.findOneByCriteria(criteria).orElse(null);
            if (bidPrjMilestoneDTO != null) {
                if (MILESTONE_STATUS.equals("N")) {
                    // 修改成未完成的資料要新增
                    bidPrjMilestoneDTO.setEstimateDate(ESTIMATE_DATE);
                    bidPrjMilestoneDTO.setRemark(REMARK);
                    bidPrjMilestoneDTO.setMilestoneStatus(MILESTONE_STATUS);
                    bidPrjMilestoneDTO.setActualDate(LocalDate.ofInstant(bidPrjStcDTO.getUpdateDate(), ZoneId.systemDefault()));
                    bidPrjMilestoneDTO.setUpdateUser(bidPrjStcDTO.getUpdateUser());
                    bidPrjMilestoneDTO.setUpdateDate(bidPrjStcDTO.getUpdateDate());
                    this.save(bidPrjMilestoneDTO);
                } else if (MILESTONE_STATUS.equals("Y")) {
                    // 根據需求，要把已完成資料刪除
                    this.delete(new BidPrjMilestonePrimaryKey(bidPrjMilestoneDTO.getWkut(), bidPrjMilestoneDTO.getPrjno(), bidPrjMilestoneDTO.getMilestoneNo()));
                }
            }
        }
    }

    /**
     * Partially update a bidPrjMilestone.
     *
     * @param bidPrjMilestoneDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjMilestoneDTO> partialUpdate(BidPrjMilestoneDTO bidPrjMilestoneDTO) {
        log.debug("Request to partially update BidPrjMilestone : {}", bidPrjMilestoneDTO);

        return bidPrjMilestoneRepository
                .findById(bidPrjMilestoneDTO.getId())
                .map(existingBidPrjMilestone -> {
                    bidPrjMilestoneMapper.partialUpdate(existingBidPrjMilestone, bidPrjMilestoneDTO);

                    return existingBidPrjMilestone;
                })
                .map(bidPrjMilestoneRepository::save)
                .map(bidPrjMilestoneMapper::toDto);
    }

    /**
     * Get all the bidPrjMilestones.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjMilestoneDTO> findAll() {
        log.debug("Request to get all BidPrjMilestones");
        return bidPrjMilestoneRepository
                .findAll()
                .stream()
                .map(bidPrjMilestoneMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjMilestone by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjMilestoneDTO> findOne(BidPrjMilestonePrimaryKey id) {
        log.debug("Request to get BidPrjMilestone : {}", id);
        return bidPrjMilestoneRepository.findById(id).map(bidPrjMilestoneMapper::toDto);
    }

    /**
     * Delete the bidPrjMilestone by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjMilestonePrimaryKey id) {
        log.debug("Request to delete BidPrjMilestone : {}", id);
        bidPrjMilestoneRepository.deleteById(id);
    }

    /**
     * Find All By Criteria
     */
    public Page<BidPrjMilestoneDTO> findByCriteria(BidPrjMilestoneCriteria criteria) {
        log.debug("Request to find BidPrjMilestone : {}", criteria);
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"milestoneNo"});
        }
        final Page<BidPrjMilestoneDTO> bidPrjMilestoneDTOS = bidPrjMilestoneRepository.findAllByCriteria(criteria);
        return bidPrjMilestoneDTOS;
    }

    /**
     * Find One By Criteria
     */
    public Optional<BidPrjMilestoneDTO> findOneByCriteria(BidPrjMilestoneCriteria criteria) {
        log.debug("Request to find BidPrjMilestone : {}", criteria);

//        return bidPrjMilestoneRepository.findAll(criteria).stream().map(bidPrjMilestoneMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        return bidPrjMilestoneRepository.findAll(criteria).stream().findAny().map(bidPrjMilestoneMapper::toDto);
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validData(BidPrjMilestoneDTO bidPrjMilestoneDTO, String fromStatus) {
        List<String> retrunArray = new ArrayList<>();

//       必填欄位在後端皆要做驗證
        if (bidPrjMilestoneDTO.getWkut() == null) {
            retrunArray.add("執行單位為必填");
        }
        if (bidPrjMilestoneDTO.getPrjno() == null) {
            retrunArray.add("標案編號為必填");
        }
        if (bidPrjMilestoneDTO.getCreateDate() == null) {
            retrunArray.add("建立日期為必填");
        }
        if (bidPrjMilestoneDTO.getCreateUser() == null) {
            retrunArray.add("建立人員為必填");
        }
        if (bidPrjMilestoneDTO.getApplyDate() == null) {
            retrunArray.add("請輸入「填報日期」(ADM-0001-W)");
        }
        if (bidPrjMilestoneDTO.getMilestoneName() == null) {
            retrunArray.add("請輸入「里程碑名稱」(ADM-0001-W)");
        }
        if (bidPrjMilestoneDTO.getMilestoneType() == null) {
            retrunArray.add("請輸入「類別」(ADM-0001-W)");
        }
        if (bidPrjMilestoneDTO.getMilestoneStatus() == null) {
            retrunArray.add("請輸入「完成狀態」(ADM-0001-W)");
        }
        if (bidPrjMilestoneDTO.getEstimateDate() == null) {
            retrunArray.add("請輸入「預定完成日期」(ADM-0001-W)");
        }

        if (bidPrjMilestoneDTO.getEstimateDate() != null && bidPrjMilestoneDTO.getActualDate() != null) {

            if (bidPrjMilestoneDTO.getEstimateDate().isAfter(bidPrjMilestoneDTO.getActualDate())) {
                retrunArray.add("「預定完成日期」不可大於「實際完成日期」(BID-0058-W)");
            }
        }

        if (bidPrjMilestoneDTO.getMilestoneStatus() != null) {
            if (bidPrjMilestoneDTO.getMilestoneStatus().equals("N") && bidPrjMilestoneDTO.getRemark() == null) {
                retrunArray.add("「完成狀態」為\"未完成\"但「屆期未完成原因目前辦理情形」未填報(BID-0059-W)");
            }
        }


        return retrunArray;
    }
}
