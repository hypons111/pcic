package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.EngCourseRepository;
import gov.pcc.pwc.service.EngCourseService;
import gov.pcc.pwc.service.criteria.EngCourseCriteria;
import gov.pcc.pwc.service.dto.Eng0401DTO;
import gov.pcc.pwc.service.dto.EngCourseDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.EngCourse}.
 */
@RestController
@RequestMapping("/api")
public class EngCourseResource {

    private final Logger log = LoggerFactory.getLogger(EngCourseResource.class);

    private static final String ENTITY_NAME = "engCourse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngCourseService engCourseService;

    private final EngCourseRepository engCourseRepository;

    public EngCourseResource(EngCourseService engCourseService, EngCourseRepository engCourseRepository) {
        this.engCourseService = engCourseService;
        this.engCourseRepository = engCourseRepository;
    }

    /**
     * {@code POST  /eng-courses} : Create a new engCourse.
     *
     * @param engCourseDTO the engCourseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engCourseDTO, or with status {@code 400 (Bad Request)} if the engCourse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-courses")
    public ResponseEntity<EngCourseDTO> createEngCourse(@Valid @RequestBody EngCourseDTO engCourseDTO) throws URISyntaxException {
        log.debug("REST request to save EngCourse : {}", engCourseDTO);
        if (engCourseDTO.getEngCourseId() != null) {
            throw new BadRequestAlertException("A new engCourse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngCourseDTO result = engCourseService.save(engCourseDTO);
        return ResponseEntity
            .created(new URI("/api/eng-courses/" + result.getEngCourseId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngCourseId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-courses/:engCourseId} : Updates an existing engCourse.
     *
     * @param engCourseId the id of the engCourseDTO to save.
     * @param engCourseDTO the engCourseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engCourseDTO,
     * or with status {@code 400 (Bad Request)} if the engCourseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engCourseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-courses/{engCourseId}")
    public ResponseEntity<EngCourseDTO> updateEngCourse(
        @PathVariable(value = "engCourseId", required = false) final String engCourseId,
        @Valid @RequestBody EngCourseDTO engCourseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EngCourse : {}, {}", engCourseId, engCourseDTO);
        if (engCourseDTO.getEngCourseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engCourseId, engCourseDTO.getEngCourseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engCourseRepository.existsById(engCourseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngCourseDTO result = engCourseService.save(engCourseDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engCourseDTO.getEngCourseId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-courses/:engCourseId} : Partial updates given fields of an existing engCourse, field will ignore if it is null
     *
     * @param engCourseId the id of the engCourseDTO to save.
     * @param engCourseDTO the engCourseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engCourseDTO,
     * or with status {@code 400 (Bad Request)} if the engCourseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engCourseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engCourseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-courses/{engCourseId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngCourseDTO> partialUpdateEngCourse(
        @PathVariable(value = "engCourseId", required = false) final String engCourseId,
        @NotNull @RequestBody EngCourseDTO engCourseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngCourse partially : {}, {}", engCourseId, engCourseDTO);
        if (engCourseDTO.getEngCourseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engCourseId, engCourseDTO.getEngCourseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engCourseRepository.existsById(engCourseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngCourseDTO> result = engCourseService.partialUpdate(engCourseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engCourseDTO.getEngCourseId())
        );
    }

    /**
     * {@code GET  /eng-courses} : get all the engCourses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engCourses in body.
     */
    @GetMapping("/eng-courses")
    public List<EngCourseDTO> getAllEngCourses() {
        log.debug("REST request to get all EngCourses");
        return engCourseService.findAll();
    }

    /**
     * {@code GET  /eng-courses/:id} : get the "id" engCourse.
     *
     * @param id the id of the engCourseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engCourseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-courses/{id}")
    public ResponseEntity<EngCourseDTO> getEngCourse(@PathVariable String id) {
        log.debug("REST request to get EngCourse : {}", id);
        Optional<EngCourseDTO> engCourseDTO = engCourseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engCourseDTO);
    }

    /**
     * {@code DELETE  /eng-courses/:id} : delete the "id" engCourse.
     *
     * @param id the id of the engCourseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-courses/{id}")
    public ResponseEntity<Void> deleteEngCourse(@PathVariable String id) {
        log.debug("REST request to delete EngCourse : {}", id);
        engCourseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @PostMapping("/eng-courses/criteria-jpa")
    public Page<EngCourseDTO> getEngCoursesByCriteria(@Valid @RequestBody EngCourseCriteria criteria) {
        log.debug("REST request to get EngCourses by criteria");
        return engCourseService.findAll(criteria);
    }

    @GetMapping("/eng-courses/eng0401/{engCourseId}")
    public Eng0401DTO getEng0401(@PathVariable String engCourseId) {
        log.debug("REST request to get Eng0401DTO : {}", engCourseId);
        return engCourseService.findEng0401(engCourseId);
    }
}
