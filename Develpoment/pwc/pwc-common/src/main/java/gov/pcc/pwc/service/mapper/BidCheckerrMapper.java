package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidCheckerr;
import gov.pcc.pwc.service.dto.BidCheckerrDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidCheckerr} and its DTO {@link BidCheckerrDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidCheckerrMapper extends EntityMapper<BidCheckerrDTO, BidCheckerr> {}
