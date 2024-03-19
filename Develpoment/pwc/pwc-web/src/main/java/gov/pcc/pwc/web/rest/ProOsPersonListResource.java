package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.ProOsPersonListRepository;
import gov.pcc.pwc.service.ProOsPersonListService;
import gov.pcc.pwc.service.dto.ProOsPersonListDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.ProOsPersonList}.
 */
@RestController
@RequestMapping("/api")
public class ProOsPersonListResource {

    private final Logger log = LoggerFactory.getLogger(ProOsPersonListResource.class);

    private static final String ENTITY_NAME = "proOsPersonList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProOsPersonListService proOsPersonListService;

    private final ProOsPersonListRepository proOsPersonListRepository;

    public ProOsPersonListResource(ProOsPersonListService proOsPersonListService, ProOsPersonListRepository proOsPersonListRepository) {
        this.proOsPersonListService = proOsPersonListService;
        this.proOsPersonListRepository = proOsPersonListRepository;
    }

    /**
     * {@code POST  /pro-os-person-lists} : Create a new proOsPersonList.
     *
     * @param proOsPersonListDTO the proOsPersonListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proOsPersonListDTO, or with status {@code 400 (Bad Request)} if the proOsPersonList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-os-person-lists")
    public ResponseEntity<ProOsPersonListDTO> createProOsPersonList(@Valid @RequestBody ProOsPersonListDTO proOsPersonListDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProOsPersonList : {}", proOsPersonListDTO);
        if (proOsPersonListDTO.getProOsPersonListId() != null) {
            throw new BadRequestAlertException("A new proOsPersonList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProOsPersonListDTO result = proOsPersonListService.save(proOsPersonListDTO);
        return ResponseEntity
            .created(new URI("/api/pro-os-person-lists/" + result.getProOsPersonListId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProOsPersonListId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-os-person-lists/:proOsPersonListId} : Updates an existing proOsPersonList.
     *
     * @param proOsPersonListId the id of the proOsPersonListDTO to save.
     * @param proOsPersonListDTO the proOsPersonListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proOsPersonListDTO,
     * or with status {@code 400 (Bad Request)} if the proOsPersonListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proOsPersonListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-os-person-lists/{proOsPersonListId}")
    public ResponseEntity<ProOsPersonListDTO> updateProOsPersonList(
        @PathVariable(value = "proOsPersonListId", required = false) final Long proOsPersonListId,
        @Valid @RequestBody ProOsPersonListDTO proOsPersonListDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProOsPersonList : {}, {}", proOsPersonListId, proOsPersonListDTO);
        if (proOsPersonListDTO.getProOsPersonListId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proOsPersonListId, proOsPersonListDTO.getProOsPersonListId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proOsPersonListRepository.existsById(proOsPersonListId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProOsPersonListDTO result = proOsPersonListService.save(proOsPersonListDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proOsPersonListDTO.getProOsPersonListId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pro-os-person-lists/:proOsPersonListId} : Partial updates given fields of an existing proOsPersonList, field will ignore if it is null
     *
     * @param proOsPersonListId the id of the proOsPersonListDTO to save.
     * @param proOsPersonListDTO the proOsPersonListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proOsPersonListDTO,
     * or with status {@code 400 (Bad Request)} if the proOsPersonListDTO is not valid,
     * or with status {@code 404 (Not Found)} if the proOsPersonListDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the proOsPersonListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pro-os-person-lists/{proOsPersonListId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProOsPersonListDTO> partialUpdateProOsPersonList(
        @PathVariable(value = "proOsPersonListId", required = false) final Long proOsPersonListId,
        @NotNull @RequestBody ProOsPersonListDTO proOsPersonListDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProOsPersonList partially : {}, {}", proOsPersonListId, proOsPersonListDTO);
        if (proOsPersonListDTO.getProOsPersonListId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proOsPersonListId, proOsPersonListDTO.getProOsPersonListId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proOsPersonListRepository.existsById(proOsPersonListId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProOsPersonListDTO> result = proOsPersonListService.partialUpdate(proOsPersonListDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proOsPersonListDTO.getProOsPersonListId().toString())
        );
    }

    /**
     * {@code GET  /pro-os-person-lists} : get all the proOsPersonLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proOsPersonLists in body.
     */
    @GetMapping("/pro-os-person-lists")
    public List<ProOsPersonListDTO> getAllProOsPersonLists() {
        log.debug("REST request to get all ProOsPersonLists");
        return proOsPersonListService.findAll();
    }

    /**
     * {@code GET  /pro-os-person-lists/:id} : get the "id" proOsPersonList.
     *
     * @param id the id of the proOsPersonListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proOsPersonListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-os-person-lists/{id}")
    public ResponseEntity<ProOsPersonListDTO> getProOsPersonList(@PathVariable Long id) {
        log.debug("REST request to get ProOsPersonList : {}", id);
        Optional<ProOsPersonListDTO> proOsPersonListDTO = proOsPersonListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proOsPersonListDTO);
    }

    /**
     * {@code DELETE  /pro-os-person-lists/:id} : delete the "id" proOsPersonList.
     *
     * @param id the id of the proOsPersonListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-os-person-lists/{id}")
    public ResponseEntity<Void> deleteProOsPersonList(@PathVariable Long id) {
        log.debug("REST request to delete ProOsPersonList : {}", id);
        proOsPersonListService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
