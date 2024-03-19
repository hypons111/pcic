package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A MrpRecording.
 */
@Entity
@Table(name = "mrp_recording")
public class MrpRecording implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long mrId;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private String accountId;

    @NotNull
    @Size(max = 50)
    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @NotNull
    @Size(max = 50)
    @Column(name = "action", length = 50, nullable = false)
    private String action;

    @Size(max = 4000)
    @Column(name = "search_conditions", length = 4000)
    private String searchConditions;

    @Size(max = 20)
    @Column(name = "ip", length = 20)
    private String ip;

    @Column(name = "num")
    private Integer num;

    @Column(name = "sample_num")
    private Integer sampleNum;

    @Column(name = "download_num")
    private Integer downloadNum;

    @Size(max = 20)
    @Column(name = "update_id", length = 20)
    private String updateId;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "create_id", length = 20)
    private String createId;

    @Column(name = "create_date")
    private Instant createDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getMrId() {
        return this.mrId;
    }

    public MrpRecording mrId(Long mrId) {
        this.setMrId(mrId);
        return this;
    }

    public void setMrId(Long mrId) {
        this.mrId = mrId;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public MrpRecording accountId(String accountId) {
        this.setAccountId(accountId);
        return this;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return this.type;
    }

    public MrpRecording type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return this.action;
    }

    public MrpRecording action(String action) {
        this.setAction(action);
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSearchConditions() {
        return this.searchConditions;
    }

    public MrpRecording searchConditions(String searchConditions) {
        this.setSearchConditions(searchConditions);
        return this;
    }

    public void setSearchConditions(String searchConditions) {
        this.searchConditions = searchConditions;
    }

    public String getIp() {
        return this.ip;
    }

    public MrpRecording ip(String ip) {
        this.setIp(ip);
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getNum() {
        return this.num;
    }

    public MrpRecording num(Integer num) {
        this.setNum(num);
        return this;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSampleNum() {
        return this.sampleNum;
    }

    public MrpRecording sampleNum(Integer sampleNum) {
        this.setSampleNum(sampleNum);
        return this;
    }

    public void setSampleNum(Integer sampleNum) {
        this.sampleNum = sampleNum;
    }

    public Integer getDownloadNum() {
        return this.downloadNum;
    }

    public MrpRecording downloadNum(Integer downloadNum) {
        this.setDownloadNum(downloadNum);
        return this;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getUpdateId() {
        return this.updateId;
    }

    public MrpRecording updateId(String updateId) {
        this.setUpdateId(updateId);
        return this;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public MrpRecording updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return this.createId;
    }

    public MrpRecording createId(String createId) {
        this.setCreateId(createId);
        return this;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public MrpRecording createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpRecording)) {
            return false;
        }
        return mrId != null && mrId.equals(((MrpRecording) o).mrId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpRecording{" +
            "mrId=" + getMrId() +
            ", accountId='" + getAccountId() + "'" +
            ", type='" + getType() + "'" +
            ", action='" + getAction() + "'" +
            ", searchConditions='" + getSearchConditions() + "'" +
            ", ip='" + getIp() + "'" +
            ", num=" + getNum() +
            ", sampleNum=" + getSampleNum() +
            ", downloadNum=" + getDownloadNum() +
            ", updateId='" + getUpdateId() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createId='" + getCreateId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
