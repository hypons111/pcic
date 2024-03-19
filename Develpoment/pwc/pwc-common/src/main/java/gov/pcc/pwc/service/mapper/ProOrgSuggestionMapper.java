package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.ProOrgSuggestion;
import gov.pcc.pwc.service.dto.ProOrgSuggestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProOrgSuggestion} and its DTO {@link ProOrgSuggestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProOrgSuggestionMapper extends EntityMapper<ProOrgSuggestionDTO, ProOrgSuggestion> {}
