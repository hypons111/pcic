package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.AdmRoleFunctionExtend;

import java.util.List;

/**
 * Spring Data SQL repository for the AdmRoleFunctionExtend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmRoleFunctionExtendRepository
    extends JpaRepository<AdmRoleFunctionExtend, Long>, JpaSpecificationExecutor<AdmRoleFunctionExtend> {
//    List<AdmRoleFunctionExtend> findByFunctionExtendId(String functionExtendId);
    void deleteByRoleId(String roleId);
}
