package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmFunctionUsedRecord;
import gov.pcc.pwc.repository.custom.AdmFunctionUsedRecordRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the AdmFunctionUsedRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmFunctionUsedRecordRepository extends JpaRepository<AdmFunctionUsedRecord, Long>, AdmFunctionUsedRecordRepositoryCustom, JpaSpecificationExecutor<AdmFunctionUsedRecord> {
    Optional<AdmFunctionUsedRecord> findTopByTableNameAndTableKeyOrderByCreateTimeDesc(String tableName, String tableKey);
}
