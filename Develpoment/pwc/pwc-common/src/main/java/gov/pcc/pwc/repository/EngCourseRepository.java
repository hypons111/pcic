package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngCourse;
import gov.pcc.pwc.repository.custom.EngCourseRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngCourse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngCourseRepository extends JpaRepository<EngCourse, String>, JpaSpecificationExecutor<EngCourse>, EngCourseRepositoryCustom {}
