package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwbRoleModule;
import gov.pcc.pwc.repository.custom.PwbRoleModuleRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwbRoleModule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwbRoleModuleRepository extends JpaRepository<PwbRoleModule, Long>, PwbRoleModuleRepositoryCustom, JpaSpecificationExecutor<PwbRoleModule> {}
