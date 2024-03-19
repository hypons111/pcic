package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmBatchService;
import gov.pcc.pwc.repository.custom.AdmBatchServiceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the AdmBatchService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmBatchServiceRepository extends JpaRepository<AdmBatchService, String>, AdmBatchServiceRepositoryCustom, JpaSpecificationExecutor<AdmBatchService> {
    Optional<AdmBatchService> findById(String batchServiceNo);
    List<AdmBatchService> findAllByBatchServiceStatus(String batchServiceStatus);
}
