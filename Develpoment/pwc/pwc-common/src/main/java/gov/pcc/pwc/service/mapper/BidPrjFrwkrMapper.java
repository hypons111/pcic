package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjFrwkr;
import gov.pcc.pwc.service.dto.BidPrjFrwkrDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link BidPrjFrwkr} and its DTO {@link BidPrjFrwkrDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjFrwkrMapper extends EntityMapper<BidPrjFrwkrDTO, BidPrjFrwkr> {}
