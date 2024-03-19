package gov.pcc.pwc.service;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidGovCheck;
import gov.pcc.pwc.domain.BidGovCheckPrimaryKey;
import gov.pcc.pwc.domain.BidUnit10;
import gov.pcc.pwc.repository.BidGovCheckRepository;
import gov.pcc.pwc.repository.BidUnit10Repository;
import gov.pcc.pwc.service.criteria.BidGovCheckCriteria;
import gov.pcc.pwc.service.dto.BidGovCheckDTO;
import gov.pcc.pwc.service.mapper.BidGovCheckMapper;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Service Implementation for managing {@link BidGovCheck}.
 */
@Service
@Transactional
public class BidGovCheckService {

    private final Logger log = LoggerFactory.getLogger(BidGovCheckService.class);

    private final BidGovCheckRepository bidGovCheckRepository;

    private final BidUnit10Repository bidUnit10Repository;

    private final BidGovCheckMapper bidGovCheckMapper;

    public BidGovCheckService(BidGovCheckRepository bidGovCheckRepository, BidUnit10Repository bidUnit10Repository, BidGovCheckMapper bidGovCheckMapper) {
        this.bidGovCheckRepository = bidGovCheckRepository;
        this.bidUnit10Repository = bidUnit10Repository;
        this.bidGovCheckMapper = bidGovCheckMapper;
    }

    /**
     * Save a bidGovCheck.
     *
     * @param bidGovCheckDTO the entity to save.
     * @return the persisted entity.
     */
    public BidGovCheckDTO save(BidGovCheckDTO bidGovCheckDTO) {
        log.debug("Request to save BidGovCheck : {}", bidGovCheckDTO);
        BidGovCheck bidGovCheck = bidGovCheckMapper.toEntity(bidGovCheckDTO);
        bidGovCheck = bidGovCheckRepository.save(bidGovCheck);
        return bidGovCheckMapper.toDto(bidGovCheck);
    }

    /**
     * Partially update a bidGovCheck.
     *
     * @param bidGovCheckDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidGovCheckDTO> partialUpdate(BidGovCheckDTO bidGovCheckDTO) {
        log.debug("Request to partially update BidGovCheck : {}", bidGovCheckDTO);

        return bidGovCheckRepository
            .findById(bidGovCheckDTO.getId())
            .map(existingBidGovCheck -> {
                bidGovCheckMapper.partialUpdate(existingBidGovCheck, bidGovCheckDTO);

                return existingBidGovCheck;
            })
            .map(bidGovCheckRepository::save)
            .map(bidGovCheckMapper::toDto);
    }

    /**
     * Get all the bidGovChecks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidGovCheckDTO> findAll() {
        log.debug("Request to get all BidGovChecks");
        return bidGovCheckRepository.findAll().stream().map(bidGovCheckMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Find the list of BidGovCheckDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidGovCheckDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidGovCheckDTO> findByCriteria(BidGovCheckCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"wkut"});
        }
        final Page<BidGovCheckDTO> BidGovCheckDTOs = bidGovCheckRepository.findAllByCriteria(criteria);
        BidGovCheckDTOs.forEach(bidGovCheckDTO -> {
            String checkUnit = bidGovCheckDTO.getCheckUnit();
            if (StringUtils.isNotBlank(checkUnit)) {
                BidUnit10 bidUnit10 = bidUnit10Repository.findById(bidGovCheckDTO.getCheckUnit()).get(); // 查核小組
                if (bidUnit10 != null) {
                    bidGovCheckDTO.setName(bidUnit10.getName());
                }
            }
            bidGovCheckDTO.setCheckString(this.getCheckString(bidGovCheckDTO)); // 建議懲處
            bidGovCheckDTO.setRoveString(this.getRoveString(bidGovCheckDTO)); // 實際懲處
            if (StringUtils.isBlank(bidGovCheckDTO.getDetel3())) { // 查核意見
                bidGovCheckDTO.setDetel3("(未填)");
            }
        });
        return BidGovCheckDTOs;
    }


    /**
     * Find the list of BidGovCheckDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidGovCheckDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidGovCheckDTO> findListByCriteria(BidGovCheckCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"wkut"});
        }

        final Page<BidGovCheckDTO> bidGovCheckDTOs = bidGovCheckRepository.findAllByCriteria(criteria);
        return bidGovCheckDTOs;
    }

    /**
     * Get one bidGovCheck by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidGovCheckDTO> findOne(BidGovCheckPrimaryKey id) {
        log.debug("Request to get BidGovCheck : {}", id);
        return bidGovCheckRepository.findById(id).map(bidGovCheckMapper::toDto);
    }

    /**
     * Delete the bidGovCheck by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidGovCheckPrimaryKey id) {
        log.debug("Request to delete BidGovCheck : {}", id);
        bidGovCheckRepository.deleteById(id);
    }

    /**
     * Get the checkString.
     *
     * @param bidGovCheckDTO the bidGovCheckDTO.
     * @return String.
     */
    private String getCheckString(BidGovCheckDTO bidGovCheckDTO) {
        String checkString = "";
        if (StringUtils.isBlank(bidGovCheckDTO.getDetel3())) {
            //
        } else {
            if ("Y".equals(bidGovCheckDTO.getRoveChk0())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "函請主辦機關依法檢討相關人員責任";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk1())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "對主辦機關人員懲處";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk7())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "對PCM辦理扣款";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk2())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "對廠商，依政府採購法第101條至第103條規定辦理";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk3())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "撤換工地負責人";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk32())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "撤換品管人員";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk33())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "撤換安衛人員";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk34())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "檢討專任工程人員責任";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk35())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "缺失嚴重部分拆除重做";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk36())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "對廠商辦理扣款";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk4())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "檢討建築師責任";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk42())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "檢討技師責任";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk43())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "委辦監造撤換監工人員";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk45())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "自辦監造懲處監工人員";
            }
            if ("Y".equals(bidGovCheckDTO.getRoveChk44())) {
                if (StringUtils.isNotBlank(checkString)) {
                    checkString += "\n";
                }
                checkString += "對監造辦理扣款";
            }
        }
        return checkString;
    }

    /**
     * Get the roveString.
     *
     * @param bidGovCheckDTO the bidGovCheckDTO.
     * @return String.
     */
    private String getRoveString(BidGovCheckDTO bidGovCheckDTO) {
        String roveString = "";
        if ("Y".equals(bidGovCheckDTO.getRoveChk1a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "對主辦機關人員懲處";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk7a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "對PCM辦理扣款";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk2a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "對廠商，依政府採購法第101條至第103條規定辦理";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk3a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "撤換工地負責人";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk32a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "撤換品管人員";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk33a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "撤換安衛人員";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk34a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "檢討專任工程人員責任";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk35a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "缺失嚴重部分拆除重做";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk36a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "對廠商辦理扣款";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk4a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "檢討建築師責任";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk42a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "檢討技師責任";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk43a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "委辦監造撤換監工人員";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk45a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "自辦監造懲處監工人員";
        }
        if ("Y".equals(bidGovCheckDTO.getRoveChk44a())) {
            if (StringUtils.isNotBlank(roveString)) {
                roveString += "\n";
            }
            roveString += "對監造辦理扣款";
        }
        return roveString;
    }


    /**
     * @param criteria query criteria(wkut and prjno)
     * @return Page<BidGovCheckDTO>
     */
    @Transactional(readOnly = true)
    public Page<BidGovCheckDTO> getBidGovCheckDTOPageByCriteria(BidGovCheckCriteria criteria) {
        return bidGovCheckRepository.findAll(criteria, criteria.toPageable()).map(bidGovCheckMapper::toDto);
    }

    /**
     * @param criteria query criteria(wkut and prjno)
     * @return Page<BidGovCheckDTO>
     */
    @Transactional(readOnly = true)
    public List<BidGovCheckDTO> getBidGovCheckDTOListByCriteria(BidGovCheckCriteria criteria) {
        criteria.setSortBy(new String[]{"checkDate"});
        criteria.setSortDirection(Sort.Direction.DESC);
        Criteria<BidGovCheck> additionalCriteria = new Criteria<BidGovCheck>() {
            @Override
            public String getOrderBy() {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<BidGovCheck> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("wkut"), criteria.getWkut()),
                        criteriaBuilder.equal(root.get("prjno"), criteria.getPrjno()),
                        criteriaBuilder.or(
                                criteriaBuilder.equal(root.get("roveChk5"),"N"),
                                criteriaBuilder.equal(root.get("roveChk5"),"Y")));

            }
        };
        return bidGovCheckRepository.findAll(additionalCriteria, criteria.toPageable().getSort()).stream().map(bidGovCheckMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
