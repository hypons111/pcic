package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.ProOsPersonList;
import gov.pcc.pwc.service.dto.ProOsPersonListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProOsPersonList} and its DTO {@link ProOsPersonListDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProOsPersonListMapper extends EntityMapper<ProOsPersonListDTO, ProOsPersonList> {}
