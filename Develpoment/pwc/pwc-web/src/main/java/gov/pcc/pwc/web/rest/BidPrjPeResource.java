package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.BidPrjPeRepository;
import gov.pcc.pwc.service.BidPrjPeService;
import gov.pcc.pwc.service.dto.BidPrjPeDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjPe}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjPeResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjPeResource.class);

    private static final String ENTITY_NAME = "bidPrjPe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjPeService bidPrjPeService;

    private final BidPrjPeRepository bidPrjPeRepository;

    public BidPrjPeResource(BidPrjPeService bidPrjPeService, BidPrjPeRepository bidPrjPeRepository) {
        this.bidPrjPeService = bidPrjPeService;
        this.bidPrjPeRepository = bidPrjPeRepository;
    }

    /**
     * {@code POST  /bid-prj-pes} : Create a new bidPrjPe.
     *
     * @param bidPrjPeDTO the bidPrjPeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjPeDTO, or with status {@code 400 (Bad Request)} if the bidPrjPe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-pes")
    public ResponseEntity<BidPrjPeDTO> createBidPrjPe(@Valid @RequestBody BidPrjPeDTO bidPrjPeDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjPe : {}", bidPrjPeDTO);
        if (bidPrjPeDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidPrjPe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPrjPeDTO result = bidPrjPeService.save(bidPrjPeDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-pes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-pes/:id} : Updates an existing bidPrjPe.
     *
     * @param id the id of the bidPrjPeDTO to save.
     * @param bidPrjPeDTO the bidPrjPeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjPeDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjPeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjPeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-pes/{id}")
    public ResponseEntity<BidPrjPeDTO> updateBidPrjPe(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody BidPrjPeDTO bidPrjPeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjPe : {}, {}", id, bidPrjPeDTO);
        if (bidPrjPeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjPeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjPeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjPeDTO result = bidPrjPeService.save(bidPrjPeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjPeDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-pes/:id} : Partial updates given fields of an existing bidPrjPe, field will ignore if it is null
     *
     * @param id the id of the bidPrjPeDTO to save.
     * @param bidPrjPeDTO the bidPrjPeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjPeDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjPeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjPeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjPeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-pes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjPeDTO> partialUpdateBidPrjPe(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody BidPrjPeDTO bidPrjPeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjPe partially : {}, {}", id, bidPrjPeDTO);
        if (bidPrjPeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjPeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjPeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjPeDTO> result = bidPrjPeService.partialUpdate(bidPrjPeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjPeDTO.getId())
        );
    }

    /**
     * {@code GET  /bid-prj-pes} : get all the bidPrjPes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjPes in body.
     */
    @GetMapping("/bid-prj-pes")
    public List<BidPrjPeDTO> getAllBidPrjPes() {
        log.debug("REST request to get all BidPrjPes");
        return bidPrjPeService.findAll();
    }

    /**
     * {@code GET  /bid-prj-pes/:id} : get the "id" bidPrjPe.
     *
     * @param id the id of the bidPrjPeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjPeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-prj-pes/{id}")
    public ResponseEntity<BidPrjPeDTO> getBidPrjPe(@PathVariable String id) {
        log.debug("REST request to get BidPrjPe : {}", id);
        Optional<BidPrjPeDTO> bidPrjPeDTO = bidPrjPeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjPeDTO);
    }

    /**
     * {@code DELETE  /bid-prj-pes/:id} : delete the "id" bidPrjPe.
     *
     * @param id the id of the bidPrjPeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-pes/{id}")
    public ResponseEntity<Void> deleteBidPrjPe(@PathVariable String id) {
        log.debug("REST request to delete BidPrjPe : {}", id);
        bidPrjPeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
