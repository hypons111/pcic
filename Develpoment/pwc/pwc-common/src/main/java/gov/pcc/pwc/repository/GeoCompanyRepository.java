package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.GeoCompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GeoCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoCompanyRepository extends JpaRepository<GeoCompany, String> {}
