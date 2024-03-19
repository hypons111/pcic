package gov.pcc.pwc.repository.custom;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

public interface EctCheckRepositoryCustom {
    Map<String, Object> uploadCertPdf(MultipartHttpServletRequest request);
}
