package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.ProInnovativeProduct;
import gov.pcc.pwc.service.dto.ProInnovativeProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProInnovativeProduct} and its DTO {@link ProInnovativeProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProInnovativeProductMapper extends EntityMapper<ProInnovativeProductDTO, ProInnovativeProduct> {}

