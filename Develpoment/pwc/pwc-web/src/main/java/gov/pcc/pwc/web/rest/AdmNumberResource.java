package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmNumber;
import gov.pcc.pwc.repository.AdmNumberRepository;
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
 * REST controller for managing {@link AdmNumber}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmNumberResource {

    private final Logger log = LoggerFactory.getLogger(AdmNumberResource.class);

    private static final String ENTITY_NAME = "admNumber";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmNumberRepository admNumberRepository;

    public AdmNumberResource(AdmNumberRepository admNumberRepository) {
        this.admNumberRepository = admNumberRepository;
    }

    /**
     * {@code POST  /adm-numbers} : Create a new admNumber.
     *
     * @param admNumber the admNumber to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admNumber, or with status {@code 400 (Bad Request)} if the admNumber has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-numbers")
    public ResponseEntity<AdmNumber> createAdmNumber(@Valid @RequestBody AdmNumber admNumber) throws URISyntaxException {
        log.debug("REST request to save AdmNumber : {}", admNumber);
        if (admNumber.getId() != null) {
            throw new BadRequestAlertException("A new admNumber cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmNumber result = admNumberRepository.save(admNumber);
        return ResponseEntity
            .created(new URI("/api/adm-numbers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-numbers/:id} : Updates an existing admNumber.
     *
     * @param id the id of the admNumber to save.
     * @param admNumber the admNumber to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admNumber,
     * or with status {@code 400 (Bad Request)} if the admNumber is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admNumber couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-numbers/{id}")
    public ResponseEntity<AdmNumber> updateAdmNumber(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmNumber admNumber
    ) throws URISyntaxException {
        log.debug("REST request to update AdmNumber : {}, {}", id, admNumber);
        if (admNumber.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admNumber.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admNumberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmNumber result = admNumberRepository.save(admNumber);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admNumber.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-numbers/:id} : Partial updates given fields of an existing admNumber, field will ignore if it is null
     *
     * @param id the id of the admNumber to save.
     * @param admNumber the admNumber to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admNumber,
     * or with status {@code 400 (Bad Request)} if the admNumber is not valid,
     * or with status {@code 404 (Not Found)} if the admNumber is not found,
     * or with status {@code 500 (Internal Server Error)} if the admNumber couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-numbers/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmNumber> partialUpdateAdmNumber(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmNumber admNumber
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmNumber partially : {}, {}", id, admNumber);
        if (admNumber.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admNumber.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admNumberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmNumber> result = admNumberRepository
            .findById(admNumber.getId())
            .map(
                existingAdmNumber -> {
                    if (admNumber.getCodeType() != null) {
                        existingAdmNumber.setCodeType(admNumber.getCodeType());
                    }
                    if (admNumber.getCodeYear() != null) {
                        existingAdmNumber.setCodeYear(admNumber.getCodeYear());
                    }
                    if (admNumber.getCodeMonth() != null) {
                        existingAdmNumber.setCodeMonth(admNumber.getCodeMonth());
                    }
                    if (admNumber.getCodeDate() != null) {
                        existingAdmNumber.setCodeDate(admNumber.getCodeDate());
                    }
                    if (admNumber.getNumber() != null) {
                        existingAdmNumber.setNumber(admNumber.getNumber());
                    }
                    if (admNumber.getCreateUser() != null) {
                        existingAdmNumber.setCreateUser(admNumber.getCreateUser());
                    }
                    if (admNumber.getCreateTime() != null) {
                        existingAdmNumber.setCreateTime(admNumber.getCreateTime());
                    }
                    if (admNumber.getUpdateUser() != null) {
                        existingAdmNumber.setUpdateUser(admNumber.getUpdateUser());
                    }
                    if (admNumber.getUpdateTime() != null) {
                        existingAdmNumber.setUpdateTime(admNumber.getUpdateTime());
                    }

                    return existingAdmNumber;
                }
            )
            .map(admNumberRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admNumber.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-numbers} : get all the admNumbers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admNumbers in body.
     */
    @GetMapping("/adm-numbers")
    public List<AdmNumber> getAllAdmNumbers() {
        log.debug("REST request to get all AdmNumbers");
        return admNumberRepository.findAll();
    }

    /**
     * {@code GET  /adm-numbers/:id} : get the "id" admNumber.
     *
     * @param id the id of the admNumber to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admNumber, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-numbers/{id}")
    public ResponseEntity<AdmNumber> getAdmNumber(@PathVariable Long id) {
        log.debug("REST request to get AdmNumber : {}", id);
        Optional<AdmNumber> admNumber = admNumberRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admNumber);
    }

    /**
     * {@code DELETE  /adm-numbers/:id} : delete the "id" admNumber.
     *
     * @param id the id of the admNumber to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-numbers/{id}")
    public ResponseEntity<Void> deleteAdmNumber(@PathVariable Long id) {
        log.debug("REST request to delete AdmNumber : {}", id);
        admNumberRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
