package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwbUsualfunction;
import gov.pcc.pwc.repository.custom.PwbUsualfunctionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the PwbUsualfunction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwbUsualfunctionRepository extends JpaRepository<PwbUsualfunction, Long>, PwbUsualfunctionRepositoryCustom, JpaSpecificationExecutor<PwbUsualfunction> {
    List<PwbUsualfunction> findByUserId(String userId);

}
