package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmBatchService.
 */
@Entity
@Table(name = "adm_batch_service")
public class AdmBatchService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String batchServiceNo;

    /**
     * type: nvarchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: nvarchar(20)", required = true)
    @Column(name = "batch_service_name", length = 20, nullable = false)
    private String batchServiceName;

    /**
     * type: varchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Schema(description = "type: varchar(10)", required = true)
    @Column(name = "batch_service_category", length = 10, nullable = false)
    private String batchServiceCategory;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "is_regular", nullable = false)
    private String isRegular;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 150)
    @Schema(description = "type: varchar(150)", required = true)
    @Column(name = "batch_service_prog", length = 150, nullable = false)
    private String batchServiceProg;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "batch_service_prog_method", length = 100, nullable = false)
    private String batchServiceProgMethod;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "batch_service_status", length = 1, nullable = false)
    private String batchServiceStatus;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "batch_exe_status", length = 1, nullable = false)
    private String batchExeStatus;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "batch_frequency", length = 20, nullable = false)
    private String batchFrequency;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getBatchServiceNo() {
        return batchServiceNo;
    }

    public void setBatchServiceNo(String batchServiceNo) {
        this.batchServiceNo = batchServiceNo;
    }

    public AdmBatchService batchServiceNo(String batchServiceNo) {
        this.batchServiceNo = batchServiceNo;
        return this;
    }

    public String getBatchServiceName() {
        return this.batchServiceName;
    }

    public AdmBatchService batchServiceName(String batchServiceName) {
        this.batchServiceName = batchServiceName;
        return this;
    }

    public void setBatchServiceName(String batchServiceName) {
        this.batchServiceName = batchServiceName;
    }

    public String getBatchServiceCategory() {
        return this.batchServiceCategory;
    }

    public AdmBatchService batchServiceCategory(String batchServiceCategory) {
        this.batchServiceCategory = batchServiceCategory;
        return this;
    }

    public void setBatchServiceCategory(String batchServiceCategory) {
        this.batchServiceCategory = batchServiceCategory;
    }

    public String getIsRegular() {
        return this.isRegular;
    }

    public AdmBatchService isRegular(String isRegular) {
        this.isRegular = isRegular;
        return this;
    }

    public void setIsRegular(String isRegular) {
        this.isRegular = isRegular;
    }

    public String getBatchServiceProg() {
        return this.batchServiceProg;
    }

    public AdmBatchService batchServiceProg(String batchServiceProg) {
        this.batchServiceProg = batchServiceProg;
        return this;
    }

    public void setBatchServiceProg(String batchServiceProg) {
        this.batchServiceProg = batchServiceProg;
    }

    public String getBatchServiceProgMethod() {
        return this.batchServiceProgMethod;
    }

    public AdmBatchService batchServiceProgMethod(String batchServiceProgMethod) {
        this.batchServiceProgMethod = batchServiceProgMethod;
        return this;
    }

    public void setBatchServiceProgMethod(String batchServiceProgMethod) {
        this.batchServiceProgMethod = batchServiceProgMethod;
    }

    public String getBatchServiceStatus() {
        return this.batchServiceStatus;
    }

    public AdmBatchService batchServiceStatus(String batchServiceStatus) {
        this.batchServiceStatus = batchServiceStatus;
        return this;
    }

    public void setBatchServiceStatus(String batchServiceStatus) {
        this.batchServiceStatus = batchServiceStatus;
    }

    public String getBatchExeStatus() {
        return this.batchExeStatus;
    }

    public AdmBatchService batchExeStatus(String batchExeStatus) {
        this.batchExeStatus = batchExeStatus;
        return this;
    }

    public void setBatchExeStatus(String batchExeStatus) {
        this.batchExeStatus = batchExeStatus;
    }

    public String getBatchFrequency() {
        return this.batchFrequency;
    }

    public AdmBatchService batchFrequency(String batchFrequency) {
        this.batchFrequency = batchFrequency;
        return this;
    }

    public void setBatchFrequency(String batchFrequency) {
        this.batchFrequency = batchFrequency;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmBatchService updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmBatchService updateTime(Instant updateTime) {
        this.updateTime = updateTime;
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
        if (!(o instanceof AdmBatchService)) {
            return false;
        }
        return batchServiceNo != null && batchServiceNo.equals(((AdmBatchService) o).batchServiceNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmBatchService{" +
            "batchServiceNo=" + getBatchServiceNo() +
            ", batchServiceName='" + getBatchServiceName() + "'" +
            ", batchServiceCategory='" + getBatchServiceCategory() + "'" +
            ", isRegular='" + getIsRegular() + "'" +
            ", batchServiceProg='" + getBatchServiceProg() + "'" +
            ", batchServiceStatus='" + getBatchServiceStatus() + "'" +
            ", batchExeStatus='" + getBatchExeStatus() + "'" +
            ", batchFrequency='" + getBatchFrequency() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
