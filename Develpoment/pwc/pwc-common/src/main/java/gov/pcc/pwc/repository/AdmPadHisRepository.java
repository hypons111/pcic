package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.domain.AdmPadHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmPadHis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmPadHisRepository extends JpaRepository<AdmPadHis, Long> {}
