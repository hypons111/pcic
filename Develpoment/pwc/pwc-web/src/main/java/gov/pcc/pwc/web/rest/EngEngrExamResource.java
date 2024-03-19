package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.EngEngrExamRepository;
import gov.pcc.pwc.service.EngEngrExamService;
import gov.pcc.pwc.service.criteria.EngEngrExamCriteria;
import gov.pcc.pwc.service.dto.EngEngrExamDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngEngrExam}.
 */
@RestController
@RequestMapping("/api")
public class EngEngrExamResource {

    private final Logger log = LoggerFactory.getLogger(EngEngrExamResource.class);

    private static final String ENTITY_NAME = "engEngrExam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngEngrExamService engEngrExamService;

    private final EngEngrExamRepository engEngrExamRepository;

    public EngEngrExamResource(EngEngrExamService engEngrExamService, EngEngrExamRepository engEngrExamRepository) {
        this.engEngrExamService = engEngrExamService;
        this.engEngrExamRepository = engEngrExamRepository;
    }

    /**
     * {@code POST  /eng-engr-exams} : Create a new engEngrExam.
     *
     * @param engEngrExamDTO the engEngrExamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engEngrExamDTO, or with status {@code 400 (Bad Request)} if the engEngrExam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-engr-exams")
    public ResponseEntity<EngEngrExamDTO> createEngEngrExam(@Valid @RequestBody EngEngrExamDTO engEngrExamDTO) throws URISyntaxException {
        log.debug("REST request to save EngEngrExam : {}", engEngrExamDTO);
        if (engEngrExamDTO.getEngEngrExamId() != null) {
            throw new BadRequestAlertException("A new engEngrExam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngEngrExamDTO result = engEngrExamService.save(engEngrExamDTO);
        return ResponseEntity
            .created(new URI("/api/eng-engr-exams/" + result.getEngEngrExamId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngEngrExamId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-engr-exams/:engEngrExamId} : Updates an existing engEngrExam.
     *
     * @param engEngrExamId the id of the engEngrExamDTO to save.
     * @param engEngrExamDTO the engEngrExamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEngrExamDTO,
     * or with status {@code 400 (Bad Request)} if the engEngrExamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engEngrExamDTO couldn't be updated.
     */
    @PutMapping("/eng-engr-exams/{engEngrExamId}")
    public ResponseEntity<EngEngrExamDTO> updateEngEngrExam(
        @PathVariable(value = "engEngrExamId", required = false) final String engEngrExamId,
        @Valid @RequestBody EngEngrExamDTO engEngrExamDTO
    ) {
        log.debug("REST request to update EngEngrExam : {}, {}", engEngrExamId, engEngrExamDTO);
        if (engEngrExamDTO.getEngEngrExamId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEngrExamId, engEngrExamDTO.getEngEngrExamId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEngrExamRepository.existsById(engEngrExamId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngEngrExamDTO result = engEngrExamService.save(engEngrExamDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEngrExamDTO.getEngEngrExamId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-engr-exams/:engEngrExamId} : Partial updates given fields of an existing engEngrExam, field will ignore if it is null
     *
     * @param engEngrExamId the id of the engEngrExamDTO to save.
     * @param engEngrExamDTO the engEngrExamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEngrExamDTO,
     * or with status {@code 400 (Bad Request)} if the engEngrExamDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engEngrExamDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engEngrExamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-engr-exams/{engEngrExamId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngEngrExamDTO> partialUpdateEngEngrExam(
        @PathVariable(value = "engEngrExamId", required = false) final String engEngrExamId,
        @NotNull @RequestBody EngEngrExamDTO engEngrExamDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngEngrExam partially : {}, {}", engEngrExamId, engEngrExamDTO);
        if (engEngrExamDTO.getEngEngrExamId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEngrExamId, engEngrExamDTO.getEngEngrExamId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEngrExamRepository.existsById(engEngrExamId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngEngrExamDTO> result = engEngrExamService.partialUpdate(engEngrExamDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEngrExamDTO.getEngEngrExamId())
        );
    }

    /**
     * {@code GET  /eng-engr-exams} : get all the engEngrExams.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engEngrExams in body.
     */
    @GetMapping("/eng-engr-exams")
    public List<EngEngrExamDTO> getAllEngEngrExams() {
        log.debug("REST request to get all EngEngrExams");
        return engEngrExamService.findAll();
    }

    /**
     * {@code GET  /eng-engr-exams/:id} : get the "id" engEngrExam.
     *
     * @param id the id of the engEngrExamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engEngrExamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-engr-exams/{id}")
    public ResponseEntity<EngEngrExamDTO> getEngEngrExam(@PathVariable String id) {
        log.debug("REST request to get EngEngrExam : {}", id);
        Optional<EngEngrExamDTO> engEngrExamDTO = engEngrExamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engEngrExamDTO);
    }

    /**
     * {@code DELETE  /eng-engr-exams/:id} : delete the "id" engEngrExam.
     *
     * @param id the id of the engEngrExamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-engr-exams/{id}")
    public ResponseEntity<Void> deleteEngEngrExam(@PathVariable String id) {
        log.debug("REST request to delete EngEngrExam : {}", id);
        engEngrExamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code GET  /eng-engr-exams/:idno/:subject} : get the engEngrExam.
     *
     * @param idno the idno of the engEngrExamDTO to retrieve.
     * @param subject the subject of the engEngrExamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engEngrExamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-engr-exams/{idno}/{subject}")
    public EngEngrExamDTO getEngEngrExam(@PathVariable String idno, @PathVariable String subject) {
        log.debug("REST request to get EngEngrExam : {}, {}", idno, subject);
        return engEngrExamService.findOne(idno, subject);
    }

    @PostMapping("/eng-engr-exams/criteria-jpa")
    public Page<EngEngrExamDTO> getEngEngrExamByCriteria(@Valid @RequestBody EngEngrExamCriteria criteria) throws InterruptedException {
        log.debug("REST request to get EngEngrCertificate : {}", criteria);
        return engEngrExamService.findAll(criteria);
    }
}
