package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EctPublic} entity.
 */
public class EctPublicDTO implements Serializable {

    @NotNull
    private Long ectPublishId;

    @NotNull
    private Long certificateNo;

    @NotNull
    private Integer certificateType;

    @NotNull
    private Integer applyItem;

    @NotNull
    private Integer certificateState;

    @NotNull
    @Size(max = 30)
    private String certificateWord;

    @NotNull
    @Size(max = 50)
    private String certificateText;

    @NotNull
    @Size(max = 10)
    private String publicYearMonth;

    @NotNull
    @Size(max = 50)
    private String publicText;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String chNameRome;

    @Size(max = 100)
    private String enName;

    @Size(max = 30)
    private String idno;

    private LocalDate birthDate;

    private String sex;

    private String isDie;

    @Size(max = 50)
    private String qtyCode;

    @Size(max = 200)
    private String qtyName;

    @Size(max = 100)
    private String classes;

    @Size(max = 100)
    private String qtclassName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer classHour;

    @Size(max = 200)
    private String perPhotoName;

    @Size(max = 60)
    private String compName;

    @Size(max = 300)
    private String compAddr;

    @Size(max = 10)
    private String compBan;

    @Size(max = 60)
    private String rpName;

    @Size(max = 100)
    private String reason;

    @Size(max = 1000)
    private String businessContent;

    @Size(max = 300)
    private String classificationCname;

    @Size(max = 300)
    private String classificationEname;

    @Size(max = 600)
    private String examNum;

    @Size(max = 300)
    private String examEnum;

    @Size(max = 300)
    private String praApproach;

    @Size(max = 100)
    private String praComp;

    @Size(max = 300)
    private String praCompAddr;

    @Size(max = 2000)
    private String praClassBusinessScope;

    @Size(max = 100)
    private String praClassCname;

    @Size(max = 1000)
    private String businessScope;

    @Size(max = 10)
    private String praStartDate;

    @Size(max = 10)
    private String praExpireDate;

    @Size(max = 200)
    private String pfxName;

    @Size(max = 50)
    private String keyAname;

    @Size(max = 200)
    private String hashMessage;

    @Size(max = 200)
    private String fileName;

    private Instant fileCreateTime;

    @NotNull
    private String isValid;

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

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public Integer getApplyItem() {
        return applyItem;
    }

    public void setApplyItem(Integer applyItem) {
        this.applyItem = applyItem;
    }

    public Integer getCertificateState() {
        return certificateState;
    }

    public void setCertificateState(Integer certificateState) {
        this.certificateState = certificateState;
    }

    public String getCertificateWord() {
        return certificateWord;
    }

    public void setCertificateWord(String certificateWord) {
        this.certificateWord = certificateWord;
    }

    public String getCertificateText() {
        return certificateText;
    }

    public void setCertificateText(String certificateText) {
        this.certificateText = certificateText;
    }

    public String getPublicYearMonth() {
        return publicYearMonth;
    }

    public void setPublicYearMonth(String publicYearMonth) {
        this.publicYearMonth = publicYearMonth;
    }

    public String getPublicText() {
        return publicText;
    }

    public void setPublicText(String publicText) {
        this.publicText = publicText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIsDie() {
        return isDie;
    }

    public void setIsDie(String isDie) {
        this.isDie = isDie;
    }

    public String getQtyCode() {
        return qtyCode;
    }

    public void setQtyCode(String qtyCode) {
        this.qtyCode = qtyCode;
    }

    public String getQtyName() {
        return qtyName;
    }

    public void setQtyName(String qtyName) {
        this.qtyName = qtyName;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public String getPerPhotoName() {
        return perPhotoName;
    }

    public void setPerPhotoName(String perPhotoName) {
        this.perPhotoName = perPhotoName;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    public String getCompBan() {
        return compBan;
    }

    public void setCompBan(String compBan) {
        this.compBan = compBan;
    }

    public String getRpName() {
        return rpName;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBusinessContent() {
        return businessContent;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }

    public String getClassificationCname() {
        return classificationCname;
    }

    public void setClassificationCname(String classificationCname) {
        this.classificationCname = classificationCname;
    }

    public String getClassificationEname() {
        return classificationEname;
    }

    public void setClassificationEname(String classificationEname) {
        this.classificationEname = classificationEname;
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum;
    }

    public String getExamEnum() {
        return examEnum;
    }

    public void setExamEnum(String examEnum) {
        this.examEnum = examEnum;
    }

    public String getPraApproach() {
        return praApproach;
    }

    public void setPraApproach(String praApproach) {
        this.praApproach = praApproach;
    }

    public String getPraComp() {
        return praComp;
    }

    public void setPraComp(String praComp) {
        this.praComp = praComp;
    }

    public String getPraCompAddr() {
        return praCompAddr;
    }

    public void setPraCompAddr(String praCompAddr) {
        this.praCompAddr = praCompAddr;
    }

    public String getPraClassBusinessScope() {
        return praClassBusinessScope;
    }

    public void setPraClassBusinessScope(String praClassBusinessScope) {
        this.praClassBusinessScope = praClassBusinessScope;
    }

    public String getPraClassCname() {
        return praClassCname;
    }

    public void setPraClassCname(String praClassCname) {
        this.praClassCname = praClassCname;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
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

    public String getPfxName() {
        return pfxName;
    }

    public void setPfxName(String pfxName) {
        this.pfxName = pfxName;
    }

    public String getKeyAname() {
        return keyAname;
    }

    public void setKeyAname(String keyAname) {
        this.keyAname = keyAname;
    }

    public String getHashMessage() {
        return hashMessage;
    }

    public void setHashMessage(String hashMessage) {
        this.hashMessage = hashMessage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Instant getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(Instant fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EctPublicDTO)) {
            return false;
        }

        EctPublicDTO ectPublicDTO = (EctPublicDTO) o;
        if (this.ectPublishId == null) {
            return false;
        }
        return Objects.equals(this.ectPublishId, ectPublicDTO.ectPublishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ectPublishId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EctPublicDTO{" +
            "ectPublishId=" + getEctPublishId() +
            ", certificateNo=" + getCertificateNo() +
            ", certificateType=" + getCertificateType() +
            ", applyItem=" + getApplyItem() +
            ", certificateState=" + getCertificateState() +
            ", certificateWord='" + getCertificateWord() + "'" +
            ", certificateText='" + getCertificateText() + "'" +
            ", publicYearMonth='" + getPublicYearMonth() + "'" +
            ", publicText='" + getPublicText() + "'" +
            ", name='" + getName() + "'" +
            ", chNameRome='" + getChNameRome() + "'" +
            ", enName='" + getEnName() + "'" +
            ", idno='" + getIdno() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", sex='" + getSex() + "'" +
            ", isDie='" + getIsDie() + "'" +
            ", qtyCode='" + getQtyCode() + "'" +
            ", qtyName='" + getQtyName() + "'" +
            ", classes='" + getClasses() + "'" +
            ", qtclassName='" + getQtclassName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", classHour=" + getClassHour() +
            ", perPhotoName='" + getPerPhotoName() + "'" +
            ", compName='" + getCompName() + "'" +
            ", compAddr='" + getCompAddr() + "'" +
            ", compBan='" + getCompBan() + "'" +
            ", rpName='" + getRpName() + "'" +
            ", reason='" + getReason() + "'" +
            ", businessContent='" + getBusinessContent() + "'" +
            ", classificationCname='" + getClassificationCname() + "'" +
            ", classificationEname='" + getClassificationEname() + "'" +
            ", examNum='" + getExamNum() + "'" +
            ", examEnum='" + getExamEnum() + "'" +
            ", praApproach='" + getPraApproach() + "'" +
            ", praComp='" + getPraComp() + "'" +
            ", praCompAddr='" + getPraCompAddr() + "'" +
            ", praClassBusinessScope='" + getPraClassBusinessScope() + "'" +
            ", praClassCname='" + getPraClassCname() + "'" +
            ", businessScope='" + getBusinessScope() + "'" +
            ", praStartDate='" + getPraStartDate() + "'" +
            ", praExpireDate='" + getPraExpireDate() + "'" +
            ", pfxName='" + getPfxName() + "'" +
            ", keyAname='" + getKeyAname() + "'" +
            ", hashMessage='" + getHashMessage() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", fileCreateTime='" + getFileCreateTime() + "'" +
            ", isValid='" + getIsValid() + "'" +
            ", sourceIp='" + getSourceIp() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
