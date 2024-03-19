package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.service.PwcInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcInformation}.
 */
@RestController
@RequestMapping("/api/service")
public class UtilResource {

    private final Logger log = LoggerFactory.getLogger(UtilResource.class);

    private static final String ENTITY_NAME = "util";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcInformationService pwcInformationService;


    public UtilResource( PwcInformationService pwcInformationService) {
        this.pwcInformationService = pwcInformationService;
    }

    /**
     * EXCEL-UPLOAD
     */
    @PutMapping(path ="/excel-upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void updateInformation(@Valid @RequestPart("uploadFile") List<MultipartFile> uploadFile) throws URISyntaxException, IOException {
        log.debug("REST request to update File : ");
        int fileName = uploadFile.size();
        try {
            pwcInformationService.getFlieListData(uploadFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
