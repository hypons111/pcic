package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjMilestone;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjMilestone} and its DTO {@link BidPrjMilestoneDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjMilestoneMapper extends EntityMapper<BidPrjMilestoneDTO, BidPrjMilestone> {}
