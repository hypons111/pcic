package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngEbillingCheckDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngEbillingCheck} and its DTO {@link EngEbillingCheckDTO}.
 */
@Mapper(componentModel = "spring")
public interface EngEbillingCheckMapper extends EntityMapper<EngEbillingCheckDTO, EngEbillingCheck> {}
