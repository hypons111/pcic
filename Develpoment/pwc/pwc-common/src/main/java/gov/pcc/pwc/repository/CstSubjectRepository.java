package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.CstSubject;

/**
 * Spring Data SQL repository for the CstSubject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CstSubjectRepository extends JpaRepository<CstSubject, Long> {}
