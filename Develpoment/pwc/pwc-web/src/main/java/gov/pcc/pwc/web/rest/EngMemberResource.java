package gov.pcc.pwc.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.pcc.pwc.repository.EngMemberRepository;
import gov.pcc.pwc.service.EngMemberService;
import gov.pcc.pwc.service.criteria.EngMemberCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import gov.pcc.pwc.service.dto.EngMemberDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.GeoMemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngMember}.
 */
@RestController
@RequestMapping("/api")
public class EngMemberResource {

    private final Logger log = LoggerFactory.getLogger(EngMemberResource.class);

    private static final String ENTITY_NAME = "engMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngMemberService engMemberService;

    private final EngMemberRepository engMemberRepository;

    public EngMemberResource(EngMemberService engMemberService, EngMemberRepository engMemberRepository) {
        this.engMemberService = engMemberService;
        this.engMemberRepository = engMemberRepository;
    }

    /**
     * {@code POST  /eng-members} : Create a new engMember.
     *
     * @param engMemberDTO the engMemberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engMemberDTO, or with status {@code 400 (Bad Request)} if the engMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-members")
    public ResponseEntity<EngMemberDTO> createEngMember(@Valid @RequestBody EngMemberDTO engMemberDTO) throws URISyntaxException {
        log.debug("REST request to save EngMember : {}", engMemberDTO);
        if (engMemberDTO.getEngMemberId() != null) {
            throw new BadRequestAlertException("A new engMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngMemberDTO result = engMemberService.save(engMemberDTO);
        return ResponseEntity
            .created(new URI("/api/eng-members/" + result.getEngMemberId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngMemberId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-members/:engMemberId} : Updates an existing engMember.
     *
     * @param engMemberId the id of the engMemberDTO to save.
     * @param engMemberDTO the engMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engMemberDTO,
     * or with status {@code 400 (Bad Request)} if the engMemberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-members/{engMemberId}")
    public ResponseEntity<EngMemberDTO> updateEngMember(
        @PathVariable(value = "engMemberId", required = false) final String engMemberId,
        @Valid @RequestBody EngMemberDTO engMemberDTO,
        @RequestHeader("functionId") String functionId
    ) throws URISyntaxException, JsonProcessingException {
        log.debug("REST request to update EngMember : {}, {}", engMemberId, engMemberDTO);
        if (engMemberDTO.getEngMemberId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engMemberId, engMemberDTO.getEngMemberId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engMemberRepository.existsById(engMemberId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmFunctionUsedRecordDTO admFunctionUsedRecordDTO = new AdmFunctionUsedRecordDTO();
        admFunctionUsedRecordDTO.setFunctionId(functionId);
        admFunctionUsedRecordDTO.setRecordContent("ENG0706U1技師個人基本資料-修改");

        EngMemberDTO result = engMemberService.save(engMemberDTO, admFunctionUsedRecordDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engMemberDTO.getEngMemberId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-members/:engMemberId} : Partial updates given fields of an existing engMember, field will ignore if it is null
     *
     * @param engMemberId the id of the engMemberDTO to save.
     * @param engMemberDTO the engMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engMemberDTO,
     * or with status {@code 400 (Bad Request)} if the engMemberDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engMemberDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-members/{engMemberId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngMemberDTO> partialUpdateEngMember(
        @PathVariable(value = "engMemberId", required = false) final String engMemberId,
        @NotNull @RequestBody EngMemberDTO engMemberDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngMember partially : {}, {}", engMemberId, engMemberDTO);
        if (engMemberDTO.getEngMemberId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engMemberId, engMemberDTO.getEngMemberId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engMemberRepository.existsById(engMemberId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngMemberDTO> result = engMemberService.partialUpdate(engMemberDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engMemberDTO.getEngMemberId())
        );
    }

    /**
     * {@code GET  /eng-members} : get all the engMembers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engMembers in body.
     */
    @GetMapping("/eng-members")
    public List<EngMemberDTO> getAllEngMembers() {
        log.debug("REST request to get all EngMembers");
        return engMemberService.findAll();
    }

    /**
     * {@code GET  /eng-members/:id} : get the "id" engMember.
     *
     * @param id the id of the engMemberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engMemberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-members/{id}")
    public ResponseEntity<EngMemberDTO> getEngMember(@PathVariable String id) {
        log.debug("REST request to get EngMember : {}", id);
        Optional<EngMemberDTO> engMemberDTO = engMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engMemberDTO);
    }

    /**
     * {@code GET  /eng-members/:type/:idno} : get the "type" and "idno" engMember.
     *
     * @param type the type of the engMemberDTO to retrieve.
     * @param idno the idno of the engMemberDTO to retrieve.
     * @return the engMemberDTO.
     */
    @GetMapping("/eng-members/{type}/{idno}")
    public EngMemberDTO getEngMember(@PathVariable String type, @PathVariable String idno) {
        log.debug("REST request to get EngMeneber : {}, {}", type, idno);
        return engMemberService.findOne(type, idno);
    }


    /**
     * {@code POST  /geo-members/criteria-jpa} : get geoMembers by criteria.
     *
     * @param criteria the criteria of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoMemberDTO, or with status {@code 404 (Not Found)}.
     */
//    @PostMapping("/geo-jobs/criteria-jpa")
//    public Page<GeoMemberDTO> getGeoJobByCriteria(@Valid @RequestBody geoMemberCriteria criteria) throws InterruptedException {
//        log.debug("REST request to get geoMember : {}", criteria);
//        return geoMemberService.findByCriteria(criteria);
//    }


    /**
     * {@code POST  /eng-members/criteria-jpa} : get engMembers by criteria.
     *
     * @param criteria the criteria of the engMemberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engMemberDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/eng-members/criteria-jpa")
    public Page<EngMemberDTO> getEngMemberByCriteria(@Valid @RequestBody EngMemberCriteria criteria) throws InterruptedException {
        log.debug("REST request to get engMember : {}", criteria);
        return engMemberService.findByCriteria(criteria);
    }

    /**
     * {@code DELETE  /eng-members/:id} : delete the "id" engMember.
     *
     * @param id the id of the engMemberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-members/{id}")
    public ResponseEntity<Void> deleteEngMember(@PathVariable String id) {
        log.debug("REST request to delete EngMember : {}", id);
        engMemberService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
