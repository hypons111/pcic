package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjRatecf;
import gov.pcc.pwc.service.dto.BidPrjRatecfDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjRatecf} and its DTO {@link BidPrjRatecfDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjRatecfMapper extends EntityMapper<BidPrjRatecfDTO, BidPrjRatecf> {}
