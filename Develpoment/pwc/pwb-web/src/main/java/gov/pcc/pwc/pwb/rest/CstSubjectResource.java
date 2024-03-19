package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.CstSubject;
import gov.pcc.pwc.repository.CstSubjectRepository;
import gov.pcc.pwc.service.CstSubjectService;
import gov.pcc.pwc.service.dto.CstSubjectDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link CstSubject}.
 */
@RestController
@RequestMapping("/api")
public class CstSubjectResource {

    private final Logger log = LoggerFactory.getLogger(CstSubjectResource.class);

    private static final String ENTITY_NAME = "cstSubject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CstSubjectService cstSubjectService;

    private final CstSubjectRepository cstSubjectRepository;

    public CstSubjectResource(CstSubjectService cstSubjectService, CstSubjectRepository cstSubjectRepository) {
        this.cstSubjectService = cstSubjectService;
        this.cstSubjectRepository = cstSubjectRepository;
    }

    /**
     * {@code POST  /cst-subjects} : Create a new cstSubject.
     *
     * @param cstSubjectDTO the cstSubjectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cstSubjectDTO, or with status {@code 400 (Bad Request)} if the cstSubject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cst-subjects")
    public ResponseEntity<CstSubjectDTO> createCstSubject(@Valid @RequestBody CstSubjectDTO cstSubjectDTO) throws URISyntaxException {
        log.debug("REST request to save CstSubject : {}", cstSubjectDTO);
        if (cstSubjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new cstSubject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CstSubjectDTO result = cstSubjectService.save(cstSubjectDTO);
        return ResponseEntity
            .created(new URI("/api/cst-subjects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cst-subjects/:id} : Updates an existing cstSubject.
     *
     * @param id the id of the cstSubjectDTO to save.
     * @param cstSubjectDTO the cstSubjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cstSubjectDTO,
     * or with status {@code 400 (Bad Request)} if the cstSubjectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cstSubjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cst-subjects/{id}")
    public ResponseEntity<CstSubjectDTO> updateCstSubject(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CstSubjectDTO cstSubjectDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CstSubject : {}, {}", id, cstSubjectDTO);
        if (cstSubjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cstSubjectDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cstSubjectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CstSubjectDTO result = cstSubjectService.save(cstSubjectDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cstSubjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /cst-subjects/:id} : Partial updates given fields of an existing cstSubject, field will ignore if it is null
     *
     * @param id the id of the cstSubjectDTO to save.
     * @param cstSubjectDTO the cstSubjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cstSubjectDTO,
     * or with status {@code 400 (Bad Request)} if the cstSubjectDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cstSubjectDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cstSubjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cst-subjects/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CstSubjectDTO> partialUpdateCstSubject(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CstSubjectDTO cstSubjectDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CstSubject partially : {}, {}", id, cstSubjectDTO);
        if (cstSubjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cstSubjectDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cstSubjectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CstSubjectDTO> result = cstSubjectService.partialUpdate(cstSubjectDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cstSubjectDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cst-subjects} : get all the cstSubjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cstSubjects in body.
     */
    @GetMapping("/cst-subjects")
    public List<CstSubjectDTO> getAllCstSubjects() {
        log.debug("REST request to get all CstSubjects");
        return cstSubjectService.findAll();
    }

    /**
     * {@code GET  /cst-subjects/:id} : get the "id" cstSubject.
     *
     * @param id the id of the cstSubjectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cstSubjectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cst-subjects/{id}")
    public ResponseEntity<CstSubjectDTO> getCstSubject(@PathVariable Long id) {
        log.debug("REST request to get CstSubject : {}", id);
        Optional<CstSubjectDTO> cstSubjectDTO = cstSubjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cstSubjectDTO);
    }

    /**
     * {@code DELETE  /cst-subjects/:id} : delete the "id" cstSubject.
     *
     * @param id the id of the cstSubjectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cst-subjects/{id}")
    public ResponseEntity<Void> deleteCstSubject(@PathVariable Long id) {
        log.debug("REST request to delete CstSubject : {}", id);
        cstSubjectService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
