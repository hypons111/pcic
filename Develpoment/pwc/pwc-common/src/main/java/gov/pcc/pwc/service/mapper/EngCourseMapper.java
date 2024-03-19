package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngCourseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngCourse} and its DTO {@link EngCourseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngCourseMapper extends EntityMapper<EngCourseDTO, EngCourse> {}
