package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidUnit10;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidUnit10} and its DTO {@link BidUnit10DTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidUnit10Mapper extends EntityMapper<BidUnit10DTO, BidUnit10> {}
