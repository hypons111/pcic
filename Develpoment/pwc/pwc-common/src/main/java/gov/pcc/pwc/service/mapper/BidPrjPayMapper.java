package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjPay;
import gov.pcc.pwc.service.dto.BidPrjPayDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link BidPrjPay} and its DTO {@link BidPrjPayDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjPayMapper extends EntityMapper<BidPrjPayDTO, BidPrjPay> {}
