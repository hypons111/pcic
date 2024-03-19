package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.service.criteria.ECT0101R2QueryCriteria;
import gov.pcc.pwc.service.EctPublicService;
import gov.pcc.pwc.repository.EctPublicRepository;
import gov.pcc.pwc.service.dto.EctPublicDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EctPublic}.
 */
@RestController
@RequestMapping("/api")
public class EctPublicResource {

    private final Logger log = LoggerFactory.getLogger(EctPublicResource.class);

    private static final String ENTITY_NAME = "ectPublic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EctPublicService ectPublicService;

    private final EctPublicRepository ectPublicRepository;

    public EctPublicResource(EctPublicService ectPublicService, EctPublicRepository ectPublicRepository) {
        this.ectPublicService = ectPublicService;
        this.ectPublicRepository = ectPublicRepository;
    }

	@PostMapping("/service/ect0101r2")
    public Map<String, Object> getECT0101R2(@Valid @RequestBody ECT0101R2QueryCriteria criteria) {
        log.debug("Rest ect0101r2" + criteria);
        return ectPublicService.getECT0101R2(criteria);
    }

    @PostMapping("/service/applyect")
    public Map<String, Object> applyect(MultipartHttpServletRequest request) {
        log.debug("Rest applyect");
        return ectPublicService.applyect(request);
    }

    @PostMapping("/service/queryect")
    public Map<String, Object> queryect(MultipartHttpServletRequest request) {
        log.debug("Rest queryect");
        return ectPublicService.queryect(request);
    }

    @PostMapping("/service/revokeect")
    public Map<String, Object> revokeect(MultipartHttpServletRequest request) {
        log.debug("Rest revokeect");
        return ectPublicService.revokeect(request);
    }

    /**
     * {@code POST  /ect-publics} : Create a new ectPublic.
     *
     * @param ectPublicDTO the ectPublicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ectPublicDTO, or with status {@code 400 (Bad Request)} if the ectPublic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ect-publics")
    public ResponseEntity<EctPublicDTO> createEctPublic(@Valid @RequestBody EctPublicDTO ectPublicDTO) throws URISyntaxException {
        log.debug("REST request to save EctPublic : {}", ectPublicDTO);
        EctPublicDTO result = ectPublicService.save(ectPublicDTO);
        return ResponseEntity
            .created(new URI("/api/ect-publics/" + result.getEctPublishId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEctPublishId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ect-publics/:ectPublishId} : Updates an existing ectPublic.
     *
     * @param ectPublishId the id of the ectPublicDTO to save.
     * @param ectPublicDTO the ectPublicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ectPublicDTO,
     * or with status {@code 400 (Bad Request)} if the ectPublicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ectPublicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ect-publics/{ectPublishId}")
    public ResponseEntity<EctPublicDTO> updateEctPublic(
        @PathVariable(value = "ectPublishId", required = false) final Long ectPublishId,
        @Valid @RequestBody EctPublicDTO ectPublicDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EctPublic : {}, {}", ectPublishId, ectPublicDTO);

        EctPublicDTO result = ectPublicService.save(ectPublicDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ectPublicDTO.getEctPublishId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ect-publics/:ectPublishId} : Partial updates given fields of an existing ectPublic, field will ignore if it is null
     *
     * @param ectPublishId the id of the ectPublicDTO to save.
     * @param ectPublicDTO the ectPublicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ectPublicDTO,
     * or with status {@code 400 (Bad Request)} if the ectPublicDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ectPublicDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ectPublicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ect-publics/{ectPublishId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EctPublicDTO> partialUpdateEctPublic(
        @PathVariable(value = "ectPublishId", required = false) final Long ectPublishId,
        @NotNull @RequestBody EctPublicDTO ectPublicDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EctPublic partially : {}, {}", ectPublishId, ectPublicDTO);

        Optional<EctPublicDTO> result = ectPublicService.partialUpdate(ectPublicDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ectPublicDTO.getEctPublishId().toString())
        );
    }

    /**
     * {@code GET  /ect-publics} : get all the ectPublics.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ectPublics in body.
     */
    @GetMapping("/ect-publics")
    public List<EctPublicDTO> getAllEctPublics() {
        log.debug("REST request to get all EctPublics");
        return ectPublicService.findAll();
    }

    /**
     * {@code GET  /ect-publics/:id} : get the "id" ectPublic.
     *
     * @param id the id of the ectPublicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ectPublicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ect-publics/{id}")
    public ResponseEntity<EctPublicDTO> getEctPublic(@PathVariable Long id) {
        log.debug("REST request to get EctPublic : {}", id);
        Optional<EctPublicDTO> ectPublicDTO = ectPublicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ectPublicDTO);
    }

    /**
     * {@code DELETE  /ect-publics/:id} : delete the "id" ectPublic.
     *
     * @param id the id of the ectPublicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ect-publics/{id}")
    public ResponseEntity<Void> deleteEctPublic(@PathVariable Long id) {
        log.debug("REST request to delete EctPublic : {}", id);
        ectPublicService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
