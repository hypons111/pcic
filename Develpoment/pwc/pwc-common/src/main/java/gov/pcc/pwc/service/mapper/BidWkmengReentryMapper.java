package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidWkmengReentry;
import gov.pcc.pwc.service.dto.BidWkmengReentryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidWkmengReentry} and its DTO {@link BidWkmengReentryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidWkmengReentryMapper extends EntityMapper<BidWkmengReentryDTO, BidWkmengReentry> {}
