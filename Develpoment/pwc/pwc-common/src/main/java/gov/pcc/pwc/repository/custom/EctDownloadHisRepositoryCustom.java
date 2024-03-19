package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.ECT0101R2Criteria;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;

public interface EctDownloadHisRepositoryCustom {
    void doDownloadRecord(ECT0101R2Criteria criteria, HttpServletRequest request);
    Map<String, Object> getECT0301(@NotNull ECT0101R2Criteria criteria);
    Map<String, Object> getDownloadFile(MultipartHttpServletRequest request);
}
