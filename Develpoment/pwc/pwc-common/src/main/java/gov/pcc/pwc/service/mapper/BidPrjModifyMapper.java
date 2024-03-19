package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjModify;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link BidPrjModify} and its DTO {@link BidPrjModifyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjModifyMapper extends EntityMapper<BidPrjModifyDTO, BidPrjModify> {}
