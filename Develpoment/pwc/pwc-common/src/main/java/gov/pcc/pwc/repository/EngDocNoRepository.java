package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngDocNo;
import gov.pcc.pwc.domain.EngDocNoPK;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the EngDocNo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngDocNoRepository extends JpaRepository<EngDocNo, EngDocNoPK> {
    List<EngDocNo> findByEngDocNoTypeOrderByEngDocNoNo(String engDocNoType);
}
