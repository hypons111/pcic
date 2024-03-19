package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.GeoMemberRepository;
import gov.pcc.pwc.service.GeoMemberService;
import gov.pcc.pwc.service.criteria.GeoMemberCriteria;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.GeoMember}.
 */
@RestController
@RequestMapping("/api")
public class GeoMemberResource {

    private final Logger log = LoggerFactory.getLogger(GeoMemberResource.class);

    private static final String ENTITY_NAME = "geoMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoMemberService geoMemberService;

    private final GeoMemberRepository geoMemberRepository;

    public GeoMemberResource(GeoMemberService geoMemberService, GeoMemberRepository geoMemberRepository) {
        this.geoMemberService = geoMemberService;
        this.geoMemberRepository = geoMemberRepository;
    }

    /**
     * {@code POST  /geo-members} : Create a new geoMember.
     *
     * @param geoMemberDTO the geoMemberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoMemberDTO, or with status {@code 400 (Bad Request)} if the geoMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-members")
    public ResponseEntity<GeoMemberDTO> createGeoMember(@Valid @RequestBody GeoMemberDTO geoMemberDTO) throws URISyntaxException {
        geoMemberDTO.setCreateTime(Instant.now());
        geoMemberDTO.setCreateUser(SecurityContextHolder.getContext().getAuthentication().getName());
        log.debug("REST request to save GeoMember : {}", geoMemberDTO);
        if (geoMemberDTO.getGeoMemberId() != null) {
            throw new BadRequestAlertException("A new geoMember cannot already have an ID", ENTITY_NAME, "idexists");
        }

        GeoMemberDTO result = geoMemberService.save(geoMemberDTO);

        return ResponseEntity
            .created(new URI("/api/geo-members/" + result.getGeoMemberId()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_GEO01_0002_S(result.getUserId(), result.getUserName())))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getGeoMemberId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-members/:geoMemberId} : Updates an existing geoMember.
     *
     * @param geoMemberId the id of the geoMemberDTO to save.
     * @param geoMemberDTO the geoMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoMemberDTO,
     * or with status {@code 400 (Bad Request)} if the geoMemberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-members/{geoMemberId}")
    public ResponseEntity<GeoMemberDTO> updateGeoMember(
        @PathVariable(value = "geoMemberId", required = false) final String geoMemberId,
        @Valid @RequestBody GeoMemberDTO geoMemberDTO
    ) throws URISyntaxException {
        geoMemberDTO.setUpdateTime(Instant.now());
        geoMemberDTO.setUpdateUser(SecurityContextHolder.getContext().getAuthentication().getName());
        log.debug("REST request to update GeoMember : {}, {}", geoMemberId, geoMemberDTO);
        if (geoMemberDTO.getGeoMemberId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(geoMemberId, geoMemberDTO.getGeoMemberId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!geoMemberRepository.existsById(geoMemberId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GeoMemberDTO result = geoMemberService.save(geoMemberDTO);

        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_GEO01_0001_S(result.getUserId(), result.getUserName())))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoMemberDTO.getGeoMemberId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /geo-members/:geoMemberId} : Partial updates given fields of an existing geoMember, field will ignore if it is null
     *
     * @param geoMemberId the id of the geoMemberDTO to save.
     * @param geoMemberDTO the geoMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoMemberDTO,
     * or with status {@code 400 (Bad Request)} if the geoMemberDTO is not valid,
     * or with status {@code 404 (Not Found)} if the geoMemberDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the geoMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/geo-members/{geoMemberId}", consumes = "application/merge-patch+json")
    public ResponseEntity<GeoMemberDTO> partialUpdateGeoMember(
        @PathVariable(value = "geoMemberId", required = false) final String geoMemberId,
        @NotNull @RequestBody GeoMemberDTO geoMemberDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GeoMember partially : {}, {}", geoMemberId, geoMemberDTO);
        if (geoMemberDTO.getGeoMemberId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(geoMemberId, geoMemberDTO.getGeoMemberId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!geoMemberRepository.existsById(geoMemberId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GeoMemberDTO> result = geoMemberService.partialUpdate(geoMemberDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoMemberDTO.getGeoMemberId().toString())
        );
    }

    /**
     * {@code GET  /geo-members} : get all the geoMembers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoMembers in body.
     */
    @GetMapping("/geo-members")
    public List<GeoMemberDTO> getAllGeoMembers() {
        log.debug("REST request to get all GeoMembers");
        return geoMemberService.findAll();
    }

    /**
     * {@code GET  /geo-members/:id} : get the "id" geoMember.
     *
     * @param id the id of the geoMemberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoMemberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-members/{id}")
    public ResponseEntity<GeoMemberDTO> getGeoMember(@PathVariable String id) {
        log.debug("REST request to get GeoMember : {}", id);
        Optional<GeoMemberDTO> geoMemberDTO = geoMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoMemberDTO);
    }

    @GetMapping("/geo-members-by-user-id/{userId}")
    public ResponseEntity<GeoMemberDTO> getGeoMemberByUserId(@PathVariable String userId) {
        log.debug("REST request to get GeoMember : {}", userId);
        Optional<GeoMemberDTO> geoMemberDTO = geoMemberService.findByUserId(userId);
        return ResponseUtil.wrapOrNotFound(geoMemberDTO);
    }

    /**
     * {@code POST  /geo-members/criteria-jpa} : get geoMembers by criteria.
     *
     * @param criteria the criteria of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoMemberDTO, or with status {@code 404 (Not Found)}.
     */
//    @PostMapping("/geo-members/criteria-jpa")
//    public Page<GeoMemberDTO> getGeoMemberByCriteria(@Valid @RequestBody GeoMemberCriteria criteria) throws InterruptedException {
//        log.debug("REST request to get GeoMember : {}", criteria);
//        return geoMemberService.findByCriteria(criteria);
//    }
    @PostMapping("/geo-members/criteria")
    public Page<GeoMemberDTO> getGeoMemberByCriteria(@Valid @RequestBody GeoMemberCriteria criteria) throws InterruptedException {
        log.debug("REST request to get GeoMember : {}", criteria);
        return geoMemberService.findByCriteriaImpl(criteria);
    }


    /**
     * {@code DELETE  /geo-members/:id} : delete the "id" geoMember.
     *
     * @param id the id of the geoMemberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-members/{id}")
    public ResponseEntity<Void> deleteGeoMember(@PathVariable String id) {
        log.debug("REST request to delete GeoMember : {}", id);
        geoMemberService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
