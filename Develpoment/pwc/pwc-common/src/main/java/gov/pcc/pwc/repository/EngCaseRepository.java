package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngCase;
import gov.pcc.pwc.repository.custom.EngCaseRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngCaseRepository extends JpaRepository<EngCase, String>, EngCaseRepositoryCustom, JpaSpecificationExecutor<EngCase>  {

    public EngCase getByEngCaseId(String engCaseId);
}
