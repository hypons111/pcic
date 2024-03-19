package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjMonth;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjMonth} and its DTO {@link BidPrjMonthDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjMonthMapper extends EntityMapper<BidPrjMonthDTO, BidPrjMonth> {}
