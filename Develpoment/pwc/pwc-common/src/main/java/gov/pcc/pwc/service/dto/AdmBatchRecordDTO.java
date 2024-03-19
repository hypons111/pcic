package gov.pcc.pwc.service.dto;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

public class AdmBatchRecordDTO {

    private Long id;

    @Size(max = 20)
    private String batchServiceNo;

    @Size(max = 20)
    private String batchServiceName;

    @Size(max = 10)
    private String batchServiceCategory;

    private Instant triggerTime;

    private Instant finishTime;

    @Size(max = 1)
    private String result;

    private int rowNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchServiceNo() {
        return batchServiceNo;
    }

    public void setBatchServiceNo(String batchServiceNo) {
        this.batchServiceNo = batchServiceNo;
    }

    public String getBatchServiceName() {
        return batchServiceName;
    }

    public void setBatchServiceName(String batchServiceName) {
        this.batchServiceName = batchServiceName;
    }

    public String getBatchServiceCategory() {
        return batchServiceCategory;
    }

    public void setBatchServiceCategory(String batchServiceCategory) {
        this.batchServiceCategory = batchServiceCategory;
    }

    public Instant getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Instant triggerTime) {
        this.triggerTime = triggerTime;
    }

    public Instant getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Instant finishTime) {
        this.finishTime = finishTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmBatchRecordDTO that = (AdmBatchRecordDTO) o;
        return id.equals(that.id) && Objects.equals(batchServiceNo, that.batchServiceNo) && Objects.equals(batchServiceName, that.batchServiceName) && Objects.equals(batchServiceCategory, that.batchServiceCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, batchServiceNo, batchServiceName, batchServiceCategory);
    }

    @Override
    public String toString() {
        return "AdmBatchRecordDTO{" +
                "id=" + id +
                ", batchServiceNo='" + batchServiceNo + '\'' +
                ", batchServiceName='" + batchServiceName + '\'' +
                ", batchServiceCategory='" + batchServiceCategory + '\'' +
                ", triggerTime=" + triggerTime +
                ", finishTime=" + finishTime +
                ", result='" + result + '\'' +
                ", rowNum=" + rowNum +
                '}';
    }
}
