package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmHolidayDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AdmHolidayCriteria extends Criteria<AdmHolidayDTO> {

    private Instant holidayDate;

    private String holidayName;

    private Instant dateStart;

    private Instant dateEnd;

    public Instant getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Instant holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Instant getDateStart() {
        return dateStart;
    }

    public void setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmHolidayCriteria that = (AdmHolidayCriteria) o;
        return Objects.equals(holidayDate, that.holidayDate) && Objects.equals(holidayName, that.holidayName) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holidayDate, holidayName, dateStart, dateEnd);
    }

    @Override
    public Predicate toPredicate(Root<AdmHolidayDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        if (holidayDate != null) {
            predicates.add(criteriaBuilder.equal(root.get("holidayDate"), holidayDate));
        }
        if (StringUtils.isNotBlank(holidayName)) {
            predicates.add(criteriaBuilder.like(root.get("holidayName"), WILD_CARD + holidayName + WILD_CARD));
        }
        if (dateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateStart"), dateStart));
        }
        if (dateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateEnd"), dateEnd));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("holidayDate")) {
            sortBy = "holiday_date";
        } else if (this.getSortBy()[0].equals("holidayName")) {
            sortBy = "holiday_name";
        } else {
            sortBy = "holiday_date";
        }
        return sortBy + " " + this.getSortDirection().name();
    }
}
