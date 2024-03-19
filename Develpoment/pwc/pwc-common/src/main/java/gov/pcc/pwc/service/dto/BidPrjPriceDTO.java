package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjPricePrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjPrice} entity.
 */
public class BidPrjPriceDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 3)
    private String year;

    @NotNull
    @Size(max = 20)
    private String pUnit;

    @NotNull
    @Size(max = 1)
    private String grade;

    @Size(max = 30)
    private String pType;

    @Size(max = 30)
    private String pName;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 60)
    private String uIp;

    private LocalDate pSdate;

    private LocalDate pEdate;

    public BidPrjPricePrimaryKey getId() {
        return new BidPrjPricePrimaryKey(this.wkut, this.prjno, this.year, this.pUnit, this.grade);
    }

    public void setId(BidPrjPricePrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.year = id.getYear();
        this.pUnit = id.getpUnit();
        this.grade = id.getGrade();
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getpUnit() {
        return pUnit;
    }

    public void setpUnit(String pUnit) {
        this.pUnit = pUnit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
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

    public LocalDate getpSdate() {
        return pSdate;
    }

    public void setpSdate(LocalDate pSdate) {
        this.pSdate = pSdate;
    }

    public LocalDate getpEdate() {
        return pEdate;
    }

    public void setpEdate(LocalDate pEdate) {
        this.pEdate = pEdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjPriceDTO)) return false;
        BidPrjPriceDTO that = (BidPrjPriceDTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(year, that.year) && Objects.equals(pUnit, that.pUnit) && Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, pUnit, grade);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjPriceDTO{" +
                ", wkut='" + getWkut() + "'" +
                ", prjno='" + getPrjno() + "'" +
                ", year='" + getYear() + "'" +
                ", pUnit='" + getpUnit() + "'" +
                ", grade='" + getGrade() + "'" +
                ", pType='" + getpType() + "'" +
                ", pName='" + getpName() + "'" +
                ", createDate='" + getCreateDate() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", updateDate='" + getUpdateDate() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                ", uIp='" + getuIp() + "'" +
                ", pSdate='" + getpSdate() + "'" +
                ", pEdate='" + getpEdate() + "'" +
                "}";
    }

    public String getIdString(){
        return this.wkut +"-"+ this.prjno +"-"+ this.year +"-"+ this.pUnit +"-"+ this.grade;
    }
}
