package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmSysCode;
import gov.pcc.pwc.domain.AdmSysCodePK;
import gov.pcc.pwc.repository.custom.AdmSysCodeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the AdmSysCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmSysCodeRepository extends JpaRepository<AdmSysCode, AdmSysCodePK>, AdmSysCodeRepositoryCustom, JpaSpecificationExecutor<AdmSysCode> {
    void deleteByModuleTypeAndDataTypeAndDataKey(String moduleType, String dataType, String dataKey);
    Optional<AdmSysCode> findByModuleTypeAndDataTypeAndDataKey(@Param("moduleType")String moduleType,@Param("dataType")String dataType, @Param("dataKey")String dataKey);
}
