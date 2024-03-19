package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.MrpSynonym} entity.
 */
public class MrpSynonymDTO implements Serializable {

    @NotNull
    private Long mrpSynonymId;

    @NotNull
    @Size(max = 10)
    private String sectionName;

    @Size(max = 50)
    private String keyword;

    @Size(max = 50)
    private String phrase;

    @Size(max = 20)
    private String updateId;

    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    private String createId;

    private Instant createDate;

    @Size(max = 50)
    private String dataKeyType;

    public Long getMrpSynonymId() {
        return mrpSynonymId;
    }

    public void setMrpSynonymId(Long mrpSynonymId) {
        this.mrpSynonymId = mrpSynonymId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
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

    public String getDataKeyType() {
        return dataKeyType;
    }

    public void setDataKeyType(String dataKeyType) {
        this.dataKeyType = dataKeyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpSynonymDTO)) {
            return false;
        }

        MrpSynonymDTO mrpSynonymDTO = (MrpSynonymDTO) o;
        if (this.mrpSynonymId == null) {
            return false;
        }
        return Objects.equals(this.mrpSynonymId, mrpSynonymDTO.mrpSynonymId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mrpSynonymId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpSynonymDTO{" +
            "mrpSynonymId=" + getMrpSynonymId() +
            ", sectionName='" + getSectionName() + "'" +
            ", keyword='" + getKeyword() + "'" +
            ", phrase='" + getPhrase() + "'" +
            ", updateId='" + getUpdateId() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createId='" + getCreateId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", dataKeyType='" + getDataKeyType() + "'" +
            "}";
    }
}
