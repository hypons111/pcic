package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidProject} entity.
 */
public class BidProjectInfoDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @Size(max = 60)
    private String wkutName;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @Size(max = 180)
    private String name;

    @Size(max = 2)
    private String typeCode;

    @Size(max = 21)
    private String bos2Code;

    @Size(max = 21)
    private String bos2bCode;

    @Size(max = 21)
    private String bos2cCode;

    @Size(max = 21)
    private String bos2dCode;

    @Size(max = 21)
    private String bosCode;

    @Size(max = 21)
    private String depCode;

    @Size(max = 80)
    private String man;

    @Size(max = 40)
    private String tel;

    private BigDecimal bdgt1;

    private BigDecimal ctSum;

    private LocalDate actDate;

    private BigDecimal ctSumF;

    @Size(max = 2)
    private String actmth;

    private LocalDate asDate;

    private BigDecimal pays;

    private String plnCode;

    private String plnName;

    private String plnNo;

    private String bosName;

    private String bos2Name;
    private String bos2bName;
    private String bos2cName;
    private String bos2dName;

    private BigDecimal bos2Rate;
    private BigDecimal bos2bRate;
    private BigDecimal bos2cRate;
    private BigDecimal bos2dRate;

    private String deptName;

    @Size(max = 80)
    private String cntid;

    public String getTypeCode() {return typeCode;}

    public void setTypeCode(String typeCode) {this.typeCode = typeCode;    }

    public String getBos2Code() {
        return bos2Code;
    }

    public void setBos2Code(String bos2Code) {
        this.bos2Code = bos2Code;
    }

    public String getBos2bCode() {return bos2bCode;}

    public void setBos2bCode(String bos2bCode) {this.bos2bCode = bos2bCode;}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public String getBos2cCode() {
        return bos2cCode;
    }

    public void setBos2cCode(String bos2cCode) {
        this.bos2cCode = bos2cCode;
    }

    public String getBos2dCode() {
        return bos2dCode;
    }

    public void setBos2dCode(String bos2dCode) {
        this.bos2dCode = bos2dCode;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public BigDecimal getBdgt1() {
        return bdgt1;
    }

    public void setBdgt1(BigDecimal bdgt1) {
        this.bdgt1 = bdgt1;
    }

    public LocalDate getActDate() {
        return actDate;
    }

    public void setActDate(LocalDate actDate) {
        this.actDate = actDate;
    }

        public BigDecimal getCtSum() {
        return ctSum;
    }

    public void setCtSum(BigDecimal ctSum) {
        this.ctSum = ctSum;
    }

    public BigDecimal getCtSumF() {
        return ctSumF;
    }

    public void setCtSumF(BigDecimal ctSumF) {
        this.ctSumF = ctSumF;
    }

    public String getCntid() {
        return cntid;
    }

    public void setCntid(String cntid) {
        this.cntid = cntid;
    }

    public String getActmth() {
        return actmth;
    }

    public void setActmth(String actmth) {
        this.actmth = actmth;
    }

    public LocalDate getAsDate() {
        return asDate;
    }

    public void setAsDate(LocalDate asDate) {
        this.asDate = asDate;
    }

    public BigDecimal getPays() {
        return pays;
    }

    public void setPays(BigDecimal pays) {
        this.pays = pays;
    }

    public String getPlnCode() {
        return plnCode;
    }

    public void setPlnCode(String plnCode) {
        this.plnCode = plnCode;
    }

    public String getPlnName() {
        return plnName;
    }

    public void setPlnName(String plnName) {
        this.plnName = plnName;
    }

    public String getPlnNo() {
        return plnNo;
    }

    public void setPlnNo(String plnNo) {
        this.plnNo = plnNo;
    }

    public String getWkutName() {
        return wkutName;
    }

    public void setWkutName(String wkutName) {
        this.wkutName = wkutName;
    }

    public String getBosName() {
        return bosName;
    }

    public void setBosName(String bosName) {
        this.bosName = bosName;
    }

    public String getBos2Name() {
        return bos2Name;
    }

    public void setBos2Name(String bos2Name) {
        this.bos2Name = bos2Name;
    }

    public String getBos2bName() {
        return bos2bName;
    }

    public void setBos2bName(String bos2bName) {
        this.bos2bName = bos2bName;
    }

    public String getBos2cName() {
        return bos2cName;
    }

    public void setBos2cName(String bos2cName) {
        this.bos2cName = bos2cName;
    }

    public String getBos2dName() {
        return bos2dName;
    }

    public void setBos2dName(String bos2dName) {
        this.bos2dName = bos2dName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public BigDecimal getBos2Rate() {
        return bos2Rate;
    }

    public void setBos2Rate(BigDecimal bos2Rate) {
        this.bos2Rate = bos2Rate;
    }

    public BigDecimal getBos2bRate() {
        return bos2bRate;
    }

    public void setBos2bRate(BigDecimal bos2bRate) {
        this.bos2bRate = bos2bRate;
    }

    public BigDecimal getBos2cRate() {
        return bos2cRate;
    }

    public void setBos2cRate(BigDecimal bos2cRate) {
        this.bos2cRate = bos2cRate;
    }

    public BigDecimal getBos2dRate() {
        return bos2dRate;
    }

    public void setBos2dRate(BigDecimal bos2dRate) {
        this.bos2dRate = bos2dRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidProjectInfoDTO)) {
            return false;
        }

        BidProjectInfoDTO bidProjectDTO = (BidProjectInfoDTO) o;
        if (this.prjno == null) {
            return false;
        }
        return Objects.equals(this.prjno, bidProjectDTO.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidProjectDTO{" +
            "wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", name='" + getName() + "'" +
            ", bosCode='" + getBosCode() + "'" +
            ", bosName='" + getBosName() + "'" +
            ", depCode='" + getDepCode() + "'" +
            ", depName='" + getDeptName() + "'" +
            ", typeCode='" + getTypeCode() + "'" +
            ", bos2Code='" + getBos2Code() + "'" +
            ", bos2Name='" + getBos2Name() + "'" +
            ", bos2Rate='" + getBos2Rate() + "'" +
            ", bos2cCode='" + getBos2cCode() + "'" +
            ", bos2bCode='" + getBos2bCode() + "'" +
            ", bos2bName='" + getBos2bName() + "'" +
            ", bos2bRate='" + getBos2bRate() + "'" +
            ", bos2cCode='" + getBos2cCode() + "'" +
            ", bos2cName='" + getBos2cName() + "'" +
            ", bos2cRate='" + getBos2cRate() + "'" +
            ", bos2dCode='" + getBos2dCode() + "'" +
            ", bos2dName='" + getBos2dName() + "'" +
            ", bos2dRate='" + getBos2dRate() + "'" +
            ", man='" + getMan() + "'" +
            ", tel='" + getTel() + "'" +
            ", bdgt1=" + getBdgt1() +
            ", actDate='" + getActDate() + "'" +
            ", ctSum=" + getCtSum() +
            ", ctSumF=" + getCtSumF() +
            ", cntid='" + getCntid() + "'" +
            ", asDate='" + getAsDate() + "'" +
            ", pays=" + getPays() +
            ", plnCode='" + getPlnCode() + "'" +
            ", plnName='" + getPlnName() + "'" +
            ", plnNo='" + getPlnNo() + "'" +
            "}";
    }
}
