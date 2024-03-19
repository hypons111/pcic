package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmHoliday;
import gov.pcc.pwc.repository.custom.AdmHolidayRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmHoliday entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmHolidayRepository extends JpaRepository<AdmHoliday, Long>, AdmHolidayRepositoryCustom, JpaSpecificationExecutor<AdmHoliday> {
    void deleteById(Long id);
}
