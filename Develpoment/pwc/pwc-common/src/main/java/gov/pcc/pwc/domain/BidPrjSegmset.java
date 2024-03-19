package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjSegmset.
 */
@Entity
@IdClass(BidPrjSegmsetPrimaryKey.class)
@Table(name = "bid_prj_segmset")
public class BidPrjSegmset implements Serializable {

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
    @Column(name = "agree_date", nullable = false)
    private LocalDate agreeDate;

    @Size(max = 100)
    @Column(name = "agree_num", length = 100)
    private String agreeNum;

    @Size(max = 1)
    @Column(name = "agree_reson", length = 1)
    private String agreeReson;

    @Column(name = "agree_value", precision = 21, scale = 2)
    private BigDecimal agreeValue;

    @Column(name = "tear_down_date")
    private LocalDate tearDownDate;

    @Column(name = "total_value", precision = 21, scale = 2)
    private BigDecimal totalValue;

    @Size(max = 2000)
    @Column(name = "value_remark", length = 2000)
    private String valueRemark;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjSegmsetPrimaryKey getId() {return new BidPrjSegmsetPrimaryKey(wkut, prjno, agreeDate);}

    public BidPrjSegmset id(BidPrjSegmsetPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjSegmsetPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.agreeDate = id.getAgreeDate();

    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjSegmset wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjSegmset prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getAgreeDate() {
        return this.agreeDate;
    }

    public BidPrjSegmset agreeDate(LocalDate agreeDate) {
        this.setAgreeDate(agreeDate);
        return this;
    }

    public void setAgreeDate(LocalDate agreeDate) {
        this.agreeDate = agreeDate;
    }

    public String getAgreeNum() {
        return this.agreeNum;
    }

    public BidPrjSegmset agreeNum(String agreeNum) {
        this.setAgreeNum(agreeNum);
        return this;
    }

    public void setAgreeNum(String agreeNum) {
        this.agreeNum = agreeNum;
    }

    public String getAgreeReson() {
        return this.agreeReson;
    }

    public BidPrjSegmset agreeReson(String agreeReson) {
        this.setAgreeReson(agreeReson);
        return this;
    }

    public void setAgreeReson(String agreeReson) {
        this.agreeReson = agreeReson;
    }

    public BigDecimal getAgreeValue() {
        return this.agreeValue;
    }

    public BidPrjSegmset agreeValue(BigDecimal agreeValue) {
        this.setAgreeValue(agreeValue);
        return this;
    }

    public void setAgreeValue(BigDecimal agreeValue) {
        this.agreeValue = agreeValue;
    }

    public LocalDate getTearDownDate() {
        return this.tearDownDate;
    }

    public BidPrjSegmset tearDownDate(LocalDate tearDownDate) {
        this.setTearDownDate(tearDownDate);
        return this;
    }

    public void setTearDownDate(LocalDate tearDownDate) {
        this.tearDownDate = tearDownDate;
    }

    public BigDecimal getTotalValue() {
        return this.totalValue;
    }

    public BidPrjSegmset totalValue(BigDecimal totalValue) {
        this.setTotalValue(totalValue);
        return this;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getValueRemark() {
        return this.valueRemark;
    }

    public BidPrjSegmset valueRemark(String valueRemark) {
        this.setValueRemark(valueRemark);
        return this;
    }

    public void setValueRemark(String valueRemark) {
        this.valueRemark = valueRemark;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjSegmset createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjSegmset createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjSegmset updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjSegmset updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjSegmset uIp(String uIp) {
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
        if (!(o instanceof BidPrjSegmset)) {return false;}
        BidPrjSegmset that =  (BidPrjSegmset)  o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && agreeDate.equals(that.agreeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, agreeDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjSegmset{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", agreeDate='" + getAgreeDate() + "'" +
            ", agreeNum='" + getAgreeNum() + "'" +
            ", agreeReson='" + getAgreeReson() + "'" +
            ", agreeValue=" + getAgreeValue() +
            ", tearDownDate='" + getTearDownDate() + "'" +
            ", totalValue=" + getTotalValue() +
            ", valueRemark='" + getValueRemark() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
