package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngCourseDtl;
import gov.pcc.pwc.domain.EngCourseDtlPK;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the EngCourseDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngCourseDtlRepository extends JpaRepository<EngCourseDtl, EngCourseDtlPK> {
    List<EngCourseDtl> findByEngCourseId(String engCourseId);
}
