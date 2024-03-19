package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmCertRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmCertRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmCertRecordRepository extends JpaRepository<AdmCertRecord, Long> {}
