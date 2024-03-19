package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.MrpSynonym;
import gov.pcc.pwc.service.dto.MrpSynonymDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MrpSynonym} and its DTO {@link MrpSynonymDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MrpSynonymMapper extends EntityMapper<MrpSynonymDTO, MrpSynonym> {}
