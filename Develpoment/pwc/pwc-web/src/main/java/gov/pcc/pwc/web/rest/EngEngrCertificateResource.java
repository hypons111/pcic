package gov.pcc.pwc.web.rest;

import freemarker.template.TemplateException;
import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.MessageCodeException;
import gov.pcc.pwc.common.web.io.DownloadableResource;
import gov.pcc.pwc.domain.EngEngrCertificatePK;
import gov.pcc.pwc.repository.EngEngrCertificateRepository;
import gov.pcc.pwc.service.EngEngrCertificateService;
import gov.pcc.pwc.service.EngPrintFileService;
import gov.pcc.pwc.service.criteria.EngEngrCertificateCriteria;
import gov.pcc.pwc.service.dto.Eng0101DTO;
import gov.pcc.pwc.service.dto.EngEngrCertificateDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.EngPrintFileDTO;
import gov.pcc.pwc.service.dto.EngReviewLogDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngEngrCertificate}.
 */
@RestController
@RequestMapping("/api")
public class EngEngrCertificateResource {

    private final Logger log = LoggerFactory.getLogger(EngEngrCertificateResource.class);

    private static final String ENTITY_NAME = "engEngrCertificate";

    private final long picFileSizeLimit = 1024 * 1024;
    private final long appendixFileSizeLimit = 2 * 1024 * 1024;
    private final String[] fileTypeLimit = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_PDF_VALUE};

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngEngrCertificateService engEngrCertificateService;

    private final EngEngrCertificateRepository engEngrCertificateRepository;

    private final EngPrintFileService engPrintFileService;

    public EngEngrCertificateResource(
        EngEngrCertificateService engEngrCertificateService,
        EngEngrCertificateRepository engEngrCertificateRepository,
        EngPrintFileService engPrintFileService) {
        this.engEngrCertificateService = engEngrCertificateService;
        this.engEngrCertificateRepository = engEngrCertificateRepository;
        this.engPrintFileService = engPrintFileService;
    }

    private void checkFile(MultipartFile file, long fileSizeLimit, String[] fileTypeLimit) {
        // 檢查檔案大小
        if (file.getSize() > fileSizeLimit) {
            throw new MessageCodeException(MessageCodes.PWC_COMMON_0011_E(fileSizeLimit / (1024 * 1024) + "MB", file.getOriginalFilename()));
        }
        // 檢查檔案格式
        if (Arrays.stream(fileTypeLimit).noneMatch(type -> Objects.equals(type, file.getContentType()))) {
            throw new MessageCodeException(MessageCodes.PWC_COMMON_0012_E(Arrays.toString(fileTypeLimit), file.getOriginalFilename()));
        }
    }

    /**
     * {@code POST  /eng-engr-certificates} : Create a new engEngrCertificate.
     *
     * @param engEngrCertificateDTO the engEngrCertificateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engEngrCertificateDTO, or with status {@code 400 (Bad Request)} if the engEngrCertificate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping(path = "/eng-engr-certificates", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Eng0101DTO> createEng0101(
        @RequestPart("form") EngEngrCertificateDTO engEngrCertificateDTO,
        @RequestPart(name = "picFile", required = false) MultipartFile picFile,
        @RequestPart(name = "appendixFiles", required = false) List<MultipartFile> appendixFiles,
        @RequestPart(name = "reviewLog", required = false) EngReviewLogDTO engReviewLogDTO
    ) throws URISyntaxException, IOException, TypeVariableNotMatchException {
        log.debug("REST request to create EngEngrCertificate : {}", engEngrCertificateDTO);
        if (engEngrCertificateRepository.existsById(new EngEngrCertificatePK(engEngrCertificateDTO.getEngEngrCertificateId(), engEngrCertificateDTO.getEngEngrCertificateVersion()))) {
            throw new BadRequestAlertException("A new engEngrCertificate cannot already exist", ENTITY_NAME, "pkExists");
        }

        if(picFile != null) {
            checkFile(picFile, picFileSizeLimit, fileTypeLimit);
        }

        if(appendixFiles != null) {
            for(MultipartFile appendixFile : appendixFiles) {
                checkFile(appendixFile, appendixFileSizeLimit, fileTypeLimit);
            }
        }

        Eng0101DTO eng0101DTO = engEngrCertificateService.save(engEngrCertificateDTO, picFile, appendixFiles, engReviewLogDTO);
        String pk = eng0101DTO.getEngEngrCertificateDTO().getEngEngrCertificateId() + "_" + eng0101DTO.getEngEngrCertificateDTO().getEngEngrCertificateVersion();

        return ResponseEntity
            .created(new URI("/api/eng-engr-certificates/" + pk))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_GEO01_0002_S(engEngrCertificateDTO.getEngEngrCertificateNo(), engEngrCertificateDTO.getChName())))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, pk))
            .body(eng0101DTO);
    }

    /**
     * {@code PUT  /eng-engr-certificates/:engEngrCertificateId/engEngrCertificateVersion} : Updates an existing engEngrCertificate.
     *
     * @param engEngrCertificateDTO the engEngrCertificateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEngrCertificateDTO,
     * or with status {@code 400 (Bad Request)} if the engEngrCertificateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engEngrCertificateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping(path = "/eng-engr-certificates", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Eng0101DTO> updateEngEngrCertificate(
        @RequestPart("form") EngEngrCertificateDTO engEngrCertificateDTO,
        @RequestPart(name = "picFile", required = false) MultipartFile picFile,
        @RequestPart(name = "appendixFiles", required = false) List<MultipartFile> appendixFiles,
        @RequestPart(name = "reviewLog", required = false) EngReviewLogDTO engReviewLogDTO
    ) throws URISyntaxException, IOException, TypeVariableNotMatchException {
        log.debug("REST request to update EngEngrCertificate : {}", engEngrCertificateDTO);
        if (engEngrCertificateDTO.getEngEngrCertificateId() == null || engEngrCertificateDTO.getEngEngrCertificateVersion() == null) {
            throw new BadRequestAlertException("Invalid PK", ENTITY_NAME, "PKNull");
        }
        if (!engEngrCertificateRepository.existsById(new EngEngrCertificatePK(engEngrCertificateDTO.getEngEngrCertificateId(), engEngrCertificateDTO.getEngEngrCertificateVersion()))) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "PKNotFound");
        }

        if(picFile != null) {
            checkFile(picFile, picFileSizeLimit, fileTypeLimit);
        }

        if(appendixFiles != null) {
            for(MultipartFile appendixFile : appendixFiles) {
                checkFile(appendixFile, appendixFileSizeLimit, fileTypeLimit);
            }
        }

        Eng0101DTO eng0101DTO = engEngrCertificateService.save(engEngrCertificateDTO, picFile, appendixFiles, engReviewLogDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_GEO01_0001_S(engEngrCertificateDTO.getEngEngrCertificateNo(), engEngrCertificateDTO.getChName())))
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                    engEngrCertificateDTO.getEngEngrCertificateId() + "_" + engEngrCertificateDTO.getEngEngrCertificateVersion())
            )
            .body(eng0101DTO);
    }

    /**
     * {@code PATCH  /eng-engr-certificates/:engEngrCertificateId/engEngrCertificateVersion} : Partial updates given fields of an existing engEngrCertificate, field will ignore if it is null
     *
     * @param engEngrCertificateId the id of the engEngrCertificateDTO to save.
     * @param engEngrCertificateDTO the engEngrCertificateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEngrCertificateDTO,
     * or with status {@code 400 (Bad Request)} if the engEngrCertificateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engEngrCertificateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engEngrCertificateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/eng-engr-certificates/{engEngrCertificateId}/{engEngrCertificateVersion}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<EngEngrCertificateDTO> partialUpdateEngEngrCertificate(
        @PathVariable(value = "engEngrCertificateId", required = false) final String engEngrCertificateId,
        @PathVariable(value = "engEngrCertificateVersion", required = false) final BigDecimal engEngrCertificateVersion,
        @NotNull @RequestBody EngEngrCertificateDTO engEngrCertificateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngEngrCertificate partially : {}, {}, {}", engEngrCertificateId, engEngrCertificateVersion, engEngrCertificateDTO);
        if (engEngrCertificateDTO.getEngEngrCertificateId() == null || engEngrCertificateDTO.getEngEngrCertificateVersion() == null) {
            throw new BadRequestAlertException("Invalid PK", ENTITY_NAME, "PKnull");
        }
        if (!Objects.equals(engEngrCertificateId, engEngrCertificateDTO.getEngEngrCertificateId()) ||
            !Objects.equals(engEngrCertificateVersion, engEngrCertificateDTO.getEngEngrCertificateVersion())) {
            throw new BadRequestAlertException("Invalid PK", ENTITY_NAME, "PKinvalid");
        }

        if (!engEngrCertificateRepository.existsById(new EngEngrCertificatePK(engEngrCertificateId, engEngrCertificateVersion))) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "PKnotfound");
        }

        Optional<EngEngrCertificateDTO> result = engEngrCertificateService.partialUpdate(engEngrCertificateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                engEngrCertificateDTO.getEngEngrCertificateId() + "-" + engEngrCertificateDTO.getEngEngrCertificateVersion())
        );
    }

    /**
     * {@code GET  /eng-engr-certificates} : get all the engEngrCertificates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engEngrCertificates in body.
     */
    @GetMapping("/eng-engr-certificates")
    public List<EngEngrCertificateDTO> getAllEngEngrCertificates() {
        log.debug("REST request to get all EngEngrCertificates");
        return engEngrCertificateService.findAll();
    }

    /**
     * {@code GET  /eng-engr-certificates/:id} : get the "id" engEngrCertificate.
     *
     * @param id the id of the engEngrCertificateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engEngrCertificateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-engr-certificates/{id}/{version}")
    public ResponseEntity<EngEngrCertificateDTO> getEngEngrCertificate(@PathVariable String id, @PathVariable BigDecimal version) {
        log.debug("REST request to get EngEngrCertificate : {}, {}", id, version);
        Optional<EngEngrCertificateDTO> engEngrCertificateDTO = engEngrCertificateService.findOne(id, version);
        return ResponseUtil.wrapOrNotFound(engEngrCertificateDTO);
    }

    @GetMapping("/eng-engr-certificates/{idno}/{status}/{applyItemsList}")
    public List<EngEngrCertificateDTO> getEngEngrCertificate(@PathVariable String idno, @PathVariable String status, @PathVariable String applyItemsList) {
        log.debug("REST request to get EngEngrCertificate, idno : {}, status : {}, applyItemsList : {}", idno, status, applyItemsList);
        return engEngrCertificateService.findByIdnoAndStatusAndApplyItemsList(idno, status, applyItemsList);
    }

    /**
     * {@code POST  /eng-engr-certificates/criteria-jpa} : get engEngrCertificates by criteria.
     *
     * @param criteria the engEngrCertificateCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} the page of engEngrCertificates in body, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/eng-engr-certificates/criteria-jpa")
    public Page<EngEngrCertificateDTO> getEngEngrCertificateByCriteria(@Valid @RequestBody EngEngrCertificateCriteria criteria) throws InterruptedException {
        log.debug("REST request to get EngEngrCertificate : {}", criteria);
        return engEngrCertificateService.findAll(criteria);
    }

    @GetMapping("/eng-engr-certificates/{id}")
    public List<EngEngrCertificateDTO> getEngEngrCertificates(@PathVariable String id) {
        log.debug("REST request to get EngEngrCertificates : {}", id);
        return engEngrCertificateService.findByEngEngrCertificateId(id);
    }

    @GetMapping("/eng-engr-certificates/next-version/{id}/{version}")
    public EngEngrCertificateDTO getNextEngEngrCertificate(@PathVariable String id, @PathVariable BigDecimal version) {
        log.debug("REST request to get EngEngrCertificate : {}", id);
        return engEngrCertificateService.findTopVersionByIdAndVersionGreaterThan(id, version);
    }

    @GetMapping("/eng-engr-certificates/find-by-idno-and-status/{idno}/{status}")
    public List<EngEngrCertificateDTO> getEngEngrCertificates(@PathVariable String idno, @PathVariable String status) {
        log.debug("REST request to get EngEngrCertificates : {}, {}", idno, status);
        return engEngrCertificateService.findByIdnoAndStatus(idno, status);
    }

    @PostMapping("/eng-engr-certificates/eng0101")
    public Eng0101DTO getEngEngrCertificate(@Valid @RequestBody EngEngrCertificateDTO engEngrCertificateDTO) throws IOException {
        log.debug("REST request to get Eng0101DTO : {}", engEngrCertificateDTO);
        return engEngrCertificateService.findEng0101(engEngrCertificateDTO);
    }

    @PostMapping("/eng-engr-certificates/switch-case")
    public Eng0101DTO getEngEngrCertificate(@Valid @RequestBody EngEngrCertificateCriteria criteria) throws IOException {
        log.debug("REST request to get Eng0101DTO : {}", criteria);
        return engEngrCertificateService.findEng0101(engEngrCertificateService.findOne(criteria).orElse(new EngEngrCertificateDTO()));
    }

    @PostMapping("/eng-engr-certificates/print-file")
    public ResponseEntity<DownloadableResource> getPrintFile(@RequestBody EngPrintFileDTO engPrintFileDTO) throws InterruptedException, IOException, TemplateException {
        log.debug("REST request to get print file : {}", engPrintFileDTO);

        return ResponseEntity.ok(engPrintFileService.getPrintFile(engPrintFileDTO));
    }

    /**
     * {@code DELETE  /eng-engr-certificates/:id} : delete the "id" engEngrCertificate.
     *
     * @param id the id of the engEngrCertificateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-engr-certificates/{id}/{version}")
    public ResponseEntity<Void> deleteEngEngrCertificate(@PathVariable String id, @PathVariable BigDecimal version) {
        log.debug("REST request to delete EngEngrCertificate : {}, {}", id, version);
        engEngrCertificateService.delete(id, version);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
