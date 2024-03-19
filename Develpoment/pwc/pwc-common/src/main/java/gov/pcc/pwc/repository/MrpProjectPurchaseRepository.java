package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.MrpProjectPurchase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MrpProjectPurchase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MrpProjectPurchaseRepository extends JpaRepository<MrpProjectPurchase, String> {}
