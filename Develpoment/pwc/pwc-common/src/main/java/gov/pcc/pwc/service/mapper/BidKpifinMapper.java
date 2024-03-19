package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidKpifin;
import gov.pcc.pwc.service.dto.BidKpifinDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidKpifin} and its DTO {@link BidKpifinDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidKpifinMapper extends EntityMapper<BidKpifinDTO, BidKpifin> {}
