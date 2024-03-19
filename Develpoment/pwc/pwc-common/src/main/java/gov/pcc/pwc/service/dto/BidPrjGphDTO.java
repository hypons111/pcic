package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjGphPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjGph} entity.
 */
public class BidPrjGphDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 1)
    private String phrase;

    @NotNull
    private Long seq;

    @Size(max = 2)
    private String type;

    private Long admFileId;

    @Size(max = 100)
    private String fileName;

    private LocalDate checkDate;

    @Size(max = 4)
    private String gphno;

    @Size(max = 1000)
    private String gphfil;

    @Size(max = 2000)
    private String rmtfil;

    @Size(max = 1000)
    private String gphmo;

    @Size(max = 60)
    private String uIp;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidPrjGphPrimaryKey getId() {
        return new BidPrjGphPrimaryKey(
                this.wkut,this.prjno,this.phrase,this.seq
        );
    }

    public void setId(BidPrjGphPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.phrase = id.getPhrase();
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

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAdmFileId() {
        return admFileId;
    }

    public void setAdmFileId(Long admFileId) {
        this.admFileId = admFileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getGphno() {
        return gphno;
    }

    public void setGphno(String gphno) {
        this.gphno = gphno;
    }

    public String getGphfil() {
        return gphfil;
    }

    public void setGphfil(String gphfil) {
        this.gphfil = gphfil;
    }

    public String getRmtfil() {
        return rmtfil;
    }

    public void setRmtfil(String rmtfil) {
        this.rmtfil = rmtfil;
    }

    public String getGphmo() {
        return gphmo;
    }

    public void setGphmo(String gphmo) {
        this.gphmo = gphmo;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
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
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjGphDTO that = (BidPrjGphDTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(phrase, that.phrase) && Objects.equals(seq, that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, phrase, seq);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjGphDTO{" +
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
