package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.MrpResourceList;
import gov.pcc.pwc.service.dto.MrpResourceListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MrpResourceList} and its DTO {@link MrpResourceListDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MrpResourceListMapper extends EntityMapper<MrpResourceListDTO, MrpResourceList> {}
