package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjPrice;
import gov.pcc.pwc.service.dto.BidPrjPriceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjPrice} and its DTO {@link BidPrjPriceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjPriceMapper extends EntityMapper<BidPrjPriceDTO, BidPrjPrice> {}
