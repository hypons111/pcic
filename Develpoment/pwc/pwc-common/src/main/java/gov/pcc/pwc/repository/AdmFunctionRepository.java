package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.AdmFunction;
import gov.pcc.pwc.repository.custom.AdmFunctionRepositoryCustom;

/**
 * Spring Data SQL repository for the AdmFunction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmFunctionRepository
    extends JpaRepository<AdmFunction, Long>, AdmFunctionRepositoryCustom, JpaSpecificationExecutor<AdmFunction> {
    boolean existsByFunctionId(String functionId);
}
