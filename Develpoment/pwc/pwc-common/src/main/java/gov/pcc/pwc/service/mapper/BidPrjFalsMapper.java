package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjFals;
import gov.pcc.pwc.service.dto.BidPrjFalsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjFals} and its DTO {@link BidPrjFalsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjFalsMapper extends EntityMapper<BidPrjFalsDTO, BidPrjFals> {}
