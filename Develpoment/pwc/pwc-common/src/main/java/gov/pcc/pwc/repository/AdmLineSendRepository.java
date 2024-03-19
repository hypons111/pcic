package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmLineSend;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmLineSend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmLineSendRepository extends JpaRepository<AdmLineSend, Long> {}
