package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.TecContentRepository;
import gov.pcc.pwc.service.TecContentService;
import gov.pcc.pwc.service.dto.TecContentDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.TecContent}.
 */
@RestController
@RequestMapping("/api")
public class TecContentResource {

    private final Logger log = LoggerFactory.getLogger(TecContentResource.class);

    private static final String ENTITY_NAME = "tecContent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TecContentService tecContentService;

    private final TecContentRepository tecContentRepository;

    public TecContentResource(TecContentService tecContentService, TecContentRepository tecContentRepository) {
        this.tecContentService = tecContentService;
        this.tecContentRepository = tecContentRepository;
    }

    /**
     * {@code POST  /tec-contents} : Create a new tecContent.
     *
     * @param tecContentDTO the tecContentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tecContentDTO, or with status {@code 400 (Bad Request)} if the tecContent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tec-contents")
    public ResponseEntity<TecContentDTO> createTecContent(@Valid @RequestBody TecContentDTO tecContentDTO) throws URISyntaxException {
        log.debug("REST request to save TecContent : {}", tecContentDTO);
        if (tecContentDTO.getContentId() != null) {
            throw new BadRequestAlertException("A new tecContent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TecContentDTO result = tecContentService.save(tecContentDTO);
        return ResponseEntity
            .created(new URI("/api/tec-contents/" + result.getContentId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getContentId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tec-contents/:contentId} : Updates an existing tecContent.
     *
     * @param contentId the id of the tecContentDTO to save.
     * @param tecContentDTO the tecContentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tecContentDTO,
     * or with status {@code 400 (Bad Request)} if the tecContentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tecContentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tec-contents/{contentId}")
    public ResponseEntity<TecContentDTO> updateTecContent(
        @PathVariable(value = "contentId", required = false) final Long contentId,
        @Valid @RequestBody TecContentDTO tecContentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TecContent : {}, {}", contentId, tecContentDTO);
        if (tecContentDTO.getContentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(contentId, tecContentDTO.getContentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tecContentRepository.existsById(contentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TecContentDTO result = tecContentService.save(tecContentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tecContentDTO.getContentId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tec-contents/:contentId} : Partial updates given fields of an existing tecContent, field will ignore if it is null
     *
     * @param contentId the id of the tecContentDTO to save.
     * @param tecContentDTO the tecContentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tecContentDTO,
     * or with status {@code 400 (Bad Request)} if the tecContentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tecContentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tecContentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tec-contents/{contentId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TecContentDTO> partialUpdateTecContent(
        @PathVariable(value = "contentId", required = false) final Long contentId,
        @NotNull @RequestBody TecContentDTO tecContentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TecContent partially : {}, {}", contentId, tecContentDTO);
        if (tecContentDTO.getContentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(contentId, tecContentDTO.getContentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tecContentRepository.existsById(contentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TecContentDTO> result = tecContentService.partialUpdate(tecContentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tecContentDTO.getContentId().toString())
        );
    }

    /**
     * {@code GET  /tec-contents} : get all the tecContents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tecContents in body.
     */
    @GetMapping("/tec-contents")
    public List<TecContentDTO> getAllTecContents() {
        log.debug("REST request to get all TecContents");
        return tecContentService.findAll();
    }

    /**
     * {@code GET  /tec-contents/:id} : get the "id" tecContent.
     *
     * @param id the id of the tecContentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tecContentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tec-contents/{id}")
    public ResponseEntity<TecContentDTO> getTecContent(@PathVariable Long id) {
        log.debug("REST request to get TecContent : {}", id);
        Optional<TecContentDTO> tecContentDTO = tecContentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tecContentDTO);
    }

    /**
     * {@code DELETE  /tec-contents/:id} : delete the "id" tecContent.
     *
     * @param id the id of the tecContentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tec-contents/{id}")
    public ResponseEntity<Void> deleteTecContent(@PathVariable Long id) {
        log.debug("REST request to delete TecContent : {}", id);
        tecContentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
