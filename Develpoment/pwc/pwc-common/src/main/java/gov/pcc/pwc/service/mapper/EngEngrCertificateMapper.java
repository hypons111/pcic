package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.EngEngrCertificate;
import gov.pcc.pwc.service.dto.EngEngrCertificateDTO;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

/**
 * Mapper for the entity {@link EngEngrCertificate} and its DTO {@link EngEngrCertificateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngEngrCertificateMapper extends EntityMapper<EngEngrCertificateDTO, EngEngrCertificate> {}

//public final class EngEngrCertificateMapper {
//    private EngEngrCertificateMapper() {};
//
//    public static EngEngrCertificate toEngEngrCertificateEntity(EngEngrCertificateDTO dto) {
//        if(dto == null) return null;
//        final EngEngrCertificate entity = new EngEngrCertificate();
//        BeanUtils.copyProperties(dto, entity);
//        entity.status(dto.getStatus());
//
//        return entity;
//    }
//}