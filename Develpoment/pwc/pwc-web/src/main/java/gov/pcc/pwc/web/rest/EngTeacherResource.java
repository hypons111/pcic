package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.EngTeacherRepository;
import gov.pcc.pwc.service.EngTeacherService;
import gov.pcc.pwc.service.dto.EngTeacherDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngTeacher}.
 */
@RestController
@RequestMapping("/api")
public class EngTeacherResource {

    private final Logger log = LoggerFactory.getLogger(EngTeacherResource.class);

    private static final String ENTITY_NAME = "engTeacher";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngTeacherService engTeacherService;

    private final EngTeacherRepository engTeacherRepository;

    public EngTeacherResource(EngTeacherService engTeacherService, EngTeacherRepository engTeacherRepository) {
        this.engTeacherService = engTeacherService;
        this.engTeacherRepository = engTeacherRepository;
    }

    /**
     * {@code POST  /eng-teachers} : Create a new engTeacher.
     *
     * @param engTeacherDTO the engTeacherDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engTeacherDTO, or with status {@code 400 (Bad Request)} if the engTeacher has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-teachers")
    public ResponseEntity<EngTeacherDTO> createEngTeacher(@Valid @RequestBody EngTeacherDTO engTeacherDTO) throws URISyntaxException {
        log.debug("REST request to save EngTeacher : {}", engTeacherDTO);
        if (engTeacherDTO.getEngTeacherId() != null) {
            throw new BadRequestAlertException("A new engTeacher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngTeacherDTO result = engTeacherService.save(engTeacherDTO);
        return ResponseEntity
            .created(new URI("/api/eng-teachers/" + result.getEngTeacherId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngTeacherId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-teachers/:engTeacherId} : Updates an existing engTeacher.
     *
     * @param engTeacherId the id of the engTeacherDTO to save.
     * @param engTeacherDTO the engTeacherDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engTeacherDTO,
     * or with status {@code 400 (Bad Request)} if the engTeacherDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engTeacherDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-teachers/{engTeacherId}")
    public ResponseEntity<EngTeacherDTO> updateEngTeacher(
        @PathVariable(value = "engTeacherId", required = false) final String engTeacherId,
        @Valid @RequestBody EngTeacherDTO engTeacherDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EngTeacher : {}, {}", engTeacherId, engTeacherDTO);
        if (engTeacherDTO.getEngTeacherId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engTeacherId, engTeacherDTO.getEngTeacherId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engTeacherRepository.existsById(engTeacherId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngTeacherDTO result = engTeacherService.save(engTeacherDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engTeacherDTO.getEngTeacherId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-teachers/:engTeacherId} : Partial updates given fields of an existing engTeacher, field will ignore if it is null
     *
     * @param engTeacherId the id of the engTeacherDTO to save.
     * @param engTeacherDTO the engTeacherDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engTeacherDTO,
     * or with status {@code 400 (Bad Request)} if the engTeacherDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engTeacherDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engTeacherDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-teachers/{engTeacherId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngTeacherDTO> partialUpdateEngTeacher(
        @PathVariable(value = "engTeacherId", required = false) final String engTeacherId,
        @NotNull @RequestBody EngTeacherDTO engTeacherDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngTeacher partially : {}, {}", engTeacherId, engTeacherDTO);
        if (engTeacherDTO.getEngTeacherId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engTeacherId, engTeacherDTO.getEngTeacherId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engTeacherRepository.existsById(engTeacherId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngTeacherDTO> result = engTeacherService.partialUpdate(engTeacherDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engTeacherDTO.getEngTeacherId())
        );
    }

    /**
     * {@code GET  /eng-teachers} : get all the engTeachers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engTeachers in body.
     */
    @GetMapping("/eng-teachers")
    public List<EngTeacherDTO> getAllEngTeachers() {
        log.debug("REST request to get all EngTeachers");
        return engTeacherService.findAll();
    }

    /**
     * {@code GET  /eng-teachers/:id} : get the "id" engTeacher.
     *
     * @param id the id of the engTeacherDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engTeacherDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-teachers/{id}")
    public ResponseEntity<EngTeacherDTO> getEngTeacher(@PathVariable String id) {
        log.debug("REST request to get EngTeacher : {}", id);
        Optional<EngTeacherDTO> engTeacherDTO = engTeacherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engTeacherDTO);
    }

    /**
     * {@code DELETE  /eng-teachers/:id} : delete the "id" engTeacher.
     *
     * @param id the id of the engTeacherDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-teachers/{id}")
    public ResponseEntity<Void> deleteEngTeacher(@PathVariable String id) {
        log.debug("REST request to delete EngTeacher : {}", id);
        engTeacherService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @PostMapping("/eng-teachers/find-by-teacher-name-like")
    public List<EngTeacherDTO> getEngTeachers(@RequestBody Map<String, String> req) {
        log.debug("REST request to get EngTeachers by teacherName : {}", req.get("teacherName"));
        return engTeacherService.findByTeacherNameLike(req.get("teacherName"));
    }

    @GetMapping("/eng-teachers/get-new-pk")
    public Map<String, String> getNewEngTeacherId() throws TypeVariableNotMatchException {
        log.debug("REST request to get new engTeacherId");
        return engTeacherService.getNewEngTeacherId();
    }
}
