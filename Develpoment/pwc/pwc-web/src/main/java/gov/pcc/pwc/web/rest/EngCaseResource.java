package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.EngCaseRepository;
import gov.pcc.pwc.service.EngCaseService;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.EngCaseDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngCase}.
 */
@RestController
@RequestMapping("/api")
public class EngCaseResource {

    private final Logger log = LoggerFactory.getLogger(EngCaseResource.class);

    private static final String ENTITY_NAME = "engCase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private EngCaseService engCaseService;

    @Autowired
    private EngCaseRepository engCaseRepository;

//    public EngCaseResource(EngCaseService engCaseService, EngCaseRepository engCaseRepository) {
//        this.engCaseService = engCaseService;
//        this.engCaseRepository = engCaseRepository;
//    }


//    // 抓下工程種類下拉選單
//    @GetMapping("/eng0301-r1-select")
//    public List<AdmSysCodeDTO> menus() {
//        List<AdmSysCodeDTO> list = engCaseService.findAdmSysCode();
//        log.debug("EngCaseResource-getProjectTypes" + list);
//        return list;
//    }


    /**
     * {@code POST  /eng-cases} : Create a new engCase.
     *
     * @param engCaseDTO the engCaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engCaseDTO, or with status {@code 400 (Bad Request)} if the engCase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-cases")
    public ResponseEntity<EngCaseDTO> createEngCase(@Valid @RequestBody EngCaseDTO engCaseDTO) throws URISyntaxException {
        log.debug("REST request to save EngCase : {}", engCaseDTO);
        if (engCaseDTO.getEngCaseId() != null) {
            throw new BadRequestAlertException("A new engCase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngCaseDTO result = engCaseService.save(engCaseDTO);
        return ResponseEntity
            .created(new URI("/api/eng-cases/" + result.getEngCaseId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngCaseId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-cases/:engCaseId} : Updates an existing engCase.
     *
     * @param engCaseId the id of the engCaseDTO to save.
     * @param engCaseDTO the engCaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engCaseDTO,
     * or with status {@code 400 (Bad Request)} if the engCaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engCaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-cases/{engCaseId}")
    public ResponseEntity<EngCaseDTO> updateEngCase(
        @PathVariable(value = "engCaseId", required = false) final String engCaseId,
        @Valid @RequestBody EngCaseDTO engCaseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EngCase : {}, {}", engCaseId, engCaseDTO);
        if (engCaseDTO.getEngCaseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engCaseId, engCaseDTO.getEngCaseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engCaseRepository.existsById(engCaseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngCaseDTO result = engCaseService.save(engCaseDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engCaseDTO.getEngCaseId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-cases/:engCaseId} : Partial updates given fields of an existing engCase, field will ignore if it is null
     *
     * @param engCaseId the id of the engCaseDTO to save.
     * @param engCaseDTO the engCaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engCaseDTO,
     * or with status {@code 400 (Bad Request)} if the engCaseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engCaseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engCaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-cases/{engCaseId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngCaseDTO> partialUpdateEngCase(
        @PathVariable(value = "engCaseId", required = false) final String engCaseId,
        @NotNull @RequestBody EngCaseDTO engCaseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngCase partially : {}, {}", engCaseId, engCaseDTO);
        if (engCaseDTO.getEngCaseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engCaseId, engCaseDTO.getEngCaseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engCaseRepository.existsById(engCaseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngCaseDTO> result = engCaseService.partialUpdate(engCaseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engCaseDTO.getEngCaseId())
        );
    }

    /**
     * {@code GET  /eng-cases} : get all the engCases.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engCases in body.
     */
    @GetMapping("/eng-cases")
    public List<EngCaseDTO> getAllEngCases() {
        log.debug("REST request to get all EngCases");
        return engCaseService.findAll();
    }

    /**
     * {@code GET  /eng-cases/:id} : get the "id" engCase.
     *
     * @param id the id of the engCaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engCaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-cases/{id}")
    public ResponseEntity<EngCaseDTO> getEngCase(@PathVariable String id) {
        log.debug("REST request to get EngCase : {}", id);
        Optional<EngCaseDTO> engCaseDTO = engCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engCaseDTO);
    }

    /**
     * {@code DELETE  /eng-cases/:id} : delete the "id" engCase.
     *
     * @param id the id of the engCaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-cases/{id}")
    public ResponseEntity<Void> deleteEngCase(@PathVariable String id) {
        log.debug("REST request to delete EngCase : {}", id);
        engCaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
