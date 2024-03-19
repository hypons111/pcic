package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPlnbas;
import gov.pcc.pwc.domain.BidPlnbasPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPlnbasRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPlnbas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPlnbasRepository extends JpaRepository<BidPlnbas, BidPlnbasPrimaryKey>, BidPlnbasRepositoryCustom, JpaSpecificationExecutor<BidPlnbas> {}
