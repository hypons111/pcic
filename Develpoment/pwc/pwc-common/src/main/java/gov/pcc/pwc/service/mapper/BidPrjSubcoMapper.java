package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjSubco;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjSubco} and its DTO {@link BidPrjSubcoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjSubcoMapper extends EntityMapper<BidPrjSubcoDTO, BidPrjSubco> {}
