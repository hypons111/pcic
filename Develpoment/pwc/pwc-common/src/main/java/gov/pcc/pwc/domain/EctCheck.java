package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EctCheck.
 */
@Entity
@Table(name = "ect_check")
public class EctCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "ect_check_id", nullable = false)
    private Long ectCheckId;

    @NotNull
    @Size(max = 200)
    @Column(name = "file_name", length = 200, nullable = false)
    private String fileName;

    @Column(name = "ect_publish_id")
    private Long ectPublishId;

    @Column(name = "certificate_no")
    private Long certificateNo;

    @Column(name = "certificate_type")
    private Integer certificateType;

    @Size(max = 10)
    @Column(name = "public_year_month", length = 10)
    private String publicYearMonth;

    @Column(name = "apply_item")
    private Integer applyItem;

    @Column(name = "last_download_time")
    private Instant lastDownloadTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private String isValid;

    @Size(max = 200)
    @Column(name = "is_valid_msg", length = 200)
    private String isValidMsg;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 50)
    @Column(name = "ch_name_rome", length = 50)
    private String chNameRome;

    @Size(max = 50)
    @Column(name = "en_name", length = 50)
    private String enName;

    @Size(max = 30)
    @Column(name = "idno", length = 30)
    private String idno;

    @Column(name = "valid_date")
    private String validDate;

    @Column(name = "pra_start_date")
    private LocalDate praStartDate;

    @Column(name = "pra_expire_date")
    private LocalDate praExpireDate;

    @Size(max = 60)
    @Column(name = "comp_name", length = 60)
    private String compName;

    @Size(max = 10)
    @Column(name = "comp_ban", length = 10)
    private String compBan;

    @Size(max = 60)
    @Column(name = "rp_name", length = 60)
    private String rpName;

    @Size(max = 50)
    @Column(name = "source_ip", length = 50)
    private String sourceIp;

    @NotNull
    @Size(max = 40)
    @Column(name = "create_user", length = 40, nullable = false)
    private String createUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @Size(max = 40)
    @Column(name = "update_user", length = 40)
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getEctCheckId() {
        return this.ectCheckId;
    }

    public EctCheck ectCheckId(Long ectCheckId) {
        this.setEctCheckId(ectCheckId);
        return this;
    }

    public void setEctCheckId(Long ectCheckId) {
        this.ectCheckId = ectCheckId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public EctCheck fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getEctPublishId() {
        return this.ectPublishId;
    }

    public EctCheck ectPublishId(Long ectPublishId) {
        this.setEctPublishId(ectPublishId);
        return this;
    }

    public void setEctPublishId(Long ectPublishId) {
        this.ectPublishId = ectPublishId;
    }

    public Long getCertificateNo() {
        return this.certificateNo;
    }

    public EctCheck certificateNo(Long certificateNo) {
        this.setCertificateNo(certificateNo);
        return this;
    }

    public void setCertificateNo(Long certificateNo) {
        this.certificateNo = certificateNo;
    }

    public Integer getCertificateType() {
        return this.certificateType;
    }

    public EctCheck certificateType(Integer certificateType) {
        this.setCertificateType(certificateType);
        return this;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getPublicYearMonth() {
        return this.publicYearMonth;
    }

    public EctCheck publicYearMonth(String publicYearMonth) {
        this.setPublicYearMonth(publicYearMonth);
        return this;
    }

    public void setPublicYearMonth(String publicYearMonth) {
        this.publicYearMonth = publicYearMonth;
    }

    public Integer getApplyItem() {
        return this.applyItem;
    }

    public EctCheck applyItem(Integer applyItem) {
        this.setApplyItem(applyItem);
        return this;
    }

    public void setApplyItem(Integer applyItem) {
        this.applyItem = applyItem;
    }

    public Instant getLastDownloadTime() {
        return this.lastDownloadTime;
    }

    public EctCheck lastDownloadTime(Instant lastDownloadTime) {
        this.setLastDownloadTime(lastDownloadTime);
        return this;
    }

    public void setLastDownloadTime(Instant lastDownloadTime) {
        this.lastDownloadTime = lastDownloadTime;
    }

    public String getIsValid() {
        return this.isValid;
    }

    public EctCheck isValid(String isValid) {
        this.setIsValid(isValid);
        return this;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getIsValidMsg() {
        return this.isValidMsg;
    }

    public EctCheck isValidMsg(String isValidMsg) {
        this.setIsValidMsg(isValidMsg);
        return this;
    }

    public void setIsValidMsg(String isValidMsg) {
        this.isValidMsg = isValidMsg;
    }

    public String getName() {
        return this.name;
    }

    public EctCheck name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChNameRome() {
        return this.chNameRome;
    }

    public EctCheck chNameRome(String chNameRome) {
        this.setChNameRome(chNameRome);
        return this;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getEnName() {
        return this.enName;
    }

    public EctCheck enName(String enName) {
        this.setEnName(enName);
        return this;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getIdno() {
        return this.idno;
    }

    public EctCheck idno(String idno) {
        this.setIdno(idno);
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getValidDate() {
        return this.validDate;
    }

    public EctCheck validDate(String validDate) {
        this.setValidDate(validDate);
        return this;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public LocalDate getPraStartDate() {
        return this.praStartDate;
    }

    public EctCheck praStartDate(LocalDate praStartDate) {
        this.setPraStartDate(praStartDate);
        return this;
    }

    public void setPraStartDate(LocalDate praStartDate) {
        this.praStartDate = praStartDate;
    }

    public LocalDate getPraExpireDate() {
        return this.praExpireDate;
    }

    public EctCheck praExpireDate(LocalDate praExpireDate) {
        this.setPraExpireDate(praExpireDate);
        return this;
    }

    public void setPraExpireDate(LocalDate praExpireDate) {
        this.praExpireDate = praExpireDate;
    }

    public String getCompName() {
        return this.compName;
    }

    public EctCheck compName(String compName) {
        this.setCompName(compName);
        return this;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompBan() {
        return this.compBan;
    }

    public EctCheck compBan(String compBan) {
        this.setCompBan(compBan);
        return this;
    }

    public void setCompBan(String compBan) {
        this.compBan = compBan;
    }

    public String getRpName() {
        return this.rpName;
    }

    public EctCheck rpName(String rpName) {
        this.setRpName(rpName);
        return this;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public String getSourceIp() {
        return this.sourceIp;
    }

    public EctCheck sourceIp(String sourceIp) {
        this.setSourceIp(sourceIp);
        return this;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EctCheck createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EctCheck createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EctCheck updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EctCheck updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EctCheck)) {
            return false;
        }
        return ectCheckId != null && ectCheckId.equals(((EctCheck) o).ectCheckId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EctCheck{" +
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
