package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwbRoleApprove;
import gov.pcc.pwc.repository.custom.PwbRoleApproveRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwbRoleApprove entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwbRoleApproveRepository extends JpaRepository<PwbRoleApprove, Long>, PwbRoleApproveRepositoryCustom, JpaSpecificationExecutor<PwbRoleApprove> {}
