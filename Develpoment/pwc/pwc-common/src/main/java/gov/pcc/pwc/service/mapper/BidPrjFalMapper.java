package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjFal;
import gov.pcc.pwc.service.dto.BidPrjFalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjFal} and its DTO {@link BidPrjFalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjFalMapper extends EntityMapper<BidPrjFalDTO, BidPrjFal> {}
