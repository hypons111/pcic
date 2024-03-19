package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.RecWorkItemRepository;
import gov.pcc.pwc.service.RecWorkItemService;
import gov.pcc.pwc.service.criteria.RecWorkItemCriteria;
import gov.pcc.pwc.service.dto.RecWorkItemDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.RecWorkItem}.
 */
@RestController
@RequestMapping("/api")
public class RecWorkItemResource {

    private final Logger log = LoggerFactory.getLogger(RecWorkItemResource.class);

    private static final String ENTITY_NAME = "recWorkItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecWorkItemService recWorkItemService;

    private final RecWorkItemRepository recWorkItemRepository;

    public RecWorkItemResource(RecWorkItemService recWorkItemService, RecWorkItemRepository recWorkItemRepository) {
        this.recWorkItemService = recWorkItemService;
        this.recWorkItemRepository = recWorkItemRepository;
    }

    /**
     * {@code POST  /rec-work-item} : Create a new recWorkItem.
     *
     * @param recWorkItemDTO the recWorkItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recWorkItemDTO, or with status {@code 400 (Bad Request)} if the recWorkItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rec-work-item")
    public ResponseEntity<RecWorkItemDTO> createRecWorkItem(@Valid @RequestBody RecWorkItemDTO recWorkItemDTO) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to save RecWorkItem : {}", recWorkItemDTO);
        //新增主鍵
        recWorkItemService.beforeInsertPackage(recWorkItemDTO);

        log.debug("REST request to save RecWorkItem : {}", recWorkItemDTO);
        RecWorkItemDTO result = recWorkItemService.save(recWorkItemDTO);
        return ResponseEntity
            .created(new URI("/api/rec-work-item/" + result.getWorkItemNo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getWorkItemNo()))
            .body(result);
    }

    /**
     * {@code PUT  /rec-work-item/:workItemNo} : Updates an existing recWorkItem.
     *
     * @param workItemNo the id of the recWorkItemDTO to save.
     * @param recWorkItemDTO the recWorkItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recWorkItemDTO,
     * or with status {@code 400 (Bad Request)} if the recWorkItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recWorkItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rec-work-item/{workItemNo}")
    public ResponseEntity<RecWorkItemDTO> updateRecWorkItem(
        @PathVariable(value = "workItemNo", required = false) final String workItemNo,
        @Valid @RequestBody RecWorkItemDTO recWorkItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RecWorkItem : {}, {}", workItemNo, recWorkItemDTO);
        if (recWorkItemDTO.getWorkItemNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(workItemNo, recWorkItemDTO.getWorkItemNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recWorkItemRepository.existsById(workItemNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RecWorkItemDTO result = recWorkItemService.save(recWorkItemDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recWorkItemDTO.getWorkItemNo()))
            .body(result);
    }

    /**
     * {@code PATCH  /rec-work-item/:workItemNo} : Partial updates given fields of an existing recWorkItem, field will ignore if it is null
     *
     * @param workItemNo the id of the recWorkItemDTO to save.
     * @param recWorkItemDTO the recWorkItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recWorkItemDTO,
     * or with status {@code 400 (Bad Request)} if the recWorkItemDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recWorkItemDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recWorkItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rec-work-item/{workItemNo}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RecWorkItemDTO> partialUpdateRecWorkItem(
        @PathVariable(value = "workItemNo", required = false) final String workItemNo,
        @NotNull @RequestBody RecWorkItemDTO recWorkItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RecWorkItem partially : {}, {}", workItemNo, recWorkItemDTO);
        if (recWorkItemDTO.getWorkItemNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(workItemNo, recWorkItemDTO.getWorkItemNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recWorkItemRepository.existsById(workItemNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecWorkItemDTO> result = recWorkItemService.partialUpdate(recWorkItemDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recWorkItemDTO.getWorkItemNo())
        );
    }

    /**
     * {@code GET  /rec-work-item} : get all the recWorkItem.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recWorkItem in body.
     */
    @GetMapping("/rec-work-item")
    public List<RecWorkItemDTO> getAllRecWorkItem() {
        log.debug("REST request to get all RecWorkItem");
        return recWorkItemService.findAll();
    }

    /**
     * {@code GET  /rec-work-item/:id} : get the "id" recWorkItem.
     *
     * @param id the id of the recWorkItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recWorkItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rec-work-item/{id}")
    public ResponseEntity<RecWorkItemDTO> getRecWorkItem(@PathVariable String id) {
        log.debug("REST request to get RecWorkItem : {}", id);
        Optional<RecWorkItemDTO> recWorkItemDTO = recWorkItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recWorkItemDTO);
    }

    //災損地區查詢
    @PostMapping("/rec-work-item/criteria-jpa")
    public Page<RecWorkItemDTO> getRecWorkItemByCriteria(@Valid @RequestBody RecWorkItemCriteria criteria) throws InterruptedException {
        log.debug("REST request to get RecWorkItem : {}",criteria);
        return recWorkItemService.findByCriteria(criteria);
    }

    /**
     * {@code DELETE  /rec-work-item/:id} : delete the "id" recWorkItem.
     *
     * @param id the id of the recWorkItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rec-work-item/{id}")
    public ResponseEntity<Void> deleteRecWorkItem(@PathVariable String id) {
        log.debug("REST request to delete RecWorkItem : {}", id);
        recWorkItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
