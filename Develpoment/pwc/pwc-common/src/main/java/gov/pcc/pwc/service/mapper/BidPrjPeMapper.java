package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjPe;
import gov.pcc.pwc.service.dto.BidPrjPeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjPe} and its DTO {@link BidPrjPeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjPeMapper extends EntityMapper<BidPrjPeDTO, BidPrjPe> {}
