package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmSmsSend;
import gov.pcc.pwc.repository.AdmSmsSendRepository;
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
 * REST controller for managing {@link AdmSmsSend}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmSmsSendResource {

    private final Logger log = LoggerFactory.getLogger(AdmSmsSendResource.class);

    private static final String ENTITY_NAME = "admSmsSend";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmSmsSendRepository admSmsSendRepository;

    public AdmSmsSendResource(AdmSmsSendRepository admSmsSendRepository) {
        this.admSmsSendRepository = admSmsSendRepository;
    }

    /**
     * {@code POST  /adm-sms-sends} : Create a new admSmsSend.
     *
     * @param admSmsSend the admSmsSend to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admSmsSend, or with status {@code 400 (Bad Request)} if the admSmsSend has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-sms-sends")
    public ResponseEntity<AdmSmsSend> createAdmSmsSend(@Valid @RequestBody AdmSmsSend admSmsSend) throws URISyntaxException {
        log.debug("REST request to save AdmSmsSend : {}", admSmsSend);
        if (admSmsSend.getId() != null) {
            throw new BadRequestAlertException("A new admSmsSend cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmSmsSend result = admSmsSendRepository.save(admSmsSend);
        return ResponseEntity
            .created(new URI("/api/adm-sms-sends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-sms-sends/:id} : Updates an existing admSmsSend.
     *
     * @param id the id of the admSmsSend to save.
     * @param admSmsSend the admSmsSend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admSmsSend,
     * or with status {@code 400 (Bad Request)} if the admSmsSend is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admSmsSend couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-sms-sends/{id}")
    public ResponseEntity<AdmSmsSend> updateAdmSmsSend(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmSmsSend admSmsSend
    ) throws URISyntaxException {
        log.debug("REST request to update AdmSmsSend : {}, {}", id, admSmsSend);
        if (admSmsSend.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admSmsSend.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admSmsSendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmSmsSend result = admSmsSendRepository.save(admSmsSend);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admSmsSend.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-sms-sends/:id} : Partial updates given fields of an existing admSmsSend, field will ignore if it is null
     *
     * @param id the id of the admSmsSend to save.
     * @param admSmsSend the admSmsSend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admSmsSend,
     * or with status {@code 400 (Bad Request)} if the admSmsSend is not valid,
     * or with status {@code 404 (Not Found)} if the admSmsSend is not found,
     * or with status {@code 500 (Internal Server Error)} if the admSmsSend couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-sms-sends/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmSmsSend> partialUpdateAdmSmsSend(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmSmsSend admSmsSend
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmSmsSend partially : {}, {}", id, admSmsSend);
        if (admSmsSend.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admSmsSend.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admSmsSendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmSmsSend> result = admSmsSendRepository
            .findById(admSmsSend.getId())
            .map(
                existingAdmSmsSend -> {
                    if (admSmsSend.getSmsType() != null) {
                        existingAdmSmsSend.setSmsType(admSmsSend.getSmsType());
                    }
                    if (admSmsSend.getSourceId() != null) {
                        existingAdmSmsSend.setSourceId(admSmsSend.getSourceId());
                    }
                    if (admSmsSend.getSender() != null) {
                        existingAdmSmsSend.setSender(admSmsSend.getSender());
                    }
                    if (admSmsSend.getReceiver() != null) {
                        existingAdmSmsSend.setReceiver(admSmsSend.getReceiver());
                    }
                    if (admSmsSend.getSupplier() != null) {
                        existingAdmSmsSend.setSupplier(admSmsSend.getSupplier());
                    }
                    if (admSmsSend.getMessage() != null) {
                        existingAdmSmsSend.setMessage(admSmsSend.getMessage());
                    }
                    if (admSmsSend.getExpectSendTime() != null) {
                        existingAdmSmsSend.setExpectSendTime(admSmsSend.getExpectSendTime());
                    }
                    if (admSmsSend.getRealSendTime() != null) {
                        existingAdmSmsSend.setRealSendTime(admSmsSend.getRealSendTime());
                    }
                    if (admSmsSend.getStatus() != null) {
                        existingAdmSmsSend.setStatus(admSmsSend.getStatus());
                    }
                    if (admSmsSend.getProcessMessage() != null) {
                        existingAdmSmsSend.setProcessMessage(admSmsSend.getProcessMessage());
                    }
                    if (admSmsSend.getCreateUser() != null) {
                        existingAdmSmsSend.setCreateUser(admSmsSend.getCreateUser());
                    }
                    if (admSmsSend.getCreateTime() != null) {
                        existingAdmSmsSend.setCreateTime(admSmsSend.getCreateTime());
                    }
                    if (admSmsSend.getUpdateUser() != null) {
                        existingAdmSmsSend.setUpdateUser(admSmsSend.getUpdateUser());
                    }
                    if (admSmsSend.getUpdateTime() != null) {
                        existingAdmSmsSend.setUpdateTime(admSmsSend.getUpdateTime());
                    }

                    return existingAdmSmsSend;
                }
            )
            .map(admSmsSendRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admSmsSend.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-sms-sends} : get all the admSmsSends.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admSmsSends in body.
     */
    @GetMapping("/adm-sms-sends")
    public List<AdmSmsSend> getAllAdmSmsSends() {
        log.debug("REST request to get all AdmSmsSends");
        return admSmsSendRepository.findAll();
    }

    /**
     * {@code GET  /adm-sms-sends/:id} : get the "id" admSmsSend.
     *
     * @param id the id of the admSmsSend to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admSmsSend, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-sms-sends/{id}")
    public ResponseEntity<AdmSmsSend> getAdmSmsSend(@PathVariable Long id) {
        log.debug("REST request to get AdmSmsSend : {}", id);
        Optional<AdmSmsSend> admSmsSend = admSmsSendRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admSmsSend);
    }

    /**
     * {@code DELETE  /adm-sms-sends/:id} : delete the "id" admSmsSend.
     *
     * @param id the id of the admSmsSend to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-sms-sends/{id}")
    public ResponseEntity<Void> deleteAdmSmsSend(@PathVariable Long id) {
        log.debug("REST request to delete AdmSmsSend : {}", id);
        admSmsSendRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
