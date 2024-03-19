package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.RecTyphoon;
import gov.pcc.pwc.repository.custom.RecTyphoonRepositoryCustom;

/**
 * Spring Data SQL repository for the RecTyphoon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecTyphoonRepository extends JpaRepository<RecTyphoon, String>, RecTyphoonRepositoryCustom, JpaSpecificationExecutor<RecTyphoon> {}
