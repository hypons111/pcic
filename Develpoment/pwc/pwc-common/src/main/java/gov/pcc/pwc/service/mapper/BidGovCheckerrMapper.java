package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidGovCheckerr;
import gov.pcc.pwc.service.dto.BidGovCheckerrDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidGovCheckerr} and its DTO {@link BidGovCheckerrDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidGovCheckerrMapper extends EntityMapper<BidGovCheckerrDTO, BidGovCheckerr> {}
