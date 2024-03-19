package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EctDownloadHis} entity.
 */
public class ECT0301DTO implements Serializable {

    @NotNull
    private Long ectDownloadHisId;

    @NotNull
    private Integer certificateType;

    @NotNull
    private Long ectPublishId;

    @NotNull
    private Long certificateNo;

    private Instant lastDownloadTime;

    @Size(max = 50)
    private String sourceIp;

    @NotNull
    @Size(max = 40)
    private String createUser;

    @NotNull
    private Instant createTime;

    @Size(max = 40)
    private String updateUser;

    private Instant updateTime;

    @Size(max = 50)
    private String name;

    @Size(max = 60)
    private String rpName;

    @Size(max = 30)
    private String idno;

    private LocalDate birthDate;

    private String sex;

    @NotNull
    private Integer applyItem;

    @NotNull
    @Size(max = 30)
    private String certificateWord;

    @Size(max = 100)
    private String qtclassName;

    @Size(max = 100)
    private String classes;

    @NotNull
    private Integer certificateState;

    @Size(max = 100)
    private String praComp;

    @Size(max = 60)
    private String compName;

    @NotNull
    @Size(max = 10)
    private String publicYearMonth;

    @Size(max = 300)
    private String classificationCname;

    @Size(max = 10)
    private String compBan;

    public Long getEctDownloadHisId() {
        return ectDownloadHisId;
    }

    public void setEctDownloadHisId(Long ectDownloadHisId) {
        this.ectDownloadHisId = ectDownloadHisId;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public Long getEctPublishId() {
        return ectPublishId;
    }

    public void setEctPublishId(Long ectPublishId) {
        this.ectPublishId = ectPublishId;
    }

    public Long getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(Long certificateNo) {
        this.certificateNo = certificateNo;
    }

    public Instant getLastDownloadTime() {
        return lastDownloadTime;
    }

    public void setLastDownloadTime(Instant lastDownloadTime) {
        this.lastDownloadTime = lastDownloadTime;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRpName() {
        return rpName;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getApplyItem() {
        return applyItem;
    }

    public void setApplyItem(Integer applyItem) {
        this.applyItem = applyItem;
    }

    public String getCertificateWord() {
        return certificateWord;
    }

    public void setCertificateWord(String certificateWord) {
        this.certificateWord = certificateWord;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getQtclassName() {
        return qtclassName;
    }

    public void setQtclassName(String qtclassName) {
        this.qtclassName = qtclassName;
    }

    public Integer getCertificateState() {
        return certificateState;
    }

    public void setCertificateState(Integer certificateState) {
        this.certificateState = certificateState;
    }
    public String getPraComp() {
        return praComp;
    }

    public void setPraComp(String praComp) {
        this.praComp = praComp;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getPublicYearMonth() {
        return publicYearMonth;
    }

    public void setPublicYearMonth(String publicYearMonth) {
        this.publicYearMonth = publicYearMonth;
    }

    public String getClassificationCname() {
        return classificationCname;
    }

    public void setClassificationCname(String classificationCname) {
        this.classificationCname = classificationCname;
    }

    public String getCompBan() {
        return compBan;
    }

    public void setCompBan(String compBan) {
        this.compBan = compBan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ECT0301DTO)) {
            return false;
        }

        ECT0301DTO ect0301DTO = (ECT0301DTO) o;
        if (this.ectDownloadHisId == null) {
            return false;
        }
        return Objects.equals(this.ectDownloadHisId, ect0301DTO.ectDownloadHisId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ectDownloadHisId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ECT0301DTO{" +
            "ectDownloadHisId=" + getEctDownloadHisId() +
            ", certificateType=" + getCertificateType() +
            ", ectPublishId=" + getEctPublishId() +
            ", certificateNo=" + getCertificateNo() +
            ", lastDownloadTime='" + getLastDownloadTime() + "'" +
            ", sourceIp='" + getSourceIp() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", name='" + getName() + "'" +
            ", rpName='" + getRpName() + "'" +
            ", idno='" + getIdno() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", sex='" + getSex() + "'" +
            ", applyItem=" + getApplyItem() +
            ", certificateWord='" + getCertificateWord() + "'" +
            ", classes='" + getClasses() + "'" +
            ", qtclassName='" + getQtclassName() + "'" +
            ", certificateState=" + getCertificateState() +
            ", praComp='" + getPraComp() + "'" +
            ", compName='" + getCompName() + "'" +
            ", publicYearMonth='" + getPublicYearMonth() + "'" +
            ", classificationCname='" + getClassificationCname() + "'" +
            ", compBan='" + getCompBan() + "'" +
            "}";
    }
}
