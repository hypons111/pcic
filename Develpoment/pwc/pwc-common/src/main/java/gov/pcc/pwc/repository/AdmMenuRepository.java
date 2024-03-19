package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmMenu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmMenuRepository extends JpaRepository<AdmMenu, Long> {}
