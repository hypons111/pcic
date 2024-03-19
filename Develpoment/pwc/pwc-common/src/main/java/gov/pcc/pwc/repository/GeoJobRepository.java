package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.GeoJob;
import gov.pcc.pwc.domain.GeoQuestion;
import gov.pcc.pwc.repository.custom.GeoJobRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GeoJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoJobRepository extends JpaRepository<GeoJob, Long>, JpaSpecificationExecutor<GeoJob>, GeoJobRepositoryCustom {}
