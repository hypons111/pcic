package gov.pcc.pwc.service;

import gov.pcc.pwc.common.enums.bid.BidFormStatusEnum;
import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;
import gov.pcc.pwc.domain.BidPrjMonth;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.repository.BidPrjMonthRepository;
import gov.pcc.pwc.service.criteria.BidPrjMonthCriteria;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.mapper.BidPrjMonthMapper;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjMonth}.
 */
@Service
@Transactional
public class BidPrjMonthService {

    private final Logger log = LoggerFactory.getLogger(BidPrjMonthService.class);

    private final BidPrjMonthRepository bidPrjMonthRepository;

    private final BidPrjMonthMapper bidPrjMonthMapper;

    private final BidProjectService bidProjectService;

    private final BidPrjModifyService bidPrjModifyService;

    public BidPrjMonthService(BidPrjMonthRepository bidPrjMonthRepository, BidPrjMonthMapper bidPrjMonthMapper, BidProjectService bidProjectService, BidPrjModifyService bidPrjModifyService) {
        this.bidPrjMonthRepository = bidPrjMonthRepository;
        this.bidPrjMonthMapper = bidPrjMonthMapper;
        this.bidProjectService = bidProjectService;
        this.bidPrjModifyService = bidPrjModifyService;
    }

    /**
     * Save a bidPrjMonth.
     *
     * @param bidPrjMonthDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjMonthDTO save(BidPrjMonthDTO bidPrjMonthDTO) {
        log.debug("Request to save BidPrjMonth : {}", bidPrjMonthDTO);
        BidPrjMonth bidPrjMonth = bidPrjMonthMapper.toEntity(bidPrjMonthDTO);
        bidPrjMonth = bidPrjMonthRepository.save(bidPrjMonth);
        return bidPrjMonthMapper.toDto(bidPrjMonth);
    }

    /**
     * Partially update a bidPrjMonth.
     *
     * @param bidPrjMonthDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjMonthDTO> partialUpdate(BidPrjMonthDTO bidPrjMonthDTO) {
        log.debug("Request to partially update BidPrjMonth : {}", bidPrjMonthDTO);

        return bidPrjMonthRepository
                .findById(new BidPrjMonthPrimaryKey(bidPrjMonthDTO.getWkut(), bidPrjMonthDTO.getPrjno(), bidPrjMonthDTO.getSyr(), bidPrjMonthDTO.getMonth()))
                .map(existingBidPrjMonth -> {
                    bidPrjMonthMapper.partialUpdate(existingBidPrjMonth, bidPrjMonthDTO);

                    return existingBidPrjMonth;
                })
                .map(bidPrjMonthRepository::save)
                .map(bidPrjMonthMapper::toDto);
    }

    /**
     * Get one bidPrjMonth by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjMonthDTO> findOne(BidPrjMonthPrimaryKey id) {
        log.debug("Request to get BidPrjMonth : {}", id);
        return bidPrjMonthRepository.findById(id).map(bidPrjMonthMapper::toDto);
    }

    /**
     * Delete the bidPrjMonth by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjMonthPrimaryKey id) {
        log.debug("Request to delete BidPrjMonth : {}", id);
        bidPrjMonthRepository.deleteById(id);
    }

    /**
     * 透過 wkut和prjno 撈出最近一筆的 prjmonth
     *
     * @param wkut  project wkut
     * @param prjno project prjno
     * @return the entity
     */
    public Optional<BidPrjMonthDTO> findLatestPrjMnth(String wkut, String prjno) {
        return bidPrjMonthRepository.findLatestPrjMnth(wkut, prjno);
    }

    /**
     * 透過 wkut和prjno 撈出所有的 prjmonth
     *
     * @param wkut  project wkut
     * @param prjno project prjno
     * @return all entity
     */
    public List<BidPrjMonthDTO> findAllBidPrjMonth(String wkut, String prjno) {
        return bidPrjMonthRepository.findAllBidPrjMonth(wkut, prjno);
    }

    /**
     * 透過 wkut和prjno 撈出所有的 prjmonth(帶page)
     *
     * @param criteria criteria object,with property wkut and prjno
     * @return all entity
     */
    public Page<BidPrjMonthDTO> findAllBidPrjMonthByCriteria(BidPrjMonthCriteria criteria) {
        return bidPrjMonthRepository.findAll(criteria, criteria.toPageable()).map(bidPrjMonthMapper::toDto);
    }

    public List<BidPrjMonthDTO> findAllBidPrjMonthListByCriteria(BidPrjMonthCriteria criteria) {
        criteria.setSortBy(new String[]{"syr", "month"});
        criteria.setSortDirection(Sort.Direction.DESC);
        return bidPrjMonthRepository.findAll(criteria,criteria.toPageable().getSort()).stream().map(bidPrjMonthMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * 驗證各月分配的DTO
     * aab-001
     */
    public List<String> validProjectMonthDistibuteData(BidPrjMonthDTO bidPrjMonthDTO, BidFormStatusEnum formStatus) {
        List<String> retrunArray = new ArrayList<>();
        BidProjectDTO compareProject = bidProjectService.findOne(new BidProjectPrimaryKey(bidPrjMonthDTO.getWkut(), bidPrjMonthDTO.getPrjno())).orElse(null);
        Optional<BidPrjModifyDTO> compareProjectModify = bidPrjModifyService.findProjectLatestModdify(new BidProjectPrimaryKey(bidPrjMonthDTO.getWkut(), bidPrjMonthDTO.getPrjno()));
        //找不到對應的project
        if(compareProject == null){
            retrunArray.add("查無標案基本資料");
            return retrunArray;
        }


        if (StringUtils.isBlank(bidPrjMonthDTO.getSyr())) {
            retrunArray.add("請輸入「資料年」(ADM-0001-W)");
        }

        if (StringUtils.isBlank(bidPrjMonthDTO.getMonth())) {
            retrunArray.add("請輸入「資料月」(ADM-0001-W)");
        }

        if (formStatus==BidFormStatusEnum.CREATE && bidPrjMonthRepository.existsById(new BidPrjMonthPrimaryKey(bidPrjMonthDTO.getWkut(), bidPrjMonthDTO.getPrjno(), bidPrjMonthDTO.getSyr(), bidPrjMonthDTO.getMonth()))) {
            retrunArray.add(bidPrjMonthDTO.getSyr() + "年" + bidPrjMonthDTO.getMonth() + "月已有資料，不可新增(BID-0043-W)");

        }

        //null問題
        //若「總累計預定完成金額(bid_prj_month.pexp)」＞「總累計預定完成金額(bid_project.ctsum) 」且「變更設計後總價金額(bid_prj_modify.new_amt)」＝空值
        if(bidPrjMonthDTO.getPexp()!=null && compareProject.getCtSum() !=null){
            if ((bidPrjMonthDTO.getPexp().compareTo(compareProject.getCtSum()) > 0)
                    && compareProjectModify.isPresent()
                    && compareProjectModify.get().getNewAmt() == null) {
                retrunArray.add("「總累計預定完成金額」不合理(無變更設計時不應大於決標金額)，如有變更設計漏填請先至變更設計補填修正後總價(BID-0041-W)");
            }
        }


        if (compareProjectModify.isPresent() && compareProjectModify.get().getNewAmt() != null
                && bidPrjMonthDTO.getPexp().compareTo(compareProjectModify.get().getNewAmt()) > 0
        ) {
            retrunArray.add("「總累計預定完成金額」不合理(不應大於變更設計後總價金額" + compareProjectModify.get().getNewAmt().toString() + "千元)，如有變更設計漏填請先至變更設計補填修正後總價(BID-0042-W)");
        }
        return retrunArray;
    }

}

