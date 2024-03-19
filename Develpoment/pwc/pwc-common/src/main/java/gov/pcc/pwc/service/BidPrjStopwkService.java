package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.BidPrjStopwkRepository;
import gov.pcc.pwc.repository.VwBidProjectRepository;
import gov.pcc.pwc.service.criteria.BidPrjStopwkCriteria;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import gov.pcc.pwc.service.dto.BidPrjStopwkDTO;
import gov.pcc.pwc.service.mapper.BidPrjStopwkMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Service Implementation for managing {@link BidPrjStopwk}.
 */
@Service
@Transactional
public class BidPrjStopwkService {

    private final Logger log = LoggerFactory.getLogger(BidPrjStopwkService.class);

    private final BidPrjStopwkRepository bidPrjStopwkRepository;

    private final VwBidProjectRepository vwBidProjectRepository;

    private final BidPrjMonthService bidPrjMonthService;

    private final BidPrjStopwkMapper bidPrjStopwkMapper;

    public BidPrjStopwkService(BidPrjStopwkRepository bidPrjStopwkRepository, VwBidProjectRepository vwBidProjectRepository, BidPrjMonthService bidPrjMonthService, BidPrjStopwkMapper bidPrjStopwkMapper) {
        this.bidPrjStopwkRepository = bidPrjStopwkRepository;
        this.vwBidProjectRepository = vwBidProjectRepository;
        this.bidPrjMonthService = bidPrjMonthService;
        this.bidPrjStopwkMapper = bidPrjStopwkMapper;
    }

    /**
     * Save a bidPrjStopwk.
     *
     * @param bidPrjStopwkDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjStopwkDTO save(BidPrjStopwkDTO bidPrjStopwkDTO) {
        log.debug("Request to save BidPrjStopwk : {}", bidPrjStopwkDTO);
        BidPrjStopwk bidPrjStopwk = bidPrjStopwkMapper.toEntity(bidPrjStopwkDTO);
        bidPrjStopwk = bidPrjStopwkRepository.save(bidPrjStopwk);
        return bidPrjStopwkMapper.toDto(bidPrjStopwk);
    }

    /**
     * Partially update a bidPrjStopwk.
     *
     * @param bidPrjStopwkDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjStopwkDTO> partialUpdate(BidPrjStopwkDTO bidPrjStopwkDTO) {
        log.debug("Request to partially update BidPrjStopwk : {}", bidPrjStopwkDTO);

        return bidPrjStopwkRepository
            .findById(bidPrjStopwkDTO.getId())
            .map(existingBidPrjStopwk -> {
                bidPrjStopwkMapper.partialUpdate(existingBidPrjStopwk, bidPrjStopwkDTO);

                return existingBidPrjStopwk;
            })
            .map(bidPrjStopwkRepository::save)
            .map(bidPrjStopwkMapper::toDto);
    }

    /**
     * Get all the bidPrjStopwks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjStopwkDTO> findAll() {
        log.debug("Request to get all BidPrjStopwks");
        return bidPrjStopwkRepository.findAll().stream().map(bidPrjStopwkMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjStopwk by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjStopwkDTO> findOne(BidPrjStopwkPrimaryKey id) {
        log.debug("Request to get BidPrjStopwk : {}", id);
        return bidPrjStopwkRepository.findById(id).map(bidPrjStopwkMapper::toDto);
    }

    /**
     * Delete the bidPrjStopwk by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjStopwkPrimaryKey id) {
        log.debug("Request to delete BidPrjStopwk : {}", id);
        bidPrjStopwkRepository.deleteById(id);
    }

    /**
     * 透過criteia 撈出 對應的page DTO
     * @param criteria 用criteria 撈出 project 所有的 停工資料
     * @return 停工資料DTO
     */
    public Page<BidPrjStopwkDTO> findProjectAllStopwkBycriteria(BidPrjStopwkCriteria criteria){
        return bidPrjStopwkRepository.findAll(criteria,criteria.toPageable()).map(bidPrjStopwkMapper::toDto);
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validData(BidPrjStopwkDTO bidPrjStopwkDTO,String formStatus){
        List<String> retrunArray = new ArrayList<>();
        VwBidProject comparedProject = vwBidProjectRepository.findById(new BidProjectPrimaryKey(bidPrjStopwkDTO.getWkut(), bidPrjStopwkDTO.getPrjno())).orElse(null);
        if(comparedProject == null){
            retrunArray.add("查無該標案資料");
            return retrunArray;
        }
        if(bidPrjStopwkDTO.getStartDate()==null){
            retrunArray.add("請輸入「停工日期」(ADM-0001-W)");
            return retrunArray;
        }
        if(LocalDate.now().isBefore(bidPrjStopwkDTO.getStartDate())){
            //retrunArray.add("「停工日期」不可是尚未到達之月份(BID-0019-W)");
        }
        if(LocalDate.now().isBefore(bidPrjStopwkDTO.getStartDate())){
            retrunArray.add("「停工日期」不可是尚未到達之日期(BID-0020-W)");
        }
        if(bidPrjStopwkDTO.getStartDate()!=null){
            long diffDay = DAYS.between( bidPrjStopwkDTO.getStartDate(),LocalDate.now());
            if(diffDay>93){
                retrunArray.add("停工三個月仍未填將不允許補填(BID-0021-W)");
            }
        }

        if(StringUtils.isBlank(bidPrjStopwkDTO.getPrmNo())){
            retrunArray.add("請輸入「核定文號」(ADM-0001-W)");
        }
        if(StringUtils.isBlank(bidPrjStopwkDTO.getStopReson())){
            retrunArray.add("請輸入「停工原因」(ADM-0001-W)");        }

        if(StringUtils.isBlank(bidPrjStopwkDTO.getRespons())){
            retrunArray.add("請輸入「責任歸屬」(ADM-0001-W)");
        }
        if(StringUtils.isBlank(bidPrjStopwkDTO.getRemark())){
            retrunArray.add("請輸入「停工原因說明」(ADM-0001-W)");
        }

        if(bidPrjStopwkDTO.getEndDate()!=null && LocalDate.now().isBefore(bidPrjStopwkDTO.getEndDate())){
            retrunArray.add("「實際復工日期」不可是尚未到達之日期(BID-0022-W)");
        }

        if(bidPrjStopwkDTO.getEndDate()!=null && StringUtils.isBlank(bidPrjStopwkDTO.geteRemark())){
             retrunArray.add("已填「實際復工日期」但「復工原因」未填報(BID-0023-W)");
        }

        if(bidPrjStopwkDTO.getEndDate()==null && !StringUtils.isBlank(bidPrjStopwkDTO.geteRemark())){
            retrunArray.add("已填「復工原因」但「實際復工日期」未填報(BID-0024-W)");
        }

        if(bidPrjStopwkDTO.getEndDate()!=null){
            long diffDay = DAYS.between( LocalDate.now(),bidPrjStopwkDTO.getEndDate());
            if(diffDay>93){
                retrunArray.add("已實際復工且復工日期至今日已超過三個月，本資料不再開放修改(BID-0025-W)");
            }
        }
        BidPrjStopwkCriteria descCriteria = new BidPrjStopwkCriteria(bidPrjStopwkDTO.getWkut(), bidPrjStopwkDTO.getPrjno());
        descCriteria.setSortBy(new String[]{"startDate"});
        descCriteria.setSortDirection(Sort.Direction.DESC);
        List<BidPrjStopwk> bidPrjStopwklist = bidPrjStopwkRepository.findAll(descCriteria,descCriteria.toPageable()).toList();
        if ("new".equals(formStatus)&&bidPrjStopwklist.size()>0){
            BidPrjStopwk lastBidPrjStopwk = bidPrjStopwklist.get(0);
            if(lastBidPrjStopwk.getEndDate()==null){
                retrunArray.add("上次停工之「實際復工日期」未填報(BID-0171-W)");
            }

            if(lastBidPrjStopwk.getEndDate()!=null && bidPrjStopwkDTO.getStartDate().isBefore(lastBidPrjStopwk.getEndDate())){
                retrunArray.add("本次停工日"+bidPrjStopwkDTO.getStartDate()+"不應與上次復工日"+lastBidPrjStopwk.getEndDate()+"重疊或連續，如屬同一停工事實，上次停工狀況未復工前請不要填入實際復工日期(BID-0026-W)");
            }
        }
        if( comparedProject.getAeDate()!= null && bidPrjStopwkDTO.getStartDate().isBefore(comparedProject.getAeDate())){
            retrunArray.add("停工日"+bidPrjStopwkDTO.getStartDate()+"，不應在開工日之前(BID-0027-W)");
        }
        //(15)	若該月進度已填報為100%
        int yr = bidPrjStopwkDTO.getStartDate().getYear()-1911;
        int monthInt = bidPrjStopwkDTO.getStartDate().getMonth().getValue();

        String year = String.valueOf(yr);
        String month = StringUtils.leftPad(String.valueOf(monthInt),2,"0");

        Optional<BidPrjMonthDTO> monthSchedule = bidPrjMonthService.findOne(new BidPrjMonthPrimaryKey(bidPrjStopwkDTO.getWkut(), bidPrjStopwkDTO.getPrjno(), year, month));

        if(monthSchedule.isPresent()){
            if (monthSchedule.get().getAtrate() != null && monthSchedule.get().getAtrate().compareTo(new BigDecimal(100))==0){
                retrunArray.add("停工日"+bidPrjStopwkDTO.getStartDate()+"該月進度已填報為100%，不應再填報停工資料(BID-0028-W");
            }
        }
        //16
        if(bidPrjStopwkDTO.getPeDate()!=null&& bidPrjStopwkDTO.getStartDate()!= null && bidPrjStopwkDTO.getPeDate().isBefore(bidPrjStopwkDTO.getStartDate())){
            retrunArray.add(String.format("預定復工日期%s，不應在實際停工日期%s之前(BID-0029-W)",bidPrjStopwkDTO.getPeDate(),bidPrjStopwkDTO.getStartDate()));
        }

        if(bidPrjStopwkDTO.getStartDate()!= null && bidPrjStopwkDTO.getPeDate()!=null
                && DAYS.between(bidPrjStopwkDTO.getStartDate(),bidPrjStopwkDTO.getPeDate())> bidPrjStopwkDTO.getNoCntDay()){
            retrunArray.add("實際停工"+bidPrjStopwkDTO.getStartDate() +
                    "到預定復工" +bidPrjStopwkDTO.getPeDate()+
                    "僅"+DAYS.between(bidPrjStopwkDTO.getStartDate(),bidPrjStopwkDTO.getPeDate())+"天，" +
                    "不計工期日數"+bidPrjStopwkDTO.getNoCntDay()+"明顯不合理(BID-0030-W)");
        }
        return retrunArray;
    }
}
