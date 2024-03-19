package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngTeacher;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the EngTeacher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngTeacherRepository extends JpaRepository<EngTeacher, String> {
    List<EngTeacher> findByTeacherNameContaining(@Param("teacherName") String teacherName);
}
