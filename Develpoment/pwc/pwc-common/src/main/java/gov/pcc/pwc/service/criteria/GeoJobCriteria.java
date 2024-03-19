package gov.pcc.pwc.service.criteria;

import com.google.common.base.CaseFormat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.GeoJob;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class GeoJobCriteria extends Criteria<GeoJob> {

    private String publishStatus;
    private String jobKeyWord;
    private String workPlaceList;
    private String jobKind;
    private String fileName;
    private LocalDate publishDate1;
    private LocalDate publishDate2;



    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getJobKeyWord() {
        return jobKeyWord;
    }

    public void setJobKeyWord(String jobKeyWord) {
        this.jobKeyWord = jobKeyWord;
    }

    public String getWorkPlaceList() {
        return workPlaceList;
    }

    public void setWorkPlaceList(String workPlaceList) {
        this.workPlaceList = workPlaceList;
    }

    public String getJobKind() {
        return jobKind;
    }

    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
    }

    public LocalDate getPublishDate1() {
        return publishDate1;
    }

    public void setPublishDate1(LocalDate publishDate1) {
        this.publishDate1 = publishDate1;
    }

    public LocalDate getPublishDate2() {
        return publishDate2;
    }

    public void setPublishDate2(LocalDate publishDate2) {
        this.publishDate2 = publishDate2;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoJobCriteria that = (GeoJobCriteria) o;
        return
                Objects.equal(publishStatus, that.publishStatus)&&
                Objects.equal(jobKeyWord, that.jobKeyWord)&&
                Objects.equal(workPlaceList, that.workPlaceList)&&
                Objects.equal(jobKind, that.jobKind)&&
                Objects.equal(fileName, that.fileName)&&
                Objects.equal(publishDate1, that.publishDate1)&&
                Objects.equal(publishDate2, that.publishDate2);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(publishStatus, jobKeyWord, workPlaceList, jobKind, publishDate1, publishDate2, fileName);
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("perPage", perPage)
                .add("currentPage", currentPage)
                .add("sortBy", sortBy)
                .add("sortDirection", sortDirection)
                .add("publishStatus", publishStatus)
                .add("jobKeyWord", jobKeyWord)
                .add("workPlaceList", workPlaceList)
                .add("jobKind", jobKind)
                .add("fileName", fileName)
                .add("publishDate1", publishDate1)
                .add("publishDate2", publishDate2)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<GeoJob> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(publishStatus)) {
        predicates.add(criteriaBuilder.equal(root.get("publishStatus"), publishStatus));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, sortBy[0]) + " " + sortDirection.name();
    }
}
