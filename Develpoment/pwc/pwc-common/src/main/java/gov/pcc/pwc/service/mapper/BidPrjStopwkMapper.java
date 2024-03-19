package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjStopwk;
import gov.pcc.pwc.service.dto.BidPrjStopwkDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjStopwk} and its DTO {@link BidPrjStopwkDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjStopwkMapper extends EntityMapper<BidPrjStopwkDTO, BidPrjStopwk> {}
