package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.pcc.pwc.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
