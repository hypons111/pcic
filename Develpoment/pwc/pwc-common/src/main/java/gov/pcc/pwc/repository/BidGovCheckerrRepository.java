package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidGovCheckerr;
import gov.pcc.pwc.domain.BidGovCheckerrPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data SQL repository for the BidGovCheckerr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidGovCheckerrRepository extends JpaRepository<BidGovCheckerr, BidGovCheckerrPrimaryKey>, JpaSpecificationExecutor<BidGovCheckerr> {
    List<BidGovCheckerr> findByWkutAndPrjnoAndCheckDate(String wkut, String prjno, LocalDate checkDate);
}
