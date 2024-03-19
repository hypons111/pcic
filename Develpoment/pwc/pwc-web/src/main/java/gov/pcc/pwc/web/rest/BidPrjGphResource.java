package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.domain.BidPrjGphPrimaryKey;
import gov.pcc.pwc.repository.AdmFileRepository;
import gov.pcc.pwc.repository.BidPrjGphRepository;
import gov.pcc.pwc.service.AdmFileService;
import gov.pcc.pwc.service.BidPrjGphService;
import gov.pcc.pwc.service.criteria.BidPrjGphCriteria;
import gov.pcc.pwc.service.dto.BidPrjGphDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjGph}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjGphResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjGphResource.class);

    private static final String ENTITY_NAME = "bidPrjGph";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjGphService bidPrjGphService;

    private final BidPrjGphRepository bidPrjGphRepository;

    private final AdmFileRepository admFileRepository;

    private final AdmFileService admFileService;

    public BidPrjGphResource(BidPrjGphService bidPrjGphService, BidPrjGphRepository bidPrjGphRepository, AdmFileRepository admFileRepository, AdmFileService admFileService) {
        this.bidPrjGphService = bidPrjGphService;
        this.bidPrjGphRepository = bidPrjGphRepository;
        this.admFileRepository = admFileRepository;
        this.admFileService = admFileService;
    }

    /**
     * {@code POST  /bid-prj-gphs} : Create a new bidPrjGph.
     *
     * @param bidPrjGphDTO the bidPrjGphDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjGphDTO, or with status {@code 400 (Bad Request)} if the bidPrjGph has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-gphs")
    public ResponseEntity<BidPrjGphDTO> createBidPrjGph(
        @RequestPart("form") BidPrjGphDTO bidPrjGphDTO,
        @RequestPart(name = "file", required = false) MultipartFile file,
        Authentication authentication
    ) throws URISyntaxException, IOException {
        log.debug("REST request to save BidPrjGph : {}", bidPrjGphDTO);
        AdmFile admFile = new AdmFile();
        Long admFileId = bidPrjGphDTO.getAdmFileId();
        if (admFileId != null) {
            Optional<AdmFile> oldAdmFile = admFileRepository.findById(admFileId);
            if(oldAdmFile.isPresent()) {
                admFile = oldAdmFile.get();
                admFile.setUpdateTime(Instant.now());
                admFile.setUpdateUser(authentication.getName());
            } else {
                admFile = new AdmFile();
                admFile.setModuleType("BID");
                admFile.setBusinessType("AAD-007_GPH");
                admFile.setSourceId(bidPrjGphDTO.getPrjno() + "@" + bidPrjGphDTO.getPhrase() + "@" + bidPrjGphDTO.getSeq());
                admFile.setFileVirusStatus("1");
                admFile.setCreateTime(Instant.now());
                admFile.setCreateUser(authentication.getName());
            }
        } else {
            admFile.setModuleType("BID");
            admFile.setBusinessType("AAD-007_GPH");
            admFile.setSourceId(bidPrjGphDTO.getPrjno() + "@" + bidPrjGphDTO.getPhrase() + "@" + bidPrjGphDTO.getSeq());
            admFile.setFileVirusStatus("1");
            admFile.setCreateTime(Instant.now());
            admFile.setCreateUser(authentication.getName());
        }
        String functionPath = "BID/PRJ_GPH/" + bidPrjGphDTO.getWkut() + "/" + bidPrjGphDTO.getPrjno() + "/0/" + bidPrjGphDTO.getSeq();
        admFile = admFileService.saveFile(admFile, file, functionPath);
        bidPrjGphDTO.setAdmFileId(admFile.getId());
        bidPrjGphDTO.setCreateDate(Instant.now());
        bidPrjGphDTO.setCreateUser(authentication.getName());

        BidPrjGphDTO result = bidPrjGphService.save(bidPrjGphDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-gphs/" + result.getWkut() + "/"+result.getPrjno()+"/"+result.getPhrase()+"/"+result.getSeq()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-gphs} : Updates an existing bidPrjGph.
     *
     * @param bidPrjGphDTO the bidPrjGphDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjGphDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjGphDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjGphDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-gphs")
    public ResponseEntity<BidPrjGphDTO> updateBidPrjGph(
        @RequestPart("form") BidPrjGphDTO bidPrjGphDTO,
        @RequestPart(name = "file", required = false) MultipartFile file,
        Authentication authentication
    ) throws URISyntaxException, IOException {
        BidPrjGphPrimaryKey bidPrjGphPK = new BidPrjGphPrimaryKey(bidPrjGphDTO.getWkut(), bidPrjGphDTO.getPrjno(), bidPrjGphDTO.getPhrase(), bidPrjGphDTO.getSeq());
        log.debug("REST request to update BidPrjGph : {}", bidPrjGphDTO);
        if (bidPrjGphDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjGphPK, bidPrjGphDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!bidPrjGphRepository.existsById(bidPrjGphPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        if (!"Z".equals(bidPrjGphDTO.getType())) {
            AdmFile admFile = new AdmFile();
            Long admFileId = bidPrjGphDTO.getAdmFileId();
            if (admFileId != null) {
                Optional<AdmFile> oldAdmFile = admFileRepository.findById(admFileId);
                if (oldAdmFile.isPresent()) {
                    admFile = oldAdmFile.get();
                    admFile.setUpdateTime(Instant.now());
                    admFile.setUpdateUser(authentication.getName());
                } else {
                    admFile = new AdmFile();
                    admFile.setModuleType("BID");
                    admFile.setBusinessType("AAD-007_GPH");
                    admFile.setSourceId(bidPrjGphDTO.getPrjno() + "@" + bidPrjGphDTO.getPhrase() + "@" + bidPrjGphDTO.getSeq());
                    admFile.setFileVirusStatus("1");
                    admFile.setCreateTime(Instant.now());
                    admFile.setCreateUser(authentication.getName());
                }
            } else {
                admFile.setModuleType("BID");
                admFile.setBusinessType("AAD-007_GPH");
                admFile.setSourceId(bidPrjGphDTO.getPrjno() + "@" + bidPrjGphDTO.getPhrase() + "@" + bidPrjGphDTO.getSeq());
                admFile.setFileVirusStatus("1");
                admFile.setCreateTime(Instant.now());
                admFile.setCreateUser(authentication.getName());
            }
            String functionPath = "BID/PRJ_GPH/" + bidPrjGphDTO.getWkut() + "/" + bidPrjGphDTO.getPrjno() + "/0/" + bidPrjGphDTO.getSeq();
            admFile = admFileService.saveFile(admFile, file, functionPath);
            bidPrjGphDTO.setAdmFileId(admFile.getId());
        }
        bidPrjGphDTO.setUpdateDate(Instant.now());
        bidPrjGphDTO.setUpdateUser(authentication.getName());

        BidPrjGphDTO result = bidPrjGphService.save(bidPrjGphDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjGphDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-gphs/:id} : Partial updates given fields of an existing bidPrjGph, field will ignore if it is null
     *
     * @param id the id of the bidPrjGphDTO to save.
     * @param bidPrjGphDTO the bidPrjGphDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjGphDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjGphDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjGphDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjGphDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-gphs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjGphDTO> partialUpdateBidPrjGph(
        @PathVariable(value = "id", required = false) final BidPrjGphPrimaryKey id,
        @NotNull @RequestBody BidPrjGphDTO bidPrjGphDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjGph partially : {}, {}", id, bidPrjGphDTO);
        if (bidPrjGphDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjGphDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjGphRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjGphDTO> result = bidPrjGphService.partialUpdate(bidPrjGphDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjGphDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-gphs} : get all the bidPrjGphs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjGphs in body.
     */
    @GetMapping("/bid-prj-gphs")
    public List<BidPrjGphDTO> getAllBidPrjGphs() {
        log.debug("REST request to get all BidPrjGphs");
        return bidPrjGphService.findAll();
    }

    /**
     * {@code GET  /bid-prj-gphs/:id} : get the "id" bidPrjGph.
     *
     * @param id the id of the bidPrjGphDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjGphDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-prj-gphs/{id}")
    public ResponseEntity<BidPrjGphDTO> getBidPrjGph(@PathVariable BidPrjGphPrimaryKey id) {
        log.debug("REST request to get BidPrjGph : {}", id);
        Optional<BidPrjGphDTO> bidPrjGphDTO = bidPrjGphService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjGphDTO);
    }

    @PostMapping("/bid-prj-gphs/getPages")
    public Page<BidPrjGphDTO> getPages(@RequestBody BidPrjGphCriteria criteria) {
        log.debug("REST request to get BidPrjGph : {}", criteria);
        return bidPrjGphService.findPages(criteria);
    }

    @PostMapping("/bid-prj-gphs/getMaxSeq")
    public int getMaxSeq(@RequestBody BidPrjGphCriteria criteria) {
        log.debug("REST request to get BidPrjGph : {}", criteria);
        return bidPrjGphService.findMaxSeq(criteria);
    }

    /**
     * {@code DELETE  /bid-prj-gphs} : delete the "id" bidPrjGph.
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-gphs")
    public ResponseEntity<Void> deleteBidPrjGph(@RequestBody BidPrjGphDTO bidPrjGphDTO) throws IOException {
        log.debug("REST request to delete BidPrjGph : {}", bidPrjGphDTO);
        admFileService.delete(bidPrjGphDTO.getAdmFileId());
        bidPrjGphService.delete(bidPrjGphDTO.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjGphDTO.getId().toString()))
            .build();
    }
}
