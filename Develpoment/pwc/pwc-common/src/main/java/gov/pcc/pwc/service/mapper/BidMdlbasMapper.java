package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidMdlbas;
import gov.pcc.pwc.service.dto.BidMdlbasDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidMdlbas} and its DTO {@link BidMdlbasDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidMdlbasMapper extends EntityMapper<BidMdlbasDTO, BidMdlbas> {}
