package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngDocNoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngDocNo} and its DTO {@link EngDocNoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngDocNoMapper extends EntityMapper<EngDocNoDTO, EngDocNo> {}
