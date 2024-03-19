package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjWksorc;
import gov.pcc.pwc.service.dto.BidPrjWksorcDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjWksorc} and its DTO {@link BidPrjWksorcDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjWksorcMapper extends EntityMapper<BidPrjWksorcDTO, BidPrjWksorc> {}
