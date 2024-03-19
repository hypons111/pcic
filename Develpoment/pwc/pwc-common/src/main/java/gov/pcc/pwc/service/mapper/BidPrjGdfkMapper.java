package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjGdfk;
import gov.pcc.pwc.service.dto.BidPrjGdfkDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjGdfk} and its DTO {@link BidPrjGdfkDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjGdfkMapper extends EntityMapper<BidPrjGdfkDTO, BidPrjGdfk> {}
