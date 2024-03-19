package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjWkmeng;
import gov.pcc.pwc.service.dto.BidPrjWkmengDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjWkmeng} and its DTO {@link BidPrjWkmengDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjWkmengMapper extends EntityMapper<BidPrjWkmengDTO, BidPrjWkmeng> {}
