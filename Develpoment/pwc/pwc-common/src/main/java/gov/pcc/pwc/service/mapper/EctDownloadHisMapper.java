package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.EctDownloadHis;
import gov.pcc.pwc.service.dto.EctDownloadHisDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EctDownloadHis} and its DTO {@link EctDownloadHisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EctDownloadHisMapper extends EntityMapper<EctDownloadHisDTO, EctDownloadHis> {}
