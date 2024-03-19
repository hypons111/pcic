package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.BidOverseeRepository;
import gov.pcc.pwc.service.BidOverseeService;
import gov.pcc.pwc.service.criteria.BidOverseeCriteria;
import gov.pcc.pwc.service.dto.BidOverseeDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidOversee}.
 */
@RestController
@RequestMapping("/api")
public class BidOverseeResource {

    private final Logger log = LoggerFactory.getLogger(BidOverseeResource.class);

    private static final String ENTITY_NAME = "bidOversee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidOverseeService bidOverseeService;

    private final BidOverseeRepository bidOverseeRepository;

    public BidOverseeResource(BidOverseeService bidOverseeService, BidOverseeRepository bidOverseeRepository) {
        this.bidOverseeService = bidOverseeService;
        this.bidOverseeRepository = bidOverseeRepository;
    }

    /**
     * {@code POST  /bid-oversees} : Create a new bidOversee.
     *
     * @param bidOverseeDTO the bidOverseeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidOverseeDTO, or with status {@code 400 (Bad Request)} if the bidOversee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-oversees")
    public ResponseEntity<BidOverseeDTO> createBidOversee(@Valid @RequestBody BidOverseeDTO bidOverseeDTO) throws URISyntaxException {
        log.debug("REST request to save BidOversee : {}", bidOverseeDTO);
        if (bidOverseeDTO.getSeqNo() != null) {
            throw new BadRequestAlertException("A new bidOversee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidOverseeDTO result = bidOverseeService.save(bidOverseeDTO);
        return ResponseEntity
            .created(new URI("/api/bid-oversees/" + result.getSeqNo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getSeqNo().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-oversees/:id} : Updates an existing bidOversee.
     *
     * @param id the id of the bidOverseeDTO to save.
     * @param bidOverseeDTO the bidOverseeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidOverseeDTO,
     * or with status {@code 400 (Bad Request)} if the bidOverseeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidOverseeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-oversees/{id}")
    public ResponseEntity<BidOverseeDTO> updateBidOversee(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody BidOverseeDTO bidOverseeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidOversee : {}, {}", id, bidOverseeDTO);
        if (bidOverseeDTO.getSeqNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidOverseeDTO.getSeqNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidOverseeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidOverseeDTO result = bidOverseeService.save(bidOverseeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidOverseeDTO.getSeqNo().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-oversees/:id} : Partial updates given fields of an existing bidOversee, field will ignore if it is null
     *
     * @param id the id of the bidOverseeDTO to save.
     * @param bidOverseeDTO the bidOverseeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidOverseeDTO,
     * or with status {@code 400 (Bad Request)} if the bidOverseeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidOverseeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidOverseeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-oversees/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidOverseeDTO> partialUpdateBidOversee(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody BidOverseeDTO bidOverseeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidOversee partially : {}, {}", id, bidOverseeDTO);
        if (bidOverseeDTO.getSeqNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidOverseeDTO.getSeqNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidOverseeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidOverseeDTO> result = bidOverseeService.partialUpdate(bidOverseeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidOverseeDTO.getSeqNo().toString())
        );
    }

    /**
     * {@code GET  /bid-oversees} : get all the bidOversees.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidOversees in body.
     */
    @GetMapping("/bid-oversees")
    public List<BidOverseeDTO> getAllBidOversees() {
        log.debug("REST request to get all BidOversees");
        return bidOverseeService.findAll();
    }

    /**
     * {@code GET  /bid-oversees/:id} : get the "id" bidOversee.
     *
     * @param id the id of the bidOverseeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidOverseeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-oversees/{id}")
    public ResponseEntity<BidOverseeDTO> getBidOversee(@PathVariable String id) {
        log.debug("REST request to get BidOversee : {}", id);
        Optional<BidOverseeDTO> bidOverseeDTO = bidOverseeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidOverseeDTO);
    }

    /**
     * {@code DELETE  /bid-oversees/:id} : delete the "id" bidOversee.
     *
     * @param id the id of the bidOverseeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-oversees/{id}")
    public ResponseEntity<Void> deleteBidOversee(@PathVariable String id) {
        log.debug("REST request to delete BidOversee : {}", id);
        bidOverseeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/bid-oversees/list-by-criteria")
    public List<BidOverseeDTO> getBidOverseeDtoListByCriteria(@RequestBody BidOverseeCriteria criteria){
        return bidOverseeService.findListByCriteria(criteria);
    }
}
