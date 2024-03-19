package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.repository.EngEngrSubjectpointRepository;
import gov.pcc.pwc.service.EngEngrSubjectpointService;
import gov.pcc.pwc.service.criteria.EngEngrSubjectpointCriteria;
import gov.pcc.pwc.service.dto.EngEngrSubjectpointDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import javax.validation.Valid;

import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngEngrSubjectpoint}.
 */
@RestController
@RequestMapping("/api")
public class EngEngrSubjectpointResource {

    private final Logger log = LoggerFactory.getLogger(EngEngrSubjectpointResource.class);

    private static final String ENTITY_NAME = "engEngrSubjectpoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngEngrSubjectpointService engEngrSubjectpointService;

    private final EngEngrSubjectpointRepository engEngrSubjectpointRepository;

    public EngEngrSubjectpointResource(
        EngEngrSubjectpointService engEngrSubjectpointService,
        EngEngrSubjectpointRepository engEngrSubjectpointRepository
    ) {
        this.engEngrSubjectpointService = engEngrSubjectpointService;
        this.engEngrSubjectpointRepository = engEngrSubjectpointRepository;
    }

    /**
     * {@code POST  /eng-engr-subjectpoints} : Create a new engEngrSubjectpoint.
     *
     * @param engEngrSubjectpointDTO the engEngrSubjectpointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engEngrSubjectpointDTO, or with status {@code 400 (Bad Request)} if the engEngrSubjectpoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-engr-subjectpoints")
    public ResponseEntity<EngEngrSubjectpointDTO> createEngEngrSubjectpoint(
        @Valid @RequestBody EngEngrSubjectpointDTO engEngrSubjectpointDTO
    ) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to save EngEngrSubjectpoint : {}", engEngrSubjectpointDTO);
        if (engEngrSubjectpointDTO.getEngEngrSubjectpointId() != null) {
            throw new BadRequestAlertException("A new engEngrSubjectpoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngEngrSubjectpointDTO result = engEngrSubjectpointService.save(engEngrSubjectpointDTO);
        EngEngrSubjectpointDTO result2 = engEngrSubjectpointService.findDTOById(result.getEngEngrSubjectpointId());
        return ResponseEntity
            .created(new URI("/api/eng-engr-subjectpoints/" + result.getEngEngrSubjectpointId()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_GEO01_0002_S(engEngrSubjectpointDTO.getEngEngrSubjectpointId(), engEngrSubjectpointDTO.getChName())))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngEngrSubjectpointId()))
            .body(result2);
    }

    /**
     * {@code PUT  /eng-engr-subjectpoints/:engEngrSubjectpointId} : Updates an existing engEngrSubjectpoint.
     *
     * @param engEngrSubjectpointId the id of the engEngrSubjectpointDTO to save.
     * @param engEngrSubjectpointDTO the engEngrSubjectpointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEngrSubjectpointDTO,
     * or with status {@code 400 (Bad Request)} if the engEngrSubjectpointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engEngrSubjectpointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-engr-subjectpoints/{engEngrSubjectpointId}")
    public ResponseEntity<EngEngrSubjectpointDTO> updateEngEngrSubjectpoint(
        @PathVariable(value = "engEngrSubjectpointId", required = false) final String engEngrSubjectpointId,
        @Valid @RequestBody EngEngrSubjectpointDTO engEngrSubjectpointDTO
    ) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to update EngEngrSubjectpoint : {}, {}", engEngrSubjectpointId, engEngrSubjectpointDTO);
        if (engEngrSubjectpointDTO.getEngEngrSubjectpointId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEngrSubjectpointId, engEngrSubjectpointDTO.getEngEngrSubjectpointId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEngrSubjectpointRepository.existsById(engEngrSubjectpointId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngEngrSubjectpointDTO result = engEngrSubjectpointService.save(engEngrSubjectpointDTO);
        EngEngrSubjectpointDTO result2 = engEngrSubjectpointService.findDTOById(result.getEngEngrSubjectpointId());
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_GEO01_0001_S(engEngrSubjectpointDTO.getEngEngrSubjectpointId(), engEngrSubjectpointDTO.getChName())))
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEngrSubjectpointDTO.getEngEngrSubjectpointId())
            )
            .body(result2);
    }

    /**
     * {@code GET  /eng-engr-subjectpoints/:id} : get the "id" engEngrSubjectpoint.
     *
     * @param id the id of the engEngrSubjectpointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engEngrSubjectpointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-engr-subjectpoints/{id}")
    public ResponseEntity<EngEngrSubjectpointDTO> getEngEngrSubjectpoint(@PathVariable String id) {
        log.debug("REST request to get EngEngrSubjectpoint : {}", id);
        EngEngrSubjectpointDTO engEngrSubjectpointDTO = engEngrSubjectpointService.findDTOById(id);
        return ResponseEntity.ok(engEngrSubjectpointDTO);
    }

    @PostMapping("/eng-engr-subjectpoints/criteria")
    public Page<EngEngrSubjectpointDTO> getAllEngEngrSubjectpoints(@Valid @RequestBody EngEngrSubjectpointCriteria criteria) {
        log.debug("REST request to get all EngEngrSubjectpoints by criteria : {}", criteria);
        return engEngrSubjectpointService.findAll(criteria);
    }
}
