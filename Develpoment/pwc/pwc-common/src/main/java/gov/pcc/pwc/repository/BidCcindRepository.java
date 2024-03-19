package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidCcind;
import gov.pcc.pwc.domain.BidCcindPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidCcind entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidCcindRepository extends JpaRepository<BidCcind, BidCcindPrimaryKey>, JpaSpecificationExecutor<BidCcind> {}
