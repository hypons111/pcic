package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngCstLicense;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngCstLicense entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngCstLicenseRepository extends JpaRepository<EngCstLicense, String> {}
