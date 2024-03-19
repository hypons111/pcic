package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.RecAreaRepository;
import gov.pcc.pwc.service.RecAreaService;
import gov.pcc.pwc.service.criteria.RecAreaCriteria;
import gov.pcc.pwc.service.dto.RecAreaDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.RecArea}.
 */
@RestController
@RequestMapping("/api")
public class RecAreaResource {

    private final Logger log = LoggerFactory.getLogger(RecAreaResource.class);

    private static final String ENTITY_NAME = "recArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecAreaService recAreaService;

    private final RecAreaRepository recAreaRepository;

    public RecAreaResource(RecAreaService recAreaService, RecAreaRepository recAreaRepository) {
        this.recAreaService = recAreaService;
        this.recAreaRepository = recAreaRepository;
    }

    /**
     * {@code POST  /rec-area} : Create a new recArea.
     *
     * @param recAreaDTO the recAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recAreaDTO, or with status {@code 400 (Bad Request)} if the recArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rec-area")
    public ResponseEntity<RecAreaDTO> createRecArea(@Valid @RequestBody RecAreaDTO recAreaDTO) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to save RecArea : {}", recAreaDTO);
        //新增主鍵
        recAreaService.beforeInsertPackage(recAreaDTO);

        log.debug("REST request to save RecArea : {}", recAreaDTO);
        RecAreaDTO result = recAreaService.save(recAreaDTO);
        return ResponseEntity
            .created(new URI("/api/rec-area/" + result.getAreaNo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getAreaNo()))
            .body(result);
    }

    /**
     * {@code PUT  /rec-area/:areaNo} : Updates an existing recArea.
     *
     * @param areaNo the id of the recAreaDTO to save.
     * @param recAreaDTO the recAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recAreaDTO,
     * or with status {@code 400 (Bad Request)} if the recAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rec-area/{areaNo}")
    public ResponseEntity<RecAreaDTO> updateRecArea(
        @PathVariable(value = "areaNo", required = false) final String areaNo,
        @Valid @RequestBody RecAreaDTO recAreaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RecArea : {}, {}", areaNo, recAreaDTO);
        if (recAreaDTO.getAreaNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(areaNo, recAreaDTO.getAreaNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recAreaRepository.existsById(areaNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RecAreaDTO result = recAreaService.save(recAreaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recAreaDTO.getAreaNo()))
            .body(result);
    }

    /**
     * {@code PATCH  /rec-area/:areaNo} : Partial updates given fields of an existing recArea, field will ignore if it is null
     *
     * @param areaNo the id of the recAreaDTO to save.
     * @param recAreaDTO the recAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recAreaDTO,
     * or with status {@code 400 (Bad Request)} if the recAreaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recAreaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rec-area/{areaNo}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RecAreaDTO> partialUpdateRecArea(
        @PathVariable(value = "areaNo", required = false) final String areaNo,
        @NotNull @RequestBody RecAreaDTO recAreaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RecArea partially : {}, {}", areaNo, recAreaDTO);
        if (recAreaDTO.getAreaNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(areaNo, recAreaDTO.getAreaNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recAreaRepository.existsById(areaNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecAreaDTO> result = recAreaService.partialUpdate(recAreaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recAreaDTO.getAreaNo())
        );
    }

    /**
     * {@code GET  /rec-area} : get all the recArea.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recArea in body.
     */
    @GetMapping("/rec-area")
    public List<RecAreaDTO> getAllRecArea() {
        log.debug("REST request to get all RecArea");
        return recAreaService.findAll();
    }

    /**
     * {@code GET  /rec-area/:id} : get the "id" recArea.
     *
     * @param id the id of the recAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rec-area/{id}")
    public ResponseEntity<RecAreaDTO> getRecArea(@PathVariable String id) {
        log.debug("REST request to get RecArea : {}", id);
        Optional<RecAreaDTO> recAreaDTO = recAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recAreaDTO);
    }

    //災損地區查詢
    @PostMapping("/rec-area/criteria-jpa")
    public Page<RecAreaDTO> getRecAreaByCriteria(@Valid @RequestBody RecAreaCriteria criteria) throws InterruptedException {
        log.debug("REST request to get RecArea : {}",criteria);
        return recAreaService.findByCriteria(criteria);
    }

    @PutMapping("/rec-area/upload")
    public void uploadRecFile(@RequestBody MultipartFile file) throws IOException {
        file.transferTo(new File("C:\\picFile\\" + file.getOriginalFilename()));
    }

    /**
     * {@code DELETE  /rec-area/:id} : delete the "id" recArea.
     *
     * @param id the id of the recAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rec-area/{id}")
    public ResponseEntity<Void> deleteRecArea(@PathVariable String id) {
        log.debug("REST request to delete RecArea : {}", id);
        recAreaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
