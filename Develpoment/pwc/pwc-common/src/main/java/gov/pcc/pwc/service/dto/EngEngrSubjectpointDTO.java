package gov.pcc.pwc.service.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngEngrSubjectpoint} entity.
 */
public class EngEngrSubjectpointDTO implements Serializable {

    @Size(max = 20)
    private String engEngrSubjectpointId;

    private LocalDate applyDate;

    @Size(max = 20)
    private String engrId;

    @Size(max = 200)
    private String theme;

    @Size(max = 2000)
    private String content;

    @Size(max = 3)
    private String law;

    private BigDecimal point;

    @Size(max = 100)
    private String subjectList;

    @Size(max = 5)
    private String subject;

    @Size(max = 200)
    private String checkDesc;

    @Size(max = 2000)
    private String remark;

    @Size(max = 20)
    private String contractorId;

    @NotNull
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    @Size(max = 50)
    private String chName;

    @Size(max = 50)
    private String enName;

    @Size(max = 20)
    private String idno;

    @Size(max = 50)
    private String contractorName;

    public String getEngEngrSubjectpointId() {
        return engEngrSubjectpointId;
    }

    public void setEngEngrSubjectpointId(String engEngrSubjectpointId) {
        this.engEngrSubjectpointId = engEngrSubjectpointId;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngEngrSubjectpointDTO that = (EngEngrSubjectpointDTO) o;
        return Objects.equal(engEngrSubjectpointId, that.engEngrSubjectpointId) && Objects.equal(applyDate, that.applyDate) && Objects.equal(engrId, that.engrId) && Objects.equal(theme, that.theme) && Objects.equal(content, that.content) && Objects.equal(law, that.law) && Objects.equal(point, that.point) && Objects.equal(subjectList, that.subjectList) && Objects.equal(subject, that.subject) && Objects.equal(checkDesc, that.checkDesc) && Objects.equal(remark, that.remark) && Objects.equal(contractorId, that.contractorId) && Objects.equal(updateTime, that.updateTime) && Objects.equal(updateUser, that.updateUser) && Objects.equal(createTime, that.createTime) && Objects.equal(createUser, that.createUser) && Objects.equal(transferFrom, that.transferFrom) && Objects.equal(transferTime, that.transferTime) && Objects.equal(chName, that.chName) && Objects.equal(enName, that.enName) && Objects.equal(idno, that.idno) && Objects.equal(contractorName, that.contractorName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(engEngrSubjectpointId, applyDate, engrId, theme, content, law, point, subjectList, subject, checkDesc, remark, contractorId, updateTime, updateUser, createTime, createUser, transferFrom, transferTime, chName, enName, idno, contractorName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("engEngrSubjectpointId", engEngrSubjectpointId)
                .add("applyDate", applyDate)
                .add("engrId", engrId)
                .add("theme", theme)
                .add("content", content)
                .add("law", law)
                .add("point", point)
                .add("subjectList", subjectList)
                .add("subject", subject)
                .add("checkDesc", checkDesc)
                .add("remark", remark)
                .add("contractorId", contractorId)
                .add("updateTime", updateTime)
                .add("updateUser", updateUser)
                .add("createTime", createTime)
                .add("createUser", createUser)
                .add("transferFrom", transferFrom)
                .add("transferTime", transferTime)
                .add("chName", chName)
                .add("enName", enName)
                .add("idno", idno)
                .add("contractorName", contractorName)
                .toString();
    }
}
