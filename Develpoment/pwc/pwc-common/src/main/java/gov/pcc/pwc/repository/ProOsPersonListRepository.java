package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.ProOsPersonList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProOsPersonList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProOsPersonListRepository extends JpaRepository<ProOsPersonList, Long> {}
