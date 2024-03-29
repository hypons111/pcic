package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.CmsCompRepository;
import gov.pcc.pwc.service.CmsCompService;
import gov.pcc.pwc.service.criteria.CmsCompCriteria;
import gov.pcc.pwc.service.dto.CmsCompDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.CmsComp}.
 */
@RestController
@RequestMapping("/api")
public class CmsCompResource {

    private final Logger log = LoggerFactory.getLogger(CmsCompResource.class);

    private static final String ENTITY_NAME = "cmsComp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CmsCompService cmsCompService;

    private final CmsCompRepository cmsCompRepository;

    public CmsCompResource(CmsCompService cmsCompService, CmsCompRepository cmsCompRepository) {
        this.cmsCompService = cmsCompService;
        this.cmsCompRepository = cmsCompRepository;
    }

    /**
     * {@code POST  /cms-comps} : Create a new cmsComp.
     *
     * @param cmsCompDTO the cmsCompDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cmsCompDTO, or with status {@code 400 (Bad Request)} if the cmsComp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cms-comps")
    public ResponseEntity<CmsCompDTO> createCmsComp(@Valid @RequestBody CmsCompDTO cmsCompDTO) throws URISyntaxException {
        log.debug("REST request to save CmsComp : {}", cmsCompDTO);
        if (cmsCompDTO.getId() != null) {
            throw new BadRequestAlertException("A new cmsComp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CmsCompDTO result = cmsCompService.save(cmsCompDTO);
        return ResponseEntity
            .created(new URI("/api/cms-comps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /cms-comps/:id} : Updates an existing cmsComp.
     *
     * @param id the id of the cmsCompDTO to save.
     * @param cmsCompDTO the cmsCompDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cmsCompDTO,
     * or with status {@code 400 (Bad Request)} if the cmsCompDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cmsCompDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cms-comps/{id}")
    public ResponseEntity<CmsCompDTO> updateCmsComp(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody CmsCompDTO cmsCompDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CmsComp : {}, {}", id, cmsCompDTO);
        if (cmsCompDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cmsCompDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cmsCompRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CmsCompDTO result = cmsCompService.save(cmsCompDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_OGN01_0001_S(result.getId(), result.getName())))
            .body(result);
    }

    /**
     * {@code PATCH  /cms-comps/:id} : Partial updates given fields of an existing cmsComp, field will ignore if it is null
     *
     * @param id the id of the cmsCompDTO to save.
     * @param cmsCompDTO the cmsCompDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cmsCompDTO,
     * or with status {@code 400 (Bad Request)} if the cmsCompDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cmsCompDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cmsCompDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cms-comps/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CmsCompDTO> partialUpdateCmsComp(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody CmsCompDTO cmsCompDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CmsComp partially : {}, {}", id, cmsCompDTO);
        if (cmsCompDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cmsCompDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cmsCompRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CmsCompDTO> result = cmsCompService.partialUpdate(cmsCompDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cmsCompDTO.getId())
        );
    }

    /**
     * {@code GET  /cms-comps} : get all the cmsComps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cmsComps in body.
     */
    @GetMapping("/cms-comps")
    public List<CmsCompDTO> getAllCmsComps() {
        log.debug("REST request to get all CmsComps");
        return cmsCompService.findAll();
    }

    /**
     * {@code GET  /cms-comps/:id} : get the "id" cmsComp.
     *
     * @param id the id of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cmsCompDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cms-comps/{id}")
    public ResponseEntity<CmsCompDTO> getCmsComp(@PathVariable String id) {
        log.debug("REST request to get CmsComp : {}", id);
        Optional<CmsCompDTO> cmsCompDTO = cmsCompService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cmsCompDTO);
    }

    /**
     * {@code POST  /cms-comps/criteria} : get cmsComps by criteria.
     *
     * @param criteria the criteria of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cmsCompDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/cms-comps/criteria")
    public List<CmsCompDTO> getCmsCompByCriteria(@RequestBody CmsCompDTO criteria) {
        log.debug("REST request to get CmsComp : {}", criteria);
        return cmsCompService.findByCriteria(criteria);
    }

    /**
     * {@code POST  /cms-comps/criteria-jpa} : get cmsComps by criteria.
     *
     * @param criteria the criteria of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cmsCompDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/cms-comps/criteria-jpa")
    public Page<CmsCompDTO> getCmsCompByCriteria(@Valid @RequestBody CmsCompCriteria criteria) throws InterruptedException {
        log.debug("REST request to get CmsComp : {}", criteria);
        return cmsCompService.findByCriteria(criteria);
    }

    /**
     * {@code DELETE  /cms-comps/:id} : delete the "id" cmsComp.
     *
     * @param id the id of the cmsCompDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cms-comps/{id}")
    public ResponseEntity<Void> deleteCmsComp(@PathVariable String id) {
        log.debug("REST request to delete CmsComp : {}", id);
        cmsCompService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
