package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.AdmFunctionExtend;
import gov.pcc.pwc.repository.custom.AdmFunctionExtendRepositoryCustom;

/**
 * Spring Data SQL repository for the AdmFunctionExtend entity.
 */
@Repository
public interface AdmFunctionExtendRepository
    extends JpaRepository<AdmFunctionExtend, Long>, AdmFunctionExtendRepositoryCustom, JpaSpecificationExecutor<AdmFunctionExtend> {
    boolean existsByFunctionExtendId(String functionExtendId);
}
