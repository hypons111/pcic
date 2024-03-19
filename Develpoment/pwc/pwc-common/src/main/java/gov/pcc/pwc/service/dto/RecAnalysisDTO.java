package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.RecAnalysis} entity.
 */
public class RecAnalysisDTO implements Serializable {

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Schema(description = "type: VARCHAR(22)", required = true)
    private String projectNo;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Schema(description = "type: VARCHAR(22)", required = true)
    private String analysisNo;

    /**
     * type: NVARCHAR(-1)
     */
    @Schema(description = "type: NVARCHAR(-1)")
    private String analysisA1;

    /**
     * type: NVARCHAR(-1)
     */
    @Schema(description = "type: NVARCHAR(-1)")
    private String analysisA2;

    /**
     * type: NVARCHAR(-1)
     */
    @Schema(description = "type: NVARCHAR(-1)")
    private String analysisA3;

    /**
     * type: NVARCHAR(-1)
     */
    @Schema(description = "type: NVARCHAR(-1)")
    private String analysisA4;

    /**
     * type: NVARCHAR(-1)
     */
    @Schema(description = "type: NVARCHAR(-1)")
    private String analysisA5;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)")
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME")
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)")
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME")
    private Instant updateTime;

    /**
     * type: NVARCHAR(1)
     */
    @Schema(description = "type: NVARCHAR(1)")
    private String invaFlag;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getAnalysisNo() {
        return analysisNo;
    }

    public void setAnalysisNo(String analysisNo) {
        this.analysisNo = analysisNo;
    }

    public String getAnalysisA1() {
        return analysisA1;
    }

    public void setAnalysisA1(String analysisA1) {
        this.analysisA1 = analysisA1;
    }

    public String getAnalysisA2() {
        return analysisA2;
    }

    public void setAnalysisA2(String analysisA2) {
        this.analysisA2 = analysisA2;
    }

    public String getAnalysisA3() {
        return analysisA3;
    }

    public void setAnalysisA3(String analysisA3) {
        this.analysisA3 = analysisA3;
    }

    public String getAnalysisA4() {
        return analysisA4;
    }

    public void setAnalysisA4(String analysisA4) {
        this.analysisA4 = analysisA4;
    }

    public String getAnalysisA5() {
        return analysisA5;
    }

    public void setAnalysisA5(String analysisA5) {
        this.analysisA5 = analysisA5;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
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

    public String getInvaFlag() {
        return invaFlag;
    }

    public void setInvaFlag(String invaFlag) {
        this.invaFlag = invaFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecAnalysisDTO)) {
            return false;
        }

        RecAnalysisDTO recAnalysisDTO = (RecAnalysisDTO) o;
        if (this.analysisNo == null) {
            return false;
        }
        return Objects.equals(this.analysisNo, recAnalysisDTO.analysisNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.analysisNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecAnalysis{" +
                "projectNo=" + getProjectNo() +
                ", analysisNo='" + getAnalysisNo() + "'" +
                ", analysisA1='" + getAnalysisA1() + "'" +
                ", analysisA2='" + getAnalysisA2() + "'" +
                ", analysisA3='" + getAnalysisA3() + "'" +
                ", analysisA4='" + getAnalysisA4() + "'" +
                ", analysisA5='" + getAnalysisA5() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", createTime='" + getCreateTime() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                ", updateTime='" + getUpdateTime() + "'" +
                ", invaFlag='" + getInvaFlag() + "'" +
                "}";
    }
}
