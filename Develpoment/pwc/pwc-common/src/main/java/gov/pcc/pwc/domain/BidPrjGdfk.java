package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjGdfk.
 */
@Entity
@IdClass(BidPrjGdfkPrimaryKey.class)
@Table(name = "bid_prj_gdfk")
public class BidPrjGdfk implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

    @Id
    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;

    @Id
    @Size(max = 40)
    @Column(name = "prjno", length = 40)
    private String prjno;

    @Id
    @Size(max = 1)
    @Column(name = "use_type", length = 1)
    private String useType;

    @Id
    @Column(name = "start_date")
    private LocalDate startDate;

    @Size(max = 100)
    @Column(name = "prm_no", length = 100)
    private String prmNo;

    @Size(max = 10)
    @Column(name = "ncc_unit", length = 10)
    private String nccUnit;

    @Column(name = "lct_sum", precision = 21, scale = 2)
    private BigDecimal lctSum;

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

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

    @Column(name = "nce_date")
    private LocalDate nceDate;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjGdfkPrimaryKey getId() {
        return new BidPrjGdfkPrimaryKey(wkut, prjno, useType, startDate);}

    public BidPrjGdfk id(BidPrjGdfkPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjGdfkPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.useType = id.getUseType();
        this.startDate = id.getStartDate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjGdfk wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjGdfk prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getUseType() {
        return this.useType;
    }

    public BidPrjGdfk useType(String useType) {
        this.setUseType(useType);
        return this;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public BidPrjGdfk startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getPrmNo() {
        return this.prmNo;
    }

    public BidPrjGdfk prmNo(String prmNo) {
        this.setPrmNo(prmNo);
        return this;
    }

    public void setPrmNo(String prmNo) {
        this.prmNo = prmNo;
    }

    public String getNccUnit() {
        return this.nccUnit;
    }

    public BidPrjGdfk nccUnit(String nccUnit) {
        this.setNccUnit(nccUnit);
        return this;
    }

    public void setNccUnit(String nccUnit) {
        this.nccUnit = nccUnit;
    }

    public BigDecimal getLctSum() {
        return this.lctSum;
    }

    public BidPrjGdfk lctSum(BigDecimal lctSum) {
        this.setLctSum(lctSum);
        return this;
    }

    public void setLctSum(BigDecimal lctSum) {
        this.lctSum = lctSum;
    }

    public String getRemark() {
        return this.remark;
    }

    public BidPrjGdfk remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjGdfk createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjGdfk createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjGdfk updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjGdfk updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDate getNceDate() {
        return this.nceDate;
    }

    public BidPrjGdfk nceDate(LocalDate nceDate) {
        this.setNceDate(nceDate);
        return this;
    }

    public void setNceDate(LocalDate nceDate) {
        this.nceDate = nceDate;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjGdfk confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjGdfk uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BidPrjGdfk)) {return false;}
        BidPrjGdfk that =(BidPrjGdfk)  o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && useType.equals(that.useType) && startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
            return Objects.hash(wkut, prjno, useType, startDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjGdfk{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", useType='" + getUseType() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", prmNo='" + getPrmNo() + "'" +
            ", nccUnit='" + getNccUnit() + "'" +
            ", lctSum=" + getLctSum() +
            ", remark='" + getRemark() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", nceDate='" + getNceDate() + "'" +
            ", confOut=" + getConfOut() +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
