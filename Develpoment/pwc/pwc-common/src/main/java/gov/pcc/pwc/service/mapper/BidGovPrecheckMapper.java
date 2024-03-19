package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidGovPrecheck;
import gov.pcc.pwc.service.dto.BidGovPrecheckDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidGovPrecheck} and its DTO {@link BidGovPrecheckDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidGovPrecheckMapper extends EntityMapper<BidGovPrecheckDTO, BidGovPrecheck> {}
