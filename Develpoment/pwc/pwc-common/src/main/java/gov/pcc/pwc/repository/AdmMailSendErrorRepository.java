package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmMailSendError;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmMailSendError entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmMailSendErrorRepository extends JpaRepository<AdmMailSendError, Long> {}
