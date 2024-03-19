package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngEbillingCheck;
import gov.pcc.pwc.domain.EngEngrCertificate;
import gov.pcc.pwc.domain.EngEngrCertificatePK;
import gov.pcc.pwc.repository.custom.EngEngrCertificateRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the EngEngrCertificate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngEngrCertificateRepository extends JpaRepository<EngEngrCertificate, EngEngrCertificatePK>, JpaSpecificationExecutor<EngEngrCertificate>, EngEngrCertificateRepositoryCustom {
    List<EngEngrCertificate> findByEngEngrCertificateId(String engEngrCertificateId);
    Optional<EngEngrCertificate> findTopByEngEngrCertificateIdAndEngEngrCertificateVersionGreaterThanOrderByEngEngrCertificateVersion(String id, BigDecimal version);
    List<EngEngrCertificate> findByIdnoAndStatus(String idno, String status);
    List<EngEngrCertificate> findByIdnoAndStatusAndApplyItemsList(String idno, String status, String applyItemsList);
}
