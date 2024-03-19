package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.MrpProjectPurchase;
import gov.pcc.pwc.service.dto.MrpProjectPurchaseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MrpProjectPurchase} and its DTO {@link MrpProjectPurchaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MrpProjectPurchaseMapper extends EntityMapper<MrpProjectPurchaseDTO, MrpProjectPurchase> {}
