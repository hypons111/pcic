package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidQcReentry;
import gov.pcc.pwc.service.dto.BidQcReentryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidQcReentry} and its DTO {@link BidQcReentryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidQcReentryMapper extends EntityMapper<BidQcReentryDTO, BidQcReentry> {}
