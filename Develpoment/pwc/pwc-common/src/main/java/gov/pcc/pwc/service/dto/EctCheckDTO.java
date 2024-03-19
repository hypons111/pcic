package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EctCheck} entity.
 */
public class EctCheckDTO implements Serializable {

    @NotNull
    private Long ectCheckId;

    @NotNull
    @Size(max = 200)
    private String fileName;

    private Long ectPublishId;

    private Long certificateNo;

    private Integer certificateType;

    @Size(max = 10)
    private String publicYearMonth;

    private Integer applyItem;

    private Instant lastDownloadTime;

    @NotNull
    private String isValid;

    @Size(max = 200)
    private String isValidMsg;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String chNameRome;

    @Size(max = 50)
    private String enName;

    @Size(max = 30)
    private String idno;

    private String validDate;

    private LocalDate praStartDate;

    private LocalDate praExpireDate;

    @Size(max = 60)
    private String compName;

    @Size(max = 10)
    private String compBan;

    @Size(max = 60)
    private String rpName;

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

    public String getPublicYearMonth() {
        return publicYearMonth;
    }

    public void setPublicYearMonth(String publicYearMonth) {
        this.publicYearMonth = publicYearMonth;
    }

    public Integer getApplyItem() {
        return applyItem;
    }

    public void setApplyItem(Integer applyItem) {
        this.applyItem = applyItem;
    }

    public Instant getLastDownloadTime() {
        return lastDownloadTime;
    }

    public void setLastDownloadTime(Instant lastDownloadTime) {
        this.lastDownloadTime = lastDownloadTime;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getIsValidMsg() {
        return isValidMsg;
    }

    public void setIsValidMsg(String isValidMsg) {
        this.isValidMsg = isValidMsg;
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

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public LocalDate getPraStartDate() {
        return praStartDate;
    }

    public void setPraStartDate(LocalDate praStartDate) {
        this.praStartDate = praStartDate;
    }

    public LocalDate getPraExpireDate() {
        return praExpireDate;
    }

    public void setPraExpireDate(LocalDate praExpireDate) {
        this.praExpireDate = praExpireDate;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
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
        if (!(o instanceof EctCheckDTO)) {
            return false;
        }

        EctCheckDTO ectCheckDTO = (EctCheckDTO) o;
        if (this.ectCheckId == null) {
            return false;
        }
        return Objects.equals(this.ectCheckId, ectCheckDTO.ectCheckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ectCheckId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EctCheckDTO{" +
            "ectCheckId=" + getEctCheckId() +
            ", fileName='" + getFileName() + "'" +
            ", ectPublishId=" + getEctPublishId() +
            ", certificateNo=" + getCertificateNo() +
            ", certificateType=" + getCertificateType() +
            ", publicYearMonth='" + getPublicYearMonth() + "'" +
            ", applyItem=" + getApplyItem() +
            ", lastDownloadTime='" + getLastDownloadTime() + "'" +
            ", isValid='" + getIsValid() + "'" +
            ", isValidMsg='" + getIsValidMsg() + "'" +
            ", name='" + getName() + "'" +
            ", chNameRome='" + getChNameRome() + "'" +
            ", enName='" + getEnName() + "'" +
            ", idno='" + getIdno() + "'" +
            ", validDate='" + getValidDate() + "'" +
            ", praStartDate='" + getPraStartDate() + "'" +
            ", praExpireDate='" + getPraExpireDate() + "'" +
            ", compName='" + getCompName() + "'" +
            ", compBan='" + getCompBan() + "'" +
            ", rpName='" + getRpName() + "'" +
            ", sourceIp='" + getSourceIp() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
