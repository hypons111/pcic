package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.ProOrgSuggestionRepository;
import gov.pcc.pwc.service.ProOrgSuggestionService;
import gov.pcc.pwc.service.criteria.ProOrgSuggestionHistoryCriteria;
import gov.pcc.pwc.service.criteria.ProOrgSuggestionCriteria;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import gov.pcc.pwc.service.dto.ProOrgSuggestionDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.ProOrgSuggestionHistoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.ProOrgSuggestion}.
 */
@RestController
@RequestMapping("/api")
public class ProOrgSuggestionResource {

    private final Logger log = LoggerFactory.getLogger(ProOrgSuggestionResource.class);

    private static final String ENTITY_NAME = "proOrgSuggestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProOrgSuggestionService proOrgSuggestionService;

    private final ProOrgSuggestionRepository proOrgSuggestionRepository;

    public ProOrgSuggestionResource(
        ProOrgSuggestionService proOrgSuggestionService,
        ProOrgSuggestionRepository proOrgSuggestionRepository
    ) {
        this.proOrgSuggestionService = proOrgSuggestionService;
        this.proOrgSuggestionRepository = proOrgSuggestionRepository;
    }

    /**
     * {@code POST  /pro-org-suggestions} : Create a new proOrgSuggestion.
     *
     * @param proOrgSuggestionDTO the proOrgSuggestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proOrgSuggestionDTO, or with status {@code 400 (Bad Request)} if the proOrgSuggestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-org-suggestions")
    public ResponseEntity<ProOrgSuggestionDTO> createProOrgSuggestion(@Valid @RequestBody ProOrgSuggestionDTO proOrgSuggestionDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProOrgSuggestion : {}", proOrgSuggestionDTO);
        if (proOrgSuggestionDTO.getProOrgSuggestionId() != null) {
            throw new BadRequestAlertException("A new proOrgSuggestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProOrgSuggestionDTO result = proOrgSuggestionService.save(proOrgSuggestionDTO);
        return ResponseEntity
            .created(new URI("/api/pro-org-suggestions/" + result.getProOrgSuggestionId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProOrgSuggestionId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-org-suggestions/:proOrgSuggestionId} : Updates an existing proOrgSuggestion.
     *
     * @param proOrgSuggestionId  the id of the proOrgSuggestionDTO to save.
     * @param proOrgSuggestionDTO the proOrgSuggestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proOrgSuggestionDTO,
     * or with status {@code 400 (Bad Request)} if the proOrgSuggestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proOrgSuggestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-org-suggestions/{proOrgSuggestionId}")
    public ResponseEntity<ProOrgSuggestionDTO> updateProOrgSuggestion(
        @PathVariable(value = "proOrgSuggestionId", required = false) final Long proOrgSuggestionId,
        @Valid @RequestBody ProOrgSuggestionDTO proOrgSuggestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProOrgSuggestion : {}, {}", proOrgSuggestionId, proOrgSuggestionDTO);
        if (proOrgSuggestionDTO.getProOrgSuggestionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proOrgSuggestionId, proOrgSuggestionDTO.getProOrgSuggestionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proOrgSuggestionRepository.existsById(proOrgSuggestionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProOrgSuggestionDTO result = proOrgSuggestionService.save(proOrgSuggestionDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    proOrgSuggestionDTO.getProOrgSuggestionId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pro-org-suggestions/:proOrgSuggestionId} : Partial updates given fields of an existing proOrgSuggestion, field will ignore if it is null
     *
     * @param proOrgSuggestionId  the id of the proOrgSuggestionDTO to save.
     * @param proOrgSuggestionDTO the proOrgSuggestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proOrgSuggestionDTO,
     * or with status {@code 400 (Bad Request)} if the proOrgSuggestionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the proOrgSuggestionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the proOrgSuggestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pro-org-suggestions/{proOrgSuggestionId}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ProOrgSuggestionDTO> partialUpdateProOrgSuggestion(
        @PathVariable(value = "proOrgSuggestionId", required = false) final Long proOrgSuggestionId,
        @NotNull @RequestBody ProOrgSuggestionDTO proOrgSuggestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProOrgSuggestion partially : {}, {}", proOrgSuggestionId, proOrgSuggestionDTO);
        if (proOrgSuggestionDTO.getProOrgSuggestionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proOrgSuggestionId, proOrgSuggestionDTO.getProOrgSuggestionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proOrgSuggestionRepository.existsById(proOrgSuggestionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProOrgSuggestionDTO> result = proOrgSuggestionService.partialUpdate(proOrgSuggestionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proOrgSuggestionDTO.getProOrgSuggestionId().toString())
        );
    }

    /**
     * {@code GET  /pro-org-suggestions} : get all the proOrgSuggestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proOrgSuggestions in body.
     */
    @GetMapping("/pro-org-suggestions")
    public List<ProOrgSuggestionDTO> getAllProOrgSuggestions() {
        log.debug("REST request to get all ProOrgSuggestions");
        return proOrgSuggestionService.findAll();
    }

    /**
     * {@code GET  /pro-org-suggestions/:id} : get the "id" proOrgSuggestion.
     *
     * @param id the id of the proOrgSuggestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proOrgSuggestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-org-suggestions/{id}")
    public ResponseEntity<ProOrgSuggestionDTO> getProOrgSuggestion(@PathVariable Long id) {
        log.debug("REST request to get ProOrgSuggestion : {}", id);
        Optional<ProOrgSuggestionDTO> proOrgSuggestionDTO = proOrgSuggestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proOrgSuggestionDTO);
    }

    /**
     * {@code DELETE  /pro-org-suggestions/:id} : delete the "id" proOrgSuggestion.
     *
     * @param id the id of the proOrgSuggestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-org-suggestions/{id}")
    public ResponseEntity<Void> deleteProOrgSuggestion(@PathVariable Long id) {
        log.debug("REST request to delete ProOrgSuggestion : {}", id);
        proOrgSuggestionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /pro-org-suggestions/getProOrgSuggestionHistory} : get ProOrgSuggestion by criteria.
     *
     * @param criteria the criteria of the ProOrgSuggestionHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ProOrgSuggestionHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pro-org-suggestions/getProOrgSuggestionHistory")
    public List<ProOrgSuggestionHistoryDTO> getProOrgSuggestionHistoryByCriteria(@RequestBody ProOrgSuggestionHistoryDTO criteria) throws InterruptedException {
        log.debug("REST request to get ProOrgSuggestion: {}", criteria);
        return proOrgSuggestionService.findByCriteria(criteria);
    }

    @PostMapping("/pro-org-suggestions/getProOrgSuggestionHistoryByCriteria")
    public Page<ProOrgSuggestionHistoryDTO> getProOrgSuggestionHistoryByCriteria(@Valid @RequestBody ProOrgSuggestionHistoryCriteria criteria) throws InterruptedException {
        log.debug("REST request to get ProOrgSuggestion: {}", criteria);
        return proOrgSuggestionService.findByCriteria(criteria);
    }

    /**
     * {@code POST  /pro-org-suggestions/getComStatus} : get proOrgSuggestions by criteria.
     *
     * @param criteria the criteria of the ProOrgSuggestionCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ComStatusSearchDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pro-org-suggestions/getComStatus")
    public List<ComStatusSearchDTO> getComStatus(@RequestBody ProOrgSuggestionCriteria criteria) {
        log.debug("REST request to get ProOrgSuggestion : {}", criteria);
        return proOrgSuggestionService.findAllByCriteria(criteria);
    }

    /**
     * {@code POST  /pro-org-suggestions/getComStatusPage} : get proOrgSuggestions by criteria.
     *
     * @param criteria the criteria of the ProOrgSuggestionCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ComStatusSearchDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pro-org-suggestions/getComStatusPage")
    public Page<ComStatusSearchDTO> getComStatusPage(@Valid @RequestBody ProOrgSuggestionCriteria criteria) throws InterruptedException {
        log.debug("REST request to get CmsComp : {}", criteria);
        return proOrgSuggestionService.findPageByCriteria(criteria);
    }

}
