package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidProjectUnit;
import gov.pcc.pwc.service.dto.BidProjectUnitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidProjectUnit} and its DTO {@link BidProjectUnitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidProjectUnitMapper extends EntityMapper<BidProjectUnitDTO, BidProjectUnit> {}
