package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmSmsSend;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmSmsSend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmSmsSendRepository extends JpaRepository<AdmSmsSend, Long> {}
