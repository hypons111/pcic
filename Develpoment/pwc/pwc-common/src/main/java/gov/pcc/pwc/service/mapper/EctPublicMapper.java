package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.EctPublic;
import gov.pcc.pwc.service.dto.EctPublicDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EctPublic} and its DTO {@link EctPublicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EctPublicMapper extends EntityMapper<EctPublicDTO, EctPublic> {}
