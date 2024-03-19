package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.AdmMenuDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdmMenu} and its DTO {@link AdmMenuDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmMenuMapper extends EntityMapper<AdmMenuDTO, AdmMenu> {
    @Override
    default AdmMenuDTO toDto(AdmMenu entity) {
        AdmMenuDTO admMenuDTO = new AdmMenuDTO();
        admMenuDTO.setId(entity.getId());
        admMenuDTO.setParentId(entity.getParentId());
        admMenuDTO.setPath(entity.getPath());
        admMenuDTO.getMeta().setDirectory(entity.getDirectory());
        admMenuDTO.getMeta().setFunctionId(entity.getFunctionId());
        admMenuDTO.getMeta().setIcon(entity.getIcon());
        admMenuDTO.getMeta().setLabel(entity.getLabel());
        return admMenuDTO;
    }
}
