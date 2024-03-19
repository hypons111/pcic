package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmFunction;
import gov.pcc.pwc.repository.AdmFunctionRepository;
import gov.pcc.pwc.service.AdmFunctionService;
import gov.pcc.pwc.service.dto.AdmFunctionDTO;
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
 * REST controller for managing {@link AdmFunction}.
 */
@RestController
@RequestMapping("/api")
public class AdmFunctionResource {

    private final Logger log = LoggerFactory.getLogger(AdmFunctionResource.class);

    private static final String ENTITY_NAME = "AdmFunction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmFunctionService admFunctionService;

    private final AdmFunctionRepository admFunctionRepository;

    public AdmFunctionResource(AdmFunctionService admFunctionService, AdmFunctionRepository admFunctionRepository) {
        this.admFunctionService = admFunctionService;
        this.admFunctionRepository = admFunctionRepository;
    }

    /**
     * {@code POST  /adm-function} : Create a new AdmFunction.
     *
     * @param admFunctionDTO the AdmFunction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admFunctionDTO, or with status {@code 400 (Bad Request)} if the admFunction has already an functionId.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-function")
    public ResponseEntity<AdmFunctionDTO> createAdmFunction(@Valid @RequestBody AdmFunctionDTO admFunctionDTO) throws URISyntaxException {
        log.debug("REST request to save AdmFunction: {}", admFunctionDTO);
        if (admFunctionRepository.existsByFunctionId(admFunctionDTO.getFunctionId())) {
            throw new BadRequestAlertException("the functionId had already used", ENTITY_NAME, "idexists");
        }
        AdmFunctionDTO result = admFunctionService.save(admFunctionDTO);
        return ResponseEntity
            .created(new URI("/api/adm-function/" + result.getFunctionId()))
            //            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getFunctionId()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD06_0001_S(result.getFunctionId())))
            .body(result);
    }

    /**
     * {@code PUT  /adm-function/:functionId} : Updates an existing admFunction.
     *
     * @param functionId the unique key of the admFunctionDTO to save.
     * @param admFunctionDTO the AdmFunction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admFunctionDTO,
     * or with status {@code 400 (Bad Request)} if the admFunctionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admFunctionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-function/{functionId}")
    public ResponseEntity<AdmFunctionDTO> updateAdmFunction(
        @PathVariable(value = "functionId") final String functionId,
        @Valid @RequestBody AdmFunctionDTO admFunctionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update admFunction : {}, {}", functionId, admFunctionDTO);
        if (admFunctionDTO.getFunctionId() == null) {
            throw new BadRequestAlertException("Invalid functionId", ENTITY_NAME, "unique key null");
        }
        if (!Objects.equals(functionId, admFunctionDTO.getFunctionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "unique key invalid");
        }

        if (!admFunctionRepository.existsByFunctionId(functionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "unique key not found");
        }

        AdmFunctionDTO result = admFunctionService.update(admFunctionDTO);
        return ResponseEntity.ok().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD06_0002_S(result.getFunctionId()))).body(result);
    }

    /**
     * {@code GET  /adm-function/:functionId} : get the "functionId" admFunction.
     *
     * @param functionId the unique key of the admFunctionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admFunctionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-function/{functionId}")
    public AdmFunctionDTO getAdmFunction(@PathVariable String functionId) {
        log.debug("REST request to get AdmFunctionDTO : {}", functionId);
        return admFunctionService.findOne(functionId);
    }

    /**
     * {@code GET  /adm-function/get-function-opts} : get all admFunctions for front-end functionOpts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admFunctionDTOs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-function/get-function-opts")
    public List<AdmFunctionDTO> getAdmFunctionOpts() {
        log.debug("REST request to get all admFunction for front-end functionOpts");
        return admFunctionService.findAllFunctionOpts();
    }

    /**
     * {@code POST  /adm-function/criteria} : get admFunctions by criteria.
     *
     * @param criteria the criteria of the AdmFunctionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmFunctionDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-function/criteria")
    public List<AdmFunctionDTO> getAdmFunctionsByCriteria(@RequestBody AdmFunctionDTO criteria) {
        log.debug("REST request to get AdmFunctionDTOs : {}", criteria);
        return admFunctionService.findByCriteria(criteria);
    }

    /**
     * {@code DELETE  /adm-function/:functionId} : delete data of the "functionId" admFunction.
     *
     * @param functionId the unique key of the admFunctionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-function/{functionId}")
    public ResponseEntity<Void> deleteAdmFunction(@PathVariable String functionId) {
        log.debug("REST request to delete admFunction : {}", functionId);
        String result = admFunctionService.delete(functionId);
        if ("Y".equals(result)) {
            return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD06_0003_S(functionId))).build();
        } else {
            return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD06_0004_S(functionId))).build();
        }
    }
}
