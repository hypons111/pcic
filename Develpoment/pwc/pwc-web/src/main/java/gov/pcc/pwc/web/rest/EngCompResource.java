package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.EngCompRepository;
import gov.pcc.pwc.service.EngCompService;
import gov.pcc.pwc.service.criteria.Eng0822R1DTOCriteria;
import gov.pcc.pwc.service.dto.Eng0822R1DTO;
import gov.pcc.pwc.service.dto.EngCompDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngComp}.
 */
@RestController
@RequestMapping("/api")
public class EngCompResource {

    private final Logger log = LoggerFactory.getLogger(EngCompResource.class);

    private static final String ENTITY_NAME = "engComp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngCompService engCompService;

    private final EngCompRepository engCompRepository;


    public EngCompResource(EngCompService engCompService,
                           EngCompRepository engCompRepository) {
        this.engCompService = engCompService;
        this.engCompRepository = engCompRepository;
    }

    // 查詢所有顧問公司聯絡資訊
    @PostMapping("/service/eng-eng0821r1")
    public Page<Eng0822R1DTO> findeng0821(@Valid @RequestBody Eng0822R1DTOCriteria criteria) throws InterruptedException {
        log.debug("Rest request to findeng0821");
        return engCompService.findEng0821(criteria);
    }

    // 業管新增公會
    @PostMapping(path = "/eng-eng0502-c1", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Eng0822R1DTO> createEng0502(
        @RequestPart("form") Eng0822R1DTO eng0822R1DTO
    ) throws URISyntaxException, IOException, TypeVariableNotMatchException {
        log.debug("createEng0502" + eng0822R1DTO);
        Eng0822R1DTO eng0822R1DTO1 = engCompService.createEng0502(eng0822R1DTO);
        return new ResponseEntity<Eng0822R1DTO>(eng0822R1DTO1, HttpStatus.OK);
    }

    // 業管編輯公會
    @PutMapping(path = "/eng-eng0502-u1", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Eng0822R1DTO> updateEng0502(
        @RequestPart("form") Eng0822R1DTO eng0822R1DTO
    ) throws URISyntaxException, IOException, TypeVariableNotMatchException  {
        log.debug("resourceUpdateEng0502" + eng0822R1DTO);
        try {
            Eng0822R1DTO eng0822R1DTO1 = engCompService.updateEng0502(eng0822R1DTO);
            return new ResponseEntity<Eng0822R1DTO>(eng0822R1DTO1, HttpStatus.OK);
        } catch (TypeVariableNotMatchException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用engCompId檢視公會資料
    @GetMapping("/eng-eng0502-r2/{id}")
    public ResponseEntity<Eng0822R1DTO> viewEng0502(@PathVariable String id) throws InterruptedException {
        log.debug("ResViewEng0502: " + id);
        try {
            Eng0822R1DTO dto = engCompService.viewEng0502(id);
            if(dto == null) {
                return new ResponseEntity<Eng0822R1DTO>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Eng0822R1DTO>(dto, HttpStatus.OK); // 正確這邊回傳
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Eng0822R1DTO>(HttpStatus.NOT_FOUND);
        }
    }

    // 條件查詢公會
    @PostMapping("/eng-eng0502r1")
    public Page<Eng0822R1DTO> findEng0502(@Valid @RequestBody Eng0822R1DTOCriteria criteria) throws InterruptedException {
        log.debug("Rest request to eng-eng0502r1");
        return engCompService.findEng0502(criteria);
    }

    // 編輯自己公會資料
    @PutMapping(path = "/eng-eng0506-u1", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Eng0822R1DTO> updateEng0506(
        @RequestPart("form") Eng0822R1DTO eng0822R1DTO
    ) throws URISyntaxException, IOException, TypeVariableNotMatchException  {
        log.debug("resourceUpdateEng0506" + eng0822R1DTO);
        try {
            Eng0822R1DTO eng0822R1DTO1 = engCompService.updateEng0506(eng0822R1DTO);
            return new ResponseEntity<Eng0822R1DTO>(eng0822R1DTO1, HttpStatus.OK);
        } catch (TypeVariableNotMatchException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 檢視自己公會資料
    @GetMapping("/eng-eng0506-r1/{id}")
    public ResponseEntity<Eng0822R1DTO> viewEng0506(@PathVariable String id) throws InterruptedException {
        log.debug("ResViewEng0506: " + id);
        try {
            Eng0822R1DTO dto = engCompService.viewEng0506(id);
            if(dto == null) {
                return new ResponseEntity<Eng0822R1DTO>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Eng0822R1DTO>(dto, HttpStatus.OK); // 正確這邊回傳
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Eng0822R1DTO>(HttpStatus.NOT_FOUND);
        }
    }

    // 查詢所有顧問公司聯絡資訊
    @PostMapping("/service/eng-eng0822r1")
    public Page<Eng0822R1DTO> findConnection(@Valid @RequestBody Eng0822R1DTOCriteria criteria) throws InterruptedException {
        log.debug("Rest request to eng-conection");
        return engCompService.findEng0822R1(criteria);
    }

    // 用名稱查詢區域主管機關
    @PostMapping("/eng-eng0703r1")
    public Page<Eng0822R1DTO> findEng0703(@Valid @RequestBody Eng0822R1DTOCriteria criteria) throws InterruptedException {
        log.debug("Rest request to eng-eng0703r1");
        return engCompService.findEng0703(criteria);
    }

    // 檢視該主管機關資料
    @GetMapping("/eng-eng0703-r2/{id}")
    public ResponseEntity<Eng0822R1DTO> viewEng0703(@PathVariable String id) throws InterruptedException {
        try {
            Eng0822R1DTO eng0822R1DTO = engCompService.viewEng0703(id);
            if(eng0822R1DTO == null) {
                return new ResponseEntity<Eng0822R1DTO>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Eng0822R1DTO>(eng0822R1DTO, HttpStatus.OK); // 正確這邊回傳
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Eng0822R1DTO>(HttpStatus.NOT_FOUND);
        }
    }

    // 編輯主管機關資料
    @PutMapping(path = "/eng-eng0703-u1", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Eng0822R1DTO> updateEng0703(
        @RequestPart("form") Eng0822R1DTO eng0822R1DTO
        ) throws URISyntaxException, IOException, TypeVariableNotMatchException  {
        log.debug("resourceUpdateEng0703" + eng0822R1DTO);
        try {
            Eng0822R1DTO eng0822R1DTO1 = engCompService.updateEng0703(eng0822R1DTO);
            return new ResponseEntity<Eng0822R1DTO>(eng0822R1DTO1, HttpStatus.OK);
        } catch (TypeVariableNotMatchException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 新增主管機關資料
    @PostMapping(path = "/eng-eng0703-c1", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Eng0822R1DTO> createEng0703(
        @RequestPart("form") Eng0822R1DTO eng0822R1DTO
    ) throws URISyntaxException, IOException, TypeVariableNotMatchException {
        log.debug("createEng0703" + eng0822R1DTO);
        Eng0822R1DTO eng0822R1DTO1 = engCompService.createEng0703(eng0822R1DTO);
        return new ResponseEntity<Eng0822R1DTO>(eng0822R1DTO1, HttpStatus.OK);
    }

    // jhipster建的
    /**
     * {@code POST  /eng-comps} : Create a new engComp.
     *
     * @param engCompDTO the engCompDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engCompDTO, or with status {@code 400 (Bad Request)} if the engComp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-comps")
    public ResponseEntity<EngCompDTO> createEngComp(@Valid @RequestBody EngCompDTO engCompDTO) throws URISyntaxException {
        log.debug("REST request to save EngComp : {}", engCompDTO);
        if (engCompDTO.getEngCompId() != null) {
            throw new BadRequestAlertException("A new engComp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngCompDTO result = engCompService.save(engCompDTO);
        return ResponseEntity
            .created(new URI("/api/eng-comps/" + result.getEngCompId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngCompId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-comps/:engCompId} : Updates an existing engComp.
     *
     * @param engCompId the id of the engCompDTO to save.
     * @param engCompDTO the engCompDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engCompDTO,
     * or with status {@code 400 (Bad Request)} if the engCompDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engCompDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-comps/{engCompId}")
    public ResponseEntity<EngCompDTO> updateEngComp(
        @PathVariable(value = "engCompId", required = false) final String engCompId,
        @Valid @RequestBody EngCompDTO engCompDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EngComp : {}, {}", engCompId, engCompDTO);
        if (engCompDTO.getEngCompId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engCompId, engCompDTO.getEngCompId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engCompRepository.existsById(engCompId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngCompDTO result = engCompService.save(engCompDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engCompDTO.getEngCompId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-comps/:engCompId} : Partial updates given fields of an existing engComp, field will ignore if it is null
     *
     * @param engCompId the id of the engCompDTO to save.
     * @param engCompDTO the engCompDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engCompDTO,
     * or with status {@code 400 (Bad Request)} if the engCompDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engCompDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engCompDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-comps/{engCompId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngCompDTO> partialUpdateEngComp(
        @PathVariable(value = "engCompId", required = false) final String engCompId,
        @NotNull @RequestBody EngCompDTO engCompDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngComp partially : {}, {}", engCompId, engCompDTO);
        if (engCompDTO.getEngCompId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engCompId, engCompDTO.getEngCompId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engCompRepository.existsById(engCompId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngCompDTO> result = engCompService.partialUpdate(engCompDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engCompDTO.getEngCompId())
        );
    }

    /**
     * {@code GET  /eng-comps} : get all the engComps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engComps in body.
     */
    @GetMapping("/eng-comps")
    public List<EngCompDTO> getAllEngComps() {
        log.debug("REST request to get all EngComps");
        return engCompService.findAll();
    }

    /**
     * {@code GET  /eng-comps/:id} : get the "id" engComp.
     *
     * @param id the id of the engCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engCompDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-comps/{id}")
    public ResponseEntity<EngCompDTO> getEngComp(@PathVariable String id) {
        log.debug("REST request to get EngComp : {}", id);
        Optional<EngCompDTO> engCompDTO = engCompService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engCompDTO);
    }

    /**
     * {@code DELETE  /eng-comps/:id} : delete the "id" engComp.
     *
     * @param id the id of the engCompDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-comps/{id}")
    public ResponseEntity<Void> deleteEngComp(@PathVariable String id) {
        log.debug("REST request to delete EngComp : {}", id);
        engCompService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

}
