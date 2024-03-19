package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngCourseDtlTeacherDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngCourseDtlTeacher} and its DTO {@link EngCourseDtlTeacherDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngCourseDtlTeacherMapper extends EntityMapper<EngCourseDtlTeacherDTO, EngCourseDtlTeacher> {}
