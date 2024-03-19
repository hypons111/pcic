package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.RecAnalysisRepository;
import gov.pcc.pwc.service.RecAnalysisService;
import gov.pcc.pwc.service.criteria.RecAnalysisCriteria;
import gov.pcc.pwc.service.dto.RecAnalysisDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.RecAnalysis}.
 */
@RestController
@RequestMapping("/api")
public class RecAnalysisResource {

    private final Logger log = LoggerFactory.getLogger(RecAnalysisResource.class);

    private static final String ENTITY_NAME = "recAnalysis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecAnalysisService recAnalysisService;

    private final RecAnalysisRepository recAnalysisRepository;

    public RecAnalysisResource(RecAnalysisService recAnalysisService, RecAnalysisRepository recAnalysisRepository) {
        this.recAnalysisService = recAnalysisService;
        this.recAnalysisRepository = recAnalysisRepository;
    }

    /**
     * {@code POST  /rec-analysis} : Create a new recAnalysis.
     *
     * @param recAnalysisDTO the recAnalysisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recAnalysisDTO, or with status {@code 400 (Bad Request)} if the recAnalysis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rec-analysis")
    public ResponseEntity<RecAnalysisDTO> createRecAnalysis(@Valid @RequestBody RecAnalysisDTO recAnalysisDTO) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to save RecAnalysis : {}", recAnalysisDTO);
        //新增主鍵
        recAnalysisService.beforeInsertPackage(recAnalysisDTO);

        log.debug("REST request to save RecAnalysis : {}", recAnalysisDTO);
        RecAnalysisDTO result = recAnalysisService.save(recAnalysisDTO);
        return ResponseEntity
            .created(new URI("/api/rec-analysis/" + result.getAnalysisNo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getAnalysisNo()))
            .body(result);
    }

    /**
     * {@code PUT  /rec-analysis/:analysisNo} : Updates an existing recAnalysis.
     *
     * @param analysisNo the id of the recAnalysisDTO to save.
     * @param recAnalysisDTO the recAnalysisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recAnalysisDTO,
     * or with status {@code 400 (Bad Request)} if the recAnalysisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recAnalysisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rec-analysis/{analysisNo}")
    public ResponseEntity<RecAnalysisDTO> updateRecAnalysis(
        @PathVariable(value = "analysisNo", required = false) final String analysisNo,
        @Valid @RequestBody RecAnalysisDTO recAnalysisDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RecAnalysis : {}, {}", analysisNo, recAnalysisDTO);
        if (recAnalysisDTO.getAnalysisNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(analysisNo, recAnalysisDTO.getAnalysisNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recAnalysisRepository.existsById(analysisNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RecAnalysisDTO result = recAnalysisService.save(recAnalysisDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recAnalysisDTO.getAnalysisNo()))
            .body(result);
    }

    /**
     * {@code PATCH  /rec-analysis/:analysisNo} : Partial updates given fields of an existing recAnalysis, field will ignore if it is null
     *
     * @param analysisNo the id of the recAnalysisDTO to save.
     * @param recAnalysisDTO the recAnalysisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recAnalysisDTO,
     * or with status {@code 400 (Bad Request)} if the recAnalysisDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recAnalysisDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recAnalysisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rec-analysis/{analysisNo}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RecAnalysisDTO> partialUpdateRecAnalysis(
        @PathVariable(value = "analysisNo", required = false) final String analysisNo,
        @NotNull @RequestBody RecAnalysisDTO recAnalysisDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RecAnalysis partially : {}, {}", analysisNo, recAnalysisDTO);
        if (recAnalysisDTO.getAnalysisNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(analysisNo, recAnalysisDTO.getAnalysisNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recAnalysisRepository.existsById(analysisNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecAnalysisDTO> result = recAnalysisService.partialUpdate(recAnalysisDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recAnalysisDTO.getAnalysisNo())
        );
    }

    /**
     * {@code GET  /rec-analysis} : get all the recAnalysis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recAnalysis in body.
     */
    @GetMapping("/rec-analysis")
    public List<RecAnalysisDTO> getAllRecAnalysis() {
        log.debug("REST request to get all RecAnalysis");
        return recAnalysisService.findAll();
    }

    /**
     * {@code GET  /rec-analysis/:id} : get the "id" recAnalysis.
     *
     * @param id the id of the recAnalysisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recAnalysisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rec-analysis/{id}")
    public ResponseEntity<RecAnalysisDTO> getRecAnalysis(@PathVariable String id) {
        log.debug("REST request to get RecAnalysis : {}", id);
        Optional<RecAnalysisDTO> recAnalysisDTO = recAnalysisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recAnalysisDTO);
    }

    //災損情形查詢
    @PostMapping("/rec-analysis/criteria-jpa")
    public Page<RecAnalysisDTO> getRecAnalysisByCriteria(@Valid @RequestBody RecAnalysisCriteria criteria) throws InterruptedException {
        log.debug("REST request to get RecAnalysis : {}",criteria);
        return recAnalysisService.findByCriteria(criteria);
    }


    /**
     * {@code DELETE  /rec-analysis/:id} : delete the "id" recAnalysis.
     *
     * @param id the id of the recAnalysisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rec-analysis/{id}")
    public ResponseEntity<Void> deleteRecAnalysis(@PathVariable String id) {
        log.debug("REST request to delete RecAnalysis : {}", id);
        recAnalysisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
