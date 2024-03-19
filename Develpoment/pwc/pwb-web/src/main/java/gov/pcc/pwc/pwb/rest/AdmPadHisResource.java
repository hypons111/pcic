package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmPadHis;
import gov.pcc.pwc.repository.AdmPadHisRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link AdmPadHis}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmPadHisResource {

    private final Logger log = LoggerFactory.getLogger(AdmPadHisResource.class);

    private static final String ENTITY_NAME = "admPadHis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmPadHisRepository admPadHisRepository;

    public AdmPadHisResource(AdmPadHisRepository admPadHisRepository) {
        this.admPadHisRepository = admPadHisRepository;
    }

    /**
     * {@code POST  /adm-pad-his} : Create a new admPadHis.
     *
     * @param admPadHis the admPadHis to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admPadHis, or with status {@code 400 (Bad Request)} if the admPadHis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-pad-his")
    public ResponseEntity<AdmPadHis> createAdmPadHis(@Valid @RequestBody AdmPadHis admPadHis) throws URISyntaxException {
        log.debug("REST request to save AdmPadHis : {}", admPadHis);
        if (admPadHis.getId() != null) {
            throw new BadRequestAlertException("A new admPadHis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmPadHis result = admPadHisRepository.save(admPadHis);
        return ResponseEntity
            .created(new URI("/api/adm-pad-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-pad-his/:id} : Updates an existing admPadHis.
     *
     * @param id the id of the admPadHis to save.
     * @param admPadHis the admPadHis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admPadHis,
     * or with status {@code 400 (Bad Request)} if the admPadHis is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admPadHis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-pad-his/{id}")
    public ResponseEntity<AdmPadHis> updateAdmPadHis(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmPadHis admPadHis
    ) throws URISyntaxException {
        log.debug("REST request to update AdmPadHis : {}, {}", id, admPadHis);
        if (admPadHis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admPadHis.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admPadHisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmPadHis result = admPadHisRepository.save(admPadHis);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admPadHis.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-pad-his/:id} : Partial updates given fields of an existing admPadHis, field will ignore if it is null
     *
     * @param id the id of the admPadHis to save.
     * @param admPadHis the admPadHis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admPadHis,
     * or with status {@code 400 (Bad Request)} if the admPadHis is not valid,
     * or with status {@code 404 (Not Found)} if the admPadHis is not found,
     * or with status {@code 500 (Internal Server Error)} if the admPadHis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-pad-his/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmPadHis> partialUpdateAdmPadHis(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmPadHis admPadHis
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmPadHis partially : {}, {}", id, admPadHis);
        if (admPadHis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admPadHis.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admPadHisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmPadHis> result = admPadHisRepository
            .findById(admPadHis.getId())
            .map(
                existingAdmPadHis -> {
                    if (admPadHis.getUserId() != null) {
                        existingAdmPadHis.setUserId(admPadHis.getUserId());
                    }
                    if (admPadHis.getUserPadHash() != null) {
                        existingAdmPadHis.setUserPadHash(admPadHis.getUserPadHash());
                    }
                    if (admPadHis.getCreateTime() != null) {
                        existingAdmPadHis.setCreateTime(admPadHis.getCreateTime());
                    }

                    return existingAdmPadHis;
                }
            )
            .map(admPadHisRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admPadHis.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-pad-his} : get all the admPadHis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admPadHis in body.
     */
    @GetMapping("/adm-pad-his")
    public List<AdmPadHis> getAllAdmPadHis() {
        log.debug("REST request to get all AdmPadHis");
        return admPadHisRepository.findAll();
    }

    /**
     * {@code GET  /adm-pad-his/:id} : get the "id" admPadHis.
     *
     * @param id the id of the admPadHis to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admPadHis, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-pad-his/{id}")
    public ResponseEntity<AdmPadHis> getAdmPadHis(@PathVariable Long id) {
        log.debug("REST request to get AdmPadHis : {}", id);
        Optional<AdmPadHis> admPadHis = admPadHisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admPadHis);
    }

    /**
     * {@code DELETE  /adm-pad-his/:id} : delete the "id" admPadHis.
     *
     * @param id the id of the admPadHis to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-pad-his/{id}")
    public ResponseEntity<Void> deleteAdmPadHis(@PathVariable Long id) {
        log.debug("REST request to delete AdmPadHis : {}", id);
        admPadHisRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
