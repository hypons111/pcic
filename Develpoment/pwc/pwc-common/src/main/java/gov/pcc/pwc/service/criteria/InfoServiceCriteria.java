package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.PwcInformation;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class InfoServiceCriteria extends Criteria<PwcInformation> {

    //內文搜尋
    private String searchText;
    //狀態
    private String kind;
    //開始日期
    private String startQueryDate;
    //結束日期
    private String endQueryDate;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getStartQueryDate() {
        return startQueryDate;
    }

    public void setStartQueryDate(String startQueryDate) {
        this.startQueryDate = startQueryDate;
    }

    public String getEndQueryDate() {
        return endQueryDate;
    }

    public void setEndQueryDate(String endQueryDate) {
        this.endQueryDate = endQueryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoServiceCriteria that = (InfoServiceCriteria) o;
        return (
            Objects.equal(searchText, that.searchText) &&
            Objects.equal(kind, that.kind) &&
            Objects.equal(startQueryDate, that.startQueryDate) &&
            Objects.equal(endQueryDate, that.endQueryDate)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(searchText, kind, startQueryDate, endQueryDate);
    }

    @Override
    public String toString() {
        return MoreObjects
            .toStringHelper(this)
            .add("perPage", perPage)
            .add("currentPage", currentPage)
            .add("sortBy", sortBy)
            .add("sortDirection", sortDirection)
            .add("searchText", searchText)
            .add("kind", kind)
            .add("startQueryDate", startQueryDate)
            .add("endQueryDate", endQueryDate)
            .toString();
    }

    @Override
    public Predicate toPredicate(Root<PwcInformation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(searchText)) {
            predicates.add(criteriaBuilder.like(root.get("text"), WILD_CARD + searchText + WILD_CARD));
        }
        if (StringUtils.isNotBlank(kind)) {
            predicates.add(criteriaBuilder.like(root.get("kind"), kind));
        }
        if (StringUtils.isNotBlank(startQueryDate)) {
            predicates.add(criteriaBuilder.equal(root.get("release_date"), startQueryDate));
        }
        if (StringUtils.isNotBlank(endQueryDate)) {
            predicates.add(criteriaBuilder.equal(root.get("release_date"), endQueryDate));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + this.getSortDirection().name();
    }
}
