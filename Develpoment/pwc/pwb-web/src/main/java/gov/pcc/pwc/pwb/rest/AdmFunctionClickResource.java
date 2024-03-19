package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmFunctionClick;
import gov.pcc.pwc.repository.AdmFunctionClickRepository;
import gov.pcc.pwc.service.AdmFunctionClickService;
import gov.pcc.pwc.service.criteria.AdmFunctionClickCriteria;
import gov.pcc.pwc.service.criteria.AdmFunctionUsedRecordCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionClickDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
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
 * REST controller for managing {@link AdmFunctionClick}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmFunctionClickResource {

    private final Logger log = LoggerFactory.getLogger(AdmFunctionClickResource.class);

    private static final String ENTITY_NAME = "admFunctionClick";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmFunctionClickService admFunctionClickService;

    private final AdmFunctionClickRepository admFunctionClickRepository;

    public AdmFunctionClickResource(
        AdmFunctionClickService admFunctionClickService,
        AdmFunctionClickRepository admFunctionClickRepository
    ) {
        this.admFunctionClickService = admFunctionClickService;
        this.admFunctionClickRepository = admFunctionClickRepository;
    }

    /**
     * {@code POST  /adm-function-clicks} : Create a new admFunctionClick.
     *
     * @param admFunctionClick the admFunctionClick to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admFunctionClick, or with status {@code 400 (Bad Request)} if the admFunctionClick has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-function-clicks")
    public ResponseEntity<AdmFunctionClick> createAdmFunctionClick(@Valid @RequestBody AdmFunctionClick admFunctionClick)
        throws URISyntaxException {
        log.debug("REST request to save AdmFunctionClick : {}", admFunctionClick);
        if (admFunctionClick.getId() != null) {
            throw new BadRequestAlertException("A new admFunctionClick cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmFunctionClick result = admFunctionClickRepository.save(admFunctionClick);
        return ResponseEntity
            .created(new URI("/api/adm-function-clicks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-function-clicks/:id} : Updates an existing admFunctionClick.
     *
     * @param id the id of the admFunctionClick to save.
     * @param admFunctionClick the admFunctionClick to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admFunctionClick,
     * or with status {@code 400 (Bad Request)} if the admFunctionClick is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admFunctionClick couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-function-clicks/{id}")
    public ResponseEntity<AdmFunctionClick> updateAdmFunctionClick(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmFunctionClick admFunctionClick
    ) throws URISyntaxException {
        log.debug("REST request to update AdmFunctionClick : {}, {}", id, admFunctionClick);
        if (admFunctionClick.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admFunctionClick.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admFunctionClickRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmFunctionClick result = admFunctionClickRepository.save(admFunctionClick);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admFunctionClick.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-function-clicks/:id} : Partial updates given fields of an existing admFunctionClick, field will ignore if it is null
     *
     * @param id the id of the admFunctionClick to save.
     * @param admFunctionClick the admFunctionClick to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admFunctionClick,
     * or with status {@code 400 (Bad Request)} if the admFunctionClick is not valid,
     * or with status {@code 404 (Not Found)} if the admFunctionClick is not found,
     * or with status {@code 500 (Internal Server Error)} if the admFunctionClick couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-function-clicks/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmFunctionClick> partialUpdateAdmFunctionClick(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmFunctionClick admFunctionClick
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmFunctionClick partially : {}, {}", id, admFunctionClick);
        if (admFunctionClick.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admFunctionClick.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admFunctionClickRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmFunctionClick> result = admFunctionClickRepository
            .findById(admFunctionClick.getId())
            .map(
                existingAdmFunctionClick -> {
                    if (admFunctionClick.getFunctionId() != null) {
                        existingAdmFunctionClick.setFunctionId(admFunctionClick.getFunctionId());
                    }
                    if (admFunctionClick.getFunctionExtendId() != null) {
                        existingAdmFunctionClick.setFunctionExtendId(admFunctionClick.getFunctionExtendId());
                    }
                    if (admFunctionClick.getCreateTime() != null) {
                        existingAdmFunctionClick.setCreateTime(admFunctionClick.getCreateTime());
                    }

                    return existingAdmFunctionClick;
                }
            )
            .map(admFunctionClickRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admFunctionClick.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-function-clicks} : get all the admFunctionClicks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admFunctionClicks in body.
     */
    @GetMapping("/adm-function-clicks")
    public List<AdmFunctionClick> getAllAdmFunctionClicks() {
        log.debug("REST request to get all AdmFunctionClicks");
        return admFunctionClickRepository.findAll();
    }

    /**
     * {@code GET  /adm-function-clicks/:id} : get the "id" admFunctionClick.
     *
     * @param id the id of the admFunctionClick to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admFunctionClick, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-function-clicks/{id}")
    public ResponseEntity<AdmFunctionClick> getAdmFunctionClick(@PathVariable Long id) {
        log.debug("REST request to get AdmFunctionClick : {}", id);
        Optional<AdmFunctionClick> admFunctionClick = admFunctionClickRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admFunctionClick);
    }

    /**
     * {@code DELETE  /adm-function-clicks/:id} : delete the "id" admFunctionClick.
     *
     * @param id the id of the admFunctionClick to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-function-clicks/{id}")
    public ResponseEntity<Void> deleteAdmFunctionClick(@PathVariable Long id) {
        log.debug("REST request to delete AdmFunctionClick : {}", id);
        admFunctionClickRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /adm-function-clicks/criteria-jpa} : get admFunctionClicks by criteria.
     *
     * @param criteria the criteria of the AdmFunctionClickCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmFunctionClickDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-function-clicks/criteria-jpa")
    public Page<AdmFunctionClickDTO> getAdmFunctionClickByCriteria(@Valid @RequestBody AdmFunctionClickCriteria criteria) {
        log.debug("REST request to get AdmFunctionClickDTO : {}", criteria);
        return admFunctionClickService.findByCriteria(criteria);
    }


    /**
     * {@code POST  /adm-function-clicks/statistics} : get admFunctionClicks statistics by criteria.
     *
     * @param criteria the criteria of the AdmFunctionClickCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmFunctionClickDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-function-clicks/statistics")
    public Page<AdmFunctionClickDTO> getAdmFunctionClickStatisticsByCriteria(@Valid @RequestBody AdmFunctionClickCriteria criteria) {
        log.debug("REST request to get AdmFunctionClickDTO of statistics : {}", criteria);
        return admFunctionClickService.findStatisticsByCriteria(criteria);
    }

    /**
     * {@code POST  /adm-function-clicks/statistics/info/{functionId}} : get admFunctionClicks statistics info by criteria.
     *
     * @param functionId the functionId of the admFunctionClicks to retrieve.
     * @param criteria the criteria of the AdmFunctionClickCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmFunctionClickDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-function-clicks/statistics/info/{functionId}")
    public List<AdmFunctionClickDTO> getAdmFunctionClickStatisticsInfoByCriteria(@Valid @RequestBody AdmFunctionClickCriteria criteria, @PathVariable String functionId) {
        log.debug("REST request to get AdmFunctionClickDTO of statistics info: {}", criteria);
        criteria.setFunctionId(functionId);
        return admFunctionClickService.findStatisticsInfoByCriteria(criteria);
    }
}
