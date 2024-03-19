package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmFunction;
import gov.pcc.pwc.repository.PwbUsualfunctionRepository;
import gov.pcc.pwc.service.PwbUsualfunctionService;
import gov.pcc.pwc.service.dto.PwbUsualfunctionDTO;
import gov.pcc.pwc.service.dto.PwcPengingViewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.PwbUsualfunction}.
 */
@RestController
@RequestMapping("/api")
public class PwbUsualfunctionResource {

    private final Logger log = LoggerFactory.getLogger(PwbUsualfunctionResource.class);

    private static final String ENTITY_NAME = "pwbUsualfunction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwbUsualfunctionService pwbUsualfunctionService;

    private final PwbUsualfunctionRepository pwbUsualfunctionRepository;

    public PwbUsualfunctionResource(
        PwbUsualfunctionService pwbUsualfunctionService,
        PwbUsualfunctionRepository pwbUsualfunctionRepository
    ) {
        this.pwbUsualfunctionService = pwbUsualfunctionService;
        this.pwbUsualfunctionRepository = pwbUsualfunctionRepository;
    }

    /**
     * {@code POST  /pwb-usualfunctions} : Create a new pwbUsualfunction.
     *
     * @param pwbUsualfunctionDTO the pwbUsualfunctionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbUsualfunctionDTO, or with status {@code 400 (Bad Request)} if the pwbUsualfunction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-usualfunctions")
    public ResponseEntity<PwbUsualfunctionDTO> createPwbUsualfunction(@Valid @RequestBody PwbUsualfunctionDTO pwbUsualfunctionDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwbUsualfunction : {}", pwbUsualfunctionDTO);
        if (pwbUsualfunctionDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwbUsualfunction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwbUsualfunctionDTO result = pwbUsualfunctionService.save(pwbUsualfunctionDTO);
        return ResponseEntity
            .created(new URI("/api/pwb-usualfunctions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwb-usualfunctions/:id} : Updates an existing pwbUsualfunction.
     *
     * @param id the id of the pwbUsualfunctionDTO to save.
     * @param pwbUsualfunctionDTO the pwbUsualfunctionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbUsualfunctionDTO,
     * or with status {@code 400 (Bad Request)} if the pwbUsualfunctionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbUsualfunctionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwb-usualfunctions/{id}")
    public ResponseEntity<PwbUsualfunctionDTO> updatePwbUsualfunction(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PwbUsualfunctionDTO pwbUsualfunctionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbUsualfunction : {}, {}", id, pwbUsualfunctionDTO);
        if (pwbUsualfunctionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUsualfunctionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbUsualfunctionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwbUsualfunctionDTO result = pwbUsualfunctionService.save(pwbUsualfunctionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUsualfunctionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pwb-usualfunctions/:id} : Partial updates given fields of an existing pwbUsualfunction, field will ignore if it is null
     *
     * @param id the id of the pwbUsualfunctionDTO to save.
     * @param pwbUsualfunctionDTO the pwbUsualfunctionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbUsualfunctionDTO,
     * or with status {@code 400 (Bad Request)} if the pwbUsualfunctionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwbUsualfunctionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwbUsualfunctionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwb-usualfunctions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PwbUsualfunctionDTO> partialUpdatePwbUsualfunction(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PwbUsualfunctionDTO pwbUsualfunctionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwbUsualfunction partially : {}, {}", id, pwbUsualfunctionDTO);
        if (pwbUsualfunctionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUsualfunctionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbUsualfunctionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwbUsualfunctionDTO> result = pwbUsualfunctionService.partialUpdate(pwbUsualfunctionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUsualfunctionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pwb-usualfunctions} : get all the pwbUsualfunctions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwbUsualfunctions in body.
     */
    @GetMapping("/pwb-usualfunctions")
    public List<PwbUsualfunctionDTO> getAllPwbUsualfunctions() {
        log.debug("REST request to get all PwbUsualfunctions");
        return pwbUsualfunctionService.findAll();
    }

    /**
     * {@code GET  /pwb-usualfunctions/:id} : get the "id" pwbUsualfunction.
     *
     * @param id the id of the pwbUsualfunctionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwbUsualfunctionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwb-usualfunctions/{id}")
    public ResponseEntity<PwbUsualfunctionDTO> getPwbUsualfunction(@PathVariable Long id) {
        log.debug("REST request to get PwbUsualfunction : {}", id);
        Optional<PwbUsualfunctionDTO> pwbUsualfunctionDTO = pwbUsualfunctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwbUsualfunctionDTO);
    }

    /**
     * {@code DELETE  /pwb-usualfunctions/:id} : delete the "id" pwbUsualfunction.
     *
     * @param id the id of the pwbUsualfunctionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwb-usualfunctions/{id}")
    public ResponseEntity<Void> deletePwbUsualfunction(@PathVariable Long id) {
        log.debug("REST request to delete PwbUsualfunction : {}", id);
        pwbUsualfunctionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /usualfunction-functionName} : get all the pwbUsualfunctions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwbUsualfunctions in body.
     */
    @GetMapping("/usualfunction-functionName/{id}")
    public List<AdmFunction> getUsualfunctionFunctionName(@PathVariable String id) {
        log.debug("REST request to get all PwbUsualfunction FunctionName");
        return pwbUsualfunctionService.getUsualfunctionFunctionName(id);
    }

    /**
     * {@code POST  /usualfunction-functionName-default} : get all the pwbUsualfunctions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwbUsualfunctions in body.
     */
    @GetMapping("/usualfunction-functionName-default/{id}")
    public List<AdmFunction> getUsualfunctionFunctionNameDefault(@PathVariable String id) {
        log.debug("REST request to get all PwbUsualfunction FunctionName Default");
        return pwbUsualfunctionService.getUsualfunctionFunctionNameDefault(id);
    }

    /**
     * {@code PUT  /add-usualfunctions} : Create a new pwbUsualfunction.
     *
     * @param pwbUsualfunctionDTO the pwbUsualfunctionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbUsualfunctionDTO, or with status {@code 400 (Bad Request)} if the pwbUsualfunction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/add-usualfunctions")
    public void addPwbUsualfunction(@Valid @RequestBody PwbUsualfunctionDTO pwbUsualfunctionDTO)
            throws URISyntaxException {
        log.debug("REST request to save PwbUsualfunction : {}", pwbUsualfunctionDTO);
        if (pwbUsualfunctionDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwbUsualfunction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        pwbUsualfunctionService.addPwbUsualfunction(pwbUsualfunctionDTO);
    }

    /**
     * {@code GET  penging-view-get-userid/{id}} : get all the pwbpengingview.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcpengingview in body.
     */
    @GetMapping("/penging-view-get-userid/{id}")
    public List<PwcPengingViewDTO> pengingViewGetUserid(@PathVariable String id) {
        log.debug("REST request to get all PwcPengingView User Id");

        List<PwcPengingViewDTO> AA = pwbUsualfunctionService.getPengingViewForUserid(id);
        return AA;
    }

}
