package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjPay2;
import gov.pcc.pwc.service.dto.BidPrjPay2DTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjPay2} and its DTO {@link BidPrjPay2DTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjPay2Mapper extends EntityMapper<BidPrjPay2DTO, BidPrjPay2> {}
