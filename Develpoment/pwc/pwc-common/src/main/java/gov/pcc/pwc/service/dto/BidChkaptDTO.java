package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidChkaptPrimaryKey;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidChkapt} entity.
 */
public class BidChkaptDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate idate;

    @Size(max = 1)
    private String type;

    @Size(max = 1)
    private String scope;

    @Size(max = 1)
    private String arlt;

    private Integer day;

    private LocalDate deadline;

    private BigDecimal fcost3;

    @Size(max = 2000)
    private String afal;

    @Size(max = 2000)
    private String item1;

    @Size(max = 2000)
    private String item2;

    private LocalDate aokDate;

    @Size(max = 1)
    private String dsts;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    private LocalDate fckdat;

    private LocalDate fokdat;

    private LocalDate ackdat;

    @Size(max = 20)
    private String chker1;

    @Size(max = 20)
    private String chker2;

    private LocalDate fcksdat;

    private LocalDate fckedat;

    private LocalDate acksdat;

    private LocalDate ackedat;

    private Integer fdays;

    private Integer adays;

    public BidChkaptPrimaryKey getId() {
        return new BidChkaptPrimaryKey(this.wkut, this.prjno, this.idate);
    }

    public void setId(BidChkaptPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.idate = id.getIdate();
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

    public LocalDate getIdate() {
        return idate;
    }

    public void setIdate(LocalDate idate) {
        this.idate = idate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getArlt() {
        return arlt;
    }

    public void setArlt(String arlt) {
        this.arlt = arlt;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getFcost3() {
        return fcost3;
    }

    public void setFcost3(BigDecimal fcost3) {
        this.fcost3 = fcost3;
    }

    public String getAfal() {
        return afal;
    }

    public void setAfal(String afal) {
        this.afal = afal;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public LocalDate getAokDate() {
        return aokDate;
    }

    public void setAokDate(LocalDate aokDate) {
        this.aokDate = aokDate;
    }

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
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

    public LocalDate getFckdat() {
        return fckdat;
    }

    public void setFckdat(LocalDate fckdat) {
        this.fckdat = fckdat;
    }

    public LocalDate getFokdat() {
        return fokdat;
    }

    public void setFokdat(LocalDate fokdat) {
        this.fokdat = fokdat;
    }

    public LocalDate getAckdat() {
        return ackdat;
    }

    public void setAckdat(LocalDate ackdat) {
        this.ackdat = ackdat;
    }

    public String getChker1() {
        return chker1;
    }

    public void setChker1(String chker1) {
        this.chker1 = chker1;
    }

    public String getChker2() {
        return chker2;
    }

    public void setChker2(String chker2) {
        this.chker2 = chker2;
    }

    public LocalDate getFcksdat() {
        return fcksdat;
    }

    public void setFcksdat(LocalDate fcksdat) {
        this.fcksdat = fcksdat;
    }

    public LocalDate getFckedat() {
        return fckedat;
    }

    public void setFckedat(LocalDate fckedat) {
        this.fckedat = fckedat;
    }

    public LocalDate getAcksdat() {
        return acksdat;
    }

    public void setAcksdat(LocalDate acksdat) {
        this.acksdat = acksdat;
    }

    public LocalDate getAckedat() {
        return ackedat;
    }

    public void setAckedat(LocalDate ackedat) {
        this.ackedat = ackedat;
    }

    public Integer getFdays() {
        return fdays;
    }

    public void setFdays(Integer fdays) {
        this.fdays = fdays;
    }

    public Integer getAdays() {
        return adays;
    }

    public void setAdays(Integer adays) {
        this.adays = adays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidChkaptDTO)) return false;
        BidChkaptDTO that = (BidChkaptDTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(idate, that.idate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, idate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidChkaptDTO{" +
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

    public String getIdString(){
        return this.wkut +"-"+ this.prjno +"-"+ this.idate.toString();
    }
}
