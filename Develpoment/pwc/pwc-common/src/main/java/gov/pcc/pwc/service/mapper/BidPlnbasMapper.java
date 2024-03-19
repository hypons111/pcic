package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPlnbas;
import gov.pcc.pwc.service.dto.BidPlnbasDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPlnbas} and its DTO {@link BidPlnbasDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPlnbasMapper extends EntityMapper<BidPlnbasDTO, BidPlnbas> {}
