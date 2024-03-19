package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.ProNoCommunicateList;
import gov.pcc.pwc.service.dto.ProNoCommunicateListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProNoCommunicateList} and its DTO {@link ProNoCommunicateListDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProNoCommunicateListMapper extends EntityMapper<ProNoCommunicateListDTO, ProNoCommunicateList> {}
