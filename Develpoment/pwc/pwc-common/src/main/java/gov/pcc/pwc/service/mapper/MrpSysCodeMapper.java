package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.MrpSysCode;
import gov.pcc.pwc.service.dto.MrpSysCodeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MrpSysCode} and its DTO {@link MrpSysCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MrpSysCodeMapper extends EntityMapper<MrpSysCodeDTO, MrpSysCode> {}
