package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EctDownloadHis} entity.
 */
public class ECT0201R1DTO implements Serializable {

    @NotNull
    private Long ectCheckId;

    @NotNull
    @Size(max = 200)
    private String fileName;

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

    @NotNull
    private String isValid;

    @Size(max = 50)
    private String chNameRome;

    @Size(max = 100)
    private String enName;

    @Size(max = 10)
    private String praStartDate;

    @Size(max = 10)
    private String praExpireDate;

    @Size(max = 200)
    private String hashMessage;

    public Long getEctCheckId() {
        return ectCheckId;
    }

    public void setEctCheckId(Long ectCheckId) {
        this.ectCheckId = ectCheckId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getChNameRome() {
        return chNameRome;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getPraStartDate() {
        return praStartDate;
    }

    public void setPraStartDate(String praStartDate) {
        this.praStartDate = praStartDate;
    }

    public String getPraExpireDate() {
        return praExpireDate;
    }

    public void setPraExpireDate(String praExpireDate) {
        this.praExpireDate = praExpireDate;
    }

    public String getHashMessage() {
        return hashMessage;
    }

    public void setHashMessage(String hashMessage) {
        this.hashMessage = hashMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ECT0201R1DTO)) {
            return false;
        }

        ECT0201R1DTO ect0201r1DTO = (ECT0201R1DTO) o;
        if (this.ectCheckId == null) {
            return false;
        }
        return Objects.equals(this.ectCheckId, ect0201r1DTO.ectCheckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ectCheckId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ECT0201R1DTO{" +
            "ectCheckId=" + getEctCheckId() +
            ", fileName='" + getFileName() + "'" +
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
            ", isValid='" + getIsValid() + "'" +
            ", chNameRome='" + getChNameRome() + "'" +
            ", enName='" + getEnName() + "'" +
            ", praStartDate='" + getPraStartDate() + "'" +
            ", praExpireDate='" + getPraExpireDate() + "'" +
            ", hashMessage='" + getHashMessage() + "'" +
            "}";
    }
}
