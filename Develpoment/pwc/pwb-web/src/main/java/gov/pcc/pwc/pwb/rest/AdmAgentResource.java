package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmAgent;
import gov.pcc.pwc.repository.AdmAgentRepository;
import gov.pcc.pwc.service.AdmAgentService;
import gov.pcc.pwc.service.criteria.AdmAgentCriteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link AdmAgent}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmAgentResource {

    private final Logger log = LoggerFactory.getLogger(AdmAgentResource.class);

    private static final String ENTITY_NAME = "admAgent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmAgentRepository admAgentRepository;

    private final AdmAgentService admAgentService;

    public AdmAgentResource(AdmAgentRepository admAgentRepository, AdmAgentService admAgentService) {
        this.admAgentRepository = admAgentRepository;
        this.admAgentService = admAgentService;
    }

    /**
     * {@code POST  /adm-agents} : Create a new admAgent.
     *
     * @param admAgent the admAgent to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admAgent, or with status {@code 400 (Bad Request)} if the admAgent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-agents")
    public ResponseEntity<AdmAgent> createAdmAgent(@Valid @RequestBody AdmAgent admAgent) throws URISyntaxException {
        log.debug("REST request to save AdmAgent : {}", admAgent);
        if (admAgent.getOrgId() != null) {
            throw new BadRequestAlertException("A new admAgent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmAgent result = admAgentRepository.save(admAgent);
        return ResponseEntity
            .created(new URI("/api/adm-agents/" + result.getOrgId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getOrgId()))
            .body(result);
    }

    /**
     * {@code GET  /adm-agents} : get all the admAgents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admAgents in body.
     */
    @GetMapping("/adm-agents")
    public List<AdmAgent> getAllAdmAgents() {
        log.debug("REST request to get all AdmAgents");
        return admAgentRepository.findAll();
    }

    /**
     * {@code GET  /adm-agents/:id} : get the "id" admAgent.
     *
     * @param id the id of the admAgent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admAgent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-agents/{id}")
    public ResponseEntity<AdmAgent> getAdmAgent(@PathVariable String id) {
        log.debug("REST request to get AdmAgent : {}", id);
        Optional<AdmAgent> admAgent = admAgentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admAgent);
    }

    /**
     * {@code POST  /adm-agents/criteria-jpa} : get admAgent by criteria.
     *
     * @param criteria the criteria of the AdmAgentCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmAgentDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-agents/criteria-jpa")
    public Page<AdmAgentDTO> getAdmAgentsByCriteria(@Valid @RequestBody AdmAgentCriteria criteria) {
        log.debug("REST request to get AdmAgentDTO : {}", criteria);
        return admAgentService.findByCriteria(criteria);
    }
}
