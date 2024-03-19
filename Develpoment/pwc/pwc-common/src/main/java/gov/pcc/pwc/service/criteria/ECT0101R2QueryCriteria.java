package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.EctPublicDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ECT0101R2QueryCriteria extends Criteria<EctPublicDTO> {

    private String certType;
    private String idno;

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ECT0101R2QueryCriteria that = (ECT0101R2QueryCriteria) o;
        return Objects.equals(certType, that.certType) && Objects.equals(idno, that.idno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(certType, idno);
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<EctPublicDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(certType)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("certType"), certType
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(idno)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("idno"), idno
            ));  // 資料庫欄位名稱
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String toString() {
        return "Eng0822R1DTOCriteria{" +
                "certType='" + certType + '\'' +
                ", idno='" + idno + '\'' +
                '}';
    }
}
