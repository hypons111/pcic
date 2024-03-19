package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.ProRecommendListRepository;
import gov.pcc.pwc.service.ProRecommendListService;
import gov.pcc.pwc.service.dto.ProRecommendListDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.ProRecommendList}.
 */
@RestController
@RequestMapping("/api")
public class ProRecommendListResource {

    private final Logger log = LoggerFactory.getLogger(ProRecommendListResource.class);

    private static final String ENTITY_NAME = "proRecommendList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProRecommendListService proRecommendListService;

    private final ProRecommendListRepository proRecommendListRepository;

    public ProRecommendListResource(
        ProRecommendListService proRecommendListService,
        ProRecommendListRepository proRecommendListRepository
    ) {
        this.proRecommendListService = proRecommendListService;
        this.proRecommendListRepository = proRecommendListRepository;
    }

    /**
     * {@code POST  /pro-recommend-lists} : Create a new proRecommendList.
     *
     * @param proRecommendListDTO the proRecommendListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proRecommendListDTO, or with status {@code 400 (Bad Request)} if the proRecommendList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-recommend-lists")
    public ResponseEntity<ProRecommendListDTO> createProRecommendList(@Valid @RequestBody ProRecommendListDTO proRecommendListDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProRecommendList : {}", proRecommendListDTO);
        if (proRecommendListDTO.getProRecommendListId() != null) {
            throw new BadRequestAlertException("A new proRecommendList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProRecommendListDTO result = proRecommendListService.save(proRecommendListDTO);
        return ResponseEntity
            .created(new URI("/api/pro-recommend-lists/" + result.getProRecommendListId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProRecommendListId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-recommend-lists/:proRecommendListId} : Updates an existing proRecommendList.
     *
     * @param proRecommendListId the id of the proRecommendListDTO to save.
     * @param proRecommendListDTO the proRecommendListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proRecommendListDTO,
     * or with status {@code 400 (Bad Request)} if the proRecommendListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proRecommendListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-recommend-lists/{proRecommendListId}")
    public ResponseEntity<ProRecommendListDTO> updateProRecommendList(
        @PathVariable(value = "proRecommendListId", required = false) final Long proRecommendListId,
        @Valid @RequestBody ProRecommendListDTO proRecommendListDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProRecommendList : {}, {}", proRecommendListId, proRecommendListDTO);
        if (proRecommendListDTO.getProRecommendListId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proRecommendListId, proRecommendListDTO.getProRecommendListId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proRecommendListRepository.existsById(proRecommendListId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProRecommendListDTO result = proRecommendListService.save(proRecommendListDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    proRecommendListDTO.getProRecommendListId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pro-recommend-lists/:proRecommendListId} : Partial updates given fields of an existing proRecommendList, field will ignore if it is null
     *
     * @param proRecommendListId the id of the proRecommendListDTO to save.
     * @param proRecommendListDTO the proRecommendListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proRecommendListDTO,
     * or with status {@code 400 (Bad Request)} if the proRecommendListDTO is not valid,
     * or with status {@code 404 (Not Found)} if the proRecommendListDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the proRecommendListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pro-recommend-lists/{proRecommendListId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProRecommendListDTO> partialUpdateProRecommendList(
        @PathVariable(value = "proRecommendListId", required = false) final Long proRecommendListId,
        @NotNull @RequestBody ProRecommendListDTO proRecommendListDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProRecommendList partially : {}, {}", proRecommendListId, proRecommendListDTO);
        if (proRecommendListDTO.getProRecommendListId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proRecommendListId, proRecommendListDTO.getProRecommendListId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proRecommendListRepository.existsById(proRecommendListId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProRecommendListDTO> result = proRecommendListService.partialUpdate(proRecommendListDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proRecommendListDTO.getProRecommendListId().toString())
        );
    }

    /**
     * {@code GET  /pro-recommend-lists} : get all the proRecommendLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRecommendLists in body.
     */
    @GetMapping("/pro-recommend-lists")
    public List<ProRecommendListDTO> getAllProRecommendLists() {
        log.debug("REST request to get all ProRecommendLists");
        return proRecommendListService.findAll();
    }

    /**
     * {@code GET  /pro-recommend-lists/:id} : get the "id" proRecommendList.
     *
     * @param id the id of the proRecommendListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proRecommendListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-recommend-lists/{id}")
    public ResponseEntity<ProRecommendListDTO> getProRecommendList(@PathVariable Long id) {
        log.debug("REST request to get ProRecommendList : {}", id);
        Optional<ProRecommendListDTO> proRecommendListDTO = proRecommendListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proRecommendListDTO);
    }

    /**
     * {@code DELETE  /pro-recommend-lists/:id} : delete the "id" proRecommendList.
     *
     * @param id the id of the proRecommendListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-recommend-lists/{id}")
    public ResponseEntity<Void> deleteProRecommendList(@PathVariable Long id) {
        log.debug("REST request to delete ProRecommendList : {}", id);
        proRecommendListService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
