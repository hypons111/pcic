package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidProject;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.repository.custom.BidProjectRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidProject entity.
 */
@Repository
public interface BidProjectRepository extends JpaRepository<BidProject, BidProjectPrimaryKey>,JpaSpecificationExecutor<BidProject>, BidProjectRepositoryCustom {}
