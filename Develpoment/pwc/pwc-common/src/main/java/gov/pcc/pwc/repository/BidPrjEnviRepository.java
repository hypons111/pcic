package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.domain.BidPrjEnviPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjEnviRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data SQL repository for the BidPrjEnvi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjEnviRepository extends JpaRepository<BidPrjEnvi, BidPrjEnviPrimaryKey> , BidPrjEnviRepositoryCustom, JpaSpecificationExecutor<BidPrjEnvi> {}
