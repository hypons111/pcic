package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmFile;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the AdmFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmFileRepository extends JpaRepository<AdmFile, Long> {
    Optional<AdmFile> findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId(String moduleType, String businessType, String sourceId);
    List<AdmFile> findByModuleTypeAndBusinessTypeAndSourceIdLikeOrderBySourceId( @Param("moduleType") String moduleType, @Param("businessType") String businessType, @Param("sourceId") String sourceId);
}
