package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.EngReviewLogRepository;
import gov.pcc.pwc.service.EngEngrCertificateService;
import gov.pcc.pwc.service.EngReviewLogService;
import gov.pcc.pwc.service.criteria.EngReviewLogCriteria;
import gov.pcc.pwc.service.dto.EngEngrCertificateDTO;
import gov.pcc.pwc.service.dto.EngReviewLogDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.EngReviewAndFunctionDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngReviewLog}.
 */
@RestController
@RequestMapping("/api")
public class EngReviewLogResource {

    private final Logger log = LoggerFactory.getLogger(EngReviewLogResource.class);

    private static final String ENTITY_NAME = "engReviewLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngReviewLogService engReviewLogService;

    private final EngEngrCertificateService engEngrCertificateService;

    private final EngReviewLogRepository engReviewLogRepository;

    public EngReviewLogResource(EngReviewLogService engReviewLogService, EngEngrCertificateService engEngrCertificateService, EngReviewLogRepository engReviewLogRepository) {
        this.engReviewLogService = engReviewLogService;
        this.engEngrCertificateService = engEngrCertificateService;
        this.engReviewLogRepository = engReviewLogRepository;
    }

    /**
     * {@code POST  /eng-review-logs} : Create a new engReviewLog.
     *
     * @param engReviewAndFunctionDTO the engReviewLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engReviewLogDTO, or with status {@code 400 (Bad Request)} if the engReviewLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-review-logs")
    @Transactional
    public ResponseEntity<EngReviewAndFunctionDTO> createEngReviewLog(@Valid @RequestBody EngReviewAndFunctionDTO engReviewAndFunctionDTO)
        throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to insert EngReviewLog : {} and update : {}", engReviewAndFunctionDTO.getEngReviewLogDTO(), engReviewAndFunctionDTO.getEngEngrCertificateDTO());
        if (engReviewAndFunctionDTO.getEngReviewLogDTO().getEngReviewLogId() != null) {
            throw new BadRequestAlertException("A new engReviewLog cannot already have an ID", ENTITY_NAME, "idexists");
        }

        EngReviewAndFunctionDTO newEngReviewAndFunctionDTO = new EngReviewAndFunctionDTO();
        EngReviewLogDTO engReviewLogDTO = engReviewLogService.insert(engReviewAndFunctionDTO.getEngReviewLogDTO());
        newEngReviewAndFunctionDTO.setEngReviewLogDTO(engReviewLogDTO);

        switch (engReviewLogDTO.getReviewCaseType()) {
            case "1":
//                EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateService.save(engReviewAndFunctionDTO.getEngEngrCertificateDTO()).getEngEngrCertificateDTO();
//                newEngReviewAndFunctionDTO.setEngEngrCertificateDTO(engEngrCertificateDTO);
                break;
            case "2":
                break;
            case "3":

        }

        return ResponseEntity
            .created(new URI("/api/eng-review-logs/" + engReviewLogDTO.getEngReviewLogId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, engReviewLogDTO.getEngReviewLogId()))
            .body(newEngReviewAndFunctionDTO);
    }
//    public ResponseEntity<EngReviewLogDTO> createEngReviewLog(@Valid @RequestBody EngReviewLogDTO engReviewLogDTO)
//        throws URISyntaxException, TypeVariableNotMatchException {
//        log.debug("REST request to insert EngReviewLog : {}", engReviewLogDTO);
//        if (engReviewLogDTO.getEngReviewLogId() != null) {
//            throw new BadRequestAlertException("A new engReviewLog cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        EngReviewLogDTO result = engReviewLogService.insert(engReviewLogDTO);
//        return ResponseEntity
//            .created(new URI("/api/eng-review-logs/" + result.getEngReviewLogId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngReviewLogId()))
//            .body(result);
//    }

    /**
     * {@code PUT  /eng-review-logs/:engReviewLogId} : Updates an existing engReviewLog.
     *
     * @param engReviewLogId the id of the engReviewLogDTO to save.
     * @param engReviewLogDTO the engReviewLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engReviewLogDTO,
     * or with status {@code 400 (Bad Request)} if the engReviewLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engReviewLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-review-logs/{engReviewLogId}")
    public ResponseEntity<EngReviewLogDTO> updateEngReviewLog(
        @PathVariable(value = "engReviewLogId", required = false) final String engReviewLogId,
        @Valid @RequestBody EngReviewLogDTO engReviewLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EngReviewLog : {}, {}", engReviewLogId, engReviewLogDTO);
        if (engReviewLogDTO.getEngReviewLogId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engReviewLogId, engReviewLogDTO.getEngReviewLogId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engReviewLogRepository.existsById(engReviewLogId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngReviewLogDTO result = engReviewLogService.save(engReviewLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engReviewLogDTO.getEngReviewLogId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-review-logs/:engReviewLogId} : Partial updates given fields of an existing engReviewLog, field will ignore if it is null
     *
     * @param engReviewLogId the id of the engReviewLogDTO to save.
     * @param engReviewLogDTO the engReviewLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engReviewLogDTO,
     * or with status {@code 400 (Bad Request)} if the engReviewLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engReviewLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engReviewLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-review-logs/{engReviewLogId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngReviewLogDTO> partialUpdateEngReviewLog(
        @PathVariable(value = "engReviewLogId", required = false) final String engReviewLogId,
        @NotNull @RequestBody EngReviewLogDTO engReviewLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngReviewLog partially : {}, {}", engReviewLogId, engReviewLogDTO);
        if (engReviewLogDTO.getEngReviewLogId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engReviewLogId, engReviewLogDTO.getEngReviewLogId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engReviewLogRepository.existsById(engReviewLogId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngReviewLogDTO> result = engReviewLogService.partialUpdate(engReviewLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engReviewLogDTO.getEngReviewLogId())
        );
    }

    /**
     * {@code GET  /eng-review-logs} : get all the engReviewLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engReviewLogs in body.
     */
    @GetMapping("/eng-review-logs")
    public List<EngReviewLogDTO> getAllEngReviewLogs() {
        log.debug("REST request to get all EngReviewLogs");
        return engReviewLogService.findAll();
    }

    /**
     * {@code GET  /eng-review-logs/:id} : get the "id" engReviewLog.
     *
     * @param id the id of the engReviewLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engReviewLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-review-logs/{id}")
    public ResponseEntity<EngReviewLogDTO> getEngReviewLog(@PathVariable String id) {
        log.debug("REST request to get EngReviewLog : {}", id);
        Optional<EngReviewLogDTO> engReviewLogDTO = engReviewLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engReviewLogDTO);
    }

    /**
     * {@code DELETE  /eng-review-logs/:id} : delete the "id" engReviewLog.
     *
     * @param id the id of the engReviewLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-review-logs/{id}")
    public ResponseEntity<Void> deleteEngReviewLog(@PathVariable String id) {
        log.debug("REST request to delete EngReviewLog : {}", id);
        engReviewLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code POST  /eng-review-logs/criteria-jpa} : get engReviewLogs by criteria.
     *
     * @param criteria the engReviewLogCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engReviewLogs in body, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/eng-review-logs/criteria-jpa")
    public List<EngReviewLogDTO> getEngReviewLogByCriteria(@Valid @RequestBody EngReviewLogCriteria criteria) throws InterruptedException {
        log.debug("REST request to get EngReviewLogCriteria : {}", criteria);
        return engReviewLogService.findByCriteria(criteria);
    }
}
