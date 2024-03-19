package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmSmsSendError;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmSmsSendError entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmSmsSendErrorRepository extends JpaRepository<AdmSmsSendError, Long> {}
