package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BidPlaceCriteria extends Criteria<BidProjectDTO> {

    private String placeCode;

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPlaceCriteria that = (BidPlaceCriteria) o;
        return Objects.equals(placeCode, that.placeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeCode);
    }

    @Override
    public Predicate toPredicate(Root<BidProjectDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        if (StringUtils.isNotBlank(placeCode)) {
            predicates.add(criteriaBuilder.like(root.get("placeCode"), placeCode + WILD_CARD));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("placeCode")) {
            sortBy = "place_code";
        } else {
            sortBy = "place_code";
        }
        return sortBy + " " + this.getSortDirection().name();
    }
}
