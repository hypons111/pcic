package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmFunction.
 */
@Entity
@Table(name = "adm_function")
public class AdmFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "function_id", length = 20, nullable = false, unique = true)
    private String functionId;

    /**
     * type: nvarchar(20)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: nvarchar(20)", required = true)
    @Column(name = "function_name", length = 20, nullable = false)
    private String functionName;

    /**
     * type: nvarchar(500)
     */
    @NotNull
    @Size(max = 500)
    @Schema(description = "type: nvarchar(500)", required = true)
    @Column(name = "function_descript", length = 500, nullable = false)
    private String functionDescript;

    /**
     * type: varchar(500)
     */
    @NotNull
    @Size(max = 500)
    @Schema(description = "type: varchar(500)", required = true)
    @Column(name = "function_path", length = 500, nullable = false)
    private String functionPath;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "function_category", length = 1, nullable = false)
    private String functionCategory;

    /**
     * type: varchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Schema(description = "type: varchar(10)", required = true)
    @Column(name = "sys_category", length = 10, nullable = false)
    private String sysCategory;

    /**
     * type: decimal(3,0)
     */
    @NotNull
    @Min(value = 0)
    @Schema(description = "type: decimal(3,0)", required = true)
    @Column(name = "sort_no", nullable = false)
    private int sortNo;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "master_function_id", length = 20, nullable = false)
    private String masterFunctionId;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "status", length = 1, nullable = false)
    private String status;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionDescript() {
        return functionDescript;
    }

    public void setFunctionDescript(String functionDescript) {
        this.functionDescript = functionDescript;
    }

    public String getFunctionPath() {
        return functionPath;
    }

    public void setFunctionPath(String functionPath) {
        this.functionPath = functionPath;
    }

    public String getFunctionCategory() {
        return functionCategory;
    }

    public void setFunctionCategory(String functionCategory) {
        this.functionCategory = functionCategory;
    }

    public String getSysCategory() {
        return sysCategory;
    }

    public void setSysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getMasterFunctionId() {
        return masterFunctionId;
    }

    public void setMasterFunctionId(String masterFunctionId) {
        this.masterFunctionId = masterFunctionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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



    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmFunction)) {
            return false;
        }
        return id != null && id.equals(((AdmFunction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore


    @Override
    public String toString() {
        return "AdmFunction{" +
                "id=" + id +
                ", functionId='" + functionId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionDescript='" + functionDescript + '\'' +
                ", functionPath='" + functionPath + '\'' +
                ", functionCategory='" + functionCategory + '\'' +
                ", sysCategory='" + sysCategory + '\'' +
                ", sortNo=" + sortNo +
                ", masterFunctionId='" + masterFunctionId + '\'' +
                ", status='" + status + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
