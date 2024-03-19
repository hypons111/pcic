package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EctPublic;
import gov.pcc.pwc.repository.custom.EctPublicRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import gov.pcc.pwc.service.criteria.ECT0101R2QueryCriteria;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Spring Data SQL repository for the EctPublic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EctPublicRepository extends JpaRepository<EctPublic, Long>, EctPublicRepositoryCustom, JpaSpecificationExecutor<EctPublic> {
	public Map<String, Object> getECT0101R2(ECT0101R2QueryCriteria criteria);

	public Map<String, Object> applyect(MultipartHttpServletRequest request);

	public Map<String, Object> queryect(MultipartHttpServletRequest request);

	public Map<String, Object> revokeect(MultipartHttpServletRequest request);
}
