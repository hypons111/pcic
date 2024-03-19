package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.AdmRole;
import gov.pcc.pwc.repository.custom.AdmRoleRepositoryCustom;

/**
 * Spring Data SQL repository for the AdmRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmRoleRepository extends JpaRepository<AdmRole, String>, AdmRoleRepositoryCustom, JpaSpecificationExecutor<AdmRole> {}
