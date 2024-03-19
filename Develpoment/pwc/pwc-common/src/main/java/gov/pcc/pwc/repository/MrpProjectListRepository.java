package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.MrpProjectList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MrpProjectList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MrpProjectListRepository extends JpaRepository<MrpProjectList, Long> {}
