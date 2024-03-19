package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngEbillingCheckDtl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngEbillingCheckDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngEbillingCheckDtlRepository extends JpaRepository<EngEbillingCheckDtl, String> {}
