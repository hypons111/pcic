package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmPadHis;
import gov.pcc.pwc.service.dto.AdmPadHisDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdmPadHis} and its DTO {@link AdmPadHisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmPadHisMapper extends EntityMapper<AdmPadHisDTO, AdmPadHis> {}
