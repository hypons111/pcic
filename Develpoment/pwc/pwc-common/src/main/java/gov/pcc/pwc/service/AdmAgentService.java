package gov.pcc.pwc.service;

import gov.pcc.pwc.repository.AdmAccountRepository;
import gov.pcc.pwc.repository.AdmAgentRepository;
import gov.pcc.pwc.repository.BidUnit10Repository;
import gov.pcc.pwc.service.criteria.AdmAgentCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmAgentAccountDTO;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.BidUnit10NameAndCodeDTO;
import gov.pcc.pwc.service.dto.ProAdmAgentOrgNameDTO;
import gov.pcc.pwc.service.mapper.BidUnit10Mapper;
import gov.pcc.pwc.service.mapper.AdmAgentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AdmAgentService {

    private final Logger log = LoggerFactory.getLogger(AdmAgentService.class);

    private final AdmAgentRepository admAgentRepository;

    private final AdmAgentMapper admAgentMapper;

    private final BidUnit10Repository bidUnit10Repository;

    private final BidUnit10Mapper bidUnit10Mapper;

    private final AdmAccountRepository admAccountRepository;

    public AdmAgentService(AdmAgentRepository admAgentRepository, AdmAgentMapper admAgentMapper, BidUnit10Repository bidUnit10Repository, BidUnit10Mapper bidUnit10Mapper, AdmAccountRepository admAccountRepository ) {
        this.admAgentRepository = admAgentRepository;
        this.bidUnit10Repository = bidUnit10Repository;
        this.admAgentMapper = admAgentMapper;
        this.admAccountRepository = admAccountRepository;
        this.bidUnit10Mapper = bidUnit10Mapper;
    }



    /**
     * Find the list of AdmAgentDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmAgentDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmAgentDTO> findByCriteria(AdmAgentCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "pccOrgId" });
        }
        final Page<AdmAgentDTO> admAgentDTOs = admAgentRepository.findAllByCriteria(criteria);
        return admAgentDTOs;
    }

    @Transactional(readOnly = true)
    public List<ProAdmAgentOrgNameDTO> findOrgIdAndOrgNameByKeyWord(String keyWord) {
        log.debug("Request to get all BidUnit10s");
        Page<ProAdmAgentOrgNameDTO> resWithPage = admAgentRepository.findOrgIdAndOrgNameByKeyWord(keyWord, PageRequest.of(0, 25));
        return resWithPage.getContent();
    }

//    @Transactional(readOnly = true)
//    public List<ProAdmAgentOrgNameDTO> findNameAndCodeByKeyWord(String keyWord) {
//        log.debug("Request to get all BidUnit10s");
//        Page<ProAdmAgentOrgNameDTO> resWithPage = admAgentRepository.findNameAndCodeByKeyWord(keyWord, PageRequest.of(0, 25));
//        return resWithPage.getContent();
//    }
    /**
     * Get one AdmAgent by id.
     *
     * @param admAccountContactDTO the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public AdmAgentAccountDTO findOne(AdmAccountContactDTO admAccountContactDTO) {
        log.debug("Request to get AdmAgent : {}", admAccountContactDTO.getUserId());
        String orgId = admAccountContactDTO.getWkutNormal();
        Optional<AdmAgentDTO> admAgentDTO = admAgentRepository.findById(orgId).map(admAgentMapper::toDto);
        //轉為admAccount userId (PccOrgId+"-0") 做查詢
        admAccountContactDTO.setUserId(admAgentDTO.get().getPccOrgId()+"-0");
        AdmAccountContactDTO admAccountContact = admAccountRepository.findByUserId(admAccountContactDTO);
        //將取到的 admAccount Status 放到 AdmAgent Descript 至前端做狀態的判斷
        AdmAgentAccountDTO admAgentAccountDTO = new AdmAgentAccountDTO();
        BeanUtils.copyProperties(admAgentDTO.get(),admAgentAccountDTO);
        if(admAccountContact != null) {
            admAgentAccountDTO.setAuditStatus(admAccountContact.getAuditStatus());
            admAgentAccountDTO.setStatus(admAccountContact.getStatus());
        }
        return admAgentAccountDTO;
    }

    /**
     * Get all the AdmAgentDTO.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmAgentDTO> findNameAndCodeByKeyWord(Map<String, String> keyWord) {
        log.debug("Request to get all AdmAgentDTO");
        String keywordName = "";
        String keywordId = "";
        if("D".equals(keyWord.get("type"))){
            keywordName = keyWord.get("keyWord");
        }else{
            keywordId = keyWord.get("keyWord");
        }
        Page<AdmAgentDTO> resWithPage = admAgentRepository.findNameAndCodeByKeyWord(keywordName, keywordId, PageRequest.of(0, 25));
        return resWithPage.getContent();
    }

    @Transactional(readOnly = true)
    public Optional<AdmAgentDTO> findOneByOrgId(String orgId){
        return admAgentRepository.findById(orgId).map(admAgentMapper::toDto);
    }

    /**
     *  bid用，透過 org id 去撈出該org id上級的機關
     */
    @Transactional(readOnly = true)
    public List<AdmAgentDTO> findBidExamineUnit(String wkutCode){
        return admAgentRepository.findBidExamineUnit(wkutCode);
    }
}
