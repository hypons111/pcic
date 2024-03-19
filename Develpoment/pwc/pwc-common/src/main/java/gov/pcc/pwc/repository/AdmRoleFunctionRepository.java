package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.AdmRoleFunction;
import gov.pcc.pwc.repository.custom.AdmRoleFunctionRepositoryCustom;

import java.util.List;

/**
 * Spring Data SQL repository for the AdmRoleFunction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmRoleFunctionRepository
    extends JpaRepository<AdmRoleFunction, Long>, AdmRoleFunctionRepositoryCustom, JpaSpecificationExecutor<AdmRoleFunction> {
    void deleteByRoleId(String roleId);
    List<AdmRoleFunction> findByFunctionId(String functionId);
}
