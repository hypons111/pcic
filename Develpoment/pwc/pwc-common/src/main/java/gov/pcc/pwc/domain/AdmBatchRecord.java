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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmBatchRecord.
 */
@Entity
@Table(name = "adm_batch_record")
public class AdmBatchRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "batch_service_no", length = 20, nullable = false)
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
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "trigger_time", nullable = false)
    private Instant triggerTime;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "finish_time", nullable = false)
    private Instant finishTime;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "result", length = 1, nullable = false)
    private String result;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmBatchRecord id(Long id) {
        this.id = id;
        return this;
    }

    public String getBatchServiceNo() {
        return this.batchServiceNo;
    }

    public AdmBatchRecord batchServiceNo(String batchServiceNo) {
        this.batchServiceNo = batchServiceNo;
        return this;
    }

    public void setBatchServiceNo(String batchServiceNo) {
        this.batchServiceNo = batchServiceNo;
    }

    public String getBatchServiceName() {
        return this.batchServiceName;
    }

    public AdmBatchRecord batchServiceName(String batchServiceName) {
        this.batchServiceName = batchServiceName;
        return this;
    }

    public void setBatchServiceName(String batchServiceName) {
        this.batchServiceName = batchServiceName;
    }

    public String getBatchServiceCategory() {
        return this.batchServiceCategory;
    }

    public AdmBatchRecord batchServiceCategory(String batchServiceCategory) {
        this.batchServiceCategory = batchServiceCategory;
        return this;
    }

    public void setBatchServiceCategory(String batchServiceCategory) {
        this.batchServiceCategory = batchServiceCategory;
    }

    public Instant getTriggerTime() {
        return this.triggerTime;
    }

    public AdmBatchRecord triggerTime(Instant triggerTime) {
        this.triggerTime = triggerTime;
        return this;
    }

    public void setTriggerTime(Instant triggerTime) {
        this.triggerTime = triggerTime;
    }

    public Instant getFinishTime() {
        return this.finishTime;
    }

    public AdmBatchRecord finishTime(Instant finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public void setFinishTime(Instant finishTime) {
        this.finishTime = finishTime;
    }

    public String getResult() {
        return this.result;
    }

    public AdmBatchRecord result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmBatchRecord)) {
            return false;
        }
        return id != null && id.equals(((AdmBatchRecord) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmBatchRecord{" +
            "id=" + getId() +
            ", batchServiceNo='" + getBatchServiceNo() + "'" +
            ", batchServiceName='" + getBatchServiceName() + "'" +
            ", batchServiceCategory='" + getBatchServiceCategory() + "'" +
            ", triggerTime='" + getTriggerTime() + "'" +
            ", finishTime='" + getFinishTime() + "'" +
            ", result='" + getResult() + "'" +
            "}";
    }
}
