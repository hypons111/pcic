package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmLineSendError;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmLineSendError entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmLineSendErrorRepository extends JpaRepository<AdmLineSendError, Long> {}
