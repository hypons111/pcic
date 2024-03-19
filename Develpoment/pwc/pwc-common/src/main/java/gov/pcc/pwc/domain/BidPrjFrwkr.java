package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjFrwkr.
 */
@Entity
@IdClass(BidPrjFrwkrPrimaryKey.class)
@Table(name = "bid_prj_frwkr")
public class BidPrjFrwkr implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

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
    @Column(name = "sdate", nullable = false)
    private LocalDate sdate;

    @Column(name = "fr_wkr")
    private Integer frWkr;

    @Column(name = "fr_wkred")
    private Integer frWkred;

    @Size(max = 1)
    @Column(name = "fr_type", length = 1)
    private String frType;

    @Size(max = 100)
    @Column(name = "fr_pno", length = 100)
    private String frPno;

    @Size(max = 2000)
    @Column(name = "fr_rmk", length = 2000)
    private String frRmk;

    @Column(name = "fr_rate", precision = 21, scale = 2)
    private BigDecimal frRate;

    @Size(max = 2)
    @Column(name = "fr_knd1", length = 2)
    private String frKnd1;

    @Size(max = 2)
    @Column(name = "fr_knd2", length = 2)
    private String frKnd2;

    @Size(max = 2)
    @Column(name = "fr_knd3", length = 2)
    private String frKnd3;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

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

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 20)
    @Column(name = "column1", length = 20)
    private String column1;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjFrwkrPrimaryKey getId() {
        return new BidPrjFrwkrPrimaryKey(wkut, prjno, sdate);
    }

    public BidPrjFrwkr id(BidPrjFrwkrPrimaryKey id) {
        this.setId(id);
        return this;
    }


    public void setId(BidPrjFrwkrPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.sdate = id.getSdate();
    }
    public String getWkut() {
        return this.wkut;
    }

    public BidPrjFrwkr wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjFrwkr prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getSdate() {
        return this.sdate;
    }

    public BidPrjFrwkr sdate(LocalDate sdate) {
        this.setSdate(sdate);
        return this;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    public Integer getFrWkr() {
        return this.frWkr;
    }

    public BidPrjFrwkr frWkr(Integer frWkr) {
        this.setFrWkr(frWkr);
        return this;
    }

    public void setFrWkr(Integer frWkr) {
        this.frWkr = frWkr;
    }

    public Integer getFrWkred() {
        return this.frWkred;
    }

    public BidPrjFrwkr frWkred(Integer frWkred) {
        this.setFrWkred(frWkred);
        return this;
    }

    public void setFrWkred(Integer frWkred) {
        this.frWkred = frWkred;
    }

    public String getFrType() {
        return this.frType;
    }

    public BidPrjFrwkr frType(String frType) {
        this.setFrType(frType);
        return this;
    }

    public void setFrType(String frType) {
        this.frType = frType;
    }

    public String getFrPno() {
        return this.frPno;
    }

    public BidPrjFrwkr frPno(String frPno) {
        this.setFrPno(frPno);
        return this;
    }

    public void setFrPno(String frPno) {
        this.frPno = frPno;
    }

    public String getFrRmk() {
        return this.frRmk;
    }

    public BidPrjFrwkr frRmk(String frRmk) {
        this.setFrRmk(frRmk);
        return this;
    }

    public void setFrRmk(String frRmk) {
        this.frRmk = frRmk;
    }

    public BigDecimal getFrRate() {
        return this.frRate;
    }

    public BidPrjFrwkr frRate(BigDecimal frRate) {
        this.setFrRate(frRate);
        return this;
    }

    public void setFrRate(BigDecimal frRate) {
        this.frRate = frRate;
    }

    public String getFrKnd1() {
        return this.frKnd1;
    }

    public BidPrjFrwkr frKnd1(String frKnd1) {
        this.setFrKnd1(frKnd1);
        return this;
    }

    public void setFrKnd1(String frKnd1) {
        this.frKnd1 = frKnd1;
    }

    public String getFrKnd2() {
        return this.frKnd2;
    }

    public BidPrjFrwkr frKnd2(String frKnd2) {
        this.setFrKnd2(frKnd2);
        return this;
    }

    public void setFrKnd2(String frKnd2) {
        this.frKnd2 = frKnd2;
    }

    public String getFrKnd3() {
        return this.frKnd3;
    }

    public BidPrjFrwkr frKnd3(String frKnd3) {
        this.setFrKnd3(frKnd3);
        return this;
    }

    public void setFrKnd3(String frKnd3) {
        this.frKnd3 = frKnd3;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjFrwkr confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjFrwkr createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjFrwkr createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjFrwkr updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjFrwkr updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjFrwkr uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getColumn1() {
        return this.column1;
    }

    public BidPrjFrwkr column1(String column1) {
        this.setColumn1(column1);
        return this;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BidPrjFrwkr)) {return false;}
        BidPrjFrwkr that = (BidPrjFrwkr) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && sdate.equals(that.sdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, sdate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjFrwkr{" +
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
