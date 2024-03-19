package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.ProNoCommunicateListRepository;
import gov.pcc.pwc.service.ProNoCommunicateListService;
import gov.pcc.pwc.service.dto.ProNoCommunicateListDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.ProNoCommunicateList}.
 */
@RestController
@RequestMapping("/api")
public class ProNoCommunicateListResource {

    private final Logger log = LoggerFactory.getLogger(ProNoCommunicateListResource.class);

    private static final String ENTITY_NAME = "proNoCommunicateList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProNoCommunicateListService proNoCommunicateListService;

    private final ProNoCommunicateListRepository proNoCommunicateListRepository;

    public ProNoCommunicateListResource(
        ProNoCommunicateListService proNoCommunicateListService,
        ProNoCommunicateListRepository proNoCommunicateListRepository
    ) {
        this.proNoCommunicateListService = proNoCommunicateListService;
        this.proNoCommunicateListRepository = proNoCommunicateListRepository;
    }

    /**
     * {@code POST  /pro-no-communicate-lists} : Create a new proNoCommunicateList.
     *
     * @param proNoCommunicateListDTO the proNoCommunicateListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proNoCommunicateListDTO, or with status {@code 400 (Bad Request)} if the proNoCommunicateList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-no-communicate-lists")
    public ResponseEntity<ProNoCommunicateListDTO> createProNoCommunicateList(
        @Valid @RequestBody ProNoCommunicateListDTO proNoCommunicateListDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ProNoCommunicateList : {}", proNoCommunicateListDTO);
        if (proNoCommunicateListDTO.getProNoCommunicateListId() != null) {
            throw new BadRequestAlertException("A new proNoCommunicateList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProNoCommunicateListDTO result = proNoCommunicateListService.save(proNoCommunicateListDTO);
        return ResponseEntity
            .created(new URI("/api/pro-no-communicate-lists/" + result.getProNoCommunicateListId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProNoCommunicateListId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /pro-no-communicate-lists/:proNoCommunicateListId} : Updates an existing proNoCommunicateList.
     *
     * @param proNoCommunicateListId the id of the proNoCommunicateListDTO to save.
     * @param proNoCommunicateListDTO the proNoCommunicateListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proNoCommunicateListDTO,
     * or with status {@code 400 (Bad Request)} if the proNoCommunicateListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proNoCommunicateListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-no-communicate-lists/{proNoCommunicateListId}")
    public ResponseEntity<ProNoCommunicateListDTO> updateProNoCommunicateList(
        @PathVariable(value = "proNoCommunicateListId", required = false) final Long proNoCommunicateListId,
        @Valid @RequestBody ProNoCommunicateListDTO proNoCommunicateListDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProNoCommunicateList : {}, {}", proNoCommunicateListId, proNoCommunicateListDTO);
        if (proNoCommunicateListDTO.getProNoCommunicateListId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proNoCommunicateListId, proNoCommunicateListDTO.getProNoCommunicateListId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proNoCommunicateListRepository.existsById(proNoCommunicateListId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProNoCommunicateListDTO result = proNoCommunicateListService.save(proNoCommunicateListDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    proNoCommunicateListDTO.getProNoCommunicateListId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pro-no-communicate-lists/:proNoCommunicateListId} : Partial updates given fields of an existing proNoCommunicateList, field will ignore if it is null
     *
     * @param proNoCommunicateListId the id of the proNoCommunicateListDTO to save.
     * @param proNoCommunicateListDTO the proNoCommunicateListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proNoCommunicateListDTO,
     * or with status {@code 400 (Bad Request)} if the proNoCommunicateListDTO is not valid,
     * or with status {@code 404 (Not Found)} if the proNoCommunicateListDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the proNoCommunicateListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/pro-no-communicate-lists/{proNoCommunicateListId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ProNoCommunicateListDTO> partialUpdateProNoCommunicateList(
        @PathVariable(value = "proNoCommunicateListId", required = false) final Long proNoCommunicateListId,
        @NotNull @RequestBody ProNoCommunicateListDTO proNoCommunicateListDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ProNoCommunicateList partially : {}, {}",
            proNoCommunicateListId,
            proNoCommunicateListDTO
        );
        if (proNoCommunicateListDTO.getProNoCommunicateListId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proNoCommunicateListId, proNoCommunicateListDTO.getProNoCommunicateListId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proNoCommunicateListRepository.existsById(proNoCommunicateListId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProNoCommunicateListDTO> result = proNoCommunicateListService.partialUpdate(proNoCommunicateListDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                proNoCommunicateListDTO.getProNoCommunicateListId().toString()
            )
        );
    }

    /**
     * {@code GET  /pro-no-communicate-lists} : get all the proNoCommunicateLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proNoCommunicateLists in body.
     */
    @GetMapping("/pro-no-communicate-lists")
    public List<ProNoCommunicateListDTO> getAllProNoCommunicateLists() {
        log.debug("REST request to get all ProNoCommunicateLists");
        return proNoCommunicateListService.findAll();
    }

    /**
     * {@code GET  /pro-no-communicate-lists/:id} : get the "id" proNoCommunicateList.
     *
     * @param id the id of the proNoCommunicateListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proNoCommunicateListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-no-communicate-lists/{id}")
    public ResponseEntity<ProNoCommunicateListDTO> getProNoCommunicateList(@PathVariable Long id) {
        log.debug("REST request to get ProNoCommunicateList : {}", id);
        Optional<ProNoCommunicateListDTO> proNoCommunicateListDTO = proNoCommunicateListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proNoCommunicateListDTO);
    }

    /**
     * {@code DELETE  /pro-no-communicate-lists/:id} : delete the "id" proNoCommunicateList.
     *
     * @param id the id of the proNoCommunicateListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-no-communicate-lists/{id}")
    public ResponseEntity<Void> deleteProNoCommunicateList(@PathVariable Long id) {
        log.debug("REST request to delete ProNoCommunicateList : {}", id);
        proNoCommunicateListService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
