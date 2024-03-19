package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmImportRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmImportRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmImportRecordRepository extends JpaRepository<AdmImportRecord, Long> {}
