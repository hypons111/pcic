package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmLineSend;
import gov.pcc.pwc.repository.AdmLineSendRepository;
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
 * REST controller for managing {@link AdmLineSend}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmLineSendResource {

    private final Logger log = LoggerFactory.getLogger(AdmLineSendResource.class);

    private static final String ENTITY_NAME = "admLineSend";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmLineSendRepository admLineSendRepository;

    public AdmLineSendResource(AdmLineSendRepository admLineSendRepository) {
        this.admLineSendRepository = admLineSendRepository;
    }

    /**
     * {@code POST  /adm-line-sends} : Create a new admLineSend.
     *
     * @param admLineSend the admLineSend to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admLineSend, or with status {@code 400 (Bad Request)} if the admLineSend has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-line-sends")
    public ResponseEntity<AdmLineSend> createAdmLineSend(@Valid @RequestBody AdmLineSend admLineSend) throws URISyntaxException {
        log.debug("REST request to save AdmLineSend : {}", admLineSend);
        if (admLineSend.getId() != null) {
            throw new BadRequestAlertException("A new admLineSend cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmLineSend result = admLineSendRepository.save(admLineSend);
        return ResponseEntity
            .created(new URI("/api/adm-line-sends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-line-sends/:id} : Updates an existing admLineSend.
     *
     * @param id the id of the admLineSend to save.
     * @param admLineSend the admLineSend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admLineSend,
     * or with status {@code 400 (Bad Request)} if the admLineSend is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admLineSend couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-line-sends/{id}")
    public ResponseEntity<AdmLineSend> updateAdmLineSend(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmLineSend admLineSend
    ) throws URISyntaxException {
        log.debug("REST request to update AdmLineSend : {}, {}", id, admLineSend);
        if (admLineSend.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admLineSend.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admLineSendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmLineSend result = admLineSendRepository.save(admLineSend);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admLineSend.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-line-sends/:id} : Partial updates given fields of an existing admLineSend, field will ignore if it is null
     *
     * @param id the id of the admLineSend to save.
     * @param admLineSend the admLineSend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admLineSend,
     * or with status {@code 400 (Bad Request)} if the admLineSend is not valid,
     * or with status {@code 404 (Not Found)} if the admLineSend is not found,
     * or with status {@code 500 (Internal Server Error)} if the admLineSend couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-line-sends/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmLineSend> partialUpdateAdmLineSend(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmLineSend admLineSend
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmLineSend partially : {}, {}", id, admLineSend);
        if (admLineSend.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admLineSend.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admLineSendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmLineSend> result = admLineSendRepository
            .findById(admLineSend.getId())
            .map(
                existingAdmLineSend -> {
                    if (admLineSend.getLineType() != null) {
                        existingAdmLineSend.setLineType(admLineSend.getLineType());
                    }
                    if (admLineSend.getSourceId() != null) {
                        existingAdmLineSend.setSourceId(admLineSend.getSourceId());
                    }
                    if (admLineSend.getSender() != null) {
                        existingAdmLineSend.setSender(admLineSend.getSender());
                    }
                    if (admLineSend.getReceiver() != null) {
                        existingAdmLineSend.setReceiver(admLineSend.getReceiver());
                    }
                    if (admLineSend.getMessage() != null) {
                        existingAdmLineSend.setMessage(admLineSend.getMessage());
                    }
                    if (admLineSend.getExpectSendTime() != null) {
                        existingAdmLineSend.setExpectSendTime(admLineSend.getExpectSendTime());
                    }
                    if (admLineSend.getRealSendTime() != null) {
                        existingAdmLineSend.setRealSendTime(admLineSend.getRealSendTime());
                    }
                    if (admLineSend.getStatus() != null) {
                        existingAdmLineSend.setStatus(admLineSend.getStatus());
                    }
                    if (admLineSend.getProcessMessage() != null) {
                        existingAdmLineSend.setProcessMessage(admLineSend.getProcessMessage());
                    }
                    if (admLineSend.getCreateUser() != null) {
                        existingAdmLineSend.setCreateUser(admLineSend.getCreateUser());
                    }
                    if (admLineSend.getCreateTime() != null) {
                        existingAdmLineSend.setCreateTime(admLineSend.getCreateTime());
                    }
                    if (admLineSend.getUpdateUser() != null) {
                        existingAdmLineSend.setUpdateUser(admLineSend.getUpdateUser());
                    }
                    if (admLineSend.getUpdateTime() != null) {
                        existingAdmLineSend.setUpdateTime(admLineSend.getUpdateTime());
                    }

                    return existingAdmLineSend;
                }
            )
            .map(admLineSendRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admLineSend.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-line-sends} : get all the admLineSends.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admLineSends in body.
     */
    @GetMapping("/adm-line-sends")
    public List<AdmLineSend> getAllAdmLineSends() {
        log.debug("REST request to get all AdmLineSends");
        return admLineSendRepository.findAll();
    }

    /**
     * {@code GET  /adm-line-sends/:id} : get the "id" admLineSend.
     *
     * @param id the id of the admLineSend to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admLineSend, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-line-sends/{id}")
    public ResponseEntity<AdmLineSend> getAdmLineSend(@PathVariable Long id) {
        log.debug("REST request to get AdmLineSend : {}", id);
        Optional<AdmLineSend> admLineSend = admLineSendRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admLineSend);
    }

    /**
     * {@code DELETE  /adm-line-sends/:id} : delete the "id" admLineSend.
     *
     * @param id the id of the admLineSend to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-line-sends/{id}")
    public ResponseEntity<Void> deleteAdmLineSend(@PathVariable Long id) {
        log.debug("REST request to delete AdmLineSend : {}", id);
        admLineSendRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
