package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.ECT0101R2QueryCriteria;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface EctPublicRepositoryCustom {
    Map<String, Object> getECT0101R2(ECT0101R2QueryCriteria criteria);

    Map<String, Object> applyect(MultipartHttpServletRequest request);

    Map<String, Object> queryect(MultipartHttpServletRequest request);

    Map<String, Object> revokeect(MultipartHttpServletRequest request);
}
