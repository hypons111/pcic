package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjStc;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjStc} and its DTO {@link BidPrjStcDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjStcMapper extends EntityMapper<BidPrjStcDTO, BidPrjStc> {}
