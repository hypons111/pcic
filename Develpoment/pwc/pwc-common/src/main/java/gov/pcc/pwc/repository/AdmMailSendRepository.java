package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.repository.custom.AdmMailSendRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmMailSend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmMailSendRepository extends JpaRepository<AdmMailSend, Long> , AdmMailSendRepositoryCustom, JpaSpecificationExecutor<AdmMailSend> {}
