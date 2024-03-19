package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmLoginHis;
import gov.pcc.pwc.repository.AdmLoginHisRepository;
import gov.pcc.pwc.service.AdmLoginHisService;
import gov.pcc.pwc.service.criteria.AdmLoginHisCriteria;
import gov.pcc.pwc.service.dto.AdmLoginHisDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link AdmLoginHis}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmLoginHisResource {

    private final Logger log = LoggerFactory.getLogger(AdmLoginHisResource.class);

    private static final String ENTITY_NAME = "admLoginHis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmLoginHisService admLoginHisService;

    private final AdmLoginHisRepository admLoginHisRepository;

    public AdmLoginHisResource(AdmLoginHisService admLoginHisService, AdmLoginHisRepository admLoginHisRepository) {
        this.admLoginHisService = admLoginHisService;
        this.admLoginHisRepository = admLoginHisRepository;
    }

    /**
     * {@code POST  /adm-login-his} : Create a new admLoginHis.
     *
     * @param admLoginHis the admLoginHis to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admLoginHis, or with status {@code 400 (Bad Request)} if the admLoginHis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-login-his")
    public ResponseEntity<AdmLoginHis> createAdmLoginHis(@Valid @RequestBody AdmLoginHis admLoginHis) throws URISyntaxException {
        log.debug("REST request to save AdmLoginHis : {}", admLoginHis);
        if (admLoginHis.getId() != null) {
            throw new BadRequestAlertException("A new admLoginHis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmLoginHis result = admLoginHisRepository.save(admLoginHis);
        return ResponseEntity
            .created(new URI("/api/adm-login-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-login-his/:id} : Updates an existing admLoginHis.
     *
     * @param id the id of the admLoginHis to save.
     * @param admLoginHis the admLoginHis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admLoginHis,
     * or with status {@code 400 (Bad Request)} if the admLoginHis is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admLoginHis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-login-his/{id}")
    public ResponseEntity<AdmLoginHis> updateAdmLoginHis(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmLoginHis admLoginHis
    ) throws URISyntaxException {
        log.debug("REST request to update AdmLoginHis : {}, {}", id, admLoginHis);
        if (admLoginHis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admLoginHis.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admLoginHisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmLoginHis result = admLoginHisRepository.save(admLoginHis);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admLoginHis.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-login-his/:id} : Partial updates given fields of an existing admLoginHis, field will ignore if it is null
     *
     * @param id the id of the admLoginHis to save.
     * @param admLoginHis the admLoginHis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admLoginHis,
     * or with status {@code 400 (Bad Request)} if the admLoginHis is not valid,
     * or with status {@code 404 (Not Found)} if the admLoginHis is not found,
     * or with status {@code 500 (Internal Server Error)} if the admLoginHis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-login-his/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmLoginHis> partialUpdateAdmLoginHis(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmLoginHis admLoginHis
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmLoginHis partially : {}, {}", id, admLoginHis);
        if (admLoginHis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admLoginHis.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admLoginHisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmLoginHis> result = admLoginHisRepository
            .findById(admLoginHis.getId())
            .map(
                existingAdmLoginHis -> {
                    if (admLoginHis.getSysCategory() != null) {
                        existingAdmLoginHis.setSysCategory(admLoginHis.getSysCategory());
                    }
                    if (admLoginHis.getResult() != null) {
                        existingAdmLoginHis.setResult(admLoginHis.getResult());
                    }
                    if (admLoginHis.getDescript() != null) {
                        existingAdmLoginHis.setDescript(admLoginHis.getDescript());
                    }
                    if (admLoginHis.getSourceIp() != null) {
                        existingAdmLoginHis.setSourceIp(admLoginHis.getSourceIp());
                    }
                    if (admLoginHis.getBrowser() != null) {
                        existingAdmLoginHis.setBrowser(admLoginHis.getBrowser());
                    }
                    if (admLoginHis.getCreateTime() != null) {
                        existingAdmLoginHis.setCreateTime(admLoginHis.getCreateTime());
                    }
                    if (admLoginHis.getCreateUserSys() != null) {
                        existingAdmLoginHis.setCreateUserSys(admLoginHis.getCreateUserSys());
                    }

                    return existingAdmLoginHis;
                }
            )
            .map(admLoginHisRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admLoginHis.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-login-his} : get all the admLoginHis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admLoginHis in body.
     */
    @GetMapping("/adm-login-his")
    public List<AdmLoginHis> getAllAdmLoginHis() {
        log.debug("REST request to get all AdmLoginHis");
        return admLoginHisRepository.findAll();
    }

    /**
     * {@code GET  /adm-login-his/:id} : get the "id" admLoginHis.
     *
     * @param id the id of the admLoginHis to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admLoginHis, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-login-his/{id}")
    public AdmLoginHisDTO getAdmLoginHis(@PathVariable Long id) {
        log.debug("REST request to get AdmLoginHisDTO : {}", id);
        return admLoginHisService.findByPk(id);
    }

    /**
     * {@code DELETE  /adm-login-his/:id} : delete the "id" admLoginHis.
     *
     * @param id the id of the admLoginHis to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-login-his/{id}")
    public ResponseEntity<Void> deleteAdmLoginHis(@PathVariable Long id) {
        log.debug("REST request to delete AdmLoginHis : {}", id);
        admLoginHisRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /adm-login-his/criteria-jpa} : get admLoginHis by criteria.
     *
     * @param criteria the criteria of the AdmLoginHisCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmLoginHisDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-login-his/criteria-jpa")
    public Page<AdmLoginHisDTO> getAdmLoginHisByCriteria(@Valid @RequestBody AdmLoginHisCriteria criteria) {
        log.debug("REST request to get AdmLoginHisDTO : {}", criteria);
        return admLoginHisService.findByCriteria(criteria);
    }
}
