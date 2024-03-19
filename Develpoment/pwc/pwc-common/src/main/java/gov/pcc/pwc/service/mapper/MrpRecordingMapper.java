package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.MrpRecording;
import gov.pcc.pwc.service.dto.MrpRecordingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MrpRecording} and its DTO {@link MrpRecordingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MrpRecordingMapper extends EntityMapper<MrpRecordingDTO, MrpRecording> {}
