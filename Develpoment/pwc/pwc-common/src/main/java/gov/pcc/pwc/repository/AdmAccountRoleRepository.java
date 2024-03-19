package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.AdmAccountRole;
import gov.pcc.pwc.repository.custom.AdmAccountRoleRepositoryCustom;

import java.util.List;

/**
 * Spring Data SQL repository for the AdmAccountRole entity.
 */
@Repository
public interface AdmAccountRoleRepository
    extends JpaRepository<AdmAccountRole, Long>, AdmAccountRoleRepositoryCustom, JpaSpecificationExecutor<AdmAccountRole> {
    List<AdmAccountRole> findByRoleId(String roleId);
    void deleteByUserId(String userId);
}
