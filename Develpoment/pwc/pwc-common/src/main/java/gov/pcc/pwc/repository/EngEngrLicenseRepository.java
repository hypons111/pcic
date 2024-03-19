package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngEngrLicense;
import gov.pcc.pwc.domain.GeoQuestion;
import gov.pcc.pwc.repository.custom.EngEngrLicenseRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngEngrLicense entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngEngrLicenseRepository extends JpaRepository<EngEngrLicense, String>, EngEngrLicenseRepositoryCustom,  JpaSpecificationExecutor<EngEngrLicense> {}
