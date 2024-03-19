package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidProjectFilePrimaryKey;
import gov.pcc.pwc.repository.BidProjectFileRepository;
import gov.pcc.pwc.service.BidProjectFileService;
import gov.pcc.pwc.service.dto.BidProjectFileDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidProjectFile}.
 */
@RestController
@RequestMapping("/api")
public class BidProjectFileResource {

    private final Logger log = LoggerFactory.getLogger(BidProjectFileResource.class);

    private static final String ENTITY_NAME = "bidProjectFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidProjectFileService bidProjectFileService;

    private final BidProjectFileRepository bidProjectFileRepository;

    public BidProjectFileResource(BidProjectFileService bidProjectFileService, BidProjectFileRepository bidProjectFileRepository) {
        this.bidProjectFileService = bidProjectFileService;
        this.bidProjectFileRepository = bidProjectFileRepository;
    }

    /**
     * {@code POST  /bid-project-files} : Create a new bidProjectFile.
     *
     * @param bidProjectFileDTO the bidProjectFileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidProjectFileDTO, or with status {@code 400 (Bad Request)} if the bidProjectFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-project-files")
    public ResponseEntity<BidProjectFileDTO> createBidProjectFile(@Valid @RequestBody BidProjectFileDTO bidProjectFileDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidProjectFile : {}", bidProjectFileDTO);
        if (bidProjectFileDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidProjectFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidProjectFileDTO result = bidProjectFileService.save(bidProjectFileDTO);
        return ResponseEntity
            .created(new URI("/api/bid-project-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-project-files/:id} : Updates an existing bidProjectFile.
     *
     * @param id the id of the bidProjectFileDTO to save.
     * @param bidProjectFileDTO the bidProjectFileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidProjectFileDTO,
     * or with status {@code 400 (Bad Request)} if the bidProjectFileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidProjectFileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-project-files/{id}")
    public ResponseEntity<BidProjectFileDTO> updateBidProjectFile(
        @PathVariable(value = "id", required = false) final BidProjectFilePrimaryKey id,
        @Valid @RequestBody BidProjectFileDTO bidProjectFileDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidProjectFile : {}, {}", id, bidProjectFileDTO);
        if (bidProjectFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidProjectFileDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidProjectFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidProjectFileDTO result = bidProjectFileService.save(bidProjectFileDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidProjectFileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-project-files/:id} : Partial updates given fields of an existing bidProjectFile, field will ignore if it is null
     *
     * @param id the id of the bidProjectFileDTO to save.
     * @param bidProjectFileDTO the bidProjectFileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidProjectFileDTO,
     * or with status {@code 400 (Bad Request)} if the bidProjectFileDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidProjectFileDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidProjectFileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-project-files/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidProjectFileDTO> partialUpdateBidProjectFile(
        @PathVariable(value = "id", required = false) final BidProjectFilePrimaryKey id,
        @NotNull @RequestBody BidProjectFileDTO bidProjectFileDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidProjectFile partially : {}, {}", id, bidProjectFileDTO);
        if (bidProjectFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidProjectFileDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidProjectFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidProjectFileDTO> result = bidProjectFileService.partialUpdate(bidProjectFileDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidProjectFileDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-project-files} : get all the bidProjectFiles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidProjectFiles in body.
     */
    @GetMapping("/bid-project-files")
    public List<BidProjectFileDTO> getAllBidProjectFiles() {
        log.debug("REST request to get all BidProjectFiles");
        return bidProjectFileService.findAll();
    }

    /**
     * {@code GET  /bid-project-files/:id} : get the "id" bidProjectFile.
     *
     * @param id the id of the bidProjectFileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidProjectFileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-project-files/{id}")
    public ResponseEntity<BidProjectFileDTO> getBidProjectFile(@PathVariable BidProjectFilePrimaryKey id) {
        log.debug("REST request to get BidProjectFile : {}", id);
        Optional<BidProjectFileDTO> bidProjectFileDTO = bidProjectFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidProjectFileDTO);
    }

    /**
     * {@code POST  /bid-project-files/getOne} : get the "id" bidProjectFile.
     *
     * @param bidProjectFilePrimaryKey the id of the bidProjectFileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidProjectFileDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-project-files/getOne")
    public BidProjectFileDTO getOne(@RequestBody BidProjectFilePrimaryKey bidProjectFilePrimaryKey) {
        log.debug("REST request to get BidProjectFile : {}", bidProjectFilePrimaryKey);
        Optional<BidProjectFileDTO> result = bidProjectFileService.findOne(bidProjectFilePrimaryKey);
        BidProjectFileDTO bidProjectFileDTO = new BidProjectFileDTO();
        if (result.isPresent()) {
            bidProjectFileDTO = result.get();
        } else {
            bidProjectFileDTO.setId(bidProjectFilePrimaryKey);
        }
        return bidProjectFileDTO;
    }

    /**
     * {@code DELETE  /bid-project-files/:id} : delete the "id" bidProjectFile.
     *
     * @param id the id of the bidProjectFileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-project-files/{id}")
    public ResponseEntity<Void> deleteBidProjectFile(@PathVariable BidProjectFilePrimaryKey id) {
        log.debug("REST request to delete BidProjectFile : {}", id);
        bidProjectFileService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
