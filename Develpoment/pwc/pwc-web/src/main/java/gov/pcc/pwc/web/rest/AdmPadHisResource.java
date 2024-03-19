package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.AdmPadHisRepository;
import gov.pcc.pwc.service.AdmPadHisService;
import gov.pcc.pwc.service.dto.AdmPadHisDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.AdmPadHis}.
 */
@RestController
@RequestMapping("/api")
public class AdmPadHisResource {

    private final Logger log = LoggerFactory.getLogger(AdmPadHisResource.class);

    private static final String ENTITY_NAME = "admPadHis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmPadHisService admPadHisService;

    private final AdmPadHisRepository admPadHisRepository;

    public AdmPadHisResource(AdmPadHisService admPadHisService, AdmPadHisRepository admPadHisRepository) {
        this.admPadHisService = admPadHisService;
        this.admPadHisRepository = admPadHisRepository;
    }

    /**
     * {@code POST  /adm-pad-his} : Create a new admPadHis.
     *
     * @param admPadHisDTO the admPadHisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admPadHisDTO, or with status {@code 400 (Bad Request)} if the admPadHis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-pad-his")
    public ResponseEntity<AdmPadHisDTO> createAdmPadHis(@Valid @RequestBody AdmPadHisDTO admPadHisDTO) throws URISyntaxException {
        log.debug("REST request to save AdmPadHis : {}", admPadHisDTO);
        if (admPadHisDTO.getId() != null) {
            throw new BadRequestAlertException("A new admPadHis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmPadHisDTO result = admPadHisService.save(admPadHisDTO);
        return ResponseEntity
            .created(new URI("/api/adm-pad-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-pad-his/:id} : Updates an existing admPadHis.
     *
     * @param id the id of the admPadHisDTO to save.
     * @param admPadHisDTO the admPadHisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admPadHisDTO,
     * or with status {@code 400 (Bad Request)} if the admPadHisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admPadHisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-pad-his/{id}")
    public ResponseEntity<AdmPadHisDTO> updateAdmPadHis(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmPadHisDTO admPadHisDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdmPadHis : {}, {}", id, admPadHisDTO);
        if (admPadHisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admPadHisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admPadHisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmPadHisDTO result = admPadHisService.save(admPadHisDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admPadHisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-pad-his/:id} : Partial updates given fields of an existing admPadHis, field will ignore if it is null
     *
     * @param id the id of the admPadHisDTO to save.
     * @param admPadHisDTO the admPadHisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admPadHisDTO,
     * or with status {@code 400 (Bad Request)} if the admPadHisDTO is not valid,
     * or with status {@code 404 (Not Found)} if the admPadHisDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the admPadHisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-pad-his/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AdmPadHisDTO> partialUpdateAdmPadHis(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmPadHisDTO admPadHisDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmPadHis partially : {}, {}", id, admPadHisDTO);
        if (admPadHisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admPadHisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admPadHisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmPadHisDTO> result = admPadHisService.partialUpdate(admPadHisDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admPadHisDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-pad-his} : get all the admPadHis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admPadHis in body.
     */
    @GetMapping("/adm-pad-his")
    public List<AdmPadHisDTO> getAllAdmPadHis() {
        log.debug("REST request to get all AdmPadHis");
        return admPadHisService.findAll();
    }

    /**
     * {@code GET  /adm-pad-his/:id} : get the "id" admPadHis.
     *
     * @param id the id of the admPadHisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admPadHisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-pad-his/{id}")
    public ResponseEntity<AdmPadHisDTO> getAdmPadHis(@PathVariable Long id) {
        log.debug("REST request to get AdmPadHis : {}", id);
        Optional<AdmPadHisDTO> admPadHisDTO = admPadHisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admPadHisDTO);
    }

    /**
     * {@code DELETE  /adm-pad-his/:id} : delete the "id" admPadHis.
     *
     * @param id the id of the admPadHisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-pad-his/{id}")
    public ResponseEntity<Void> deleteAdmPadHis(@PathVariable Long id) {
        log.debug("REST request to delete AdmPadHis : {}", id);
        admPadHisService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
