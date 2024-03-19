package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidGovCheck;
import gov.pcc.pwc.service.dto.BidGovCheckDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidGovCheck} and its DTO {@link BidGovCheckDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidGovCheckMapper extends EntityMapper<BidGovCheckDTO, BidGovCheck> {}
