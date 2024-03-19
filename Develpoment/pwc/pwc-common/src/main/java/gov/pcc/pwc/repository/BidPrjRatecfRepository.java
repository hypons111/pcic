package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjRatecf;
import gov.pcc.pwc.domain.BidPrjRatecfPrimaryKey;
import gov.pcc.pwc.domain.BidPrjWksorc;
import gov.pcc.pwc.repository.custom.BidPrjRatecfRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjRatecf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjRatecfRepository extends JpaRepository<BidPrjRatecf, BidPrjRatecfPrimaryKey>, JpaSpecificationExecutor<BidPrjRatecf>, BidPrjRatecfRepositoryCustom {}
