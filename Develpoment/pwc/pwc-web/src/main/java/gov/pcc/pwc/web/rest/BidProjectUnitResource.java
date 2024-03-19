package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidProjectUnitPrimaryKey;
import gov.pcc.pwc.repository.BidProjectUnitRepository;
import gov.pcc.pwc.service.BidProjectUnitService;
import gov.pcc.pwc.service.dto.BidProjectUnitDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidProjectUnit}.
 */
@RestController
@RequestMapping("/api")
public class BidProjectUnitResource {

    private final Logger log = LoggerFactory.getLogger(BidProjectUnitResource.class);

    private static final String ENTITY_NAME = "bidProjectUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidProjectUnitRepository bidProjectUnitRepository;

    public BidProjectUnitResource(BidProjectUnitService bidProjectUnitService, BidProjectUnitRepository bidProjectUnitRepository) {
        this.bidProjectUnitService = bidProjectUnitService;
        this.bidProjectUnitRepository = bidProjectUnitRepository;
    }

    /**
     * {@code POST  /bid-project-units} : Create a new bidProjectUnit.
     *
     * @param bidProjectUnitDTO the bidProjectUnitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidProjectUnitDTO, or with status {@code 400 (Bad Request)} if the bidProjectUnit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-project-units")
    public ResponseEntity<BidProjectUnitDTO> createBidProjectUnit(@Valid @RequestBody BidProjectUnitDTO bidProjectUnitDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidProjectUnit : {}", bidProjectUnitDTO);
        if (bidProjectUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidProjectUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidProjectUnitDTO result = bidProjectUnitService.save(bidProjectUnitDTO);
        return ResponseEntity
            .created(new URI("/api/bid-project-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-project-units/:id} : Updates an existing bidProjectUnit.
     *
     * @param bidProjectUnitDTO the bidProjectUnitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidProjectUnitDTO,
     * or with status {@code 400 (Bad Request)} if the bidProjectUnitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidProjectUnitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-project-units/{id}")
    public ResponseEntity<BidProjectUnitDTO> updateBidProjectUnit(@Valid @RequestBody BidProjectUnitDTO bidProjectUnitDTO)
        throws URISyntaxException {
        BidProjectUnitPrimaryKey bidProjectUnitPrimaryKey = new BidProjectUnitPrimaryKey(
            bidProjectUnitDTO.getWkut(),
            bidProjectUnitDTO.getPrjno(),
            bidProjectUnitDTO.getType(),
            bidProjectUnitDTO.getSeq()
        );
        log.debug("REST request to update BidProjectUnit : {}", bidProjectUnitDTO);
        if (bidProjectUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidProjectUnitPrimaryKey, bidProjectUnitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidProjectUnitRepository.existsById(bidProjectUnitPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidProjectUnitDTO result = bidProjectUnitService.save(bidProjectUnitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidProjectUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-project-units/:id} : Partial updates given fields of an existing bidProjectUnit, field will ignore if it is null
     *
     * @param bidProjectUnitDTO the bidProjectUnitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidProjectUnitDTO,
     * or with status {@code 400 (Bad Request)} if the bidProjectUnitDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidProjectUnitDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidProjectUnitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-project-units/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidProjectUnitDTO> partialUpdateBidProjectUnit(@NotNull @RequestBody BidProjectUnitDTO bidProjectUnitDTO)
        throws URISyntaxException {
        BidProjectUnitPrimaryKey bidProjectUnitPrimaryKey = new BidProjectUnitPrimaryKey(
            bidProjectUnitDTO.getWkut(),
            bidProjectUnitDTO.getPrjno(),
            bidProjectUnitDTO.getType(),
            bidProjectUnitDTO.getSeq()
        );

        log.debug("REST request to partial update BidProjectUnit partially : {}", bidProjectUnitDTO);
        if (bidProjectUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidProjectUnitPrimaryKey, bidProjectUnitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidProjectUnitRepository.existsById(bidProjectUnitPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidProjectUnitDTO> result = bidProjectUnitService.partialUpdate(bidProjectUnitDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidProjectUnitDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-project-units} : get all the bidProjectUnits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidProjectUnits in body.
     */
    @GetMapping("/bid-project-units")
    public List<BidProjectUnitDTO> getAllBidProjectUnits() {
        log.debug("REST request to get all BidProjectUnits");
        return bidProjectUnitService.findAll();
    }

    @GetMapping("/bid-project-units/units/{wkut}/{prjno}/{type}")
    public List<BidProjectUnitDTO> findUnitsByWkutAndPrjnoAndType(@PathVariable String wkut, @PathVariable String prjno, @PathVariable String type) {
        log.debug("REST request to get BidProjectUnit : {}, {}, {}", wkut, prjno, type);
        return bidProjectUnitService.findUnitsByWkutAndPrjnoAndType(wkut, prjno, type);
    }

    /**
     * {@code GET  /bid-project-units/:id} : get the "id" bidProjectUnit.
     *
     * @param id the id of the bidProjectUnitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidProjectUnitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-project-units/{id}")
    public ResponseEntity<BidProjectUnitDTO> getBidProjectUnit(@PathVariable BidProjectUnitPrimaryKey id) {
        log.debug("REST request to get BidProjectUnit : {}", id);
        Optional<BidProjectUnitDTO> bidProjectUnitDTO = bidProjectUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidProjectUnitDTO);
    }

    /**
     * {@code DELETE  /bid-project-units/:id} : delete the "id" bidProjectUnit.
     *
     * @param id the id of the bidProjectUnitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-project-units/{id}")
    public ResponseEntity<Void> deleteBidProjectUnit(@PathVariable BidProjectUnitPrimaryKey id) {
        log.debug("REST request to delete BidProjectUnit : {}", id);
        bidProjectUnitService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/bid-project-units/ccut/{wkut}/{prjno}")
    public List<BidProjectUnitDTO> findBidCcutNamesByKeyWorld(@PathVariable String wkut, @PathVariable String prjno) {
        log.debug("REST request to get all BidProjectUint with wkut:{}, prjno:{}", wkut, prjno);
        return bidProjectUnitService.findBidCcutByKeyWorld(wkut, prjno);
    }

    @PostMapping("/bid-project-units/ccnt/{wkut}/{prjno}")
    public List<BidProjectUnitDTO> findBidCcntNamesByKeyWorld(@PathVariable String wkut, @PathVariable String prjno) {
        log.debug("REST request to get all BidProjectUint with wkut:{}, prjno:{}", wkut, prjno);
        return bidProjectUnitService.findBidCcntByKeyWorld(wkut, prjno);
    }
}
