package gov.pcc.pwc.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwcInformationRepository;
import gov.pcc.pwc.service.AdmRoleService;
import gov.pcc.pwc.service.AdmSysCodeService;
import gov.pcc.pwc.service.AdmUnitService;
import gov.pcc.pwc.service.PwcInformationService;
import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcInformation}.
 */
@RestController
@RequestMapping("/api/service")
public class PwcInformationResource {

    private final Logger log = LoggerFactory.getLogger(PwcInformationResource.class);

    private static final String ENTITY_NAME = "pwcInformation";

    private final ObjectMapper objectMapper;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcInformationService pwcInformationService;

    private final AdmSysCodeService admSysCodeService;

    private final PwcInformationRepository pwcInformationRepository;

    private final AdmUnitService admUnitService;

    private final AdmRoleService admRoleService;

    public PwcInformationResource(ObjectMapper objectMapper, PwcInformationService pwcInformationService, AdmSysCodeService admSysCodeService, PwcInformationRepository pwcInformationRepository, AdmUnitService admUnitService, AdmRoleService admRoleService) {
        this.objectMapper = objectMapper;
        this.pwcInformationService = pwcInformationService;
        this.admSysCodeService = admSysCodeService;
        this.pwcInformationRepository = pwcInformationRepository;
        this.admUnitService = admUnitService;
        this.admRoleService = admRoleService;
    }

    /**
     * {@code POST  /pwc-informations} : Create a new pwcInformation.
     *
     * @param pwcInformationDTO the pwcInformationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwcInformationDTO, or with status {@code 400 (Bad Request)} if the pwcInformation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwc-informations")
    public ResponseEntity<PwcInformationDTO> createPwcInformation(@Valid @RequestBody PwcInformationDTO pwcInformationDTO)
            throws URISyntaxException {
        log.debug("REST request to save PwcInformation : {}", pwcInformationDTO);
        if (pwcInformationDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwcInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcInformationDTO result = pwcInformationService.save(pwcInformationDTO);
        return ResponseEntity
                .created(new URI("/api/pwc-informations/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /pwc-informations/:proInformationId} : Updates an existing pwcInformation.
     *
     * @param proInformationId  the id of the pwcInformationDTO to save.
     * @param pwcInformationDTO the pwcInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwc-informations/{proInformationId}")
    public ResponseEntity<PwcInformationDTO> updatePwcInformation(
            @PathVariable(value = "proInformationId", required = false) final Long proInformationId,
            @Valid @RequestBody PwcInformationDTO pwcInformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwcInformation : {}, {}", proInformationId, pwcInformationDTO);
        if (pwcInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInformationId, pwcInformationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcInformationRepository.existsById(proInformationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcInformationDTO result = pwcInformationService.save(pwcInformationDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwcInformationDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code PATCH  /pwc-informations/:proInformationId} : Partial updates given fields of an existing pwcInformation, field will ignore if it is null
     *
     * @param proInformationId  the id of the pwcInformationDTO to save.
     * @param pwcInformationDTO the pwcInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwcInformationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwc-informations/{proInformationId}", consumes = "application/merge-patch+json")
    public ResponseEntity<PwcInformationDTO> partialUpdatePwcInformation(
            @PathVariable(value = "proInformationId", required = false) final Long proInformationId,
            @NotNull @RequestBody PwcInformationDTO pwcInformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwcInformation partially : {}, {}", proInformationId, pwcInformationDTO);
        if (pwcInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInformationId, pwcInformationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcInformationRepository.existsById(proInformationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwcInformationDTO> result = pwcInformationService.partialUpdate(pwcInformationDTO);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwcInformationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pwc-informations} : get all the pwcInformations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcInformations in body.
     */
    @GetMapping("/pwc-informations")
    public List<PwcInformationDTO> getAllPwcInformations() {
        log.debug("REST request to get all PwcInformations");
        return pwcInformationService.findAll();
    }

    /**
     * {@code GET  /pwc-informations/:id} : get the "id" pwcInformation.
     *
     * @param id the id of the pwcInformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcInformationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwc-informations/{id}")
    public ResponseEntity<PwcInformationDTO> getPwcInformation(@PathVariable Long id) {
        log.debug("REST request to get PwcInformation : {}", id);
        Optional<PwcInformationDTO> pwcInformationDTO = pwcInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwcInformationDTO);
    }

    /**
     * {@code DELETE  /pwc-informations/:id} : delete the "id" pwcInformation.
     *
     * @param id the id of the pwcInformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwc-informations/{id}")
    public ResponseEntity<Void> deletePwcInformation(@PathVariable Long id) {
        log.debug("REST request to delete PwcInformation : {}", id);
        pwcInformationService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    /**
     * {@code POST  /cms-comps/criteria} : get pwcInformationDTO by criteria.
     *
     * @param criteria the criteria of the pwcInformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcInformationDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pwc-information/criteria")
    public List<PwcInformationDTO> getInformationByCriteria(@RequestBody PwcInformationDTO criteria) {
        log.debug("REST request to get PwcInformation : {}", criteria);
        return pwcInformationService.findInformationByCriteria(criteria);
    }

    /**
     * {@code DELETE  /info-delete} : delete the "id" pwcInformation.
     *
     * @param criteria the id of the pwcInformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/info-delete")
    public ResponseEntity<Integer> informationDelete(@RequestBody PwcInformationDTO criteria) {
        log.debug("REST request to delete PwcInformation : {}", criteria.getId());
        return ResponseEntity.ok(pwcInformationService.deleteByID(criteria));
    }

    /**
     * {@code POST  /adm-sys-codes/criteria-jpa} : get admSysCode by criteria.
     *
     * @param criteria the criteria of the AdmSysCodeCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmSysCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/sys-codes/criteria-jpa")
    public Page<AdmSysCodeDTO> getSysCodeByCriteria(@Valid @RequestBody AdmSysCodeCriteria criteria) {
        log.debug("REST request to get AdmSysCodeDTO : {}", criteria);
        return admSysCodeService.findByCriteria(criteria);
    }

    /**
     * {@code POST  /information-save} : Create a new pwcInformation....
     *
     * @param bulletinDownAndInfoDTO the bulletinDownAndInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bulletinDownAndInfoDTO, or with status {@code 400 (Bad Request)} if the pwcInformation.. has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping(path = "/information-save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PwcInformationDTO> createInformation(
                                       @Valid @RequestPart("form") BulletinDownAndInfoDTO bulletinDownAndInfoDTO,
                                       @RequestPart("uploadFile") List<MultipartFile> uploadFile
    )
            throws URISyntaxException, IOException {
        log.debug("REST request to save PwcInformation : {}", bulletinDownAndInfoDTO);

        if (bulletinDownAndInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwcInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PwcInformationDTO result = pwcInformationService.saveAll(bulletinDownAndInfoDTO, uploadFile);
        return ResponseEntity
                .created(new URI("/api/information-save/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /pwc-informations/:proInformationId} : Updates an existing pwcInformation.
     *
     * @param bulletinDownAndInfoDTO the pwcInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping(path ="/information-updated", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PwcInformationDTO> updateInformation(@Valid @RequestPart("form") BulletinDownAndInfoDTO bulletinDownAndInfoDTO,
                                                               @RequestPart("uploadFile") List<MultipartFile> uploadFile) throws URISyntaxException, IOException {
        log.debug("REST request to update PwcInformation : {}, {}", bulletinDownAndInfoDTO.getId(), bulletinDownAndInfoDTO);
        if (bulletinDownAndInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        if (!pwcInformationRepository.existsById(bulletinDownAndInfoDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcInformationDTO result = pwcInformationService.saveAll(bulletinDownAndInfoDTO, uploadFile);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bulletinDownAndInfoDTO.getId().toString()))
                .body(result);
    }

    @GetMapping("/adm-units/get-unit-opts")
    public List<AdmUnitDTO> getServiceUnitOpts() {
        log.debug("REST request to get all admUnitDTO for front-end unitOpts");
        return admUnitService.findAllUnitOpts();
    }

    @GetMapping("/adm-roles/get-role-opts")
    public List<AdmRoleDTO> getServiceRoleOpts() {
        log.debug("REST request to get all AdmRoleDTO for front-end roleOpts");
        return admRoleService.findAllRoleOpts();
    }

    /**
     * {@code POST  /pwc-information/getInfoByCriteria} : get pwcInformationDTO by criteria.
     *
     * @param criteria the criteria of the pwcInformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcInformationDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pwc-information/getInfoByCriteria")
    public List<PwcInformationDTO> getInfoByCriteria(@RequestBody PwcInformationDTO criteria) {
        log.debug("REST request to get PwcInformation : {}", criteria);
        return pwcInformationService.findInfoByCriteria(criteria);
    }
}
