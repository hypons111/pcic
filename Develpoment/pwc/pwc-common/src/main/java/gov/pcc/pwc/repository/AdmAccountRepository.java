package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.AdmAccount;
import gov.pcc.pwc.repository.custom.AdmAccountRepositoryCustom;

/**
 * Spring Data SQL repository for the UserAccount entity.
 */
@Repository
public interface AdmAccountRepository
    extends JpaRepository<AdmAccount, String>, AdmAccountRepositoryCustom, JpaSpecificationExecutor<AdmAccount> {}
