package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.EctDownloadHisRepository;
import gov.pcc.pwc.service.EctDownloadHisService;
import gov.pcc.pwc.service.criteria.ECT0101R2Criteria;
import gov.pcc.pwc.service.dto.EctDownloadHisDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.odsExporter.DownloadService;
import gov.pcc.pwc.utils.PropUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EctDownloadHis}.
 */
@RestController
@RequestMapping("/api")
public class EctDownloadHisResource {

    private final Logger log = LoggerFactory.getLogger(EctDownloadHisResource.class);

    private static final String ENTITY_NAME = "ectDownloadHis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EctDownloadHisService ectDownloadHisService;

    private final EctDownloadHisRepository ectDownloadHisRepository;

    private final DownloadService downloadService;

    public EctDownloadHisResource(EctDownloadHisService ectDownloadHisService, EctDownloadHisRepository ectDownloadHisRepository, DownloadService downloadService) {
        this.ectDownloadHisService = ectDownloadHisService;
        this.ectDownloadHisRepository = ectDownloadHisRepository;
        this.downloadService = downloadService;
    }

    @PostMapping("/service/export-cert-pdf")
    public void exportPdf(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody ECT0101R2Criteria criteria) throws IOException {
        log.debug("REST request to exportCertPdf");
        ectDownloadHisService.doDownloadRecord(criteria, request);
        String certType = criteria.getCertType();
        String fileName = criteria.getFileName();
        String fileCreateDate = criteria.getFileCreateTime();
        String y = fileCreateDate.substring(0,4);
        String m = fileCreateDate.substring(5,7);
        String d = fileCreateDate.substring(8,10);
        String downloadFilePath = //"/stsdat/pwc/ECT/"
            PropUtil.getPropVal("ect.cer.savepath") + certType + "/" + y + "/" + m + "/" + d + "/" + fileName.replace(".pdf", "") + ".pdf";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
        File pdf = new File(downloadFilePath);
        System.out.println("HAS PDF FILE: " + pdf.exists());
        if(pdf.exists()){
            InputStream fis = new FileInputStream(pdf);
            IOUtils.copy(fis, response.getOutputStream());
        }
//        downloadService.downloadByResponse(response,downloadFilePath,fileName+".pdf");
    }

    @PostMapping("/service/view-cert-pdf")
    public void viewCertPdf(HttpServletResponse response, @Valid @RequestBody ECT0101R2Criteria criteria) throws IOException {
        log.debug("REST request to viewCertPdf");
        String certType = criteria.getCertType();
        String fileName = criteria.getFileName();
        String fileCreateDate = criteria.getFileCreateTime();
        String y = fileCreateDate.substring(0,4);
        String m = fileCreateDate.substring(5,7);
        String d = fileCreateDate.substring(8,10);
        String isValid = criteria.getIsValid();
        String viewType = "_v1";
        if(isValid.equals("1")){
            viewType = "_v1";
        }else if(isValid.equals("2")){
            viewType = "_v2";
        }else if(isValid.equals("3") && certType.equals("4")){
            viewType = "_v3";
        }
        String downloadFilePath = //"/stsdat/pwc/ECT/"
            PropUtil.getPropVal("ect.cer.savepath") + certType + "/" + y + "/" + m + "/" + d + "/" + fileName.replace(".pdf", "") + viewType +".pdf";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        response.setContentType("application/pdf");
        File pdf = new File(downloadFilePath);
        System.out.println("HAS PDF FILE: " + pdf.exists());
        if(pdf.exists()){
            InputStream fis = new FileInputStream(pdf);
            IOUtils.copy(fis, response.getOutputStream());
        }
    }

    @GetMapping("/service/downloadect")
    public Map<String, Object> downloadect(MultipartHttpServletRequest request, HttpServletResponse response) {
        log.debug("REST request to exportCertPdf");
        Map<String, Object> frs = ectDownloadHisService.getDownloadFile(request);
		Map<String, Object> rs = new HashMap<String, Object>();
        if(frs.get("resultCode").toString() != "1"){
            rs.put("resultCode", "2");
            rs.put("resultMsg", "發生錯誤");
            return rs;
        }else{
            try {
                String downloadFilePath = frs.get("path").toString();
                String fileName = frs.get("fileName").toString();
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                File pdf = new File(downloadFilePath);
                System.out.println("HAS PDF FILE: " + pdf.exists());
                if (pdf.exists()) {
                    InputStream fis = new FileInputStream(pdf);
                    IOUtils.copy(fis, response.getOutputStream());
                }
				rs.put("resultCode", "1");
                rs.put("resultMsg", "成功");
            }catch(Exception e){
                e.printStackTrace();
				rs.put("resultCode", "2");
                rs.put("resultMsg", "下載時發生錯誤");
            }
            return rs;
        }
//        String downloadFilePath = "/stsdat/pwc/ECT/2/2022/10/03/QMR_10000001.pdf";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
//        File pdf = new File(downloadFilePath);
//        Path filePath = pdf.toPath();
//        Resource resource = new UrlResource(filePath.toUri());
//
//        return ResponseEntity.ok()
//            .contentType(MediaType.parseMediaType("application/x-download"))
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=QMR_10000001.pdf")
//            .body(resource);
    }

	@PostMapping("/service/downloadectA")
    public Map<String, Object> downloadectA(MultipartHttpServletRequest request, HttpServletResponse response) {
        log.debug("REST request to exportCertPdf");
        Map<String, Object> frs = ectDownloadHisService.getDownloadFile(request);
        Map<String, Object> rs = new HashMap<String, Object>();
        if(frs.get("resultCode").toString() != "1"){
            rs.put("resultCode", "2");
            rs.put("resultMsg", "發生錯誤");
            return rs;
        }else{
            try {
                String downloadFilePath = frs.get("path").toString();
                String fileName = frs.get("fileName").toString();
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                File pdf = new File(downloadFilePath);
                System.out.println("HAS PDF FILE: " + pdf.exists());
                if (pdf.exists()) {
                    InputStream fis = new FileInputStream(pdf);
                    IOUtils.copy(fis, response.getOutputStream());
                }
                rs.put("resultCode", "1");
                rs.put("resultMsg", "成功");
            }catch(Exception e){
                e.printStackTrace();
                rs.put("resultCode", "2");
                rs.put("resultMsg", "下載時發生錯誤");
            }
            return rs;
        }
    }

    @GetMapping("/service/getCerFile")
    public ResponseEntity getCer() {
        try {
            String downloadFilePath = PropUtil.getPropVal("ect.sign.cerpath");//"/stsdat/pwc/ECT/pki/pwccct02.cer";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
            File pdf = new File(downloadFilePath);
            Path filePath = pdf.toPath();
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-download"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + PropUtil.getPropVal("ect.sign.cername"))
                .body(resource);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/service/getHiCOS")
    public ResponseEntity getHiCOS() {
        try {
            String downloadFilePath = "/stsdat/pwc/ECT/HiCOS_Client.zip";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
            File pdf = new File(downloadFilePath);
            Path filePath = pdf.toPath();
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-download"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=HiCOS_Client.zip")
                .body(resource);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/service/getTheFile")
    public ResponseEntity getTheFile(@RequestParam("fileName") String fileName) {
        try {
            String downloadFilePath = PropUtil.getPropVal("mrp.file.savepath") + fileName;//"/stsdat/pwc/mrp/" + fileName;  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
            File pdf = new File(downloadFilePath);
            Path filePath = pdf.toPath();
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-download"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/service/ect0301")
    public Map<String, Object> getECT0301(HttpServletResponse response, @Valid @RequestBody ECT0101R2Criteria criteria) {
        log.debug("Rest ect0301" + criteria);
        return ectDownloadHisService.getECT0301(criteria);
    }

    /**
     * {@code POST  /ect-download-his} : Create a new ectDownloadHis.
     *
     * @param ectDownloadHisDTO the ectDownloadHisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ectDownloadHisDTO, or with status {@code 400 (Bad Request)} if the ectDownloadHis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ect-download-his")
    public ResponseEntity<EctDownloadHisDTO> createEctDownloadHis(@Valid @RequestBody EctDownloadHisDTO ectDownloadHisDTO)
        throws URISyntaxException {
        log.debug("REST request to save EctDownloadHis : {}", ectDownloadHisDTO);
        EctDownloadHisDTO result = ectDownloadHisService.save(ectDownloadHisDTO);
        return ResponseEntity
            .created(new URI("/api/ect-download-his/" + result.getEctDownloadHisId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEctDownloadHisId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ect-download-his/:ectDownloadHisId} : Updates an existing ectDownloadHis.
     *
     * @param ectDownloadHisId the id of the ectDownloadHisDTO to save.
     * @param ectDownloadHisDTO the ectDownloadHisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ectDownloadHisDTO,
     * or with status {@code 400 (Bad Request)} if the ectDownloadHisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ectDownloadHisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ect-download-his/{ectDownloadHisId}")
    public ResponseEntity<EctDownloadHisDTO> updateEctDownloadHis(
        @PathVariable(value = "ectDownloadHisId", required = false) final Long ectDownloadHisId,
        @Valid @RequestBody EctDownloadHisDTO ectDownloadHisDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EctDownloadHis : {}, {}", ectDownloadHisId, ectDownloadHisDTO);

        EctDownloadHisDTO result = ectDownloadHisService.save(ectDownloadHisDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ectDownloadHisDTO.getEctDownloadHisId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /ect-download-his/:ectDownloadHisId} : Partial updates given fields of an existing ectDownloadHis, field will ignore if it is null
     *
     * @param ectDownloadHisId the id of the ectDownloadHisDTO to save.
     * @param ectDownloadHisDTO the ectDownloadHisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ectDownloadHisDTO,
     * or with status {@code 400 (Bad Request)} if the ectDownloadHisDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ectDownloadHisDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ectDownloadHisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ect-download-his/{ectDownloadHisId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EctDownloadHisDTO> partialUpdateEctDownloadHis(
        @PathVariable(value = "ectDownloadHisId", required = false) final Long ectDownloadHisId,
        @NotNull @RequestBody EctDownloadHisDTO ectDownloadHisDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EctDownloadHis partially : {}, {}", ectDownloadHisId, ectDownloadHisDTO);

        Optional<EctDownloadHisDTO> result = ectDownloadHisService.partialUpdate(ectDownloadHisDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ectDownloadHisDTO.getEctDownloadHisId().toString())
        );
    }

    /**
     * {@code GET  /ect-download-his} : get all the ectDownloadHis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ectDownloadHis in body.
     */
    @GetMapping("/ect-download-his")
    public List<EctDownloadHisDTO> getAllEctDownloadHis() {
        log.debug("REST request to get all EctDownloadHis");
        return ectDownloadHisService.findAll();
    }

    /**
     * {@code GET  /ect-download-his/:id} : get the "id" ectDownloadHis.
     *
     * @param id the id of the ectDownloadHisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ectDownloadHisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ect-download-his/{id}")
    public ResponseEntity<EctDownloadHisDTO> getEctDownloadHis(@PathVariable Long id) {
        log.debug("REST request to get EctDownloadHis : {}", id);
        Optional<EctDownloadHisDTO> ectDownloadHisDTO = ectDownloadHisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ectDownloadHisDTO);
    }

    /**
     * {@code DELETE  /ect-download-his/:id} : delete the "id" ectDownloadHis.
     *
     * @param id the id of the ectDownloadHisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ect-download-his/{id}")
    public ResponseEntity<Void> deleteEctDownloadHis(@PathVariable Long id) {
        log.debug("REST request to delete EctDownloadHis : {}", id);
        ectDownloadHisService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
