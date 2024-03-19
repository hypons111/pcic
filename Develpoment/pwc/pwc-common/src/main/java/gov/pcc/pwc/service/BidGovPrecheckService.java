package gov.pcc.pwc.service;

import gov.pcc.pwc.common.enums.bid.BidFormStatusEnum;
import gov.pcc.pwc.domain.BidGovPrecheck;
import gov.pcc.pwc.domain.BidGovPrecheckPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.domain.VwBidProject;
import gov.pcc.pwc.repository.BidGovPrecheckRepository;
import gov.pcc.pwc.service.criteria.BidGovPrecheckCriteria;
import gov.pcc.pwc.service.dto.BidGovPrecheckDTO;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import gov.pcc.pwc.service.mapper.BidGovPrecheckMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
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
 * Service Implementation for managing {@link BidGovPrecheck}.
 */
@Service
@Transactional
public class BidGovPrecheckService {

    private final Logger log = LoggerFactory.getLogger(BidGovPrecheckService.class);

    private final BidGovPrecheckRepository bidGovPrecheckRepository;

    private final BidGovPrecheckMapper bidGovPrecheckMapper;

    public BidGovPrecheckService(BidGovPrecheckRepository bidGovPrecheckRepository, BidGovPrecheckMapper bidGovPrecheckMapper) {
        this.bidGovPrecheckRepository = bidGovPrecheckRepository;
        this.bidGovPrecheckMapper = bidGovPrecheckMapper;
    }

    /**
     * Save a bidGovPrecheck.
     *
     * @param bidGovPrecheckDTO the entity to save.
     * @return the persisted entity.
     */
    public BidGovPrecheckDTO save(BidGovPrecheckDTO bidGovPrecheckDTO) {
        log.debug("Request to save BidGovPrecheck : {}", bidGovPrecheckDTO);
        BidGovPrecheck bidGovPrecheck = bidGovPrecheckMapper.toEntity(bidGovPrecheckDTO);
        bidGovPrecheck = bidGovPrecheckRepository.save(bidGovPrecheck);
        return bidGovPrecheckMapper.toDto(bidGovPrecheck);
    }

    /**
     * Partially update a bidGovPrecheck.
     *
     * @param bidGovPrecheckDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidGovPrecheckDTO> partialUpdate(BidGovPrecheckDTO bidGovPrecheckDTO) {
        log.debug("Request to partially update BidGovPrecheck : {}", bidGovPrecheckDTO);

        return bidGovPrecheckRepository
                .findById(bidGovPrecheckDTO.getId())
                .map(existingBidGovPrecheck -> {
                    bidGovPrecheckMapper.partialUpdate(existingBidGovPrecheck, bidGovPrecheckDTO);

                    return existingBidGovPrecheck;
                })
                .map(bidGovPrecheckRepository::save)
                .map(bidGovPrecheckMapper::toDto);
    }

    /**
     * Get all the bidGovPrechecks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidGovPrecheckDTO> findAll() {
        log.debug("Request to get all BidGovPrechecks");
        return bidGovPrecheckRepository
                .findAll()
                .stream()
                .map(bidGovPrecheckMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidGovPrecheck by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidGovPrecheckDTO> findOne(BidGovPrecheckPrimaryKey id) {
        log.debug("Request to get BidGovPrecheck : {}", id);
        return bidGovPrecheckRepository.findById(id).map(bidGovPrecheckMapper::toDto);
    }

    /**
     * Delete the bidGovPrecheck by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidGovPrecheckPrimaryKey id) {
        log.debug("Request to delete BidGovPrecheck : {}", id);
        bidGovPrecheckRepository.deleteById(id);
    }

    /**
     * @param criteria 查詢條件，通常是prjno 和 wkut
     * @return 用criteria 找 list
     */
    @Transactional(readOnly = true)
    public List<BidGovPrecheckDTO> findListByCriteria(BidGovPrecheckCriteria criteria) {
        log.debug("criteria to get BidGovPrecheck List : {}", criteria);
        return bidGovPrecheckRepository.findAll(criteria).stream()
                .map(bidGovPrecheckMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * @param criteria 查詢條件，通常是prjno 和 wkut
     * @return 用criteria 找 list
     */
    @Transactional(readOnly = true)
    public Page<BidGovPrecheckDTO> findPageByCriteria(BidGovPrecheckCriteria criteria) {
        log.debug("criteria to get BidGovPrecheck List : {}", criteria);
        return bidGovPrecheckRepository.findAll(criteria, criteria.toPageable()).map(bidGovPrecheckMapper::toDto);
    }


    public List<String> validData(BidGovPrecheckDTO bidGovPrecheckDTO, BidFormStatusEnum status) {
        List<String> retrunArray = new ArrayList<>();

        //新增的話現在的時間 要比前一筆 多45天
        if (status == BidFormStatusEnum.CREATE) {

            BidGovPrecheckCriteria criteria = new BidGovPrecheckCriteria();
            criteria.setPrjno(bidGovPrecheckDTO.getPrjno());
            criteria.setWkut(bidGovPrecheckDTO.getWkut());
            criteria.setSortBy(new String[]{"checkDate"});
            criteria.setSortDirection(Sort.Direction.DESC);
            List<BidGovPrecheckDTO> preChecks = findPageByCriteria(criteria).getContent();
            if (preChecks.size() > 0) {
                BidGovPrecheckDTO latest = preChecks.get(0);
                Period period = Period.between(latest.getCheckDate(), LocalDate.now());

                if (Math.abs(period.getDays()) > 45) {
                    retrunArray.add("本案已有預排查核行程，不開放新增");
                }
            }
        }

        if (bidGovPrecheckDTO.getMethod().equals("N") && bidGovPrecheckDTO.getCheckDateEnd() == null) {
            retrunArray.add("需填寫預定查核日期(止日)");
        }


        return retrunArray;
    }
}
