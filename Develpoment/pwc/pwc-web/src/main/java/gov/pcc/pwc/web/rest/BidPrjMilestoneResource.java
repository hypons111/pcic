package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.BidPrjMilestonePrimaryKey;
import gov.pcc.pwc.repository.BidPrjMilestoneRepository;
import gov.pcc.pwc.service.BidPrjMilestoneService;
import gov.pcc.pwc.service.criteria.BidPrjMilestoneCriteria;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjMilestone}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjMilestoneResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjMilestoneResource.class);

    private static final String ENTITY_NAME = "bidPrjMilestone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjMilestoneService bidPrjMilestoneService;

    private final BidPrjMilestoneRepository bidPrjMilestoneRepository;

    public BidPrjMilestoneResource(BidPrjMilestoneService bidPrjMilestoneService, BidPrjMilestoneRepository bidPrjMilestoneRepository) {
        this.bidPrjMilestoneService = bidPrjMilestoneService;
        this.bidPrjMilestoneRepository = bidPrjMilestoneRepository;
    }

    /**
     * {@code POST  /bid-prj-milestones} : Create a new bidPrjMilestone.
     *
     * @param bidPrjMilestoneDTO the bidPrjMilestoneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjMilestoneDTO, or with status {@code 400 (Bad Request)} if the bidPrjMilestone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-milestones")
    public ResponseEntity<BidPrjMilestoneDTO> createBidPrjMilestone(@Valid @RequestBody BidPrjMilestoneDTO bidPrjMilestoneDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjMilestone : {}", bidPrjMilestoneDTO);
        String fromStatus = "create";

        //       後端驗證
        List<String> validResult = bidPrjMilestoneService.validData(bidPrjMilestoneDTO,fromStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));

        }

        BidPrjMilestoneDTO result = bidPrjMilestoneService.save(bidPrjMilestoneDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-milestones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/bid-prj-milestones/insert")
    public void insertBidPrjMilestone(@Valid @RequestBody BidPrjMilestoneDTO bidPrjMilestoneDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjMilestone : {}", bidPrjMilestoneDTO);
        String fromStatus = "create";

        //       後端驗證
        List<String> validResult = bidPrjMilestoneService.validData(bidPrjMilestoneDTO,fromStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        bidPrjMilestoneService.insert(bidPrjMilestoneDTO);
    }
    /**
     * {@code PUT  /bid-prj-milestones/:id} : Updates an existing bidPrjMilestone.
     *
     * @param bidPrjMilestoneDTO the bidPrjMilestoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjMilestoneDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjMilestoneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjMilestoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-milestones/update")
    public ResponseEntity<BidPrjMilestoneDTO> updateBidPrjMilestone(
        @Valid @RequestBody BidPrjMilestoneDTO bidPrjMilestoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjMilestone : {}",  bidPrjMilestoneDTO);
        BidPrjMilestonePrimaryKey bidPrjMilestonePrimaryKey = new BidPrjMilestonePrimaryKey(bidPrjMilestoneDTO.getWkut(),bidPrjMilestoneDTO.getPrjno(),bidPrjMilestoneDTO.getMilestoneNo());
        if (bidPrjMilestoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        if (!Objects.equals(bidPrjMilestonePrimaryKey, bidPrjMilestoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjMilestoneRepository.existsById(bidPrjMilestonePrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        String formStatus = "modify";
//        後端驗證
        List<String> validResult = bidPrjMilestoneService.validData(bidPrjMilestoneDTO , formStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        BidPrjMilestoneDTO result = bidPrjMilestoneService.save(bidPrjMilestoneDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjMilestoneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-milestones/:id} : Partial updates given fields of an existing bidPrjMilestone, field will ignore if it is null
     *
     * @param bidPrjMilestoneDTO the bidPrjMilestoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjMilestoneDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjMilestoneDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjMilestoneDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjMilestoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-milestones/partialUpdate", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjMilestoneDTO> partialUpdateBidPrjMilestone(
        @NotNull @RequestBody BidPrjMilestoneDTO bidPrjMilestoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjMilestone partially : {}", bidPrjMilestoneDTO);
        BidPrjMilestonePrimaryKey bidPrjMilestonePrimaryKey = new BidPrjMilestonePrimaryKey(bidPrjMilestoneDTO.getWkut(),bidPrjMilestoneDTO.getPrjno(),bidPrjMilestoneDTO.getMilestoneNo());

        if (bidPrjMilestoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjMilestonePrimaryKey, bidPrjMilestoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjMilestoneRepository.existsById(bidPrjMilestonePrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjMilestoneDTO> result = bidPrjMilestoneService.partialUpdate(bidPrjMilestoneDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjMilestoneDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-milestones} : get all the bidPrjMilestones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjMilestones in body.
     */
    @GetMapping("/bid-prj-milestones")
    public List<BidPrjMilestoneDTO> getAllBidPrjMilestones() {
        log.debug("REST request to get all BidPrjMilestones");
        return bidPrjMilestoneService.findAll();
    }

    /**
     * {@code GET  /bid-prj-milestones/:id} : get the "id" bidPrjMilestone.
     *
     * @param id the id of the bidPrjMilestoneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjMilestoneDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-milestones/getOne")
    public ResponseEntity<BidPrjMilestoneDTO> getBidPrjMilestone(@Valid@RequestBody BidPrjMilestonePrimaryKey id) {
        log.debug("REST request to get BidPrjMilestone : {}", id);
        Optional<BidPrjMilestoneDTO> bidPrjMilestoneDTO = bidPrjMilestoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjMilestoneDTO);
    }

    /**
     * {@code DELETE  /bid-prj-milestones/:id} : delete the "id" bidPrjMilestone.
     *
     * @param id the id of the bidPrjMilestoneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-milestones/delete")
    public ResponseEntity<Void> deleteBidPrjMilestone(@RequestBody BidPrjMilestonePrimaryKey id) {
        log.debug("REST request to delete BidPrjMilestone : {}", id);
        bidPrjMilestoneService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * get all bidPrjMilestone by Criteria
     * */
    @PostMapping("/bid-prj-milestones/criteria")
    public Page<BidPrjMilestoneDTO> getBidPrjMilestoneByCriteria(@Valid @RequestBody BidPrjMilestoneCriteria criteria){
        log.debug("REST request to get BidPrjMilestone : {}",criteria);
        return bidPrjMilestoneService.findByCriteria(criteria);
    }
}
