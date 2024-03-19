package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmUnit;
import gov.pcc.pwc.repository.AdmUnitRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.AdmUnitService;
import gov.pcc.pwc.service.dto.AdmUnitDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link AdmUnit}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmUnitResource {

    private final Logger log = LoggerFactory.getLogger(AdmUnitResource.class);

    private static final String ENTITY_NAME = "admUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmUnitRepository admUnitRepository;

    private final AdmUnitService admUnitService;

    public AdmUnitResource(AdmUnitRepository admUnitRepository, AdmUnitService admUnitService) {
        this.admUnitRepository = admUnitRepository;
        this.admUnitService = admUnitService;
    }

    /**
     * {@code POST  /adm-units} : Create a new admUnit.
     *
     * @param admUnit the admUnit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admUnit, or with status {@code 400 (Bad Request)} if the admUnit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-units")
    public ResponseEntity<AdmUnit> createAdmUnit(@Valid @RequestBody AdmUnit admUnit) throws URISyntaxException {
        log.debug("REST request to save AdmUnit : {}", admUnit);
        if (admUnit.getUnitId() != null) {
            throw new BadRequestAlertException("A new admUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmUnit result = admUnitRepository.save(admUnit);
        return ResponseEntity
            .created(new URI("/api/adm-units/" + result.getUnitId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUnitId()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-units/:unitId} : Updates an existing admUnit.
     *
     * @param unitId the id of the admUnit to save.
     * @param admUnit the admUnit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admUnit,
     * or with status {@code 400 (Bad Request)} if the admUnit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admUnit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-units/{unitId}")
    public ResponseEntity<AdmUnit> updateAdmUnit(
        @PathVariable(value = "unitId", required = false) final String unitId,
        @Valid @RequestBody AdmUnit admUnit
    ) throws URISyntaxException {
        log.debug("REST request to update AdmUnit : {}, {}", unitId, admUnit);
        if (admUnit.getUnitId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(unitId, admUnit.getUnitId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admUnitRepository.existsById(unitId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmUnit result = admUnitRepository.save(admUnit);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admUnit.getUnitId()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-units/:unitId} : Partial updates given fields of an existing admUnit, field will ignore if it is null
     *
     * @param unitId the id of the admUnit to save.
     * @param admUnit the admUnit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admUnit,
     * or with status {@code 400 (Bad Request)} if the admUnit is not valid,
     * or with status {@code 404 (Not Found)} if the admUnit is not found,
     * or with status {@code 500 (Internal Server Error)} if the admUnit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-units/{unitId}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmUnit> partialUpdateAdmUnit(
        @PathVariable(value = "unitId", required = false) final String unitId,
        @NotNull @RequestBody AdmUnit admUnit
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmUnit partially : {}, {}", unitId, admUnit);
        if (admUnit.getUnitId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(unitId, admUnit.getUnitId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admUnitRepository.existsById(unitId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmUnit> result = admUnitRepository
            .findById(admUnit.getUnitId())
            .map(
                existingAdmUnit -> {
                    if (admUnit.getMasterUnitId() != null) {
                        existingAdmUnit.setMasterUnitId(admUnit.getMasterUnitId());
                    }
                    if (admUnit.getAdminOrgId() != null) {
                        existingAdmUnit.setAdminOrgId(admUnit.getAdminOrgId());
                    }
                    if (admUnit.getUnitName() != null) {
                        existingAdmUnit.setUnitName(admUnit.getUnitName());
                    }
                    if (admUnit.getAddr() != null) {
                        existingAdmUnit.setAddr(admUnit.getAddr());
                    }
                    if (admUnit.getTel() != null) {
                        existingAdmUnit.setTel(admUnit.getTel());
                    }
                    if (admUnit.getFax() != null) {
                        existingAdmUnit.setFax(admUnit.getFax());
                    }
                    if (admUnit.getDescript() != null) {
                        existingAdmUnit.setDescript(admUnit.getDescript());
                    }
                    if (admUnit.getUpdateUser() != null) {
                        existingAdmUnit.setUpdateUser(admUnit.getUpdateUser());
                    }
                    if (admUnit.getUpdateTime() != null) {
                        existingAdmUnit.setUpdateTime(admUnit.getUpdateTime());
                    }

                    return existingAdmUnit;
                }
            )
            .map(admUnitRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admUnit.getUnitId())
        );
    }

    /**
     * {@code GET  /adm-units} : get all the admUnits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admUnits in body.
     */
    @GetMapping("/adm-units")
    public List<AdmUnit> getAllAdmUnits() {
        log.debug("REST request to get all AdmUnits");
        return admUnitRepository.findAll();
    }

    /**
     * {@code GET  /adm-units/get-unit-opts} : get all admUnits for front-end unitOpts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admUnitDTOs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-units/get-unit-opts")
    public List<AdmUnitDTO> getUnitOpts() {
        log.debug("REST request to get all admUnitDTO for front-end unitOpts");
        return admUnitService.findAllUnitOpts();
    }

    @GetMapping("/service/adm-units/get-unit-opts")
    public List<AdmUnitDTO> getServiceUnitOpts() {
        log.debug("REST request to get all admUnitDTO for front-end unitOpts");
        return admUnitService.findAllUnitOpts();
    }

    /**
     * {@code GET  /adm-units/:id} : get the "id" admUnit.
     *
     * @param id the id of the admUnit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admUnit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-units/{id}")
    public ResponseEntity<AdmUnit> getAdmUnit(@PathVariable String id) {
        log.debug("REST request to get AdmUnit : {}", id);
        Optional<AdmUnit> admUnit = admUnitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admUnit);
    }

    /**
     * {@code DELETE  /adm-units/:id} : delete the "id" admUnit.
     *
     * @param id the id of the admUnit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-units/{id}")
    public ResponseEntity<Void> deleteAdmUnit(@PathVariable String id) {
        log.debug("REST request to delete AdmUnit : {}", id);
        admUnitRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
