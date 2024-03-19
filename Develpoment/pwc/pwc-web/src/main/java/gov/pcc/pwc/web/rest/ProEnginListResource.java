package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.ProEnginListRepository;
import gov.pcc.pwc.service.ProEnginListService;
import gov.pcc.pwc.service.dto.ProEnginListDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.ProEnginList}.
 */
@RestController
@RequestMapping("/api")
public class ProEnginListResource {

    private final Logger log = LoggerFactory.getLogger(ProEnginListResource.class);

    private static final String ENTITY_NAME = "proEnginList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProEnginListService proEnginListService;

    private final ProEnginListRepository proEnginListRepository;

    public ProEnginListResource(ProEnginListService proEnginListService, ProEnginListRepository proEnginListRepository) {
        this.proEnginListService = proEnginListService;
        this.proEnginListRepository = proEnginListRepository;
    }

    /**
     * {@code POST  /pro-engin-lists} : Create a new proEnginList.
     *
     * @param proEnginListDTO the proEnginListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proEnginListDTO, or with status {@code 400 (Bad Request)} if the proEnginList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-engin-lists")
    public ResponseEntity<ProEnginListDTO> createProEnginList(@Valid @RequestBody ProEnginListDTO proEnginListDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProEnginList : {}", proEnginListDTO);
        if (proEnginListDTO.getProEnginListId() != null) {
            throw new BadRequestAlertException("A new proEnginList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProEnginListDTO result = proEnginListService.save(proEnginListDTO);
        return ResponseEntity
            .created(new URI("/api/pro-engin-lists/" + result.getProEnginListId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProEnginListId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-engin-lists/:proEnginListId} : Updates an existing proEnginList.
     *
     * @param proEnginListId the id of the proEnginListDTO to save.
     * @param proEnginListDTO the proEnginListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proEnginListDTO,
     * or with status {@code 400 (Bad Request)} if the proEnginListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proEnginListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-engin-lists/{proEnginListId}")
    public ResponseEntity<ProEnginListDTO> updateProEnginList(
        @PathVariable(value = "proEnginListId", required = false) final Long proEnginListId,
        @Valid @RequestBody ProEnginListDTO proEnginListDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProEnginList : {}, {}", proEnginListId, proEnginListDTO);
        if (proEnginListDTO.getProEnginListId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proEnginListId, proEnginListDTO.getProEnginListId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proEnginListRepository.existsById(proEnginListId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProEnginListDTO result = proEnginListService.save(proEnginListDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proEnginListDTO.getProEnginListId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pro-engin-lists/:proEnginListId} : Partial updates given fields of an existing proEnginList, field will ignore if it is null
     *
     * @param proEnginListId the id of the proEnginListDTO to save.
     * @param proEnginListDTO the proEnginListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proEnginListDTO,
     * or with status {@code 400 (Bad Request)} if the proEnginListDTO is not valid,
     * or with status {@code 404 (Not Found)} if the proEnginListDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the proEnginListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pro-engin-lists/{proEnginListId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProEnginListDTO> partialUpdateProEnginList(
        @PathVariable(value = "proEnginListId", required = false) final Long proEnginListId,
        @NotNull @RequestBody ProEnginListDTO proEnginListDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProEnginList partially : {}, {}", proEnginListId, proEnginListDTO);
        if (proEnginListDTO.getProEnginListId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proEnginListId, proEnginListDTO.getProEnginListId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proEnginListRepository.existsById(proEnginListId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProEnginListDTO> result = proEnginListService.partialUpdate(proEnginListDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proEnginListDTO.getProEnginListId().toString())
        );
    }

    /**
     * {@code GET  /pro-engin-lists} : get all the proEnginLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proEnginLists in body.
     */
    @GetMapping("/pro-engin-lists")
    public List<ProEnginListDTO> getAllProEnginLists() {
        log.debug("REST request to get all ProEnginLists");
        return proEnginListService.findAll();
    }

    /**
     * {@code GET  /pro-engin-lists/:id} : get the "id" proEnginList.
     *
     * @param id the id of the proEnginListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proEnginListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-engin-lists/{id}")
    public ResponseEntity<ProEnginListDTO> getProEnginList(@PathVariable Long id) {
        log.debug("REST request to get ProEnginList : {}", id);
        Optional<ProEnginListDTO> proEnginListDTO = proEnginListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proEnginListDTO);
    }

    /**
     * {@code DELETE  /pro-engin-lists/:id} : delete the "id" proEnginList.
     *
     * @param id the id of the proEnginListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-engin-lists/{id}")
    public ResponseEntity<Void> deleteProEnginList(@PathVariable Long id) {
        log.debug("REST request to delete ProEnginList : {}", id);
        proEnginListService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
