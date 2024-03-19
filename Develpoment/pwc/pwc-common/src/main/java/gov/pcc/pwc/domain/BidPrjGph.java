package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjGph.
 */
@Entity
@IdClass(BidPrjGphPrimaryKey.class)
@Table(name = "bid_prj_gph")
public class BidPrjGph implements Serializable {

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
    @Size(max = 1)
    @Column(name = "phrase", length = 1, nullable = false)
    private String phrase;

    @Id
    @NotNull
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Size(max = 2)
    @Column(name = "type", length = 2)
    private String type;

    @Column(name = "adm_file_id")
    private Long admFileId;

    @Size(max = 100)
    @Column(name = "file_name", length = 100)
    private String fileName;

    @Column(name = "check_date")
    private LocalDate checkDate;

    @Size(max = 4)
    @Column(name = "gphno", length = 4)
    private String gphno;

    @Size(max = 1000)
    @Column(name = "gphfil", length = 1000)
    private String gphfil;

    @Size(max = 2000)
    @Column(name = "rmtfil", length = 2000)
    private String rmtfil;

    @Size(max = 1000)
    @Column(name = "gphmo", length = 1000)
    private String gphmo;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

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

    public BidPrjGphPrimaryKey getId() {
        return new BidPrjGphPrimaryKey(wkut, prjno, phrase, seq);
    }

    public BidPrjGph id(BidPrjGphPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjGphPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.phrase = id.getPhrase();
        this.seq = id.getSeq();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjGph wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjGph prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public BidPrjGph phrase(String phrase) {
        this.setPhrase(phrase);
        return this;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Long getSeq() {
        return this.seq;
    }

    public BidPrjGph seq(Long seq) {
        this.setSeq(seq);
        return this;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getType() {
        return this.type;
    }

    public BidPrjGph type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAdmFileId() {
        return this.admFileId;
    }

    public BidPrjGph admFileId(Long admFileId) {
        this.setAdmFileId(admFileId);
        return this;
    }

    public void setAdmFileId(Long admFileId) {
        this.admFileId = admFileId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public BidPrjGph fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getCheckDate() {
        return this.checkDate;
    }

    public BidPrjGph checkDate(LocalDate checkDate) {
        this.setCheckDate(checkDate);
        return this;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getGphno() {
        return this.gphno;
    }

    public BidPrjGph gphno(String gphno) {
        this.setGphno(gphno);
        return this;
    }

    public void setGphno(String gphno) {
        this.gphno = gphno;
    }

    public String getGphfil() {
        return this.gphfil;
    }

    public BidPrjGph gphfil(String gphfil) {
        this.setGphfil(gphfil);
        return this;
    }

    public void setGphfil(String gphfil) {
        this.gphfil = gphfil;
    }

    public String getRmtfil() {
        return this.rmtfil;
    }

    public BidPrjGph rmtfil(String rmtfil) {
        this.setRmtfil(rmtfil);
        return this;
    }

    public void setRmtfil(String rmtfil) {
        this.rmtfil = rmtfil;
    }

    public String getGphmo() {
        return this.gphmo;
    }

    public BidPrjGph gphmo(String gphmo) {
        this.setGphmo(gphmo);
        return this;
    }

    public void setGphmo(String gphmo) {
        this.gphmo = gphmo;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjGph uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjGph createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjGph createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjGph updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjGph updateUser(String updateUser) {
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
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjGph that = (BidPrjGph) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && phrase.equals(that.phrase) && seq.equals(that.seq);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjGph{" +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", phrase='" + getPhrase() + "'" +
            ", seq=" + getSeq() +
            ", type='" + getType() + "'" +
            ", admFileId=" + getAdmFileId() +
            ", fileName='" + getFileName() + "'" +
            ", checkDate='" + getCheckDate() + "'" +
            ", gphno='" + getGphno() + "'" +
            ", gphfil='" + getGphfil() + "'" +
            ", rmtfil='" + getRmtfil() + "'" +
            ", gphmo='" + getGphmo() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
