package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmFunctionExtend;
import gov.pcc.pwc.repository.AdmFunctionExtendRepository;
import gov.pcc.pwc.service.AdmFunctionExtendService;
import gov.pcc.pwc.service.dto.AdmFunctionExtendDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link AdmFunctionExtend}.
 */
@RestController
@RequestMapping("/api")
public class AdmFunctionExtendResource {

    private final Logger log = LoggerFactory.getLogger(AdmFunctionExtendResource.class);

    private static final String ENTITY_NAME = "AdmFunctionExtend";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmFunctionExtendService admFunctionExtendService;

    private final AdmFunctionExtendRepository admFunctionExtendRepository;

    public AdmFunctionExtendResource(
        AdmFunctionExtendService admFunctionExtendService,
        AdmFunctionExtendRepository admFunctionExtendRepository
    ) {
        this.admFunctionExtendService = admFunctionExtendService;
        this.admFunctionExtendRepository = admFunctionExtendRepository;
    }

    /**
     * {@code POST  /adm-function-extend} : Create a new AdmFunctionExtend.
     *
     * @param admFunctionExtendDTO the AdmFunctionExtend to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admFunctionExtendDTO, or with status {@code 400 (Bad Request)} if the admFunctionExtend has already an functionExtendId.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-function-extend")
    public ResponseEntity<AdmFunctionExtendDTO> createFunctionExtend(@Valid @RequestBody AdmFunctionExtendDTO admFunctionExtendDTO)
        throws URISyntaxException {
        log.debug("REST request to save AdmFunctionExtend: {}", admFunctionExtendDTO);
        if (admFunctionExtendRepository.existsByFunctionExtendId(admFunctionExtendDTO.getFunctionExtendId())) {
            throw new BadRequestAlertException("the functionExtendId had already used", ENTITY_NAME, "idexists");
        }
        AdmFunctionExtendDTO result = admFunctionExtendService.save(admFunctionExtendDTO);
        return ResponseEntity
            .created(new URI("/api/adm-function-extend/" + result.getFunctionExtendId()))
            //            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getFunctionExtendId()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD07_0001_S(result.getFunctionExtendId())))
            .body(result);
    }

    /**
     * {@code PUT  /adm-function-extend/:functionExtendId} : Updates an existing admFunctionExtend.
     *
     * @param functionExtendId the unique key of the admFunctionExtendDTO to save.
     * @param admFunctionExtendDTO the AdmFunctionExtend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admFunctionExtendDTO,
     * or with status {@code 400 (Bad Request)} if the admFunctionExtendDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admFunctionExtendDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-function-extend/{functionExtendId}")
    public ResponseEntity<AdmFunctionExtendDTO> updateFunctionExtend(
        @PathVariable(value = "functionExtendId") final String functionExtendId,
        @Valid @RequestBody AdmFunctionExtendDTO admFunctionExtendDTO
    ) throws URISyntaxException {
        log.debug("REST request to update admFunctionExtend : {}, {}", functionExtendId, admFunctionExtendDTO);
        if (admFunctionExtendDTO.getFunctionExtendId() == null) {
            throw new BadRequestAlertException("Invalid functionExtendId", ENTITY_NAME, "unique key null");
        }
        if (!Objects.equals(functionExtendId, admFunctionExtendDTO.getFunctionExtendId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "unique key invalid");
        }

        if (!admFunctionExtendRepository.existsByFunctionExtendId(functionExtendId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "unique key not found");
        }

        AdmFunctionExtendDTO result = admFunctionExtendService.update(admFunctionExtendDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD07_0002_S(result.getFunctionExtendId())))
            .body(result);
    }

    /**
     * {@code GET  /adm-function-extend/:functionExtendId} : get the "functionExtendId" admFunctionExtend.
     *
     * @param functionExtendId the unique key of the admFunctionExtendDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admFunctionExtendDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-function-extend/{functionExtendId}")
    public AdmFunctionExtendDTO getAdmFunctionExtend(@PathVariable String functionExtendId) {
        log.debug("REST request to get AdmFunctionExtendDTO : {}", functionExtendId);
        return admFunctionExtendService.findOne(functionExtendId);
    }

    /**
     * {@code GET  /adm-function-extend/get-function-extend-opts} : get all admFunctions for front-end functionOpts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admFunctionDTOs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-function-extend/get-function-extend-opts")
    public List<AdmFunctionExtendDTO> getFunctionExtendOpts() {
        log.debug("REST request to get all admFunctionExtend for front-end functionExtendOpts");
        return admFunctionExtendService.findAllFunctionExtendOpts();
    }

    /**
     * {@code POST  /adm-function-extend/criteria} : get admFunctionExtends by criteria.
     *
     * @param criteria the criteria of the AdmFunctionExtendDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmFunctionExtendDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-function-extend/criteria")
    public List<AdmFunctionExtendDTO> getFunctionExtendsByCriteria(@RequestBody AdmFunctionExtendDTO criteria) {
        log.debug("REST request to get AdmFunctionExtendDTOs : {}", criteria);
        return admFunctionExtendService.findByCriteria(criteria);
    }

    /**
     * {@code DELETE  /adm-function-extend/:functionExtendId} : delete data of the "functionExtendId" admFunctionExtend.
     *
     * @param functionExtendId the unique key of the admFunctionExtendDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-function-extend/{functionExtendId}")
    public ResponseEntity<Void> deleteFunctionExtend(@PathVariable String functionExtendId) {
        log.debug("REST request to delete admFunctionExtend : {}", functionExtendId);
        String result = admFunctionExtendService.delete(functionExtendId);
        if ("Y".equals(result)) {
            return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD07_0003_S(functionExtendId))).build();
        } else {
            return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD07_0004_S(functionExtendId))).build();
        }
    }
}
