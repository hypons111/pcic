package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjDelete;
import gov.pcc.pwc.service.dto.BidPrjDeleteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjDelete} and its DTO {@link BidPrjDeleteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjDeleteMapper extends EntityMapper<BidPrjDeleteDTO, BidPrjDelete> {}
