package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.service.dto.CmsCompDTO;

/**
 * Mapper for the entity {@link CmsComp} and its DTO {@link CmsCompDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CmsCompMapper extends EntityMapper<CmsCompDTO, CmsComp> {}
