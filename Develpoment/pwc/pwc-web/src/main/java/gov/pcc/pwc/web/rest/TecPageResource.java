package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.TecPageRepository;
import gov.pcc.pwc.service.TecPageService;
import gov.pcc.pwc.service.dto.TecPageContentDTO;
import gov.pcc.pwc.service.dto.TecInfoContentDTO;
import gov.pcc.pwc.service.dto.TecPageDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.TecPage}.
 */
@RestController
@RequestMapping("/api")
public class TecPageResource {

    private final Logger log = LoggerFactory.getLogger(TecPageResource.class);

    private static final String ENTITY_NAME = "tecPage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TecPageService tecPageService;

    private final TecPageRepository tecPageRepository;

    public TecPageResource(TecPageService tecPageService, TecPageRepository tecPageRepository) {
        this.tecPageService = tecPageService;
        this.tecPageRepository = tecPageRepository;
    }

    /**
     * {@code POST  /tec-pages} : Create a new tecPage.
     *
     * @param tecPageDTO the tecPageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tecPageDTO, or with status {@code 400 (Bad Request)} if the tecPage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tec-pages")
    public ResponseEntity<TecPageDTO> createTecPage(@Valid @RequestBody TecPageDTO tecPageDTO) throws URISyntaxException {
        log.debug("REST request to save TecPage : {}", tecPageDTO);
        if (tecPageDTO.getPageId() != null) {
            throw new BadRequestAlertException("A new tecPage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TecPageDTO result = tecPageService.save(tecPageDTO);
        return ResponseEntity
            .created(new URI("/api/tec-pages/" + result.getPageId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getPageId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tec-pages/:pageId} : Updates an existing tecPage.
     *
     * @param pageId the id of the tecPageDTO to save.
     * @param tecPageDTO the tecPageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tecPageDTO,
     * or with status {@code 400 (Bad Request)} if the tecPageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tecPageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tec-pages/{pageId}")
    public ResponseEntity<TecPageDTO> updateTecPage(
        @PathVariable(value = "pageId", required = false) final Long pageId,
        @Valid @RequestBody TecPageDTO tecPageDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TecPage : {}, {}", pageId, tecPageDTO);
        if (tecPageDTO.getPageId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(pageId, tecPageDTO.getPageId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tecPageRepository.existsById(pageId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TecPageDTO result = tecPageService.save(tecPageDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tecPageDTO.getPageId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tec-pages/:pageId} : Partial updates given fields of an existing tecPage, field will ignore if it is null
     *
     * @param pageId the id of the tecPageDTO to save.
     * @param tecPageDTO the tecPageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tecPageDTO,
     * or with status {@code 400 (Bad Request)} if the tecPageDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tecPageDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tecPageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tec-pages/{pageId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TecPageDTO> partialUpdateTecPage(
        @PathVariable(value = "pageId", required = false) final Long pageId,
        @NotNull @RequestBody TecPageDTO tecPageDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TecPage partially : {}, {}", pageId, tecPageDTO);
        if (tecPageDTO.getPageId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(pageId, tecPageDTO.getPageId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tecPageRepository.existsById(pageId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TecPageDTO> result = tecPageService.partialUpdate(tecPageDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tecPageDTO.getPageId().toString())
        );
    }

    /**
     * {@code GET  /tec-pages} : get all the tecPages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tecPages in body.
     */
    @GetMapping("/tec-pages")
    public List<TecPageDTO> getAllTecPages() {
        log.debug("REST request to get all TecPages");
        return tecPageService.findAll();
    }

    //顯示最新消息
    @PostMapping("/service/tec-pages/bulletin")
    public List<TecPageContentDTO> getTopTecPages() throws InterruptedException {
        log.debug("REST request to bulletin Controller");
        return tecPageService.findByTop();
    }
    //技術資料庫簡介
    @PostMapping("/service/tec-pages/tec-intro")
    public TecInfoContentDTO getTecIntro() throws InterruptedException {
        log.debug("REST request to tec-intro Controller");
        return tecPageService.findTecIntroByQuery();
    }

    //法規參考
    @PostMapping("/service/tec-pages/law-ref")
    public TecInfoContentDTO getLawRef() throws InterruptedException {
        log.debug("REST request to tec-intro Controller");
        return tecPageService.findLawRefByQuery();
    }


    /**
     * {@code GET  /tec-pages/:id} : get the "id" tecPage.
     *
     * @param id the id of the tecPageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tecPageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tec-pages/{id}")
    public ResponseEntity<TecPageDTO> getTecPage(@PathVariable Long id) {
        log.debug("REST request to get TecPage : {}", id);
        Optional<TecPageDTO> tecPageDTO = tecPageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tecPageDTO);
    }

    /**
     * {@code DELETE  /tec-pages/:id} : delete the "id" tecPage.
     *
     * @param id the id of the tecPageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tec-pages/{id}")
    public ResponseEntity<Void> deleteTecPage(@PathVariable Long id) {
        log.debug("REST request to delete TecPage : {}", id);
        tecPageService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
