package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngReviewLog;
import gov.pcc.pwc.repository.custom.EngReviewLogRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngReviewLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngReviewLogRepository extends JpaRepository<EngReviewLog, String>, JpaSpecificationExecutor<EngReviewLog>, EngReviewLogRepositoryCustom {}
