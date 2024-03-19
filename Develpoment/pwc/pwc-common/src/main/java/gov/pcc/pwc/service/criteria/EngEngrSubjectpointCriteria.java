package gov.pcc.pwc.service.criteria;

import com.google.common.base.CaseFormat;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.EngEngrSubjectpoint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class EngEngrSubjectpointCriteria  extends Criteria<EngEngrSubjectpoint> {
    private LocalDate applyDateStart;
    private LocalDate applyDateEnd;
    private String content;
    private String engrName;
    private String idno;
    private String subject;

    @Override
    public Predicate toPredicate(@NotNull Root<EngEngrSubjectpoint> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        return null;
    }

    @Override
    public String getOrderBy() {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, sortBy[0]) + " " + sortDirection.name();
    }
}
