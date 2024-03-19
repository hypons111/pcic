package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EctCheck;
import gov.pcc.pwc.repository.custom.EctCheckRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

/**
 * Spring Data SQL repository for the EctCheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EctCheckRepository extends JpaRepository<EctCheck, Long>, EctCheckRepositoryCustom, JpaSpecificationExecutor<EctCheck> {
    public Map<String, Object> uploadCertPdf(MultipartHttpServletRequest request);
}
