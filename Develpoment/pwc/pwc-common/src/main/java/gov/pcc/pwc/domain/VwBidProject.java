package gov.pcc.pwc.domain;

import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A VwBidProject.
 */
@Entity
@IdClass(BidProjectPrimaryKey.class)
@Immutable
@Table(name = "vw_bid_project")
public class VwBidProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 21)
    @Column(name = "wkut", length = 21, nullable = false)
    private String wkut;

    @Size(max = 60)
    @Column(name = "wkut_name", length = 60)
    private String wkutName;

    @Id
    @Size(max = 20)
    @Column(name = "prjno", length = 20, nullable = false)
    private String prjno;

    @Size(max = 180)
    @Column(name = "project_name", length = 180)
    private String projectName;

    @Size(max = 2)
    @Column(name = "is_add92", length = 2)
    private String isAdd92;

    @Size(max = 1)
    @Column(name = "pnt_ctrl", length = 1)
    private String pntCtrl;

    @Size(max = 2)
    @Column(name = "type_code", length = 2)
    private String typeCode;

    @Size(max = 1)
    @Column(name = "is_union", length = 1)
    private String isUnion;

    @Size(max = 4)
    @Column(name = "place_code", length = 4)
    private String placeCode;

    @Size(max = 20)
    @Column(name = "place_name", length = 20)
    private String placeName;

    @Size(max = 100)
    @Column(name = "place", length = 100)
    private String place;

    @Size(max = 21)
    @Column(name = "bos_code", length = 21)
    private String bosCode;

    @Size(max = 21)
    @Column(name = "dep_code", length = 21)
    private String depCode;

    @Size(max = 1)
    @Column(name = "is_bg", length = 1)
    private String isBg;

    @Size(max = 21)
    @Column(name = "pln_code", length = 21)
    private String plnCode;

    @Size(max = 20)
    @Column(name = "pln_no", length = 20)
    private String plnNo;

    @Size(max = 30)
    @Column(name = "pln_unicod", length = 30)
    private String plnUnicod;

    @Column(name = "bdgt1")
    private BigDecimal bdgt1;

    @Column(name = "aop_date")
    private LocalDate aopDate;

    @Column(name = "aok_date")
    private LocalDate aokDate;

    @Column(name = "act_date")
    private LocalDate actDate;

    @Column(name = "as_date")
    private LocalDate asDate;

    @Column(name = "ae_date")
    private LocalDate aeDate;

    @Size(max = 21)
    @Column(name = "pcmut", length = 21)
    private String pcmut;

    @Size(max = 2)
    @Column(name = "mctmth", length = 2)
    private String mctmth;

    @Size(max = 21)
    @Column(name = "drut", length = 21)
    private String drut;

    @Column(name = "ct_sum")
    private BigDecimal ctSum;

    @Column(name = "ct_sum_f")
    private BigDecimal ctSumF;

    @Column(name = "pays")
    private BigDecimal pays;

    @Column(name = "cs_date")
    private LocalDate csDate;
    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getWkut() {
        return this.wkut;
    }

    public String getWkutName() {
        return this.wkutName;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getIsAdd92() {
        return this.isAdd92;
    }

    public String getPntCtrl() {
        return this.pntCtrl;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public String getIsUnion() {
        return this.isUnion;
    }

    public String getPlaceCode() {
        return this.placeCode;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public String getPlace() {
        return this.place;
    }

    public String getBosCode() {
        return this.bosCode;
    }

    public String getDepCode() {
        return this.depCode;
    }

    public String getIsBg() {
        return this.isBg;
    }

    public String getPlnCode() {
        return this.plnCode;
    }

    public String getPlnNo() {
        return this.plnNo;
    }

    public String getPlnUnicod() {
        return this.plnUnicod;
    }

    public BigDecimal getBdgt1() {
        return this.bdgt1;
    }

    public LocalDate getAopDate() {
        return this.aopDate;
    }

    public LocalDate getAokDate() {
        return this.aokDate;
    }

    public LocalDate getAeDate() {
        return this.aeDate;
    }

    public String getPcmut() {
        return this.pcmut;
    }

    public String getMctmth() {
        return this.mctmth;
    }

    public String getDrut() {
        return this.drut;
    }

    public LocalDate getActDate() { return actDate;}

    public LocalDate getAsDate() {return asDate;}

    public BigDecimal getCtSum() {return ctSum;}

    public BigDecimal getCtSumF() { return ctSumF;}

    public BigDecimal getPays() {return pays;}

    public LocalDate getCsDate() {return csDate;}

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VwBidProject that = (VwBidProject) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VwBidProject{" +
            ", wkut='" + getWkut() + "'" +
            ", wkutName='" + getWkutName() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", projectName='" + getProjectName() + "'" +
            ", iAdd92='" + getIsAdd92() + "'" +
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
            ", aeDate='" + getAeDate() + "'" +
            ", csDate='" + getCsDate() + "'" +
            ", pcmut='" + getPcmut() + "'" +
            ", mctmth='" + getMctmth() + "'" +
            ", drut='" + getDrut() + "'" +
            "}";
    }
}
