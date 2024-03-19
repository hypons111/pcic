package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmSysParameter;
import gov.pcc.pwc.domain.AdmSysParameterPK;
import gov.pcc.pwc.repository.custom.AdmSysParameterRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the AdmSysParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmSysParameterRepository extends JpaRepository<AdmSysParameter, AdmSysParameterPK>, AdmSysParameterRepositoryCustom, JpaSpecificationExecutor<AdmSysParameter> {
    void deleteBySysNameAndSysType(String sysName, String sysType);
    Optional<AdmSysParameter> findBySysNameAndSysType(String sysName, String sysType);
}
