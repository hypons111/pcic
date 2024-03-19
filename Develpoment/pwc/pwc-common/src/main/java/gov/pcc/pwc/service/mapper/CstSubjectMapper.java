package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.CstSubject;
import gov.pcc.pwc.service.dto.CstSubjectDTO;

/**
 * Mapper for the entity {@link CstSubject} and its DTO {@link CstSubjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CstSubjectMapper extends EntityMapper<CstSubjectDTO, CstSubject> {}
