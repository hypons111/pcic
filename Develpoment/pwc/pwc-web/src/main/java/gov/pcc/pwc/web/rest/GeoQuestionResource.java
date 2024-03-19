package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.GeoQuestionRepository;
import gov.pcc.pwc.service.GeoQuestionService;
import gov.pcc.pwc.service.criteria.GeoQuestionCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import gov.pcc.pwc.service.dto.GeoQuestionDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.GeoQuestion}.
 */
@RestController
@RequestMapping("/api")
public class GeoQuestionResource {

    private final Logger log = LoggerFactory.getLogger(GeoQuestionResource.class);

    private static final String ENTITY_NAME = "geoQuestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoQuestionService geoQuestionService;

    private final GeoQuestionRepository geoQuestionRepository;

    public GeoQuestionResource(GeoQuestionService geoQuestionService, GeoQuestionRepository geoQuestionRepository) {
        this.geoQuestionService = geoQuestionService;
        this.geoQuestionRepository = geoQuestionRepository;
    }

    /**
     * {@code POST  /geo-questions} : Create a new geoQuestion.
     *
     * @param geoQuestionDTO the geoQuestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoQuestionDTO, or with status {@code 400 (Bad Request)} if the geoQuestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-questions")
    public ResponseEntity<GeoQuestionDTO> createGeoQuestion(@Valid @RequestBody GeoQuestionDTO geoQuestionDTO) throws URISyntaxException {
        log.debug("REST request to save GeoQuestion : {}", geoQuestionDTO);
        if (geoQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        geoQuestionDTO.setCreateTime(Instant.now());
        geoQuestionDTO.setUpdateTime(Instant.now());
        GeoQuestionDTO result = geoQuestionService.save(geoQuestionDTO);
        return ResponseEntity
            .created(new URI("/api/geo-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
//    @PostMapping("/geo-questions")
//    public ResponseEntity<GeoQuestionDTO> createGeoQuestion(@Valid @RequestBody GeoQuestionDTO geoQuestionDTO) throws URISyntaxException {
//        log.debug("REST request to save GeoQuestion : {}", geoQuestionDTO);
//        if (geoQuestionDTO.getId() != null) {
//            throw new BadRequestAlertException("A new geoQuestion cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//
//        GeoQuestionDTO result = geoQuestionService.insert(geoQuestionDTO);
//        return ResponseEntity
//            .created(new URI("/api/geo-questions/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }


    /**
     * {@code PUT  /geo-questions/:id} : Updates an existing geoQuestion.
     *
     * @param id the id of the geoQuestionDTO to save.
     * @param geoQuestionDTO the geoQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the geoQuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-questions/{id}")
    public ResponseEntity<GeoQuestionDTO> updateGeoQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GeoQuestionDTO geoQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GeoQuestion : {}, {}", id, geoQuestionDTO);
        if (geoQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, geoQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!geoQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GeoQuestionDTO result = geoQuestionService.save(geoQuestionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /geo-questions/:id} : Partial updates given fields of an existing geoQuestion, field will ignore if it is null
     *
     * @param id the id of the geoQuestionDTO to save.
     * @param geoQuestionDTO the geoQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the geoQuestionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the geoQuestionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the geoQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/geo-questions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GeoQuestionDTO> partialUpdateGeoQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GeoQuestionDTO geoQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GeoQuestion partially : {}, {}", id, geoQuestionDTO);
        if (geoQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, geoQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!geoQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GeoQuestionDTO> result = geoQuestionService.partialUpdate(geoQuestionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoQuestionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /geo-questions} : get all the geoQuestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoQuestions in body.
     */
    @GetMapping("/geo-questions")
    public List<GeoQuestionDTO> getAllGeoQuestions() {
        log.debug("REST request to get all GeoQuestions");
        return geoQuestionService.findAll();
    }

    /**
     * {@code GET  /geo-questions/:id} : get the "id" geoQuestion.
     *
     * @param id the id of the geoQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-questions/{id}")
    public ResponseEntity<GeoQuestionDTO> getGeoQuestion(@PathVariable Long id) {
        log.debug("REST request to get GeoQuestion : {}", id);
        Optional<GeoQuestionDTO> geoQuestionDTO = geoQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoQuestionDTO);
    }


    /**
     * {@code POST  /geo-questions/criteria} : get geoQuestions by criteria.
     *
     * @param criteria the criteria of the geoQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/geo-questions/criteria")
    public Page<GeoQuestionDTO> getGeoQuestionByCriteriaImpl(@RequestBody GeoQuestionCriteria criteria) throws InterruptedException {
        log.debug("REST request to get GeoQuestion : {}", criteria);
        return geoQuestionService.findByCriteriaImpl(criteria);
    }

    /**
     * {@code POST  /geo-questions/criteria-jpa} : get geoQuestions by criteria.
     *
     * @param criteria the criteria of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cmsCompDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/geo-questions/criteria-jpa")
    public Page<GeoQuestionDTO> getGeoQuestionByCriteria(@Valid @RequestBody GeoQuestionCriteria criteria) throws InterruptedException {
        log.debug("REST request to get GeoQuestion : {}", criteria);
        return geoQuestionService.findByCriteria(criteria);
    }


    /**
     * {@code DELETE  /geo-questions/:id} : delete the "id" geoQuestion.
     *
     * @param id the id of the geoQuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-questions/{id}")
    public ResponseEntity<Void> deleteGeoQuestion(@PathVariable Long id) {
        log.debug("REST request to delete GeoQuestion : {}", id);
        geoQuestionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/geo-questions/criteria-remark")
    public List<AdmSysCodeDTO> getAdmSysCodeImpl(@RequestBody GeoQuestionCriteria criteria) {
        log.debug("REST request to get AdmSysCode : {}", criteria);
        return geoQuestionService.findAdmSysCodeByCriteriaImpl(criteria);
    }
    @PutMapping("/geo-questions/criteria-remark-update")
    public void  updateAdmSysCodeByCriteriaImpl(@RequestBody GeoQuestionCriteria criteria) {
        log.debug("REST request to update AdmSysCode : {}", criteria);
        geoQuestionService.updateAdmSysCodeByCriteriaImpl(criteria);
    }
    @PostMapping("/geo-questions/criteria-geomember")
    public List<GeoMemberDTO> getGeomemberImpl(@RequestBody GeoQuestionCriteria criteria) {
        log.debug("REST request to get AdmSysCode : {}", criteria);
        return geoQuestionService.findGeoMemberIdByCriteriaImpl(criteria);
    }
}
