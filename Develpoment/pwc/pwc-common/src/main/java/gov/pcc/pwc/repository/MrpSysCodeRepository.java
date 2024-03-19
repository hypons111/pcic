package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.MrpSysCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MrpSysCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MrpSysCodeRepository extends JpaRepository<MrpSysCode, Long> {}
