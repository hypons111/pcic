package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * A AdmAccount.
 */
@Entity
@Table(name = "adm_account")
public class AdmAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "User_Id", length = 20, nullable = false)
    private String userId;

    @Size(max = 20)
    @Column(name = "Pcc_Org_Id", length = 20)
    private String pccOrgId;

    @Size(max = 8)
    @Column(name = "Comp_Idno", length = 10)
    private String compIdno;

    @Column(name = "No")
    private Long no;

    @Column(name = "User_Pad_Hash", nullable = false)
    private String userPadHash;

    @Size(max = 1)
    @Column(name = "status", length = 1, nullable = false)
    private String status;

    @Size(max = 1)
    @Column(name = "Audit_Status", length = 1, nullable = false)
    private String auditStatus;

    @Size(max = 1)
    @Column(name = "User_Type", length = 10)
    private String userType;

    @Size(max = 50)
    @Column(name = "Update_User", length = 50)
    private String updateUser;

    @Column(name = "Pad_Update_Time")
    private Instant padUpdateTime;

    @Column(name = "Update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getUserPadHash() {
        return userPadHash;
    }

    public void setUserPadHash(String userPadHash) {
        this.userPadHash = userPadHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getPadUpdateTime() {
        return padUpdateTime;
    }

    public void setPadUpdateTime(Instant padUpdateTime) {
        this.padUpdateTime = padUpdateTime;
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
        if (!(o instanceof AdmAccount)) {
            return false;
        }
        return userId != null && userId.equals(((AdmAccount) o).userId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "AdmAccount{" +
                "userId='" + userId + '\'' +
                ", pccOrgId='" + pccOrgId + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", no=" + no +
                ", userPadHash='" + userPadHash + '\'' +
                ", status='" + status + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", userType='" + userType + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", padUpdateTime=" + padUpdateTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
