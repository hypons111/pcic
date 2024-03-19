package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidPrjEnvi} and its DTO {@link BidPrjEnviDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidPrjEnviMapper extends EntityMapper<BidPrjEnviDTO, BidPrjEnvi> {}
