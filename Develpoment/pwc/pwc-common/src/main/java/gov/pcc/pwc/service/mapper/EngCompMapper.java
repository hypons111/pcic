package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.EngComp;
import gov.pcc.pwc.service.dto.EngCompDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngComp} and its DTO {@link EngCompDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngCompMapper extends EntityMapper<EngCompDTO, EngComp> {}
