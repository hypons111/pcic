package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.AdmAgentRepository;
import gov.pcc.pwc.repository.ProInnovativeProductRepository;
import gov.pcc.pwc.service.AdmAgentService;
import gov.pcc.pwc.service.ProInnovativeProductService;

import gov.pcc.pwc.service.dto.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.criteria.ProInnovativeProductProUserCriteria;
import gov.pcc.pwc.service.dto.ProInnovativeProductDTO;
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

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.ProInnovativeProduct}.
 */
@RestController
@RequestMapping("/api")
public class ProInnovativeProductResource {

    private final Logger log = LoggerFactory.getLogger(ProInnovativeProductResource.class);

    private static final String ENTITY_NAME = "proInnovativeProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProInnovativeProductService proInnovativeProductService;

    private final ProInnovativeProductRepository proInnovativeProductRepository;

    private final AdmAgentService admAgentService;

    private final AdmAgentRepository admAgentRepository;



    public ProInnovativeProductResource(
        ProInnovativeProductService proInnovativeProductService,
        ProInnovativeProductRepository proInnovativeProductRepository,
        AdmAgentService admAgentService, AdmAgentRepository admAgentRepository) {
        this.proInnovativeProductService = proInnovativeProductService;
        this.proInnovativeProductRepository = proInnovativeProductRepository;
        this.admAgentService = admAgentService;
        this.admAgentRepository = admAgentRepository;
    }


    //新增方法
    @PostMapping("/pro-innovative-products/addProIP")
    public ResponseEntity<ProInnovativeProductSumSearchDetailDTO> createProInnovativeProductAddDetailDTO(
        @Valid @RequestBody ProInnovativeProductSumSearchDetailDTO proInnovativeProductSumSearchDetailDTO
    ) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to save createProInnovativeProductAddDetailDTO : {}", proInnovativeProductSumSearchDetailDTO);

        ProInnovativeProductSumSearchDetailDTO result = proInnovativeProductService.save(proInnovativeProductSumSearchDetailDTO);
        return ResponseEntity
            .created(new URI("/api/pro-innovative-products/addProIP" + result.getProInnovativeProductMainDTO().getProInnovativeProductId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProInnovativeProductMainDTO().getProInnovativeProductId()))
            .body(result);

    }

    @PutMapping("/adm-files/proUpload")
    public void  proUploadAdmFile(
        @RequestPart("form") ProInnovativeProductMainDTO proInnovativeProductMainDTO,
        @RequestPart(name = "picFile", required = false) MultipartFile picFile,
        @RequestPart(name = "recomPic_college", required = false) MultipartFile recomPicCollege,
        @RequestPart(name = "recomPic_research", required = false) MultipartFile recomPicResearch,
        @RequestPart(name = "recomPic_academy", required = false) MultipartFile recomPicAcademy,
        @RequestPart(name = "recomPic_guild", required = false) MultipartFile recomPicGuild,
        @RequestPart(name = "recomPic_association", required = false) MultipartFile recomPicAssociation
        ) throws  IOException, TypeVariableNotMatchException {

        Map<String, MultipartFile> appendixFilesMap = new HashMap<>();
        appendixFilesMap.put("college",recomPicCollege);
        appendixFilesMap.put("research",recomPicResearch);
        appendixFilesMap.put("academy",recomPicAcademy);
        appendixFilesMap.put("guild",recomPicGuild);
        appendixFilesMap.put("association",recomPicAssociation);

        proInnovativeProductService.saveFile(proInnovativeProductMainDTO,picFile,appendixFilesMap);
    }


    /**
     * {@code PUT  /pro-innovative-products/:proInnovativeProductId} : Updates an existing proInnovativeProduct.
     *
     * @param proInnovativeProductId the id of the proInnovativeProductDTO to save.
     * @param proInnovativeProductDTO the proInnovativeProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proInnovativeProductDTO,
     * or with status {@code 400 (Bad Request)} if the proInnovativeProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proInnovativeProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-innovative-product/accept")
    public ResponseEntity<ProInnovativeProductDTO> updateProInnovativeProduct(
        @PathVariable(value = "proInnovativeProductId", required = false) final String proInnovativeProductId,
        @Valid @RequestBody ProInnovativeProductDTO proInnovativeProductDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProInnovativeProduct : {}, {}", proInnovativeProductId, proInnovativeProductDTO);
        if (proInnovativeProductDTO.getProInnovativeProductId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInnovativeProductId, proInnovativeProductDTO.getProInnovativeProductId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proInnovativeProductRepository.existsById(proInnovativeProductId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        // TODO 為了測試先改成null,之後還原
//        ProInnovativeProductDTO result = proInnovativeProductService.save(proInnovativeProductDTO);
        ProInnovativeProductDTO result = null;
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proInnovativeProductDTO.getProInnovativeProductId())
            )
            .body(result);
    }


    /**
     * {@code PATCH  /pro-innovative-products/:proInnovativeProductId} : Partial updates given fields of an existing proInnovativeProduct, field will ignore if it is null
     *
     * @param proInnovativeProductId the id of the proInnovativeProductDTO to save.
     * @param proInnovativeProductDTO the proInnovativeProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proInnovativeProductDTO,
     * or with status {@code 400 (Bad Request)} if the proInnovativeProductDTO is not valid,
     * or with status {@code 404 (Not Found)} if the proInnovativeProductDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the proInnovativeProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/pro-innovative-products/{proInnovativeProductId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ProInnovativeProductDTO> partialUpdateProInnovativeProduct(
        @PathVariable(value = "proInnovativeProductId", required = false) final String proInnovativeProductId,
        @NotNull @RequestBody ProInnovativeProductDTO proInnovativeProductDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ProInnovativeProduct partially : {}, {}",
            proInnovativeProductId,
            proInnovativeProductDTO
        );
        if (proInnovativeProductDTO.getProInnovativeProductId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInnovativeProductId, proInnovativeProductDTO.getProInnovativeProductId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proInnovativeProductRepository.existsById(proInnovativeProductId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProInnovativeProductDTO> result = proInnovativeProductService.partialUpdate(proInnovativeProductDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proInnovativeProductDTO.getProInnovativeProductId())
        );
    }

    /**
     * {@code GET  /pro-innovative-products} : get all the proInnovativeProducts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proInnovativeProducts in body.
     */
    @GetMapping("/pro-innovative-products")
    public List<ProInnovativeProductDTO> getAllProInnovativeProducts() {
        log.debug("REST request to get all ProInnovativeProducts");
        return proInnovativeProductService.findAll();
    }

    /**
     * {@code GET  /pro-innovative-products/:id} : get the "id" proInnovativeProduct.
     *
     * @param id the id of the proInnovativeProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proInnovativeProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-innovative-products/{id}")
    public ResponseEntity<ProInnovativeProductDTO> getProInnovativeProduct(@PathVariable String id) {
        log.debug("REST request to get ProInnovativeProduct : {}", id);
        Optional<ProInnovativeProductDTO> proInnovativeProductDTO = proInnovativeProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proInnovativeProductDTO);
    }
    //LIST查詢
    @PostMapping("/service/pro-innovative-products/proCriteria")
    public List<ProInnovativeProductProUserDTO> getProInnovativeByCriteria(@RequestBody ProInnovativeProductProUserCriteria criteria) {
        log.debug("REST request to get proInnovativeProduct : {}", criteria);
        return proInnovativeProductService.findByProCriteria(criteria);

    }
    //前台PAGE查詢
    @PostMapping("/service/pro-innovative-products/proCriteria-jpa")
    public Page<ProInnovativeProductProUserDTO> getProInnovativePageByCriteria (@Valid @RequestBody ProInnovativeProductProUserCriteria criteria) throws InterruptedException {
        log.debug("REST request to get proInnovativeProduct : {}", criteria);
        return proInnovativeProductService.findPageByProCriteria(criteria);
    }

    //後台LIST查詢
    @PostMapping("/pro-innovative-products/proCriteria-backend")
    public List<ProInnovativeProductProUserDTO> getProInnovativeByCriteriaBackend(@RequestBody ProInnovativeProductProUserCriteria criteria) {
        log.debug("REST request to get proInnovativeProduct : {}", criteria);
        return proInnovativeProductService.findBackendProCriteria(criteria);
    }

    //後台PAGE查詢
    @PostMapping("/pro-innovative-products/proCriteria-pageBackend")
    public Page<ProInnovativeProductProUserDTO> getProInnovativePageByCriteriaBackend(@RequestBody ProInnovativeProductProUserCriteria criteria) {
        log.debug("REST request to get proInnovativeProduct : {}", criteria);
        return proInnovativeProductService.findPageBackendProCriteria(criteria);
    }

    //明細查詢
    @PostMapping("/service/pro-innovative-products/proCriteria-detail")
    public ProInnovativeProductSumSearchDetailDTO getProInnovativeByCriteriaDetail(@RequestBody ProInnovativeProductProUserCriteria criteria) {
        log.debug("REST request to get proInnovativeProduct proCriteria-detail: {}", criteria);
        return proInnovativeProductService.findproInnovativeProductAndDetail(criteria);

    }

    //廠商查詢
    @GetMapping("/pro-innovative-products/findCompany/{userId}")
    public ProApplyCompanyDTO getProUserByDto(@PathVariable String userId) {
        log.debug("REST request to get ProInnovativeProduct : {}", userId);
        return proInnovativeProductService.findApplyCompany(userId);
    }






    /**
     * {@code DELETE  /pro-innovative-products/:id} : delete the "id" proInnovativeProduct.
     *
     * @param proInnovativeProductId the id of the proInnovativeProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
//    @DeleteMapping("/pro-innovative-products/{id}")
//    public ResponseEntity<Void> deleteProInnovativeProduct(@PathVariable String id) {
//        log.debug("REST request to delete ProInnovativeProduct : {}", id);
//        proInnovativeProductService.delete(id);
//        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
//    }
    @DeleteMapping("/pro-innovative-products/delete/{proInnovativeProductId}")
    public ResponseEntity<Void> deleteProInnovativeProduct(@PathVariable String proInnovativeProductId) {
        log.debug("REST request to delete ProInnovativeProduct : {}", proInnovativeProductId);
        proInnovativeProductService.deleteProIP(proInnovativeProductId);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, proInnovativeProductId)).build();
    }



//查詢
       /*
        * {@code POST  /com-product-query/criteria} : get CommunicateProductQueryDTO by criteria.
        *
        * @param criteria the criteria of the CommunicateProductQueryDTO to retrieve.
        * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the CommunicateProductQueryDTO, or with status {@code 404 (Not Found)}.
        */
    @PostMapping("/pro-innovation/criteria")
    public List<CommunicateProductQueryDTO> getProInByCriteria(@RequestBody CommunicateProductQueryDTO criteria) {
        log.debug("REST request to get PwcDownload : {}", criteria);
        return proInnovativeProductService.findByCriteria(criteria);
    }




}
