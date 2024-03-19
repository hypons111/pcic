package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidChkapt;
import gov.pcc.pwc.service.dto.BidChkaptDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidChkapt} and its DTO {@link BidChkaptDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidChkaptMapper extends EntityMapper<BidChkaptDTO, BidChkapt> {}
