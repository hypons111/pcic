package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngCourseDtlDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngCourseDtl} and its DTO {@link EngCourseDtlDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngCourseDtlMapper extends EntityMapper<EngCourseDtlDTO, EngCourseDtl> {}
