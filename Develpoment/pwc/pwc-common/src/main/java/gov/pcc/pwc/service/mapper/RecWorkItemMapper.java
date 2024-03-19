package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.RecArea;
import gov.pcc.pwc.domain.RecWorkItem;
import gov.pcc.pwc.service.dto.RecAreaDTO;
import gov.pcc.pwc.service.dto.RecWorkItemDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link RecWorkItem} and its DTO {@link RecWorkItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecWorkItemMapper extends EntityMapper<RecWorkItemDTO, RecWorkItem> {}
