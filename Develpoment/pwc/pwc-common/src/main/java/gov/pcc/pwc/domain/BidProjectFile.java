package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidProjectFile.
 */
@Entity
@IdClass(BidProjectFilePrimaryKey.class)
@Table(name = "bid_project_file")
public class BidProjectFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 21)
    @Column(name = "wkut", length = 21, nullable = false)
    private String wkut;

    @Id
    @NotNull
    @Size(max = 40)
    @Column(name = "prjno", length = 40, nullable = false)
    private String prjno;

    @Id
    @NotNull
    @Size(max = 3)
    @Column(name = "type", length = 3, nullable = false)
    private String type;

    @Id
    @NotNull
    @Size(max = 2)
    @Column(name = "seq", length = 2, nullable = false)
    private String seq;

    @Column(name = "adm_file_id")
    private Long admFileId;

    @Size(max = 2)
    @Column(name = "status", length = 2)
    private String status;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidProjectFilePrimaryKey getId() {
        return new BidProjectFilePrimaryKey(wkut, prjno, type, seq);
    }

    public BidProjectFile id(BidProjectFilePrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidProjectFilePrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.type = id.getType();
        this.seq = id.getSeq();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidProjectFile wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidProjectFile prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getType() {
        return this.type;
    }

    public BidProjectFile type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeq() {
        return this.seq;
    }

    public BidProjectFile seq(String seq) {
        this.setSeq(seq);
        return this;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public Long getAdmFileId() {
        return this.admFileId;
    }

    public BidProjectFile admFileId(Long admFileId) {
        this.setAdmFileId(admFileId);
        return this;
    }

    public void setAdmFileId(Long admFileId) {
        this.admFileId = admFileId;
    }

    public String getStatus() {
        return this.status;
    }

    public BidProjectFile status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidProjectFile createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidProjectFile createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidProjectFile updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidProjectFile updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidProjectFile)) return false;
        BidProjectFile that = (BidProjectFile) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && type.equals(that.type) && seq.equals(that.seq);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidProjectFile{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", type='" + getType() + "'" +
            ", seq='" + getSeq() + "'" +
            ", admFileId=" + getAdmFileId() +
            ", status='" + getStatus() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
