package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.EngEngrLicenseRepository;
import gov.pcc.pwc.service.EngEngrLicenseService;
import gov.pcc.pwc.service.criteria.EngEngrLicenseCriteria;
import gov.pcc.pwc.service.criteria.EngMemberCriteria;
import gov.pcc.pwc.service.dto.EngEngrLicenseDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.EngMemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngEngrLicense}.
 */
@RestController
@RequestMapping("/api")
public class EngEngrLicenseResource {

    private final Logger log = LoggerFactory.getLogger(EngEngrLicenseResource.class);

    private static final String ENTITY_NAME = "engEngrLicense";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngEngrLicenseService engEngrLicenseService;

    private final EngEngrLicenseRepository engEngrLicenseRepository;

    public EngEngrLicenseResource(EngEngrLicenseService engEngrLicenseService, EngEngrLicenseRepository engEngrLicenseRepository) {
        this.engEngrLicenseService = engEngrLicenseService;
        this.engEngrLicenseRepository = engEngrLicenseRepository;
    }

    /**
     * {@code POST  /eng-engr-licenses} : Create a new engEngrLicense.
     *
     * @param engEngrLicenseDTO the engEngrLicenseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engEngrLicenseDTO, or with status {@code 400 (Bad Request)} if the engEngrLicense has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-engr-licenses")
    public ResponseEntity<EngEngrLicenseDTO> createEngEngrLicense(@Valid @RequestBody EngEngrLicenseDTO engEngrLicenseDTO)
        throws URISyntaxException {
        log.debug("REST request to save EngEngrLicense : {}", engEngrLicenseDTO);
        if (engEngrLicenseDTO.getEngEngrLicenseId() != null) {
            throw new BadRequestAlertException("A new engEngrLicense cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngEngrLicenseDTO result = engEngrLicenseService.save(engEngrLicenseDTO);
        return ResponseEntity
            .created(new URI("/api/eng-engr-licenses/" + result.getEngEngrLicenseId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngEngrLicenseId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-engr-licenses/:engEngrLicenseId} : Updates an existing engEngrLicense.
     *
     * @param engEngrLicenseId the id of the engEngrLicenseDTO to save.
     * @param engEngrLicenseDTO the engEngrLicenseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEngrLicenseDTO,
     * or with status {@code 400 (Bad Request)} if the engEngrLicenseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engEngrLicenseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-engr-licenses/{engEngrLicenseId}")
    public ResponseEntity<EngEngrLicenseDTO> updateEngEngrLicense(
        @PathVariable(value = "engEngrLicenseId", required = false) final String engEngrLicenseId,
        @Valid @RequestBody EngEngrLicenseDTO engEngrLicenseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EngEngrLicense : {}, {}", engEngrLicenseId, engEngrLicenseDTO);
        if (engEngrLicenseDTO.getEngEngrLicenseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEngrLicenseId, engEngrLicenseDTO.getEngEngrLicenseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEngrLicenseRepository.existsById(engEngrLicenseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngEngrLicenseDTO result = engEngrLicenseService.save(engEngrLicenseDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEngrLicenseDTO.getEngEngrLicenseId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-engr-licenses/:engEngrLicenseId} : Partial updates given fields of an existing engEngrLicense, field will ignore if it is null
     *
     * @param engEngrLicenseId the id of the engEngrLicenseDTO to save.
     * @param engEngrLicenseDTO the engEngrLicenseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEngrLicenseDTO,
     * or with status {@code 400 (Bad Request)} if the engEngrLicenseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engEngrLicenseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engEngrLicenseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-engr-licenses/{engEngrLicenseId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngEngrLicenseDTO> partialUpdateEngEngrLicense(
        @PathVariable(value = "engEngrLicenseId", required = false) final String engEngrLicenseId,
        @NotNull @RequestBody EngEngrLicenseDTO engEngrLicenseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngEngrLicense partially : {}, {}", engEngrLicenseId, engEngrLicenseDTO);
        if (engEngrLicenseDTO.getEngEngrLicenseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEngrLicenseId, engEngrLicenseDTO.getEngEngrLicenseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEngrLicenseRepository.existsById(engEngrLicenseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngEngrLicenseDTO> result = engEngrLicenseService.partialUpdate(engEngrLicenseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEngrLicenseDTO.getEngEngrLicenseId())
        );
    }

    /**
     * {@code GET  /eng-engr-licenses} : get all the engEngrLicenses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engEngrLicenses in body.
     */
    @GetMapping("/eng-engr-licenses")
    public List<EngEngrLicenseDTO> getAllEngEngrLicenses() {
        log.debug("REST request to get all EngEngrLicenses");
        return engEngrLicenseService.findAll();
    }

    /**
     * {@code GET  /eng-engr-licenses/:id} : get the "id" engEngrLicense.
     *
     * @param id the id of the engEngrLicenseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engEngrLicenseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-engr-licenses/{id}")
    public ResponseEntity<EngEngrLicenseDTO> getEngEngrLicense(@PathVariable String id) {
        log.debug("REST request to get EngEngrLicense : {}", id);
        Optional<EngEngrLicenseDTO> engEngrLicenseDTO = engEngrLicenseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engEngrLicenseDTO);
    }

    /**
     * {@code DELETE  /eng-engr-licenses/:id} : delete the "id" engEngrLicense.
     *
     * @param id the id of the engEngrLicenseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-engr-licenses/{id}")
    public ResponseEntity<Void> deleteEngEngrLicense(@PathVariable String id) {
        log.debug("REST request to delete EngEngrLicense : {}", id);
        engEngrLicenseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @PostMapping("/eng-engr-licenses/criteria-jpa")
    public List<EngEngrLicenseDTO> getEngEngrLicnensedByCriteriaImpl(@RequestBody EngEngrLicenseCriteria criteria){
        log.debug("REST request to get EngEngrLicense : {}", criteria);
        return engEngrLicenseService.findByCriteriaImpl(criteria);
    }

    @PostMapping("/eng-engr-licenses/criteria-jpareal")
    public Page<EngEngrLicenseDTO> getEngEngrLicnensedByCriteria(@Valid @RequestBody EngEngrLicenseCriteria criteria){
        log.debug("REST request to get EngEngrLicense : {}", criteria);
        return engEngrLicenseService.findByCriteriaImpl2(criteria);
    }

//    @PostMapping("/eng-engr-licenses/criteria-jpareal")
//    public Page<EngEngrLicenseDTO> getEngMemberByCriteria(@Valid @RequestBody EngEngrLicenseCriteria criteria) throws InterruptedException {
//        log.debug("REST request to get engMember : {}", criteria);
//        return engEngrLicenseService.findByCriteria(criteria);
//    }
}
