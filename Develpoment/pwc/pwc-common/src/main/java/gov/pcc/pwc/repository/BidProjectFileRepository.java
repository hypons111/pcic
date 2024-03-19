package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidProjectFile;
import gov.pcc.pwc.domain.BidProjectFilePrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidProjectFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidProjectFileRepository extends JpaRepository<BidProjectFile, BidProjectFilePrimaryKey>, JpaSpecificationExecutor<BidProjectFile> {}


