package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.ProEnginList;
import gov.pcc.pwc.service.dto.ProEnginListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProEnginList} and its DTO {@link ProEnginListDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProEnginListMapper extends EntityMapper<ProEnginListDTO, ProEnginList> {}
