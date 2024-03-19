package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmCodeTable;
import gov.pcc.pwc.service.dto.AdmCodeTableDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link AdmCodeTable} and its DTO {@link AdmCodeTableDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmCodeTableMapper extends EntityMapper<AdmCodeTableDTO, AdmCodeTable>{}
