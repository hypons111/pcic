package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjFals;
import gov.pcc.pwc.domain.BidPrjFalsPrimaryKey;
import gov.pcc.pwc.domain.BidPrjStc;
import gov.pcc.pwc.domain.BidPrjStcPrimaryKey;
import gov.pcc.pwc.repository.BidPrjFalsRepository;
import gov.pcc.pwc.service.criteria.BidPrjFalsCriteria;
import gov.pcc.pwc.service.criteria.BidPrjMilestoneCriteria;
import gov.pcc.pwc.service.dto.BidPrjFalsDTO;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;
import gov.pcc.pwc.service.mapper.BidPrjFalsMapper;

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
 * Service Implementation for managing {@link BidPrjFals}.
 */
@Service
@Transactional
public class BidPrjFalsService {

    private final Logger log = LoggerFactory.getLogger(BidPrjFalsService.class);

    private final BidPrjFalsRepository bidPrjFalsRepository;

    private final BidPrjFalsMapper bidPrjFalsMapper;

    public BidPrjFalsService(BidPrjFalsRepository bidPrjFalsRepository, BidPrjFalsMapper bidPrjFalsMapper) {
        this.bidPrjFalsRepository = bidPrjFalsRepository;
        this.bidPrjFalsMapper = bidPrjFalsMapper;
    }

    /**
     * Save a bidPrjFals.
     *
     * @param bidPrjFalsDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjFalsDTO save(BidPrjFalsDTO bidPrjFalsDTO) {
        log.debug("Request to save BidPrjFals : {}", bidPrjFalsDTO);
        BidPrjFals bidPrjFals = bidPrjFalsMapper.toEntity(bidPrjFalsDTO);
        bidPrjFals = bidPrjFalsRepository.save(bidPrjFals);
        return bidPrjFalsMapper.toDto(bidPrjFals);
    }

    /**
     * Partially update a bidPrjFals.
     *
     * @param bidPrjFalsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjFalsDTO> partialUpdate(BidPrjFalsDTO bidPrjFalsDTO) {
        log.debug("Request to partially update BidPrjFals : {}", bidPrjFalsDTO);

        return bidPrjFalsRepository
                .findById(bidPrjFalsDTO.getId())
                .map(existingBidPrjFals -> {
                    bidPrjFalsMapper.partialUpdate(existingBidPrjFals, bidPrjFalsDTO);

                    return existingBidPrjFals;
                })
                .map(bidPrjFalsRepository::save)
                .map(bidPrjFalsMapper::toDto);
    }

    /**
     * Get all the bidPrjFals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjFalsDTO> findAll() {
        log.debug("Request to get all BidPrjFals");
        return bidPrjFalsRepository.findAll().stream().map(bidPrjFalsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjFals by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjFalsDTO> findOne(BidPrjFalsPrimaryKey id) {
        log.debug("Request to get BidPrjFals : {}", id);
        return bidPrjFalsRepository.findById(id).map(bidPrjFalsMapper::toDto);
    }

    /**
     * Delete the bidPrjFals by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjFalsPrimaryKey id) {
        log.debug("Request to delete BidPrjFals : {}", id);
        bidPrjFalsRepository.deleteById(id);
    }

    /**
     * Find One By Criteria
     */
    public Page<BidPrjFalsDTO> findOneByCriteria(BidPrjFalsCriteria criteria) {
        log.debug("Request to find BidPrjMilestone : {}", criteria);
        criteria.setSortBy(new String[]{"syr"});
        return bidPrjFalsRepository.findAll(criteria, criteria.toPageable()).map(bidPrjFalsMapper::toDto);
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validData(BidPrjFalsDTO bidPrjFalsDTO, String fromStatus) {
        List<String> retrunArray = new ArrayList<>();

        BidPrjFalsPrimaryKey bidPrjFalsPrimaryKey = new BidPrjFalsPrimaryKey(bidPrjFalsDTO.getWkut(), bidPrjFalsDTO.getPrjno(), bidPrjFalsDTO.getSyr(), bidPrjFalsDTO.getMonth());
        BidPrjFals bidPrjFals = bidPrjFalsRepository.findById(bidPrjFalsPrimaryKey).orElse(null);

        if (bidPrjFals != null && fromStatus.equals("create")) {
            retrunArray.add("已有資料無法新增");
        }

//       必填欄位在後端皆要做驗證
        if (bidPrjFalsDTO.getWkut() == null) {
            retrunArray.add("執行單位為必填");
        }

        if (bidPrjFalsDTO.getPrjno() == null) {
            retrunArray.add("標案編號為必填");
        }

        if (bidPrjFalsDTO.getSyr() == null) {
            retrunArray.add("資料月份為必填");
        }
        if (bidPrjFalsDTO.getMonth() == null) {
            retrunArray.add("資料月份為必填");
        }
        if (bidPrjFalsDTO.getMft() == null) {
            retrunArray.add("請輸入「未開工原因」(ADM-0001-W)");
        }
        if (bidPrjFalsDTO.getMft() != null && bidPrjFalsDTO.getMft().substring(1, 3).equals("00")) {
            retrunArray.add("選擇「未開工原因」，不要選前方有●之項目((BID-0060-W)");
        }
        if (bidPrjFalsDTO.getMfas() == null) {
            retrunArray.add("請輸入「未開工原因說明」(ADM-0001-W)");
        }

        return retrunArray;
    }
}
