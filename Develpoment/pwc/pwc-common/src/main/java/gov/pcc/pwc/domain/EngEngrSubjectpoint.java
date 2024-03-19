package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngEngrSubjectpoint.
 */
@Entity
@Table(name = "eng_engr_subjectpoint")
public class EngEngrSubjectpoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_engr_subjectpoint_id", length = 20, nullable = false)
    private String engEngrSubjectpointId;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Size(max = 20)
    @Column(name = "engr_id", length = 20)
    private String engrId;

    @Size(max = 200)
    @Column(name = "theme", length = 200)
    private String theme;

    @Size(max = 2000)
    @Column(name = "content", length = 2000)
    private String content;

    @Size(max = 3)
    @Column(name = "law", length = 3)
    private String law;

    @Column(name = "point", precision = 21, scale = 2)
    private BigDecimal point;

    @Size(max = 100)
    @Column(name = "subject_list", length = 100)
    private String subjectList;

    @Size(max = 5)
    @Column(name = "subject", length = 5)
    private String subject;

    @Size(max = 200)
    @Column(name = "check_desc", length = 200)
    private String checkDesc;

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

    @Size(max = 20)
    @Column(name = "contractor_id", length = 20)
    private String contractorId;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngEngrSubjectpointId() {
        return this.engEngrSubjectpointId;
    }

    public EngEngrSubjectpoint engEngrSubjectpointId(String engEngrSubjectpointId) {
        this.setEngEngrSubjectpointId(engEngrSubjectpointId);
        return this;
    }

    public void setEngEngrSubjectpointId(String engEngrSubjectpointId) {
        this.engEngrSubjectpointId = engEngrSubjectpointId;
    }

    public LocalDate getApplyDate() {
        return this.applyDate;
    }

    public EngEngrSubjectpoint applyDate(LocalDate applyDate) {
        this.setApplyDate(applyDate);
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getEngrId() {
        return this.engrId;
    }

    public EngEngrSubjectpoint engrId(String engrId) {
        this.setEngrId(engrId);
        return this;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public String getTheme() {
        return this.theme;
    }

    public EngEngrSubjectpoint theme(String theme) {
        this.setTheme(theme);
        return this;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return this.content;
    }

    public EngEngrSubjectpoint content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLaw() {
        return this.law;
    }

    public EngEngrSubjectpoint law(String law) {
        this.setLaw(law);
        return this;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public BigDecimal getPoint() {
        return this.point;
    }

    public EngEngrSubjectpoint point(BigDecimal point) {
        this.setPoint(point);
        return this;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public String getSubjectList() {
        return this.subjectList;
    }

    public EngEngrSubjectpoint subjectList(String subjectList) {
        this.setSubjectList(subjectList);
        return this;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getSubject() {
        return this.subject;
    }

    public EngEngrSubjectpoint subject(String subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCheckDesc() {
        return this.checkDesc;
    }

    public EngEngrSubjectpoint checkDesc(String checkDesc) {
        this.setCheckDesc(checkDesc);
        return this;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngEngrSubjectpoint remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContractorId() {
        return this.contractorId;
    }

    public EngEngrSubjectpoint contractorId(String contractorId) {
        this.setContractorId(contractorId);
        return this;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngEngrSubjectpoint updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngEngrSubjectpoint updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngEngrSubjectpoint createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngEngrSubjectpoint createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngEngrSubjectpoint transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngEngrSubjectpoint transferTime(Instant transferTime) {
        this.setTransferTime(transferTime);
        return this;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngEngrSubjectpoint)) {
            return false;
        }
        return engEngrSubjectpointId != null && engEngrSubjectpointId.equals(((EngEngrSubjectpoint) o).engEngrSubjectpointId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEngrSubjectpoint{" +
            "engEngrSubjectpointId=" + getEngEngrSubjectpointId() +
            ", applyDate='" + getApplyDate() + "'" +
            ", engrId='" + getEngrId() + "'" +
            ", theme='" + getTheme() + "'" +
            ", content='" + getContent() + "'" +
            ", law='" + getLaw() + "'" +
            ", point=" + getPoint() +
            ", subjectList='" + getSubjectList() + "'" +
            ", subject='" + getSubject() + "'" +
            ", checkDesc='" + getCheckDesc() + "'" +
            ", remark='" + getRemark() + "'" +
            ", contractorId='" + getContractorId() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
