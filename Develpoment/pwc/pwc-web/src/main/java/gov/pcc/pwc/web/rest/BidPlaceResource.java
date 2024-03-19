package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPlace;
import gov.pcc.pwc.repository.BidPlaceRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.BidPlaceService;
import gov.pcc.pwc.service.criteria.BidPlaceCriteria;
import gov.pcc.pwc.service.dto.BidPlaceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPlace}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BidPlaceResource {

    private final Logger log = LoggerFactory.getLogger(BidPlaceResource.class);

    private static final String ENTITY_NAME = "bidPlace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPlaceService bidPlaceService;

    private final BidPlaceRepository bidPlaceRepository;

    public BidPlaceResource(BidPlaceService bidPlaceService, BidPlaceRepository bidPlaceRepository) {
        this.bidPlaceService = bidPlaceService;
        this.bidPlaceRepository = bidPlaceRepository;
    }

    /**
     * {@code POST  /bid-places} : Create a new bidPlace.
     *
     * @param bidPlace the bidPlace to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPlace, or with status {@code 400 (Bad Request)} if the bidPlace has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-places")
    public ResponseEntity<BidPlace> createBidPlace(@Valid @RequestBody BidPlace bidPlace) throws URISyntaxException {
        log.debug("REST request to save BidPlace : {}", bidPlace);
        if (bidPlace.getPlaceCode() != null) {
            throw new BadRequestAlertException("A new bidPlace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPlace result = bidPlaceRepository.save(bidPlace);
        return ResponseEntity
            .created(new URI("/api/bid-places/" + result.getPlaceCode()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getPlaceCode()))
            .body(result);
    }

    /**
     * {@code Get  /bid-places/map-info/:placeCode} : get map-info by placeCode.
     *
     * @param placeCode the placeCode.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidPlaceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-places/map-info/{placeCode}")
    public List<BidPlaceDTO> getMapInfoByPlaceCode(@PathVariable String placeCode) {
        log.debug("REST request to get BidPlaceDTO : {}", placeCode);
        return bidPlaceService.findMapInfoByPlaceCode(placeCode);
    }

    /**
     * {@code Post  /bid-places/criteria : get project-count by placeCode.
     *
     * @param criteria the criteria of the BidPlaceCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidPlaceDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-places/criteria")
    public Page<BidPlaceDTO> getProjectCountByCriteria(@Valid @RequestBody BidPlaceCriteria criteria) {
        log.debug("REST request to get BidPlaceDTO : {}", criteria);
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "placeCode" });
        }
        return bidPlaceService.findProjectCountByCriteria(criteria);
    }

    /**
     * {@code PUT  /bid-places/:placeCode} : Updates an existing bidPlace.
     *
     * @param placeCode the id of the bidPlace to save.
     * @param bidPlace the bidPlace to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPlace,
     * or with status {@code 400 (Bad Request)} if the bidPlace is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPlace couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-places/{placeCode}")
    public ResponseEntity<BidPlace> updateBidPlace(
        @PathVariable(value = "placeCode", required = false) final String placeCode,
        @Valid @RequestBody BidPlace bidPlace
    ) throws URISyntaxException {
        log.debug("REST request to update BidPlace : {}, {}", placeCode, bidPlace);
        if (bidPlace.getPlaceCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(placeCode, bidPlace.getPlaceCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPlaceRepository.existsById(placeCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPlace result = bidPlaceRepository.save(bidPlace);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPlace.getPlaceCode()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-places/:placeCode} : Partial updates given fields of an existing bidPlace, field will ignore if it is null
     *
     * @param placeCode the id of the bidPlace to save.
     * @param bidPlace the bidPlace to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPlace,
     * or with status {@code 400 (Bad Request)} if the bidPlace is not valid,
     * or with status {@code 404 (Not Found)} if the bidPlace is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPlace couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-places/{placeCode}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPlace> partialUpdateBidPlace(
        @PathVariable(value = "placeCode", required = false) final String placeCode,
        @NotNull @RequestBody BidPlace bidPlace
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPlace partially : {}, {}", placeCode, bidPlace);
        if (bidPlace.getPlaceCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(placeCode, bidPlace.getPlaceCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPlaceRepository.existsById(placeCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPlace> result = bidPlaceRepository
            .findById(bidPlace.getPlaceCode())
            .map(existingBidPlace -> {
                if (bidPlace.getPlaceName() != null) {
                    existingBidPlace.setPlaceName(bidPlace.getPlaceName());
                }
                if (bidPlace.getShape() != null) {
                    existingBidPlace.setShape(bidPlace.getShape());
                }
                if (bidPlace.getCoords() != null) {
                    existingBidPlace.setCoords(bidPlace.getCoords());
                }
                if (bidPlace.getMapsrc() != null) {
                    existingBidPlace.setMapsrc(bidPlace.getMapsrc());
                }
                if (bidPlace.getWid() != null) {
                    existingBidPlace.setWid(bidPlace.getWid());
                }
                if (bidPlace.getHei() != null) {
                    existingBidPlace.setHei(bidPlace.getHei());
                }
                if (bidPlace.getTm2x() != null) {
                    existingBidPlace.setTm2x(bidPlace.getTm2x());
                }
                if (bidPlace.getTm2y() != null) {
                    existingBidPlace.setTm2y(bidPlace.getTm2y());
                }
                if (bidPlace.getTm2xr() != null) {
                    existingBidPlace.setTm2xr(bidPlace.getTm2xr());
                }
                if (bidPlace.getTm2yr() != null) {
                    existingBidPlace.setTm2yr(bidPlace.getTm2yr());
                }
                if (bidPlace.getOrd() != null) {
                    existingBidPlace.setOrd(bidPlace.getOrd());
                }
                if (bidPlace.getUcnt() != null) {
                    existingBidPlace.setUcnt(bidPlace.getUcnt());
                }
                if (bidPlace.getpKind() != null) {
                    existingBidPlace.setpKind(bidPlace.getpKind());
                }
                if (bidPlace.getCreateDate() != null) {
                    existingBidPlace.setCreateDate(bidPlace.getCreateDate());
                }
                if (bidPlace.getCreateUser() != null) {
                    existingBidPlace.setCreateUser(bidPlace.getCreateUser());
                }
                if (bidPlace.getUpdateDate() != null) {
                    existingBidPlace.setUpdateDate(bidPlace.getUpdateDate());
                }
                if (bidPlace.getUpdateUser() != null) {
                    existingBidPlace.setUpdateUser(bidPlace.getUpdateUser());
                }

                return existingBidPlace;
            })
            .map(bidPlaceRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPlace.getPlaceCode())
        );
    }

    /**
     * {@code GET  /bid-places} : get all the bidPlaces.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPlaces in body.
     */
    @GetMapping("/bid-places")
    public List<BidPlace> getAllBidPlaces() {
        log.debug("REST request to get all BidPlaces");
        return bidPlaceRepository.findAll();
    }

    /**
     * {@code GET  /bid-places/:id} : get the "id" bidPlace.
     *
     * @param id the id of the bidPlace to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPlace, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-places/{id}")
    public ResponseEntity<BidPlace> getBidPlace(@PathVariable String id) {
        log.debug("REST request to get BidPlace : {}", id);
        Optional<BidPlace> bidPlace = bidPlaceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bidPlace);
    }

    /**
     * {@code DELETE  /bid-places/:id} : delete the "id" bidPlace.
     *
     * @param id the id of the bidPlace to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-places/{id}")
    public ResponseEntity<Void> deleteBidPlace(@PathVariable String id) {
        log.debug("REST request to delete BidPlace : {}", id);
        bidPlaceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
