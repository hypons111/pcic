package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.EngCourseCriteria;
import gov.pcc.pwc.service.dto.EngCourseDTO;
import org.springframework.data.domain.Page;

public interface EngCourseRepositoryCustom {
    Page<EngCourseDTO> findAllByCriteria(EngCourseCriteria criteria);
    EngCourseDTO findDTOById(String engCourseId);

}
