package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidCcind;
import gov.pcc.pwc.service.dto.BidCcindDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link BidCcind} and its DTO {@link BidCcindDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidCcindMapper extends EntityMapper<BidCcindDTO, BidCcind> {}
