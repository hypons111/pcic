package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjDsptch;
import gov.pcc.pwc.service.dto.BidPrjDsptchDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjDsptch} and its DTO {@link BidPrjDsptchDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjDsptchMapper extends EntityMapper<BidPrjDsptchDTO, BidPrjDsptch> {}
