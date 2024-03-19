package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.AdmContact;

/**
 * Spring Data SQL repository for the UserAccount entity.
 */
@Repository
public interface AdmContactRepository extends JpaRepository<AdmContact, String> {}
