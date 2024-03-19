package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjEquip;
import gov.pcc.pwc.service.dto.BidPrjEquipDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjEquip} and its DTO {@link BidPrjEquipDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjEquipMapper extends EntityMapper<BidPrjEquipDTO, BidPrjEquip> {}
