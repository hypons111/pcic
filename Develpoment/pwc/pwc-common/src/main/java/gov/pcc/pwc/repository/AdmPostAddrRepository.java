package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmPostAddr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the AdmPostAddr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmPostAddrRepository extends JpaRepository<AdmPostAddr, Long> {
    Optional<AdmPostAddr> findByAreaCode(String AreaCode);
}
