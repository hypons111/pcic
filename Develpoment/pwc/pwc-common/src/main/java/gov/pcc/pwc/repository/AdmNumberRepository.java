package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmNumber;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the AdmNumber entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmNumberRepository extends JpaRepository<AdmNumber, Long> {
    public Optional<AdmNumber> findOneByCodeTypeAndCodeYear(String codeType, String codeYear);

}
