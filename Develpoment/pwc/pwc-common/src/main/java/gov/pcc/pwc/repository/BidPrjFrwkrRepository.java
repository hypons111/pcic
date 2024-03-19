package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.domain.BidPrjFrwkr;
import gov.pcc.pwc.domain.BidPrjFrwkrPrimaryKey;
import gov.pcc.pwc.domain.BidUnit10;
import gov.pcc.pwc.repository.custom.BidPrjEnviRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjFrwkrCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the BidPrjFrwkr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjFrwkrRepository extends JpaRepository<BidPrjFrwkr, BidPrjFrwkrPrimaryKey>, JpaSpecificationExecutor<BidPrjFrwkr> {


}
