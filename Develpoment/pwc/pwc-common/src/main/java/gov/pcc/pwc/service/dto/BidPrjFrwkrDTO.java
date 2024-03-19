package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjEnviPrimaryKey;
import gov.pcc.pwc.domain.BidPrjFrwkrPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjFrwkr} entity.
 */
public class BidPrjFrwkrDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate sdate;

    private Integer frWkr;

    private Integer frWkred;

    @Size(max = 1)
    private String frType;

    @Size(max = 100)
    private String frPno;

    @Size(max = 2000)
    private String frRmk;

    private BigDecimal frRate;

    @Size(max = 2)
    private String frKnd1;

    @Size(max = 2)
    private String frKnd2;

    @Size(max = 2)
    private String frKnd3;

    private BigDecimal confOut;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 60)
    private String uIp;

    @Size(max = 20)
    private String column1;

    public BidPrjFrwkrPrimaryKey getId() {
        return new BidPrjFrwkrPrimaryKey(
                this.wkut,
                this.prjno,
                this.sdate
        );
    }

    public void setId(BidPrjFrwkrPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.sdate = id.getSdate();
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

    public LocalDate getSdate() {
        return sdate;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    public Integer getFrWkr() {
        return frWkr;
    }

    public void setFrWkr(Integer frWkr) {
        this.frWkr = frWkr;
    }

    public Integer getFrWkred() {
        return frWkred;
    }

    public void setFrWkred(Integer frWkred) {
        this.frWkred = frWkred;
    }

    public String getFrType() {
        return frType;
    }

    public void setFrType(String frType) {
        this.frType = frType;
    }

    public String getFrPno() {
        return frPno;
    }

    public void setFrPno(String frPno) {
        this.frPno = frPno;
    }

    public String getFrRmk() {
        return frRmk;
    }

    public void setFrRmk(String frRmk) {
        this.frRmk = frRmk;
    }

    public BigDecimal getFrRate() {
        return frRate;
    }

    public void setFrRate(BigDecimal frRate) {
        this.frRate = frRate;
    }

    public String getFrKnd1() {
        return frKnd1;
    }

    public void setFrKnd1(String frKnd1) {
        this.frKnd1 = frKnd1;
    }

    public String getFrKnd2() {
        return frKnd2;
    }

    public void setFrKnd2(String frKnd2) {
        this.frKnd2 = frKnd2;
    }

    public String getFrKnd3() {
        return frKnd3;
    }

    public void setFrKnd3(String frKnd3) {
        this.frKnd3 = frKnd3;
    }

    public BigDecimal getConfOut() {
        return confOut;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
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

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BidPrjFrwkrDTO)) {return false;}
        BidPrjFrwkrDTO that = (BidPrjFrwkrDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && sdate.equals(that.sdate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, sdate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjFrwkrDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", sdate='" + getSdate() + "'" +
            ", frWkr=" + getFrWkr() +
            ", frWkred=" + getFrWkred() +
            ", frType='" + getFrType() + "'" +
            ", frPno='" + getFrPno() + "'" +
            ", frRmk='" + getFrRmk() + "'" +
            ", frRate=" + getFrRate() +
            ", frKnd1='" + getFrKnd1() + "'" +
            ", frKnd2='" + getFrKnd2() + "'" +
            ", frKnd3='" + getFrKnd3() + "'" +
            ", confOut=" + getConfOut() +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", column1='" + getColumn1() + "'" +
            "}";
    }
}
