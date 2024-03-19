package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.AdmAgentRepository;
import gov.pcc.pwc.service.AdmAgentService;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmAgentAccountDTO;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.ProAdmAgentOrgNameDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AdmAgentResource {

    private final Logger log = LoggerFactory.getLogger(AdmAgentResource.class);

    private static final String ENTITY_NAME = "AdmAgent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmAgentService admAgentService;

    private final AdmAgentRepository admAgentRepository;

    public AdmAgentResource(AdmAgentService admAgentService, AdmAgentRepository admAgentRepository) {

        this.admAgentService = admAgentService;
        this.admAgentRepository = admAgentRepository;
    }

    /**
     * {@code GET  //adm-agent-findAll} : get all the admAgent.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admAgent in body.
     */
    @PostMapping("/service/adm-agent-findAll")
    public List<AdmAgentDTO> getAllAdmAgent(@RequestBody Map<String, String> keyWord) {
        log.debug("REST request to get all AdmAgent");
        return admAgentService.findNameAndCodeByKeyWord(keyWord);
    }

    /**
     * {@code GET  /service/adm-agent-info : get the admAccountContactDTO
     *
     * @param admAccountContactDTO the id of the AdmAgentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmAgentDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/service/adm-agent-info")
    public AdmAgentAccountDTO getAdmAgentInfo(@Valid @RequestBody AdmAccountContactDTO admAccountContactDTO) {
        log.debug("REST request to get PwcDownload : {}", admAccountContactDTO);
        AdmAgentAccountDTO admAgentAccountDTO = admAgentService.findOne(admAccountContactDTO);
        return admAgentAccountDTO;
    }


    /**
     * 透過pk找DTO
     * @param id orgId
     * @return admAgentDTO
     */
    @GetMapping("/adm-agent/{id}")
    public ResponseEntity<AdmAgentDTO> getAdmAgentDTO(@PathVariable String id){
        log.debug("REST request to get AdmAgentDTO : {}", id);
        Optional<AdmAgentDTO> admAgentDTO = admAgentService.findOneByOrgId(id);
        return ResponseUtil.wrapOrNotFound(admAgentDTO);
    }

    //ITREE SELECT 透過orgName查出
    @PostMapping("/adm-agents/orgId-and-orgName/keyWord")
    public List<ProAdmAgentOrgNameDTO> findOrgIdAndOrgNameByKeyWord(@RequestBody Map<String, String> keyWord){
        log.debug("REST request to get ProAdmAgentOrgNameDTO orgId and orgName : {}", keyWord.get("keyWord"));
        List<ProAdmAgentOrgNameDTO> res = admAgentService.findOrgIdAndOrgNameByKeyWord(keyWord.get("keyWord"));
        return res;
    }

}
