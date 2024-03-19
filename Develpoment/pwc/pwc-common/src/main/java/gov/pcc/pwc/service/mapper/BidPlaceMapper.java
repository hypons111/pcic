package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPlace;
import gov.pcc.pwc.service.dto.BidPlaceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPlace} and its DTO {@link BidPlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPlaceMapper extends EntityMapper<BidPlaceDTO, BidPlace> {}
