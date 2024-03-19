package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmCompany;
import gov.pcc.pwc.repository.AdmAccountRepository;
import gov.pcc.pwc.repository.AdmCompanyRepository;
import gov.pcc.pwc.service.dto.AdmCompanyApplyDTO;
import gov.pcc.pwc.service.dto.AdmCompanyDTO;
import gov.pcc.pwc.service.mapper.AdmCompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdmCompany}.
 */
@Service
@Transactional
public class AdmCompanyService {

    private final Logger log = LoggerFactory.getLogger(AdmCompanyService.class);

    private final AdmCompanyRepository admCompanyRepository;

    private final AdmCompanyMapper admCompanyMapper;


    public AdmCompanyService(AdmCompanyRepository admCompanyRepository, AdmCompanyMapper admCompanyMapper, AdmAccountRepository admAccountRepository) {
        this.admCompanyRepository = admCompanyRepository;
        this.admCompanyMapper = admCompanyMapper;
    }

    /**
     * Save a admCompany.
     *
     * @param admCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmCompanyDTO save(AdmCompanyDTO admCompanyDTO) {
        log.debug("Request to save AdmCompany : {}", admCompanyDTO);
        AdmCompany admCompany = admCompanyMapper.toEntity(admCompanyDTO);
        admCompany = admCompanyRepository.save(admCompany);
        return admCompanyMapper.toDto(admCompany);
    }

    /**
     * Partially update a admCompany.
     *
     * @param admCompanyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AdmCompanyDTO> partialUpdate(AdmCompanyDTO admCompanyDTO) {
        log.debug("Request to partially update AdmCompany : {}", admCompanyDTO);

        return admCompanyRepository
            .findById(admCompanyDTO.getCompIdno())
            .map(existingAdmCompany -> {
                admCompanyMapper.partialUpdate(existingAdmCompany, admCompanyDTO);

                return existingAdmCompany;
            })
            .map(admCompanyRepository::save)
            .map(admCompanyMapper::toDto);
    }

    /**
     * Get all the admCompanies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmCompanyDTO> findAll() {
        log.debug("Request to get all AdmCompanies");
        return admCompanyRepository.findAll().stream().map(admCompanyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one admCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdmCompanyDTO> findOne(String id) {
        log.debug("Request to get AdmCompany : {}", id);
        return admCompanyRepository.findById(id).map(admCompanyMapper::toDto);
    }

    /**
     * Delete the admCompany by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete AdmCompany : {}", id);
        admCompanyRepository.deleteById(id);
    }

    /**
     * Get one AdmCompany by compIdno.
     *
     * @param compIndo the compIdno of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public AdmCompanyApplyDTO findOneCompany(String compIndo) {
        String userId = compIndo + "-0";
        log.debug("Request to get AdmCompany : {}", userId);

        // 查詢ADM_ACCOUNT是否有此userId
        return admCompanyRepository.findByUserId(userId);
    }

    /**
     * Get all the AdmAgentDTO.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmCompanyDTO> getAllAdmCompaniesForUnit(Map<String, String> keyWord) {
        log.debug("Request to get all AdmAgentDTO");
        String keywordName = keyWord.get("keyWord");
        Page<AdmCompanyDTO> resWithPage = admCompanyRepository.findByAdmCompanyAll(keywordName, PageRequest.of(0, 25));
        return resWithPage.getContent();
    }
}
