package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPlnunitb;
import gov.pcc.pwc.service.dto.BidPlnunitbDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPlnunitb} and its DTO {@link BidPlnunitbDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPlnunitbMapper extends EntityMapper<BidPlnunitbDTO, BidPlnunitb> {}
