package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.TecContent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TecContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TecContentRepository extends JpaRepository<TecContent, Long> {}
