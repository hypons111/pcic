package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjRescis;
import gov.pcc.pwc.service.dto.BidPrjRescisDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjRescis} and its DTO {@link BidPrjRescisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjRescisMapper extends EntityMapper<BidPrjRescisDTO, BidPrjRescis> {}
