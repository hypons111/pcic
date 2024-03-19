package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidCcind;
import gov.pcc.pwc.domain.BidCcindPrimaryKey;
import gov.pcc.pwc.repository.BidCcindRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.BidCcindService;
import gov.pcc.pwc.service.dto.BidCcindDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidCcind}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BidCcindResource {

    private final Logger log = LoggerFactory.getLogger(BidCcindResource.class);

    private static final String ENTITY_NAME = "bidCcind";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidCcindRepository bidCcindRepository;

    private final BidCcindService bidCcindService;

    public BidCcindResource(BidCcindRepository bidCcindRepository, BidCcindService bidCcindService) {
        this.bidCcindRepository = bidCcindRepository;
        this.bidCcindService = bidCcindService;
    }

    /**
     * {@code POST  /bid-ccinds} : Create a new bidCcind.
     *
     * @param bidCcind the bidCcind to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidCcind, or with status {@code 400 (Bad Request)} if the bidCcind has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-ccinds")
    public ResponseEntity<BidCcind> createBidCcind(@Valid @RequestBody BidCcind bidCcind) throws URISyntaxException {
        log.debug("REST request to save BidCcind : {}", bidCcind);
        if (bidCcind.getId() != null) {
            throw new BadRequestAlertException("A new bidCcind cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidCcind result = bidCcindRepository.save(bidCcind);
        return ResponseEntity
            .created(new URI("/api/bid-ccinds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-ccinds/:id} : Updates an existing bidCcind.
     *
     * @param bidCcind the bidCcind to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidCcind,
     * or with status {@code 400 (Bad Request)} if the bidCcind is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidCcind couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-ccinds/{id}")
    public ResponseEntity<BidCcind> updateBidCcind(
        @Valid @RequestBody BidCcind bidCcind
    ) throws URISyntaxException {
        BidCcindPrimaryKey bidCcindPK = new BidCcindPrimaryKey(bidCcind.getYear(), bidCcind.getMonth());
        log.debug("REST request to update BidCcind : {}", bidCcind);
        if (bidCcind.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidCcindPK, bidCcind.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidCcindRepository.existsById(bidCcindPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidCcind result = bidCcindRepository.save(bidCcind);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidCcind.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-ccinds/:id} : Partial updates given fields of an existing bidCcind, field will ignore if it is null
     *
     * @param bidCcind the bidCcind to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidCcind,
     * or with status {@code 400 (Bad Request)} if the bidCcind is not valid,
     * or with status {@code 404 (Not Found)} if the bidCcind is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidCcind couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-ccinds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidCcind> partialUpdateBidCcind(
        @NotNull @RequestBody BidCcind bidCcind
    ) throws URISyntaxException {
        BidCcindPrimaryKey bidCcindPK = new BidCcindPrimaryKey(bidCcind.getYear(), bidCcind.getMonth());
        log.debug("REST request to partial update BidCcind partially : {}", bidCcind);
        if (bidCcind.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidCcindPK, bidCcind.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidCcindRepository.existsById(bidCcindPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidCcind> result = bidCcindRepository
            .findById(bidCcindPK)
            .map(existingBidCcind -> {
                if (bidCcind.getYear() != null) {
                    existingBidCcind.setYear(bidCcind.getYear());
                }
                if (bidCcind.getMonth() != null) {
                    existingBidCcind.setMonth(bidCcind.getMonth());
                }
                if (bidCcind.getInd1() != null) {
                    existingBidCcind.setInd1(bidCcind.getInd1());
                }
                if (bidCcind.getInd2() != null) {
                    existingBidCcind.setInd2(bidCcind.getInd2());
                }
                if (bidCcind.getInd3() != null) {
                    existingBidCcind.setInd3(bidCcind.getInd3());
                }
                if (bidCcind.getCreateDate() != null) {
                    existingBidCcind.setCreateDate(bidCcind.getCreateDate());
                }
                if (bidCcind.getCreateUser() != null) {
                    existingBidCcind.setCreateUser(bidCcind.getCreateUser());
                }
                if (bidCcind.getUpdateDate() != null) {
                    existingBidCcind.setUpdateDate(bidCcind.getUpdateDate());
                }
                if (bidCcind.getUpdateUser() != null) {
                    existingBidCcind.setUpdateUser(bidCcind.getUpdateUser());
                }

                return existingBidCcind;
            })
            .map(bidCcindRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidCcind.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-ccinds} : get all the bidCcinds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidCcinds in body.
     */
    @GetMapping("/bid-ccinds")
    public List<BidCcind> getAllBidCcinds() {
        log.debug("REST request to get all BidCcinds");
        return bidCcindRepository.findAll();
    }

    /**
     * {@code GET  /bid-ccinds/getOne} : get the "id" bidCcind.
     *
     * @param bidCcindPrimaryKey the id of the bidCcind to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidCcind, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-ccinds/getOne")
    public ResponseEntity<BidCcindDTO> getBidCcind(@RequestBody BidCcindPrimaryKey bidCcindPrimaryKey) {
        log.debug("REST request to get BidCcind : {}", bidCcindPrimaryKey);
        Optional<BidCcindDTO> bidCcindDTO = bidCcindService.findOne(bidCcindPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidCcindDTO);
    }

    /**
     * {@code DELETE  /bid-ccinds/:id} : delete the "id" bidCcind.
     *
     * @param bidCcindPrimaryKey the id of the bidCcind to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-ccinds/{id}")
    public ResponseEntity<Void> deleteBidCcind(@PathVariable BidCcindPrimaryKey bidCcindPrimaryKey) {
        log.debug("REST request to delete BidCcind : {}", bidCcindPrimaryKey);
        bidCcindRepository.deleteById(bidCcindPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidCcindPrimaryKey.toString()))
            .build();
    }
}
