package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.EctPublicDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.List;

public class ECT0101R2Criteria extends Criteria<EctPublicDTO> {

    private String fileName;
    private String certType;
	private String fileCreateTime;
    private Long ectPublishId;
    private String idno;
    private String isValid;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) { this.certType = certType; }

    public String getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(String fileCreateTime) { this.fileCreateTime = fileCreateTime; }

    public Long getEctPublishId() {
        return ectPublishId;
    }

    public void setEctPublishId(Long ectPublishId) { this.ectPublishId = ectPublishId; }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ECT0101R2Criteria that = (ECT0101R2Criteria) o;
        return Objects.equals(fileName, that.fileName) &&
                Objects.equals(certType, that.certType) &&
				Objects.equals(fileCreateTime, that.fileCreateTime) &&
                Objects.equals(ectPublishId, that.ectPublishId) &&
                Objects.equals(idno, that.idno)&&
                Objects.equals(isValid, that.isValid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, certType, fileCreateTime, ectPublishId, idno, isValid);
    }

    @Override
    public String toString() {
        return "EngCaseTotalDTOCriteria{" +
                "fileName='" + fileName + '\'' +
                ", certType=" + certType +
				", fileCreateTime=" + fileCreateTime +
                ", ectPublishId=" + ectPublishId +
                ", idno=" + idno +
                ", isValid=" + isValid +
                '}';
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<EctPublicDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(fileName)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("fileName"), fileName
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(certType)) { // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("certificateType"), certType
            ));  // 資料庫欄位名稱
        }
		if (StringUtils.isNotBlank(fileCreateTime)) { // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("fileCreateTime"), fileCreateTime
            ));  // 資料庫欄位名稱
        }
        if (ectPublishId != null) { // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("ectPublishId"), ectPublishId
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(idno)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("idno"), idno
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(isValid)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("isValid"), isValid
            ));  // 資料庫欄位名稱
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
