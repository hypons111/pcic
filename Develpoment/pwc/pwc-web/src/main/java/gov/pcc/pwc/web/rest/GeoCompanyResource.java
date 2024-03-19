package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.GeoCompanyRepository;
import gov.pcc.pwc.service.GeoCompanyService;
import gov.pcc.pwc.service.dto.GeoCompanyDTO;
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
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.GeoCompany}.
 */
@RestController
@RequestMapping("/api")
public class GeoCompanyResource {

    private final Logger log = LoggerFactory.getLogger(GeoCompanyResource.class);

    private static final String ENTITY_NAME = "geoCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoCompanyService geoCompanyService;

    private final GeoCompanyRepository geoCompanyRepository;

    public GeoCompanyResource(GeoCompanyService geoCompanyService, GeoCompanyRepository geoCompanyRepository) {
        this.geoCompanyService = geoCompanyService;
        this.geoCompanyRepository = geoCompanyRepository;
    }

    /**
     * {@code POST  /geo-companies} : Create a new geoCompany.
     *
     * @param geoCompanyDTO the geoCompanyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoCompanyDTO, or with status {@code 400 (Bad Request)} if the geoCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-companies")
    public ResponseEntity<GeoCompanyDTO> createGeoCompany(@Valid @RequestBody GeoCompanyDTO geoCompanyDTO) throws URISyntaxException {
        log.debug("REST request to save GeoCompany : {}", geoCompanyDTO);
        if (geoCompanyDTO.getGeoCompanyId() != null) {
            throw new BadRequestAlertException("A new geoCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoCompanyDTO result = geoCompanyService.save(geoCompanyDTO);
        return ResponseEntity
            .created(new URI("/api/geo-companies/" + result.getGeoCompanyId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getGeoCompanyId()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-companies/:geoCompanyId} : Updates an existing geoCompany.
     *
     * @param geoCompanyId the id of the geoCompanyDTO to save.
     * @param geoCompanyDTO the geoCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the geoCompanyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-companies/{geoCompanyId}")
    public ResponseEntity<GeoCompanyDTO> updateGeoCompany(
        @PathVariable(value = "geoCompanyId", required = false) final String geoCompanyId,
        @Valid @RequestBody GeoCompanyDTO geoCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GeoCompany : {}, {}", geoCompanyId, geoCompanyDTO);
        if (geoCompanyDTO.getGeoCompanyId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(geoCompanyId, geoCompanyDTO.getGeoCompanyId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!geoCompanyRepository.existsById(geoCompanyId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GeoCompanyDTO result = geoCompanyService.save(geoCompanyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoCompanyDTO.getGeoCompanyId()))
            .body(result);
    }

    /**
     * {@code PATCH  /geo-companies/:geoCompanyId} : Partial updates given fields of an existing geoCompany, field will ignore if it is null
     *
     * @param geoCompanyId the id of the geoCompanyDTO to save.
     * @param geoCompanyDTO the geoCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the geoCompanyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the geoCompanyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the geoCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/geo-companies/{geoCompanyId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GeoCompanyDTO> partialUpdateGeoCompany(
        @PathVariable(value = "geoCompanyId", required = false) final String geoCompanyId,
        @NotNull @RequestBody GeoCompanyDTO geoCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GeoCompany partially : {}, {}", geoCompanyId, geoCompanyDTO);
        if (geoCompanyDTO.getGeoCompanyId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(geoCompanyId, geoCompanyDTO.getGeoCompanyId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!geoCompanyRepository.existsById(geoCompanyId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GeoCompanyDTO> result = geoCompanyService.partialUpdate(geoCompanyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoCompanyDTO.getGeoCompanyId())
        );
    }

    /**
     * {@code GET  /geo-companies} : get all the geoCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoCompanies in body.
     */
    @GetMapping("/geo-companies")
    public List<GeoCompanyDTO> getAllGeoCompanies() {
        log.debug("REST request to get all GeoCompanies");
        return geoCompanyService.findAll();
    }

    /**
     * {@code GET  /geo-companies/:id} : get the "id" geoCompany.
     *
     * @param id the id of the geoCompanyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoCompanyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-companies/{id}")
    public ResponseEntity<GeoCompanyDTO> getGeoCompany(@PathVariable String id) {
        log.debug("REST request to get GeoCompany : {}", id);
        Optional<GeoCompanyDTO> geoCompanyDTO = geoCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoCompanyDTO);
    }

    /**
     * {@code DELETE  /geo-companies/:id} : delete the "id" geoCompany.
     *
     * @param id the id of the geoCompanyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-companies/{id}")
    public ResponseEntity<Void> deleteGeoCompany(@PathVariable String id) {
        log.debug("REST request to delete GeoCompany : {}", id);
        geoCompanyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
