package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPlnunitb;
import gov.pcc.pwc.repository.custom.BidPlnunitbRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPlnunitb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPlnunitbRepository extends JpaRepository<BidPlnunitb, Long>, BidPlnunitbRepositoryCustom {

}
