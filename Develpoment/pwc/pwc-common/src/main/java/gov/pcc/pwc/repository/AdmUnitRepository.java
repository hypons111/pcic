package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmUnit;
import gov.pcc.pwc.repository.custom.AdmUnitRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmUnitRepository extends JpaRepository<AdmUnit, String>, AdmUnitRepositoryCustom, JpaSpecificationExecutor<AdmUnit> {}
