package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidProjectFile;
import gov.pcc.pwc.service.dto.BidProjectFileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidProjectFile} and its DTO {@link BidProjectFileDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidProjectFileMapper extends EntityMapper<BidProjectFileDTO, BidProjectFile> {}
