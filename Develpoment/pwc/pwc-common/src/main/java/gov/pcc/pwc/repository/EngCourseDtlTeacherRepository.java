package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngCourseDtlTeacher;
import gov.pcc.pwc.domain.EngCourseDtlTeacherPK;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the EngCourseDtlTeacher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngCourseDtlTeacherRepository extends JpaRepository<EngCourseDtlTeacher, EngCourseDtlTeacherPK> {
    List<EngCourseDtlTeacher> findByEngCourseId(String engCourseId);
}
