package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.VwBidProject} entity.
 */
public class VwBidProjectDTO implements Serializable {
    @NotNull
    @Size(max = 21)
    private String wkut;

    @Size(max = 60)
    private String wkutName;

    @NotNull
    @Size(max = 20)
    private String prjno;

    @Size(max = 180)
    private String projectName;

    @Size(max = 2)
    private String iAdd92;

    @Size(max = 1)
    private String pntCtrl;

    @Size(max = 2)
    private String typeCode;

    @Size(max = 1)
    private String isUnion;

    @Size(max = 4)
    private String placeCode;

    @Size(max = 20)
    private String placeName;

    @Size(max = 100)
    private String place;

    @Size(max = 21)
    private String bosCode;

    @Size(max = 21)
    private String depCode;

    @Size(max = 1)
    private String isBg;

    @Size(max = 21)
    private String plnCode;

    @Size(max = 20)
    private String plnNo;

    @Size(max = 30)
    private String plnUnicod;

    private BigDecimal bdgt1;

    private LocalDate aopDate;

    private LocalDate aokDate;

    private LocalDate actDate;

    private LocalDate asDate;

    private LocalDate aeDate;

    @Size(max = 21)
    private String pcmut;

    @Size(max = 2)
    private String mctmth;

    @Size(max = 21)
    private String drut;

    private BigDecimal ctSum;

    private BigDecimal ctSumF;

    private BigDecimal pays;

    private LocalDate csDate;


    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getWkutName() {
        return wkutName;
    }

    public void setWkutName(String wkutName) {
        this.wkutName = wkutName;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getiAdd92() {
        return iAdd92;
    }

    public void setiAdd92(String iAdd92) {
        this.iAdd92 = iAdd92;
    }

    public String getPntCtrl() {
        return pntCtrl;
    }

    public void setPntCtrl(String pntCtrl) {
        this.pntCtrl = pntCtrl;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getIsUnion() {
        return isUnion;
    }

    public void setIsUnion(String isUnion) {
        this.isUnion = isUnion;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBosCode() {
        return bosCode;
    }

    public void setBosCode(String bosCode) {
        this.bosCode = bosCode;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getIsBg() {
        return isBg;
    }

    public void setIsBg(String isBg) {
        this.isBg = isBg;
    }

    public String getPlnCode() {
        return plnCode;
    }

    public void setPlnCode(String plnCode) {
        this.plnCode = plnCode;
    }

    public String getPlnNo() {
        return plnNo;
    }

    public void setPlnNo(String plnNo) {
        this.plnNo = plnNo;
    }

    public String getPlnUnicod() {
        return plnUnicod;
    }

    public void setPlnUnicod(String plnUnicod) {
        this.plnUnicod = plnUnicod;
    }

    public BigDecimal getBdgt1() {
        return bdgt1;
    }

    public void setBdgt1(BigDecimal bdgt1) {
        this.bdgt1 = bdgt1;
    }

    public LocalDate getAopDate() {
        return aopDate;
    }

    public void setAopDate(LocalDate aopDate) {
        this.aopDate = aopDate;
    }

    public LocalDate getAokDate() {
        return aokDate;
    }

    public void setAokDate(LocalDate aokDate) {
        this.aokDate = aokDate;
    }

    public LocalDate getAeDate() {
        return aeDate;
    }

    public void setAeDate(LocalDate aeDate) {
        this.aeDate = aeDate;
    }

    public String getPcmut() {
        return pcmut;
    }

    public void setPcmut(String pcmut) {
        this.pcmut = pcmut;
    }

    public String getMctmth() {
        return mctmth;
    }

    public void setMctmth(String mctmth) {
        this.mctmth = mctmth;
    }

    public String getDrut() {
        return drut;
    }

    public void setDrut(String drut) {
        this.drut = drut;
    }

    public LocalDate getActDate() {return actDate;}

    public void setActDate(LocalDate actDate) {this.actDate = actDate;}

    public LocalDate getAsDate() {return asDate;}

    public void setAsDate(LocalDate asDate) {this.asDate = asDate;}

    public BigDecimal getCtSum() {return ctSum;}

    public void setCtSum(BigDecimal ctSum) {this.ctSum = ctSum;}

    public BigDecimal getCtSumF() { return ctSumF; }

    public void setCtSumF(BigDecimal ctSumF) {this.ctSumF = ctSumF;}

    public BigDecimal getPays() {return pays;}

    public void setPays(BigDecimal pays) {this.pays = pays;}

    public LocalDate getCsDate() {
        return csDate;
    }

    public void setCsDate(LocalDate csDate) {
        this.csDate = csDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VwBidProjectDTO)) return false;
        VwBidProjectDTO that = (VwBidProjectDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }
    // prettier-ignore
    @Override
    public String toString() {
        return "VwBidProjectDTO{" +
            ", wkut='" + getWkut() + "'" +
            ", wkutName='" + getWkutName() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", projectName='" + getProjectName() + "'" +
            ", iAdd92='" + getiAdd92() + "'" +
            ", pntCtrl='" + getPntCtrl() + "'" +
            ", typeCode='" + getTypeCode() + "'" +
            ", isUnion='" + getIsUnion() + "'" +
            ", placeCode='" + getPlaceCode() + "'" +
            ", placeName='" + getPlaceName() + "'" +
            ", place='" + getPlace() + "'" +
            ", bosCode='" + getBosCode() + "'" +
            ", depCode='" + getDepCode() + "'" +
            ", isBg='" + getIsBg() + "'" +
            ", plnCode='" + getPlnCode() + "'" +
            ", plnNo='" + getPlnNo() + "'" +
            ", plnUnicod='" + getPlnUnicod() + "'" +
            ", bdgt1=" + getBdgt1() +
            ", aopDate='" + getAopDate() + "'" +
            ", aokDate='" + getAokDate() + "'" +
            ", actDate='" + getActDate() + "'" +
            ", aeDate='" + getAeDate() + "'" +
            ", asDate='" + getAsDate() + "'" +
            ", csDate='" + getCsDate() + "'" +
            ", pcmut='" + getPcmut() + "'" +
            ", mctmth='" + getMctmth() + "'" +
            ", drut='" + getDrut() + "'" +
            ", ctSum='" + getCtSum() + "'" +
            ", ctSumF='" + getCtSumF() + "'" +
            ", pays='" + getPays() + "'" +
            "}";
    }
}
