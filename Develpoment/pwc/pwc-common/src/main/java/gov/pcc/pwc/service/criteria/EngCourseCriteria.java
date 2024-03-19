package gov.pcc.pwc.service.criteria;

import com.google.common.base.CaseFormat;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.EngCourse;
import org.apache.commons.lang3.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class EngCourseCriteria extends Criteria<EngCourse> {
    private String courseName;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String adminCompName;
    private String docNo;
    private String status;
    private LocalDate reviewDateStart;
    private LocalDate reviewDateEnd;

    @Override
    public String getOrderBy() {
        List<String> list = Arrays.stream(sortBy).map(s -> CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, s) + " " + sortDirection.name()).collect(Collectors.toList());
        return StringUtils.join(list, ", ");
    }

    @Override
    public Predicate toPredicate(@NotNull Root<EngCourse> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(courseName)) {
            predicates.add(criteriaBuilder.like(root.get("courseName"), WILD_CARD + courseName + WILD_CARD));
        }
        if (dateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateStart"), dateStart));
        }
        if (dateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateEnd"), dateEnd));
        }
        if (StringUtils.isNotBlank(adminCompName)) {
            predicates.add(criteriaBuilder.like(root.get("adminCompName"), WILD_CARD + adminCompName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(docNo)) {
            predicates.add(criteriaBuilder.equal(root.get("docNo"), docNo));
        }
        if (StringUtils.isNotBlank(status)) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }
        if (reviewDateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("reviewTime"), reviewDateStart));
        }
        if (reviewDateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("reviewTime"), reviewDateEnd));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
