package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmCodeTableDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AdmCodeTableCriteria extends Criteria<AdmCodeTableDTO> {

    private String lang;

    private String category;

    private String code;

    private String text;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmCodeTableCriteria that = (AdmCodeTableCriteria) o;
        return Objects.equals(lang, that.lang) && Objects.equals(category, that.category) && Objects.equals(code, that.code) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lang, category, code, text);
    }

    @Override
    public Predicate toPredicate(Root<AdmCodeTableDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(lang)) {
            predicates.add(criteriaBuilder.equal(root.get("lang"), lang));
        }
        if (StringUtils.isNotBlank(category)) {
            predicates.add(criteriaBuilder.equal(root.get("category"), category));
        }
        if (StringUtils.isNotBlank(code)) {
            predicates.add(criteriaBuilder.equal(root.get("code"), code));
        }
        if (StringUtils.isNotBlank(text)) {
            predicates.add(criteriaBuilder.equal(root.get("text"), text));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("lang")) {
            sortBy = "lang";
        } else if (this.getSortBy()[0].equals("category")) {
            sortBy = "category";
        } else if (this.getSortBy()[0].equals("code")) {
            sortBy = "code";
        } else if (this.getSortBy()[0].equals("text")) {
            sortBy = "text";
        } else {
            sortBy = "lang";
        }
        return sortBy + " " + this.getSortDirection().name();
    }
}
