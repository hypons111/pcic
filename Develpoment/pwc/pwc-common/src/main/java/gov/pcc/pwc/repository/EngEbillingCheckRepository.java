package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngEbillingCheck;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the EngEbillingCheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngEbillingCheckRepository extends JpaRepository<EngEbillingCheck, String> {
    Optional<EngEbillingCheck> findByDocTypeAndDocId(String docType, String docId);
}
