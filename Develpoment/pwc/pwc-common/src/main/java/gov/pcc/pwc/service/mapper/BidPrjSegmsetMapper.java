package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjSegmset;
import gov.pcc.pwc.service.dto.BidPrjSegmsetDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjSegmset} and its DTO {@link BidPrjSegmsetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjSegmsetMapper extends EntityMapper<BidPrjSegmsetDTO, BidPrjSegmset> {}
