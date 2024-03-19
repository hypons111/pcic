package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.ProNoCommunicateList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProNoCommunicateList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProNoCommunicateListRepository extends JpaRepository<ProNoCommunicateList, Long> {}
