package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmFunctionUsedRecord;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdmFunctionUsedRecordMapper extends EntityMapper<AdmFunctionUsedRecordDTO, AdmFunctionUsedRecord> {}