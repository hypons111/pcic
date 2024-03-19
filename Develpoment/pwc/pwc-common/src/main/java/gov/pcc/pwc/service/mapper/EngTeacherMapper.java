package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngTeacherDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngTeacher} and its DTO {@link EngTeacherDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngTeacherMapper extends EntityMapper<EngTeacherDTO, EngTeacher> {}
