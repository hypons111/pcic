package gov.pcc.pwc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A RecAnalysis.
 */
@Entity
@Table(name = "rec_analysis")
public class RecAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Column(name = "project_no", length = 22, nullable = false)
    private String projectNo;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Id
    @Column(name = "analysis_no", length = 22, nullable = false)
    private String analysisNo;

    /**
     * type: NVARCHAR(-1)
     */
    @Column(name = "analysis_a1")
    private String analysisA1;

    /**
     * type: NVARCHAR(-1)
     */
    @Column(name = "analysis_a2")
    private String analysisA2;

    /**
     * type: NVARCHAR(-1)
     */
    @Column(name = "analysis_a3")
    private String analysisA3;

    /**
     * type: NVARCHAR(-1)
     */
    @Column(name = "analysis_a4")
    private String analysisA4;

    /**
     * type: NVARCHAR(-1)
     */
    @Column(name = "analysis_a5")
    private String analysisA5;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    /**
     * type: NVARCHAR(1)
     */
    @Column(name = "inva_flag")
    private String invaFlag;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getProjectNo() {
        return this.projectNo;
    }
    public RecAnalysis projectNo(String projectNo) {
        this.setProjectNo(projectNo);
        return this;
    }
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getAnalysisNo() {
        return this.analysisNo;
    }
    public RecAnalysis analysisNo(String analysisNo) {
        this.setAnalysisNo(analysisNo);
        return this;
    }
    public void setAnalysisNo(String analysisNo) {
        this.analysisNo = analysisNo;
    }

    public String getAnalysisA1() {
        return this.analysisA1;
    }
    public RecAnalysis analysisA1(String analysisA1) {
        this.setAnalysisA1(analysisA1);
        return this;
    }
    public void setAnalysisA1(String analysisA1) {
        this.analysisA1 = analysisA1;
    }

    public String getAnalysisA2() {
        return this.analysisA2;
    }
    public RecAnalysis analysisA2(String analysisA2) {
        this.setAnalysisA2(analysisA2);
        return this;
    }
    public void setAnalysisA2(String analysisA2) {
        this.analysisA2 = analysisA2;
    }

    public String getAnalysisA3() {
        return this.analysisA3;
    }
    public RecAnalysis analysisA3(String analysisA3) {
        this.setAnalysisA3(analysisA3);
        return this;
    }
    public void setAnalysisA3(String analysisA3) {
        this.analysisA3 = analysisA3;
    }

    public String getAnalysisA4() {
        return this.analysisA4;
    }
    public RecAnalysis analysisA4(String analysisA4) {
        this.setAnalysisA4(analysisA4);
        return this;
    }
    public void setAnalysisA4(String analysisA4) {
        this.analysisA4 = analysisA4;
    }

    public String getAnalysisA5() {
        return this.analysisA5;
    }
    public RecAnalysis analysisA5(String analysisA5) {
        this.setAnalysisA5(analysisA5);
        return this;
    }
    public void setAnalysisA5(String analysisA5) {
        this.analysisA5 = analysisA5;
    }

    public String getCreateUser() {
        return this.createUser;
    }
    public RecAnalysis createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }
    public RecAnalysis createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }
    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }
    public RecAnalysis updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }
    public RecAnalysis updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }
    public void setUpdateTime(Instant updateTime) { this.updateTime = updateTime; }

    public String getInvaFlag() {
        return this.invaFlag;
    }
    public RecAnalysis invaFlag(String invaFlag) {
        this.setInvaFlag(invaFlag);
        return this;
    }
    public void setInvaFlag(String invaFlag) {
        this.invaFlag = invaFlag;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecAnalysis)) {
            return false;
        }
        return analysisNo != null && analysisNo.equals(((RecAnalysis) o).analysisNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
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
