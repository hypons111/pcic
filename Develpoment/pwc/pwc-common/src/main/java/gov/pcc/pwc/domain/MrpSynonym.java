package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A MrpSynonym.
 */
@Entity
@Table(name = "mrp_synonym")
public class MrpSynonym implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long mrpSynonymId;

    @NotNull
    @Size(max = 10)
    @Column(name = "section_name", length = 10, nullable = false)
    private String sectionName;

    @Size(max = 50)
    @Column(name = "keyword", length = 50)
    private String keyword;

    @Size(max = 50)
    @Column(name = "phrase", length = 50)
    private String phrase;

    @Size(max = 20)
    @Column(name = "update_id", length = 20)
    private String updateId;

    @Column(name = "update_date")
    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_id", length = 20, nullable = false)
    private String createId;

    @Column(name = "create_date")
    private Instant createDate;

    @Size(max = 50)
    @Column(name = "data_key_type", length = 50)
    private String dataKeyType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getMrpSynonymId() {
        return this.mrpSynonymId;
    }

    public MrpSynonym mrpSynonymId(Long mrpSynonymId) {
        this.setMrpSynonymId(mrpSynonymId);
        return this;
    }

    public void setMrpSynonymId(Long mrpSynonymId) {
        this.mrpSynonymId = mrpSynonymId;
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public MrpSynonym sectionName(String sectionName) {
        this.setSectionName(sectionName);
        return this;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public MrpSynonym keyword(String keyword) {
        this.setKeyword(keyword);
        return this;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public MrpSynonym phrase(String phrase) {
        this.setPhrase(phrase);
        return this;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getUpdateId() {
        return this.updateId;
    }

    public MrpSynonym updateId(String updateId) {
        this.setUpdateId(updateId);
        return this;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public MrpSynonym updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return this.createId;
    }

    public MrpSynonym createId(String createId) {
        this.setCreateId(createId);
        return this;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public MrpSynonym createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getDataKeyType() {
        return this.dataKeyType;
    }

    public MrpSynonym dataKeyType(String dataKeyType) {
        this.setDataKeyType(dataKeyType);
        return this;
    }

    public void setDataKeyType(String dataKeyType) {
        this.dataKeyType = dataKeyType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpSynonym)) {
            return false;
        }
        return mrpSynonymId != null && mrpSynonymId.equals(((MrpSynonym) o).mrpSynonymId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpSynonym{" +
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
