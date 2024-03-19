package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidProjectFilePrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidProjectFile} entity.
 */
public class BidProjectFileDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 3)
    private String type;

    @NotNull
    @Size(max = 2)
    private String seq;

    private Long admFileId;

    @Size(max = 2)
    private String status;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidProjectFilePrimaryKey getId() {
        return new BidProjectFilePrimaryKey(this.wkut, this.prjno, this.type, this.seq);
    }

    public void setId(BidProjectFilePrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.type = id.getType();
        this.seq = id.getSeq();
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public Long getAdmFileId() {
        return admFileId;
    }

    public void setAdmFileId(Long admFileId) {
        this.admFileId = admFileId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidProjectFileDTO)) return false;
        BidProjectFileDTO that = (BidProjectFileDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno)&& type.equals(that.type) && seq.equals(that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, type, seq);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidProjectFileDTO{" +
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
