package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjScequip;
import gov.pcc.pwc.service.dto.BidPrjScequipDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjScequip} and its DTO {@link BidPrjScequipDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjScequipMapper extends EntityMapper<BidPrjScequipDTO, BidPrjScequip> {}
