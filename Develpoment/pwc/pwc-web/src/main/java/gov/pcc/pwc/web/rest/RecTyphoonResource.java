package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.RecTyphoonRepository;
import gov.pcc.pwc.service.RecTyphoonService;
import gov.pcc.pwc.service.criteria.RecTyphoonCriteria;
import gov.pcc.pwc.service.dto.RecTyphoonDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.RecTyphoonQueryDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.RecTyphoon}.
 */
@RestController
@RequestMapping("/api")
public class RecTyphoonResource {

    private final Logger log = LoggerFactory.getLogger(RecTyphoonResource.class);

    private static final String ENTITY_NAME = "recTyphoon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecTyphoonService recTyphoonService;

    private final RecTyphoonRepository recTyphoonRepository;

    public RecTyphoonResource(RecTyphoonService recTyphoonService, RecTyphoonRepository recTyphoonRepository) {
        this.recTyphoonService = recTyphoonService;
        this.recTyphoonRepository = recTyphoonRepository;
    }

    /**
     * {@code POST  /rec-typhoons} : Create a new recTyphoon.
     *
     * @param recTyphoonDTO the recTyphoonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recTyphoonDTO, or with status {@code 400 (Bad Request)} if the recTyphoon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rec-typhoons")
    public ResponseEntity<RecTyphoonDTO> createRecTyphoon(@Valid @RequestBody RecTyphoonDTO recTyphoonDTO) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to save RecTyphoon : {}", recTyphoonDTO);
        //新增主鍵
        recTyphoonService.beforeInsertPackage(recTyphoonDTO);
        //主鍵已給會牴觸
//        if (recTyphoonDTO.getTyphoonNo() != null) {
//            throw new BadRequestAlertException("A new recTyphoon cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        log.debug("REST request to save RecTyphoon : {}", recTyphoonDTO);
        RecTyphoonDTO result = recTyphoonService.save(recTyphoonDTO);
        return ResponseEntity
            .created(new URI("/api/rec-typhoons/" + result.getTyphoonNo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getTyphoonNo()))
            .body(result);
    }

    /**
     * {@code PUT  /rec-typhoons/:typhoonNo} : Updates an existing recTyphoon.
     *
     * @param typhoonNo the id of the recTyphoonDTO to save.
     * @param recTyphoonDTO the recTyphoonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recTyphoonDTO,
     * or with status {@code 400 (Bad Request)} if the recTyphoonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recTyphoonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rec-typhoons/{typhoonNo}")
    public ResponseEntity<RecTyphoonDTO> updateRecTyphoon(
        @PathVariable(value = "typhoonNo", required = false) final String typhoonNo,
        @Valid @RequestBody RecTyphoonDTO recTyphoonDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RecTyphoon : {}, {}", typhoonNo, recTyphoonDTO);
        if (recTyphoonDTO.getTyphoonNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(typhoonNo, recTyphoonDTO.getTyphoonNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recTyphoonRepository.existsById(typhoonNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RecTyphoonDTO result = recTyphoonService.save(recTyphoonDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recTyphoonDTO.getTyphoonNo()))
            .body(result);
    }

    /**
     * {@code PATCH  /rec-typhoons/:typhoonNo} : Partial updates given fields of an existing recTyphoon, field will ignore if it is null
     *
     * @param typhoonNo the id of the recTyphoonDTO to save.
     * @param recTyphoonDTO the recTyphoonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recTyphoonDTO,
     * or with status {@code 400 (Bad Request)} if the recTyphoonDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recTyphoonDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recTyphoonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rec-typhoons/{typhoonNo}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RecTyphoonDTO> partialUpdateRecTyphoon(
        @PathVariable(value = "typhoonNo", required = false) final String typhoonNo,
        @NotNull @RequestBody RecTyphoonDTO recTyphoonDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RecTyphoon partially : {}, {}", typhoonNo, recTyphoonDTO);
        if (recTyphoonDTO.getTyphoonNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(typhoonNo, recTyphoonDTO.getTyphoonNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recTyphoonRepository.existsById(typhoonNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecTyphoonDTO> result = recTyphoonService.partialUpdate(recTyphoonDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recTyphoonDTO.getTyphoonNo())
        );
    }

    /**
     * {@code GET  /rec-typhoons} : get all the recTyphoons.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recTyphoons in body.
     */
    @GetMapping("/rec-typhoons")
    public List<RecTyphoonDTO> getAllRecTyphoons() {
        log.debug("REST request to get all RecTyphoons");
        return recTyphoonService.findAll();
    }

    /**
     * {@code GET  /rec-typhoons/:id} : get the "id" recTyphoon.
     *
     * @param id the id of the recTyphoonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recTyphoonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rec-typhoons/{id}")
    public ResponseEntity<RecTyphoonDTO> getRecTyphoon(@PathVariable String id) {
        log.debug("REST request to get RecTyphoon : {}", id);
        Optional<RecTyphoonDTO> recTyphoonDTO = recTyphoonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recTyphoonDTO);
    }

    //風災查詢
    @PostMapping("/rec-typhoons/criteria-jpa")
    public Page<RecTyphoonQueryDTO> getRecTyphoonByCriteria(@Valid @RequestBody RecTyphoonCriteria criteria) throws InterruptedException {
        log.debug("REST request to get RecTyphoon : {}",criteria);
        return recTyphoonService.findByCriteria(criteria);
    }


    /**
     * {@code DELETE  /rec-typhoons/:id} : delete the "id" recTyphoon.
     *
     * @param id the id of the recTyphoonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rec-typhoons/{id}")
    public ResponseEntity<Void> deleteRecTyphoon(@PathVariable String id) {
        log.debug("REST request to delete RecTyphoon : {}", id);
        recTyphoonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
