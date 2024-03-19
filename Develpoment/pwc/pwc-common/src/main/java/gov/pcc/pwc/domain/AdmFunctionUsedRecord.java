package gov.pcc.pwc.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A AdmFunctionUsedRecord.
 */
@Entity
@Table(name = "adm_function_used_record")
public class AdmFunctionUsedRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Schema(description = "type: varchar(10)", required = true)
    @Column(name = "sys_category", length = 10, nullable = false)
    private String sysCategory;

    /**
     * type: varchar(MAX)
     */
    @NotNull
    @Schema(description = "type: varchar(MAX)", required = true)
    @Column(name = "jwt_token", nullable = false)
    private String jwtToken;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "function_id", length = 20, nullable = false)
    private String functionId;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "function_extend_id", length = 20, nullable = false)
    private String functionExtendId;

    /**
     * type: varchar(500)
     */
    @NotNull
    @Size(max = 500)
    @Schema(description = "type: varchar(500)", required = true)
    @Column(name = "table_name", length = 500, nullable = false)
    private String tableName;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "table_key", length = 100, nullable = false)
    private String tableKey;

    /**
     * type: varchar(MAX)
     */
    @NotNull
    @Schema(description = "type: varchar(MAX)", required = true)
    @Column(name = "update_value", nullable = false)
    private String updateValue;

    /**
     * type: varchar(500)
     */
    @NotNull
    @Size(max = 500)
    @Schema(description = "type: varchar(500)")
    @Column(name = "record_content", length = 500, nullable = true)
    private String recordContent;

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
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "create_user_sys", length = 20, nullable = false)
    private String createUserSys;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmFunctionUsedRecord id(Long id) {
        this.id = id;
        return this;
    }

    public String getSysCategory() {
        return this.sysCategory;
    }

    public AdmFunctionUsedRecord sysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
        return this;
    }

    public void setSysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    public AdmFunctionUsedRecord jwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public AdmFunctionUsedRecord functionId(String functionId) {
        this.functionId = functionId;
        return this;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionExtendId() {
        return this.functionExtendId;
    }

    public AdmFunctionUsedRecord functionExtendId(String functionExtendId) {
        this.functionExtendId = functionExtendId;
        return this;
    }

    public void setFunctionExtendId(String functionExtendId) {
        this.functionExtendId = functionExtendId;
    }

    public String getTableName() {
        return this.tableName;
    }

    public AdmFunctionUsedRecord tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableKey() {
        return this.tableKey;
    }

    public AdmFunctionUsedRecord tableKey(String tableKey) {
        this.tableKey = tableKey;
        return this;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getUpdateValue() {
        return this.updateValue;
    }

    public AdmFunctionUsedRecord updateValue(String updateValue) {
        this.updateValue = updateValue;
        return this;
    }

    public void setUpdateValue(String updateValue) {
        this.updateValue = updateValue;
    }

    public String getRecordContent() {
        return this.recordContent;
    }

    public AdmFunctionUsedRecord recordContent(String recordContent) {
        this.recordContent = recordContent;
        return this;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmFunctionUsedRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserSys() {
        return this.createUserSys;
    }

    public AdmFunctionUsedRecord createUserSys(String createUserSys) {
        this.createUserSys = createUserSys;
        return this;
    }

    public void setCreateUserSys(String createUserSys) {
        this.createUserSys = createUserSys;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmFunctionUsedRecord)) {
            return false;
        }
        return id != null && id.equals(((AdmFunctionUsedRecord) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "AdmFunctionUsedRecord{" +
                "id=" + id +
                ", sysCategory='" + sysCategory + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                ", functionId='" + functionId + '\'' +
                ", functionExtendId='" + functionExtendId + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableKey='" + tableKey + '\'' +
                ", updateValue='" + updateValue + '\'' +
                ", recordContent='" + recordContent + '\'' +
                ", createTime=" + createTime +
                ", createUserSys='" + createUserSys + '\'' +
                '}';
    }
}
