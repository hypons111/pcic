package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.MrpRecording} entity.
 */
public class MrpRecordingDTO implements Serializable {

    @NotNull
    private Long mrId;

    @NotNull
    private String accountId;

    @NotNull
    @Size(max = 50)
    private String type;

    @NotNull
    @Size(max = 50)
    private String action;

    @Size(max = 4000)
    private String searchConditions;

    @Size(max = 20)
    private String ip;

    private Integer num;

    private Integer sampleNum;

    private Integer downloadNum;

    @Size(max = 20)
    private String updateId;

    private Instant updateDate;

    @Size(max = 20)
    private String createId;

    private Instant createDate;

    public Long getMrId() {
        return mrId;
    }

    public void setMrId(Long mrId) {
        this.mrId = mrId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSearchConditions() {
        return searchConditions;
    }

    public void setSearchConditions(String searchConditions) {
        this.searchConditions = searchConditions;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSampleNum() {
        return sampleNum;
    }

    public void setSampleNum(Integer sampleNum) {
        this.sampleNum = sampleNum;
    }

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpRecordingDTO)) {
            return false;
        }

        MrpRecordingDTO mrpRecordingDTO = (MrpRecordingDTO) o;
        if (this.mrId == null) {
            return false;
        }
        return Objects.equals(this.mrId, mrpRecordingDTO.mrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mrId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpRecordingDTO{" +
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
