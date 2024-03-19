package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmFile.
 */
@Entity
@Table(name = "adm_file")
public class AdmFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(3)
     */
    @NotNull
    @Size(max = 3)
    @Schema(description = "type: varchar(3)", required = true)
    @Column(name = "module_type", length = 3, nullable = false)
    private String moduleType;

    /**
     * type: varchar(30)
     */
    @NotNull
    @Size(max = 30)
    @Schema(description = "type: varchar(30)", required = true)
    @Column(name = "business_type", length = 30, nullable = false)
    private String businessType;

    /**
     * type: varchar(30)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "source_id", length = 100, nullable = false)
    private String sourceId;

    /**
     * type: bigint
     */
    @Schema(description = "type: bigint")
    @Column(name = "item_no")
    private Long itemNo;

    /**
     * type: varchar(10)
     */
    @Size(max = 10)
    @Schema(description = "type: varchar(10)")
    @Column(name = "file_type", length = 10)
    private String fileType;

    /**
     * type: nvarchar(200)
     */
    @NotNull
    @Size(max = 200)
    @Schema(description = "type: nvarchar(200)", required = true)
    @Column(name = "file_name_ori", length = 200, nullable = false)
    private String fileNameOri;

    /**
     * type: nvarchar(500)
     */
    @NotNull
    @Size(max = 500)
    @Schema(description = "type: nvarchar(500)", required = true)
    @Column(name = "file_path_name", length = 500, nullable = false)
    private String filePathName;

    /**
     * type: nvarchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: nvarchar(1)", required = true)
    @Column(name = "file_virus_status", length = 1, nullable = false)
    private String fileVirusStatus;

    /**
     * type: nvarchar(100)
     */
    @Size(max = 100)
    @Schema(description = "type: nvarchar(100)")
    @Column(name = "file_remark", length = 100)
    private String fileRemark;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "s3_url", length = 100, nullable = false)
    private String s3Url;

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
    @Schema(description = "type: varchar(20)")
    @Column(name = "update_user", length = 20)
    private String updateUser;

    /**
     * type: datetime
     */
    @Schema(description = "type: datetime")
    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmFile id(Long id) {
        this.id = id;
        return this;
    }

    public String getModuleType() {
        return this.moduleType;
    }

    public AdmFile moduleType(String moduleType) {
        this.moduleType = moduleType;
        return this;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public AdmFile businessType(String businessType) {
        this.businessType = businessType;
        return this;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public AdmFile sourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Long getItemNo() { return this.itemNo; }

    public AdmFile itemNo(Long itemNo) {
        this.itemNo = itemNo;
        return this;
    }

    public void setItemNo(Long itemNo) { this.itemNo = itemNo; }

    public String getFileType() {
        return this.fileType;
    }

    public AdmFile fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileNameOri() {
        return this.fileNameOri;
    }

    public AdmFile fileNameOri(String fileNameOri) {
        this.fileNameOri = fileNameOri;
        return this;
    }

    public void setFileNameOri(String fileNameOri) {
        this.fileNameOri = fileNameOri;
    }

    public String getFilePathName() {
        return this.filePathName;
    }

    public AdmFile filePathName(String filePathName) {
        this.filePathName = filePathName;
        return this;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }

    public String getFileVirusStatus() { return this.fileVirusStatus; }

    public AdmFile fileVirusStatus(String fileVirusStatus) {
        this.fileVirusStatus = fileVirusStatus;
        return this;
    }

    public void setFileVirusStatus(String fileVirusStatus) { this.fileVirusStatus = fileVirusStatus; }

    public String getFileRemark() {
        return this.fileRemark;
    }

    public AdmFile fileRemark(String fileRemark) {
        this.fileRemark = fileRemark;
        return this;
    }

    public void setFileRemark(String fileRemark) {
        this.fileRemark = fileRemark;
    }

    public String getS3Url() {
        return this.s3Url;
    }

    public AdmFile s3Url(String s3Url) {
        this.s3Url = s3Url;
        return this;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public AdmFile createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmFile createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmFile updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmFile updateTime(Instant updateTime) {
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
        if (!(o instanceof AdmFile)) {
            return false;
        }
        return id != null && id.equals(((AdmFile) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("moduleType", moduleType)
                .add("businessType", businessType)
                .add("sourceId", sourceId)
                .add("itemNo", itemNo)
                .add("fileType", fileType)
                .add("fileNameOri", fileNameOri)
                .add("filePathName", filePathName)
                .add("fileVirusStatus", fileVirusStatus)
                .add("fileRemark", fileRemark)
                .add("s3Url", s3Url)
                .add("createUser", createUser)
                .add("createTime", createTime)
                .add("updateUser", updateUser)
                .add("updateTime", updateTime)
                .toString();
    }
}
