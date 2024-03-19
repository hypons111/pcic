package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.EctCheck;
import gov.pcc.pwc.service.dto.EctCheckDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EctCheck} and its DTO {@link EctCheckDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EctCheckMapper extends EntityMapper<EctCheckDTO, EctCheck> {}
