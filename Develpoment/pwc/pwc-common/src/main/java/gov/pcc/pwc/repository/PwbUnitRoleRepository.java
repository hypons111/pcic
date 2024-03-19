package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwbUnitRole;
import gov.pcc.pwc.repository.custom.PwbUnitRoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwbUnitRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwbUnitRoleRepository extends JpaRepository<PwbUnitRole, Long>, PwbUnitRoleRepositoryCustom, JpaSpecificationExecutor<PwbUnitRole> {}
