package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.PwbUsualfunction;
import gov.pcc.pwc.service.dto.PwbUsualfunctionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PwbUsualfunction} and its DTO {@link PwbUsualfunctionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwbUsualfunctionMapper extends EntityMapper<PwbUsualfunctionDTO, PwbUsualfunction> {}
