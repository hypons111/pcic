package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.ProInnovativeProduct;
import gov.pcc.pwc.repository.custom.ProInnovativeProductRepositoryCustom;
import gov.pcc.pwc.repository.custom.ProInnovativeProductCustom;
import gov.pcc.pwc.service.dto.ProAdmAgentOrgNameDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProInnovativeProduct entity.
 */
//使用JpaRepository提供的基本查詢和JpaSpecificationExecutor的客製化複雜查詢
@SuppressWarnings("unused")
@Repository
public interface ProInnovativeProductRepository extends JpaRepository<ProInnovativeProduct, String>, ProInnovativeProductRepositoryCustom, JpaSpecificationExecutor<ProInnovativeProduct> {

}
