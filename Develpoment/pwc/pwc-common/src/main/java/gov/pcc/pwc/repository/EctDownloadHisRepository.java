package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EctDownloadHis;
import gov.pcc.pwc.repository.custom.EctDownloadHisRepositoryCustom;
import gov.pcc.pwc.service.criteria.ECT0101R2Criteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Spring Data SQL repository for the EctDownloadHis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EctDownloadHisRepository extends JpaRepository<EctDownloadHis, Long>, EctDownloadHisRepositoryCustom, JpaSpecificationExecutor<EctDownloadHis> {
    public void doDownloadRecord(ECT0101R2Criteria criteria, HttpServletRequest request);
    public Map<String, Object> getECT0301(@NotNull ECT0101R2Criteria criteria);
    public Map<String, Object> getDownloadFile(MultipartHttpServletRequest request);
}
