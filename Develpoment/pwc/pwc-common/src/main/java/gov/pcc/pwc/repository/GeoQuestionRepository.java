package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.domain.GeoQuestion;
import gov.pcc.pwc.repository.custom.GeoQuestionRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GeoQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoQuestionRepository extends JpaRepository<GeoQuestion, Long> , GeoQuestionRepositoryCustom, JpaSpecificationExecutor<GeoQuestion> {}
