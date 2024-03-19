package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.ProEnginList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ProEnginList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProEnginListRepository extends JpaRepository<ProEnginList, Long> {

    List<ProEnginList> deleteByProInnovativeProductId(String proInnovativeProductId);

}
