package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.BidUnit10Repository;
import gov.pcc.pwc.service.BidUnit10Service;
import gov.pcc.pwc.service.criteria.BidUnit10Criteria;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import gov.pcc.pwc.service.dto.BidUnit10NameAndCodeDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidUnit10}.
 */
@RestController
@RequestMapping("/api")
public class BidUnit10Resource {

    private final Logger log = LoggerFactory.getLogger(BidUnit10Resource.class);

    private static final String ENTITY_NAME = "bidUnit10";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidUnit10Service bidUnit10Service;

    private final BidUnit10Repository bidUnit10Repository;

    public BidUnit10Resource(BidUnit10Service bidUnit10Service, BidUnit10Repository bidUnit10Repository) {
        this.bidUnit10Service = bidUnit10Service;
        this.bidUnit10Repository = bidUnit10Repository;
    }

    /**
     * {@code POST  /bid-unit-10} : Create a new bidUnit10.
     *
     * @param bidUnit10DTO the bidUnit10DTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidUnit10DTO, or with status {@code 400 (Bad Request)} if the bidUnit10 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-unit-10")
    public ResponseEntity<BidUnit10DTO> createBidUnit10(@Valid @RequestBody BidUnit10DTO bidUnit10DTO) throws URISyntaxException {
        log.debug("REST request to save BidUnit10 : {}", bidUnit10DTO);
//        if (bidUnit10DTO.getCode() != null) {
//            throw new BadRequestAlertException("A new bidUnit10 cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        BidUnit10DTO result = bidUnit10Service.save(bidUnit10DTO);
        return ResponseEntity
            .created(new URI("/api/bid-unit-10/" + result.getCode()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getCode()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-unit-10/:code} : Updates an existing bidUnit10.
     *
     * @param code the id of the bidUnit10DTO to save.
     * @param bidUnit10DTO the bidUnit10DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidUnit10DTO,
     * or with status {@code 400 (Bad Request)} if the bidUnit10DTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidUnit10DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-unit-10/{code}")
    public ResponseEntity<BidUnit10DTO> updateBidUnit10(
        @PathVariable(value = "code", required = false) final String code,
        @Valid @RequestBody BidUnit10DTO bidUnit10DTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidUnit10 : {}, {}", code, bidUnit10DTO);
        if (bidUnit10DTO.getCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(code, bidUnit10DTO.getCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidUnit10Repository.existsById(code)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidUnit10DTO result = bidUnit10Service.save(bidUnit10DTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidUnit10DTO.getCode()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-unit-10/:code} : Partial updates given fields of an existing bidUnit10, field will ignore if it is null
     *
     * @param code the id of the bidUnit10DTO to save.
     * @param bidUnit10DTO the bidUnit10DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidUnit10DTO,
     * or with status {@code 400 (Bad Request)} if the bidUnit10DTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidUnit10DTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidUnit10DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-unit-10/{code}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidUnit10DTO> partialUpdateBidUnit10(
        @PathVariable(value = "code", required = false) final String code,
        @NotNull @RequestBody BidUnit10DTO bidUnit10DTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidUnit10 partially : {}, {}", code, bidUnit10DTO);
        if (bidUnit10DTO.getCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(code, bidUnit10DTO.getCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidUnit10Repository.existsById(code)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidUnit10DTO> result = bidUnit10Service.partialUpdate(bidUnit10DTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidUnit10DTO.getCode())
        );
    }

    /**
     * {@code GET  /bid-unit-10} : get all the bidUnit10s.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidUnit10s in body.
     */
    @GetMapping("/bid-unit-10")
    public List<BidUnit10DTO> getAllBidUnit10s() {
        log.debug("REST request to get all BidUnit10s");
        return bidUnit10Service.findAll();
    }

    /**
     * {@code GET  /bid-unit-10/:id} : get the "id" bidUnit10.
     *
     * @param id the id of the bidUnit10DTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidUnit10DTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-unit-10/{id}")
    public ResponseEntity<BidUnit10DTO> getBidUnit10(@PathVariable String id) {
        log.debug("REST request to get BidUnit10 : {}", id);
        Optional<BidUnit10DTO> bidUnit10DTO = bidUnit10Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidUnit10DTO);
    }

    /**
     * @param keyWord 透過名稱 key word 去找unit10裡面符合的
     * @return list DTO
     */
    @PostMapping("/bid-unit-10/name-and-code/keyWord")
    public List<BidUnit10NameAndCodeDTO> findNameAndCodeByKeyWord(@RequestBody Map<String, String> keyWord) {
        log.debug("REST request to get BidUnit10 name and code : {}", keyWord.get("keyWord"));
        List<BidUnit10NameAndCodeDTO> res = bidUnit10Service.findNameAndCodeByKeyWord(keyWord.get("keyWord"));
        //        log.debug("check response : {}", res.get(0).getCode());
        return res;
    }

    @GetMapping("/bid-unit-10/name-and-code/All")
    public List<BidUnit10NameAndCodeDTO> findAllWithNameAndCode() {
        log.debug("REST request to get all BidUnit10 with name and code");
        return bidUnit10Service.findNameAndCodeByKeyWord("13");
    }

    /**
     * {@code DELETE  /bid-unit-10/:id} : delete the "id" bidUnit10.
     *
     * @param id the id of the bidUnit10DTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-unit-10/{id}")
    public ResponseEntity<Void> deleteBidUnit10(@PathVariable String id) {
        log.debug("REST request to delete BidUnit10 : {}", id);
        bidUnit10Service.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code POST  /bid-unit-10/getResponsName} : get name of BID_PRJ_SAFE.respons
     *
     *
     * */
    @GetMapping("/bid-unit-10/getResponsName/{code}")
    public List<BidUnit10DTO> getResponsName(@PathVariable(value = "code", required = false) final String code)
        throws InterruptedException {
        log.debug("REST request to delete BidUnit10 : {}", code);
        return bidUnit10Service.findResponsName(code);
    }

    @GetMapping("/bid-unit-10-name/{code}")
    public BidUnit10DTO getName(@PathVariable String code) {
        //        log.debug("REST request to get BidUnit10, docType : {}, code : {}", code);
        Optional<BidUnit10DTO> bidUnit10DTO = bidUnit10Service.findByCode(code);
        return bidUnit10DTO.orElse(null);
    }

    /**
     * {@code POST  /cms-comps/criteria-jpa} : get bidUnit10 by criteria.
     *
     * @param criteria the criteria of the bidUnit10DTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidUnit10DTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-unit-10/criteria-jpa")
    public Page<BidUnit10DTO> getBidUnit10ByCriteria(@Valid @RequestBody BidUnit10Criteria criteria) throws InterruptedException {
        log.debug("REST request to get BidUnit10 : {}", criteria);
        return bidUnit10Service.findByCriteria(criteria);
    }

    @PostMapping("/bid-unit-10/getPages")
    public Page<BidUnit10DTO> getPages(@RequestBody BidUnit10Criteria criteria) {
        log.debug("REST request to get BidUnit10 : {}", criteria);
        return bidUnit10Service.findAllByCriteria(criteria);
    }
}
