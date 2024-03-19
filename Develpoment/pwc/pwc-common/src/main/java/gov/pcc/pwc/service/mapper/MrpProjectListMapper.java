package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.MrpProjectList;
import gov.pcc.pwc.service.dto.MrpProjectListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MrpProjectList} and its DTO {@link MrpProjectListDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MrpProjectListMapper extends EntityMapper<MrpProjectListDTO, MrpProjectList> {}
