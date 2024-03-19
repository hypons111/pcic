package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjSur2;
import gov.pcc.pwc.service.dto.BidPrjSur2DTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjSur2} and its DTO {@link BidPrjSur2DTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjSur2Mapper extends EntityMapper<BidPrjSur2DTO, BidPrjSur2> {}
