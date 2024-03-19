package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjSafe;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjSafe} and its DTO {@link BidPrjSafeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjSafeMapper extends EntityMapper<BidPrjSafeDTO, BidPrjSafe> {}
