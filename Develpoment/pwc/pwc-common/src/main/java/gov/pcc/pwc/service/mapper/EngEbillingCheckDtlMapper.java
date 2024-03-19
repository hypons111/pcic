package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngEbillingCheckDtlDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngEbillingCheckDtl} and its DTO {@link EngEbillingCheckDtlDTO}.
 */
@Mapper(componentModel = "spring")
public interface EngEbillingCheckDtlMapper extends EntityMapper<EngEbillingCheckDtlDTO, EngEbillingCheckDtl> {}
