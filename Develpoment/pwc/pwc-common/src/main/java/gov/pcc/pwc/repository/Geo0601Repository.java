package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.GeoQuestion;
import gov.pcc.pwc.repository.custom.Geo0601RepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GeoJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Geo0601Repository extends JpaRepository<GeoQuestion, Long> ,Geo0601RepositoryCustom {}
