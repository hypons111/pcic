package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjQc;
import gov.pcc.pwc.service.dto.BidPrjQcDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjQc} and its DTO {@link BidPrjQcDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjQcMapper extends EntityMapper<BidPrjQcDTO, BidPrjQc> {}
