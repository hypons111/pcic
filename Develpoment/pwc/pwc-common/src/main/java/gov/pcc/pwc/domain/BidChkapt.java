package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidChkapt.
 */
@Entity
@IdClass(BidChkaptPrimaryKey.class)
@Table(name = "bid_chkapt")
public class BidChkapt implements Serializable {

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
    @Column(name = "idate", nullable = false)
    private LocalDate idate;

    @Size(max = 1)
    @Column(name = "type", length = 1)
    private String type;

    @Size(max = 1)
    @Column(name = "scope", length = 1)
    private String scope;

    @Size(max = 1)
    @Column(name = "arlt", length = 1)
    private String arlt;

    @Column(name = "day")
    private Integer day;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "fcost3", precision = 21, scale = 2)
    private BigDecimal fcost3;

    @Size(max = 2000)
    @Column(name = "afal", length = 2000)
    private String afal;

    @Size(max = 2000)
    @Column(name = "item1", length = 2000)
    private String item1;

    @Size(max = 2000)
    @Column(name = "item2", length = 2000)
    private String item2;

    @Column(name = "aok_date")
    private LocalDate aokDate;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Column(name = "fckdat")
    private LocalDate fckdat;

    @Column(name = "fokdat")
    private LocalDate fokdat;

    @Column(name = "ackdat")
    private LocalDate ackdat;

    @Size(max = 20)
    @Column(name = "chker1", length = 20)
    private String chker1;

    @Size(max = 20)
    @Column(name = "chker2", length = 20)
    private String chker2;

    @Column(name = "fcksdat")
    private LocalDate fcksdat;

    @Column(name = "fckedat")
    private LocalDate fckedat;

    @Column(name = "acksdat")
    private LocalDate acksdat;

    @Column(name = "ackedat")
    private LocalDate ackedat;

    @Column(name = "fdays")
    private Integer fdays;

    @Column(name = "adays")
    private Integer adays;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidChkaptPrimaryKey getId() {
        return new BidChkaptPrimaryKey(this.wkut,this.prjno,this.idate);
    }

    public BidChkapt id(BidChkaptPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidChkaptPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.idate = id.getIdate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidChkapt wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidChkapt prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getIdate() {
        return this.idate;
    }

    public BidChkapt idate(LocalDate idate) {
        this.setIdate(idate);
        return this;
    }

    public void setIdate(LocalDate idate) {
        this.idate = idate;
    }

    public String getType() {
        return this.type;
    }

    public BidChkapt type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return this.scope;
    }

    public BidChkapt scope(String scope) {
        this.setScope(scope);
        return this;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getArlt() {
        return this.arlt;
    }

    public BidChkapt arlt(String arlt) {
        this.setArlt(arlt);
        return this;
    }

    public void setArlt(String arlt) {
        this.arlt = arlt;
    }

    public Integer getDay() {
        return this.day;
    }

    public BidChkapt day(Integer day) {
        this.setDay(day);
        return this;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public BidChkapt deadline(LocalDate deadline) {
        this.setDeadline(deadline);
        return this;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getFcost3() {
        return this.fcost3;
    }

    public BidChkapt fcost3(BigDecimal fcost3) {
        this.setFcost3(fcost3);
        return this;
    }

    public void setFcost3(BigDecimal fcost3) {
        this.fcost3 = fcost3;
    }

    public String getAfal() {
        return this.afal;
    }

    public BidChkapt afal(String afal) {
        this.setAfal(afal);
        return this;
    }

    public void setAfal(String afal) {
        this.afal = afal;
    }

    public String getItem1() {
        return this.item1;
    }

    public BidChkapt item1(String item1) {
        this.setItem1(item1);
        return this;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return this.item2;
    }

    public BidChkapt item2(String item2) {
        this.setItem2(item2);
        return this;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public LocalDate getAokDate() {
        return this.aokDate;
    }

    public BidChkapt aokDate(LocalDate aokDate) {
        this.setAokDate(aokDate);
        return this;
    }

    public void setAokDate(LocalDate aokDate) {
        this.aokDate = aokDate;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidChkapt dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidChkapt createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidChkapt createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidChkapt updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidChkapt updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDate getFckdat() {
        return this.fckdat;
    }

    public BidChkapt fckdat(LocalDate fckdat) {
        this.setFckdat(fckdat);
        return this;
    }

    public void setFckdat(LocalDate fckdat) {
        this.fckdat = fckdat;
    }

    public LocalDate getFokdat() {
        return this.fokdat;
    }

    public BidChkapt fokdat(LocalDate fokdat) {
        this.setFokdat(fokdat);
        return this;
    }

    public void setFokdat(LocalDate fokdat) {
        this.fokdat = fokdat;
    }

    public LocalDate getAckdat() {
        return this.ackdat;
    }

    public BidChkapt ackdat(LocalDate ackdat) {
        this.setAckdat(ackdat);
        return this;
    }

    public void setAckdat(LocalDate ackdat) {
        this.ackdat = ackdat;
    }

    public String getChker1() {
        return this.chker1;
    }

    public BidChkapt chker1(String chker1) {
        this.setChker1(chker1);
        return this;
    }

    public void setChker1(String chker1) {
        this.chker1 = chker1;
    }

    public String getChker2() {
        return this.chker2;
    }

    public BidChkapt chker2(String chker2) {
        this.setChker2(chker2);
        return this;
    }

    public void setChker2(String chker2) {
        this.chker2 = chker2;
    }

    public LocalDate getFcksdat() {
        return this.fcksdat;
    }

    public BidChkapt fcksdat(LocalDate fcksdat) {
        this.setFcksdat(fcksdat);
        return this;
    }

    public void setFcksdat(LocalDate fcksdat) {
        this.fcksdat = fcksdat;
    }

    public LocalDate getFckedat() {
        return this.fckedat;
    }

    public BidChkapt fckedat(LocalDate fckedat) {
        this.setFckedat(fckedat);
        return this;
    }

    public void setFckedat(LocalDate fckedat) {
        this.fckedat = fckedat;
    }

    public LocalDate getAcksdat() {
        return this.acksdat;
    }

    public BidChkapt acksdat(LocalDate acksdat) {
        this.setAcksdat(acksdat);
        return this;
    }

    public void setAcksdat(LocalDate acksdat) {
        this.acksdat = acksdat;
    }

    public LocalDate getAckedat() {
        return this.ackedat;
    }

    public BidChkapt ackedat(LocalDate ackedat) {
        this.setAckedat(ackedat);
        return this;
    }

    public void setAckedat(LocalDate ackedat) {
        this.ackedat = ackedat;
    }

    public Integer getFdays() {
        return this.fdays;
    }

    public BidChkapt fdays(Integer fdays) {
        this.setFdays(fdays);
        return this;
    }

    public void setFdays(Integer fdays) {
        this.fdays = fdays;
    }

    public Integer getAdays() {
        return this.adays;
    }

    public BidChkapt adays(Integer adays) {
        this.setAdays(adays);
        return this;
    }

    public void setAdays(Integer adays) {
        this.adays = adays;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidChkapt)) return false;
        BidChkapt bidChkapt = (BidChkapt) o;
        return wkut.equals(bidChkapt.wkut) && prjno.equals(bidChkapt.prjno) && idate.equals(bidChkapt.idate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, idate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidChkapt{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", idate='" + getIdate() + "'" +
            ", type='" + getType() + "'" +
            ", scope='" + getScope() + "'" +
            ", arlt='" + getArlt() + "'" +
            ", day=" + getDay() +
            ", deadline='" + getDeadline() + "'" +
            ", fcost3=" + getFcost3() +
            ", afal='" + getAfal() + "'" +
            ", item1='" + getItem1() + "'" +
            ", item2='" + getItem2() + "'" +
            ", aokDate='" + getAokDate() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", fckdat='" + getFckdat() + "'" +
            ", fokdat='" + getFokdat() + "'" +
            ", ackdat='" + getAckdat() + "'" +
            ", chker1='" + getChker1() + "'" +
            ", chker2='" + getChker2() + "'" +
            ", fcksdat='" + getFcksdat() + "'" +
            ", fckedat='" + getFckedat() + "'" +
            ", acksdat='" + getAcksdat() + "'" +
            ", ackedat='" + getAckedat() + "'" +
            ", fdays=" + getFdays() +
            ", adays=" + getAdays() +
            "}";
    }
}
