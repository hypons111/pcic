package gov.pcc.pwc.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * 需手動加複合主鍵(moduleType、type、key)
 */
@Schema(description = "需手動加複合主鍵(moduleType、type、key)")
@IdClass(AdmSysCodePK.class)
@Entity
@Table(name = "adm_sys_code")
public class AdmSysCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: varchar(3)
     */
    @Id
    @NotNull
    @Size(max = 3)
    @Schema(description = "type: varchar(3)", required = true)
    @Column(name = "module_type", length = 3, nullable = false)
    private String moduleType;

    /**
     * type: varchar(20)
     */
    @Id
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "data_type", length = 20, nullable = false)
    private String dataType;

    /**
     * type: decimal(3,0)
     */
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "255")
    @Schema(description = "type: decimal(3,0)", required = true)
    @Column(name = "sort_no", precision = 21, scale = 2, nullable = false)
    private BigDecimal sortNo;

    /**
     * type: varchar(20)
     */
    @Id
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "data_key", length = 20, nullable = false)
    private String dataKey;

    /**
     * type: varchar(100)
     */
    @Id
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "value", length = 100, nullable = false)
    private String value;

    /**
     * type: nvarchar(500)
     */
    @Size(max = 500)
    @Schema(description = "type: nvarchar(500)")
    @Column(name = "code_descript", length = 500)
    private String codeDescript;

    /**
     * type: varchar(1000)
     */
    @Size(max = 1000)
    @Schema(description = "type: varchar(1000)")
    @Column(name = "en_code_descript", length = 1000)
    private String enCodeDescript;

    /**
     * type: varchar(100)
     */
    @Size(max = 100)
    @Schema(description = "type: varchar(100)")
    @Column(name = "remark", length = 100)
    private String remark;

    /**
     * type: bit
     */
    @NotNull
    @Schema(description = "type: bit", required = true)
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid;

    /**
     * type: datetime
     */
    @Schema(description = "type: datetime")
    @Column(name = "valid_from_date")
    private Instant validFromDate;

    /**
     * type: bit
     */
    @NotNull
    @Schema(description = "type: bit", required = true)
    @Column(name = "is_modify", nullable = false)
    private Boolean isModify;

    /**
     * type: varchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type: varchar(50)")
    @Column(name = "attr1", length = 50)
    private String attr1;

    /**
     * type: varchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type: varchar(50)")
    @Column(name = "attr2", length = 50)
    private String attr2;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: datetime
     */
    @Schema(description = "type: datetime", required = true)
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() { return this.moduleType + this.dataType + this.dataKey; }

    public String getModuleType() {
        return this.moduleType;
    }

    public AdmSysCode moduleType(String moduleType) {
        this.moduleType = moduleType;
        return this;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getDataType() {
        return this.dataType;
    }

    public AdmSysCode dataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public BigDecimal getSortNo() {
        return this.sortNo;
    }

    public AdmSysCode sortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
        return this;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    public String getDataKey() {
        return this.dataKey;
    }

    public AdmSysCode dataKey(String dataKey) {
        this.dataKey = dataKey;
        return this;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getValue() {
        return value;
    }

    public AdmSysCode value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCodeDescript() {
        return codeDescript;
    }

    public AdmSysCode codeDescript(String codeDescript) {
        this.codeDescript = codeDescript;
        return this;
    }

    public void setCodeDescript(String codeDescript) {
        this.codeDescript = codeDescript;
    }


    public String getEnCodeDescript() {
        return enCodeDescript;
    }

    public AdmSysCode enCodeDescript(String enCodeDescript) {
        this.enCodeDescript = enCodeDescript;
        return this;
    }

    public void setEnCodeDescript(String enCodeDescript) {
        this.enCodeDescript = enCodeDescript;
    }

    public String getRemark() {
        return this.remark;
    }

    public AdmSysCode remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getIsValid() {
        return this.isValid;
    }

    public AdmSysCode isValid(Boolean isValid) {
        this.isValid = isValid;
        return this;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Instant getValidFromDate() {
        return this.validFromDate;
    }

    public AdmSysCode validFromDate(Instant validFromDate) {
        this.validFromDate = validFromDate;
        return this;
    }

    public void setValidFromDate(Instant validFromDate) {
        this.validFromDate = validFromDate;
    }

    public Boolean getIsModify() {
        return this.isModify;
    }

    public AdmSysCode isModify(Boolean isModify) {
        this.isModify = isModify;
        return this;
    }

    public void setIsModify(Boolean isModify) {
        this.isModify = isModify;
    }

    public String getAttr1() {
        return this.attr1;
    }

    public AdmSysCode attr1(String attr1) {
        this.attr1 = attr1;
        return this;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return this.attr2;
    }

    public AdmSysCode attr2(String attr2) {
        this.attr2 = attr2;
        return this;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public AdmSysCode createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmSysCode createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmSysCode updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmSysCode updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmSysCode that = (AdmSysCode) o;
        return moduleType.equals(that.moduleType) && dataType.equals(that.dataType) && dataKey.equals(that.dataKey);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmSysCode{" +
            ", moduleType='" + getModuleType() + "'" +
            ", dataType='" + getDataType() + "'" +
            ", sortNo=" + getSortNo() +
            ", dataKey='" + getDataKey() + "'" +
            ", value='" + getValue() + "'" +
            ", codeDescript='" + getCodeDescript() + "'" +
            ", enCodeDescript='" + getEnCodeDescript() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isValid='" + getIsValid() + "'" +
            ", validFromDate='" + getValidFromDate() + "'" +
            ", isModify='" + getIsModify() + "'" +
            ", attr1='" + getAttr1() + "'" +
            ", attr2='" + getAttr2() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
