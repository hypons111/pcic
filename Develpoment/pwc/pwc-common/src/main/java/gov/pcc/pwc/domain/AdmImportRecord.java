package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmImportRecord.
 */
@Entity
@Table(name = "adm_import_record")
public class AdmImportRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    /**
     * type: date
     */
    @NotNull
    @Schema(description = "type: date", required = true)
    @Column(name = "process_date", nullable = false)
    private LocalDate processDate;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "process_start_time", nullable = false)
    private Instant processStartTime;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "process_end_time", nullable = false)
    private Instant processEndTime;

    /**
     * type: nvarchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: nvarchar(100)", required = true)
    @Column(name = "process_file_name", length = 100, nullable = false)
    private String processFileName;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "original_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal originalDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "processed_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal processedDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "unprocessed_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal unprocessedDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "insert_success_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal insertSuccessDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "insert_fail_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal insertFailDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "update_success_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal updateSuccessDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "update_fail_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal updateFailDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "delete_success_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal deleteSuccessDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "delete_fail_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal deleteFailDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "case_success_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal caseSuccessDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "case_fail_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal caseFailDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "interfacing_success_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal interfacingSuccessDataCount;

    /**
     * type: decimal(10,0)
     */
    @NotNull
    @Schema(description = "type: decimal(10,0)", required = true)
    @Column(name = "interfacing_fail_data_count", precision = 21, scale = 2, nullable = false)
    private BigDecimal interfacingFailDataCount;

    /**
     * type: varchar(1000)
     */
    @Size(max = 1000)
    @Schema(description = "type: varchar(1000)")
    @Column(name = "failed_data_memo", length = 1000)
    private String failedDataMemo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmImportRecord id(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getProcessDate() {
        return this.processDate;
    }

    public AdmImportRecord processDate(LocalDate processDate) {
        this.processDate = processDate;
        return this;
    }

    public void setProcessDate(LocalDate processDate) {
        this.processDate = processDate;
    }

    public Instant getProcessStartTime() {
        return this.processStartTime;
    }

    public AdmImportRecord processStartTime(Instant processStartTime) {
        this.processStartTime = processStartTime;
        return this;
    }

    public void setProcessStartTime(Instant processStartTime) {
        this.processStartTime = processStartTime;
    }

    public Instant getProcessEndTime() {
        return this.processEndTime;
    }

    public AdmImportRecord processEndTime(Instant processEndTime) {
        this.processEndTime = processEndTime;
        return this;
    }

    public void setProcessEndTime(Instant processEndTime) {
        this.processEndTime = processEndTime;
    }

    public String getProcessFileName() {
        return this.processFileName;
    }

    public AdmImportRecord processFileName(String processFileName) {
        this.processFileName = processFileName;
        return this;
    }

    public void setProcessFileName(String processFileName) {
        this.processFileName = processFileName;
    }

    public BigDecimal getOriginalDataCount() {
        return this.originalDataCount;
    }

    public AdmImportRecord originalDataCount(BigDecimal originalDataCount) {
        this.originalDataCount = originalDataCount;
        return this;
    }

    public void setOriginalDataCount(BigDecimal originalDataCount) {
        this.originalDataCount = originalDataCount;
    }

    public BigDecimal getProcessedDataCount() {
        return this.processedDataCount;
    }

    public AdmImportRecord processedDataCount(BigDecimal processedDataCount) {
        this.processedDataCount = processedDataCount;
        return this;
    }

    public void setProcessedDataCount(BigDecimal processedDataCount) {
        this.processedDataCount = processedDataCount;
    }

    public BigDecimal getUnprocessedDataCount() {
        return this.unprocessedDataCount;
    }

    public AdmImportRecord unprocessedDataCount(BigDecimal unprocessedDataCount) {
        this.unprocessedDataCount = unprocessedDataCount;
        return this;
    }

    public void setUnprocessedDataCount(BigDecimal unprocessedDataCount) {
        this.unprocessedDataCount = unprocessedDataCount;
    }

    public BigDecimal getInsertSuccessDataCount() {
        return this.insertSuccessDataCount;
    }

    public AdmImportRecord insertSuccessDataCount(BigDecimal insertSuccessDataCount) {
        this.insertSuccessDataCount = insertSuccessDataCount;
        return this;
    }

    public void setInsertSuccessDataCount(BigDecimal insertSuccessDataCount) {
        this.insertSuccessDataCount = insertSuccessDataCount;
    }

    public BigDecimal getInsertFailDataCount() {
        return this.insertFailDataCount;
    }

    public AdmImportRecord insertFailDataCount(BigDecimal insertFailDataCount) {
        this.insertFailDataCount = insertFailDataCount;
        return this;
    }

    public void setInsertFailDataCount(BigDecimal insertFailDataCount) {
        this.insertFailDataCount = insertFailDataCount;
    }

    public BigDecimal getUpdateSuccessDataCount() {
        return this.updateSuccessDataCount;
    }

    public AdmImportRecord updateSuccessDataCount(BigDecimal updateSuccessDataCount) {
        this.updateSuccessDataCount = updateSuccessDataCount;
        return this;
    }

    public void setUpdateSuccessDataCount(BigDecimal updateSuccessDataCount) {
        this.updateSuccessDataCount = updateSuccessDataCount;
    }

    public BigDecimal getUpdateFailDataCount() {
        return this.updateFailDataCount;
    }

    public AdmImportRecord updateFailDataCount(BigDecimal updateFailDataCount) {
        this.updateFailDataCount = updateFailDataCount;
        return this;
    }

    public void setUpdateFailDataCount(BigDecimal updateFailDataCount) {
        this.updateFailDataCount = updateFailDataCount;
    }

    public BigDecimal getDeleteSuccessDataCount() {
        return this.deleteSuccessDataCount;
    }

    public AdmImportRecord deleteSuccessDataCount(BigDecimal deleteSuccessDataCount) {
        this.deleteSuccessDataCount = deleteSuccessDataCount;
        return this;
    }

    public void setDeleteSuccessDataCount(BigDecimal deleteSuccessDataCount) {
        this.deleteSuccessDataCount = deleteSuccessDataCount;
    }

    public BigDecimal getDeleteFailDataCount() {
        return this.deleteFailDataCount;
    }

    public AdmImportRecord deleteFailDataCount(BigDecimal deleteFailDataCount) {
        this.deleteFailDataCount = deleteFailDataCount;
        return this;
    }

    public void setDeleteFailDataCount(BigDecimal deleteFailDataCount) {
        this.deleteFailDataCount = deleteFailDataCount;
    }

    public BigDecimal getCaseSuccessDataCount() {
        return this.caseSuccessDataCount;
    }

    public AdmImportRecord caseSuccessDataCount(BigDecimal caseSuccessDataCount) {
        this.caseSuccessDataCount = caseSuccessDataCount;
        return this;
    }

    public void setCaseSuccessDataCount(BigDecimal caseSuccessDataCount) {
        this.caseSuccessDataCount = caseSuccessDataCount;
    }

    public BigDecimal getCaseFailDataCount() {
        return this.caseFailDataCount;
    }

    public AdmImportRecord caseFailDataCount(BigDecimal caseFailDataCount) {
        this.caseFailDataCount = caseFailDataCount;
        return this;
    }

    public void setCaseFailDataCount(BigDecimal caseFailDataCount) {
        this.caseFailDataCount = caseFailDataCount;
    }

    public BigDecimal getInterfacingSuccessDataCount() {
        return this.interfacingSuccessDataCount;
    }

    public AdmImportRecord interfacingSuccessDataCount(BigDecimal interfacingSuccessDataCount) {
        this.interfacingSuccessDataCount = interfacingSuccessDataCount;
        return this;
    }

    public void setInterfacingSuccessDataCount(BigDecimal interfacingSuccessDataCount) {
        this.interfacingSuccessDataCount = interfacingSuccessDataCount;
    }

    public BigDecimal getInterfacingFailDataCount() {
        return this.interfacingFailDataCount;
    }

    public AdmImportRecord interfacingFailDataCount(BigDecimal interfacingFailDataCount) {
        this.interfacingFailDataCount = interfacingFailDataCount;
        return this;
    }

    public void setInterfacingFailDataCount(BigDecimal interfacingFailDataCount) {
        this.interfacingFailDataCount = interfacingFailDataCount;
    }

    public String getFailedDataMemo() {
        return this.failedDataMemo;
    }

    public AdmImportRecord failedDataMemo(String failedDataMemo) {
        this.failedDataMemo = failedDataMemo;
        return this;
    }

    public void setFailedDataMemo(String failedDataMemo) {
        this.failedDataMemo = failedDataMemo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmImportRecord)) {
            return false;
        }
        return id != null && id.equals(((AdmImportRecord) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmImportRecord{" +
            "id=" + getId() +
            ", processDate='" + getProcessDate() + "'" +
            ", processStartTime='" + getProcessStartTime() + "'" +
            ", processEndTime='" + getProcessEndTime() + "'" +
            ", processFileName='" + getProcessFileName() + "'" +
            ", originalDataCount=" + getOriginalDataCount() +
            ", processedDataCount=" + getProcessedDataCount() +
            ", unprocessedDataCount=" + getUnprocessedDataCount() +
            ", insertSuccessDataCount=" + getInsertSuccessDataCount() +
            ", insertFailDataCount=" + getInsertFailDataCount() +
            ", updateSuccessDataCount=" + getUpdateSuccessDataCount() +
            ", updateFailDataCount=" + getUpdateFailDataCount() +
            ", deleteSuccessDataCount=" + getDeleteSuccessDataCount() +
            ", deleteFailDataCount=" + getDeleteFailDataCount() +
            ", caseSuccessDataCount=" + getCaseSuccessDataCount() +
            ", caseFailDataCount=" + getCaseFailDataCount() +
            ", interfacingSuccessDataCount=" + getInterfacingSuccessDataCount() +
            ", interfacingFailDataCount=" + getInterfacingFailDataCount() +
            ", failedDataMemo='" + getFailedDataMemo() + "'" +
            "}";
    }
}
