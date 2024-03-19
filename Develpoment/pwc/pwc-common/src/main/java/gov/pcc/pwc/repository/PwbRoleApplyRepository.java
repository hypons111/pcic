package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwbRoleApply;
import gov.pcc.pwc.repository.custom.PwbRoleApplyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwbRoleApply entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwbRoleApplyRepository extends JpaRepository<PwbRoleApply, Long>, PwbRoleApplyRepositoryCustom, JpaSpecificationExecutor<PwbRoleApply> {}
