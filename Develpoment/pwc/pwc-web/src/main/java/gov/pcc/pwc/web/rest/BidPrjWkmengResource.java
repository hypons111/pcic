package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.BidPrjWkmengRepository;
import gov.pcc.pwc.service.BidPrjWkmengService;
import gov.pcc.pwc.service.dto.BidPrjWkmengDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjWkmeng}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjWkmengResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjWkmengResource.class);

    private static final String ENTITY_NAME = "bidPrjWkmeng";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjWkmengService bidPrjWkmengService;

    private final BidPrjWkmengRepository bidPrjWkmengRepository;

    public BidPrjWkmengResource(BidPrjWkmengService bidPrjWkmengService, BidPrjWkmengRepository bidPrjWkmengRepository) {
        this.bidPrjWkmengService = bidPrjWkmengService;
        this.bidPrjWkmengRepository = bidPrjWkmengRepository;
    }

    /**
     * {@code POST  /bid-prj-wkmengs} : Create a new bidPrjWkmeng.
     *
     * @param bidPrjWkmengDTO the bidPrjWkmengDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjWkmengDTO, or with status {@code 400 (Bad Request)} if the bidPrjWkmeng has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-wkmengs")
    public ResponseEntity<BidPrjWkmengDTO> createBidPrjWkmeng(@Valid @RequestBody BidPrjWkmengDTO bidPrjWkmengDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjWkmeng : {}", bidPrjWkmengDTO);
        if (bidPrjWkmengDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidPrjWkmeng cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPrjWkmengDTO result = bidPrjWkmengService.save(bidPrjWkmengDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-wkmengs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-wkmengs/:id} : Updates an existing bidPrjWkmeng.
     *
     * @param id the id of the bidPrjWkmengDTO to save.
     * @param bidPrjWkmengDTO the bidPrjWkmengDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjWkmengDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjWkmengDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjWkmengDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-wkmengs/{id}")
    public ResponseEntity<BidPrjWkmengDTO> updateBidPrjWkmeng(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody BidPrjWkmengDTO bidPrjWkmengDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjWkmeng : {}, {}", id, bidPrjWkmengDTO);
        if (bidPrjWkmengDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjWkmengDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjWkmengRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjWkmengDTO result = bidPrjWkmengService.save(bidPrjWkmengDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjWkmengDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-wkmengs/:id} : Partial updates given fields of an existing bidPrjWkmeng, field will ignore if it is null
     *
     * @param id the id of the bidPrjWkmengDTO to save.
     * @param bidPrjWkmengDTO the bidPrjWkmengDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjWkmengDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjWkmengDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjWkmengDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjWkmengDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-wkmengs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjWkmengDTO> partialUpdateBidPrjWkmeng(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody BidPrjWkmengDTO bidPrjWkmengDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjWkmeng partially : {}, {}", id, bidPrjWkmengDTO);
        if (bidPrjWkmengDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjWkmengDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjWkmengRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjWkmengDTO> result = bidPrjWkmengService.partialUpdate(bidPrjWkmengDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjWkmengDTO.getId())
        );
    }

    /**
     * {@code GET  /bid-prj-wkmengs} : get all the bidPrjWkmengs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjWkmengs in body.
     */
    @GetMapping("/bid-prj-wkmengs")
    public List<BidPrjWkmengDTO> getAllBidPrjWkmengs() {
        log.debug("REST request to get all BidPrjWkmengs");
        return bidPrjWkmengService.findAll();
    }

    /**
     * {@code GET  /bid-prj-wkmengs/:id} : get the "id" bidPrjWkmeng.
     *
     * @param id the id of the bidPrjWkmengDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjWkmengDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-prj-wkmengs/{id}")
    public ResponseEntity<BidPrjWkmengDTO> getBidPrjWkmeng(@PathVariable String id) {
        log.debug("REST request to get BidPrjWkmeng : {}", id);
        Optional<BidPrjWkmengDTO> bidPrjWkmengDTO = bidPrjWkmengService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjWkmengDTO);
    }

    /**
     * {@code DELETE  /bid-prj-wkmengs/:id} : delete the "id" bidPrjWkmeng.
     *
     * @param id the id of the bidPrjWkmengDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-wkmengs/{id}")
    public ResponseEntity<Void> deleteBidPrjWkmeng(@PathVariable String id) {
        log.debug("REST request to delete BidPrjWkmeng : {}", id);
        bidPrjWkmengService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
