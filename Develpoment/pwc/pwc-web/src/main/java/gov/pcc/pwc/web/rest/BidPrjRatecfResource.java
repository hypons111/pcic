package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPrjEnviPrimaryKey;
import gov.pcc.pwc.domain.BidPrjRatecfPrimaryKey;
import gov.pcc.pwc.domain.BidPrjWksorcPrimaryKey;
import gov.pcc.pwc.repository.BidPrjRatecfRepository;
import gov.pcc.pwc.service.BidPrjRatecfService;
import gov.pcc.pwc.service.criteria.BidPrjRatecfCriteria;
import gov.pcc.pwc.service.criteria.BidPrjWksorcCriteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import gov.pcc.pwc.service.dto.BidPrjRatecfDTO;
//import gov.pcc.pwc.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.BidPrjWksorcDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjRatecf}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjRatecfResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjRatecfResource.class);

    private static final String ENTITY_NAME = "bidPrjRatecf";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjRatecfService bidPrjRatecfService;

    private final BidPrjRatecfRepository bidPrjRatecfRepository;

    public BidPrjRatecfResource(BidPrjRatecfService bidPrjRatecfService, BidPrjRatecfRepository bidPrjRatecfRepository) {
        this.bidPrjRatecfService = bidPrjRatecfService;
        this.bidPrjRatecfRepository = bidPrjRatecfRepository;
    }

    /**
     * {@code POST  /bid-prj-ratecfs} : Create a new bidPrjRatecf.
     *
     * @param bidPrjRatecfDTO the bidPrjRatecfDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjRatecfDTO, or with status {@code 400 (Bad Request)} if the bidPrjRatecf has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-ratecfs")
    public ResponseEntity<BidPrjRatecfDTO> createBidPrjRatecf(@Valid @RequestBody BidPrjRatecfDTO bidPrjRatecfDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjRatecf : {}", bidPrjRatecfDTO);
//        if (bidPrjRatecfDTO.getId() != null) {
//            throw new BadRequestAlertException("A new bidPrjRatecf cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        BidPrjRatecfDTO result = bidPrjRatecfService.save(bidPrjRatecfDTO);
        return ResponseEntity
//            .created(new URI("/api/bid-prj-ratecfs/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
            .created(new URI("/api/bid-prj-safes/" +result.getId().getWkut()+"/"+ result.getId().getPrjno()+"/"+ result.getId().getSyr()+"/"+ result.getId().getMonth()+"/"+ result.getId().getChkunit()+"/"+ result.getId().getMft()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-ratecfs/:id} : Updates an existing bidPrjRatecf.
     *
     * @param bidPrjRatecfDTO the id of the bidPrjRatecfDTO to save.
     * @param bidPrjRatecfDTO the bidPrjRatecfDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjRatecfDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjRatecfDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjRatecfDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-ratecfs/{id}")
    public ResponseEntity<BidPrjRatecfDTO> updateBidPrjRatecf(
//        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BidPrjRatecfDTO bidPrjRatecfDTO
    ) throws URISyntaxException {
        BidPrjRatecfPrimaryKey bidPrjRatecfPrimaryKey = new BidPrjRatecfPrimaryKey(bidPrjRatecfDTO.getWkut(), bidPrjRatecfDTO.getPrjno(), bidPrjRatecfDTO.getSyr(), bidPrjRatecfDTO.getMonth(), bidPrjRatecfDTO.getChkunit(), bidPrjRatecfDTO.getMft());
        log.debug("REST request to update BidPrjRatecf : {}, {}", bidPrjRatecfPrimaryKey, bidPrjRatecfDTO);
        if (bidPrjRatecfDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjRatecfPrimaryKey, bidPrjRatecfDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjRatecfRepository.existsById(bidPrjRatecfPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjRatecfDTO result = bidPrjRatecfService.save(bidPrjRatecfDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjRatecfDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-ratecfs/:id} : Partial updates given fields of an existing bidPrjRatecf, field will ignore if it is null
     *
     * @param bidPrjRatecfDTO the id of the bidPrjRatecfDTO to save.
     * @param bidPrjRatecfDTO the bidPrjRatecfDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjRatecfDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjRatecfDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjRatecfDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjRatecfDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-ratecfs/", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjRatecfDTO> partialUpdateBidPrjRatecf(
//        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BidPrjRatecfDTO bidPrjRatecfDTO
    ) throws URISyntaxException {
        BidPrjRatecfPrimaryKey bidPrjRatecfPrimaryKey = new BidPrjRatecfPrimaryKey(bidPrjRatecfDTO.getWkut(), bidPrjRatecfDTO.getPrjno(), bidPrjRatecfDTO.getSyr(), bidPrjRatecfDTO.getMonth(), bidPrjRatecfDTO.getChkunit(), bidPrjRatecfDTO.getMft());
        log.debug("REST request to partial update BidPrjRatecf partially : {}, {}", bidPrjRatecfPrimaryKey, bidPrjRatecfDTO);
        if (bidPrjRatecfDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjRatecfPrimaryKey, bidPrjRatecfDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjRatecfRepository.existsById(bidPrjRatecfPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjRatecfDTO> result = bidPrjRatecfService.partialUpdate(bidPrjRatecfDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjRatecfDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-ratecfs} : get all the bidPrjRatecfs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjRatecfs in body.
     */
    @GetMapping("/bid-prj-ratecfs")
    public List<BidPrjRatecfDTO> getAllBidPrjRatecfs() {
        log.debug("REST request to get all BidPrjRatecfs");
        return bidPrjRatecfService.findAll();
    }

    /**
     * {@code GET  /bid-prj-ratecfs/:id} : get the "id" bidPrjRatecf.
     *
     * @param bidPrjRatecfPrimaryKey the id of the bidPrjRatecfDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjRatecfDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-ratecfs/getOne")
    public ResponseEntity<BidPrjRatecfDTO> getBidPrjRatecf(@PathVariable BidPrjRatecfPrimaryKey bidPrjRatecfPrimaryKey) {
        log.debug("REST request to get BidPrjRatecf : {}", bidPrjRatecfPrimaryKey);
        Optional<BidPrjRatecfDTO> bidPrjRatecfDTO = bidPrjRatecfService.findOne(bidPrjRatecfPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjRatecfDTO);
    }

    /**
     * {@code DELETE  /bid-prj-ratecfs/:id} : delete the "id" bidPrjRatecf.
     *
     * @param bidPrjRatecfPrimaryKey the id of the bidPrjRatecfDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-ratecfs/delete")
    public ResponseEntity<Void> deleteBidPrjRatecf(@RequestBody BidPrjRatecfPrimaryKey bidPrjRatecfPrimaryKey) {
        log.debug("REST request to delete BidPrjRatecf : {}", bidPrjRatecfPrimaryKey);
        bidPrjRatecfService.delete(bidPrjRatecfPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjRatecfPrimaryKey.toString()))
            .build();
    }

    //    分頁用criteria
    @PostMapping("/bid-prj-ratecf/criteria-jpa")
    public Page<BidPrjRatecfDTO> getBidPrjRatecfByCriteria(@Valid @RequestBody BidPrjRatecfCriteria criteria) throws InterruptedException {
        log.debug("REST request to get BidPrjRatecfDTO : {}", criteria);
        return bidPrjRatecfService.findByCriteria(criteria);
    }


    //沒有分頁 查預定進度
    @PostMapping("/bid-prj-ratecf-find-by-pk")
    public BidPrjRatecfDTO getPtrate(@RequestBody BidPrjRatecfPrimaryKey bidPrjRatecfPrimaryKey) {
        log.debug("REST request to getAll BidPrjEnvi : {}", bidPrjRatecfPrimaryKey);
        return bidPrjRatecfService.findByPk(bidPrjRatecfPrimaryKey.getWkut(),bidPrjRatecfPrimaryKey.getPrjno());
    }

    @PostMapping("/bid-prj-ratecf-find-org-id/{userId}")
    public List<AdmAgentDTO>  findOrgId(@PathVariable String userId) {
        log.debug("REST request to getAll userId : {}", userId);
        List<AdmAgentDTO>  aa =bidPrjRatecfService.findOrgId(userId);
        return aa;
    }

}
