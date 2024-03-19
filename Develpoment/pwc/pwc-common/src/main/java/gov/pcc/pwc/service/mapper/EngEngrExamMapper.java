package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngEngrExamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngEngrExam} and its DTO {@link EngEngrExamDTO}.
 */
@Mapper(componentModel = "spring")
public interface EngEngrExamMapper extends EntityMapper<EngEngrExamDTO, EngEngrExam> {}
