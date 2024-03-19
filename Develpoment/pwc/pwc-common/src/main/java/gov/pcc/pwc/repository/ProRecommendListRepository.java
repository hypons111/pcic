package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.ProRecommendList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ProRecommendList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProRecommendListRepository extends JpaRepository<ProRecommendList, Long> {

    List<ProRecommendList> deleteByProInnovativeProductId(String proInnovativeProductId);
}
