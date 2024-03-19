package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidOversee;
import gov.pcc.pwc.service.dto.BidOverseeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidOversee} and its DTO {@link BidOverseeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidOverseeMapper extends EntityMapper<BidOverseeDTO, BidOversee> {}
