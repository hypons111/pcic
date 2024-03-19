package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjGph;
import gov.pcc.pwc.service.dto.BidPrjGphDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjGph} and its DTO {@link BidPrjGphDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjGphMapper extends EntityMapper<BidPrjGphDTO, BidPrjGph> {}
