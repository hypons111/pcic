package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmBatchRecord;
import gov.pcc.pwc.repository.custom.AdmBatchRecordRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmBatchRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmBatchRecordRepository extends JpaRepository<AdmBatchRecord, Long>, AdmBatchRecordRepositoryCustom, JpaSpecificationExecutor<AdmBatchRecord> {}
