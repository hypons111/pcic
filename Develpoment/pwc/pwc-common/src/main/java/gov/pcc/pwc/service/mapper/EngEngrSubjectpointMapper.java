package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngEngrSubjectpointDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngEngrSubjectpoint} and its DTO {@link EngEngrSubjectpointDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngEngrSubjectpointMapper extends EntityMapper<EngEngrSubjectpointDTO, EngEngrSubjectpoint> {}
