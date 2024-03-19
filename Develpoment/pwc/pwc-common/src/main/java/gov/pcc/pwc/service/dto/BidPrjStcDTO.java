package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjStcPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjStc} entity.
 */
public class BidPrjStcDTO implements Serializable {

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    private LocalDate checkDate;

    private Long admFileId;

    @Size(max = 7)
    private String yndA01;

    @Size(max = 400)
    private String resonA01;

    private LocalDate dateA01;

    @Size(max = 7)
    private String yndA02;

    @Size(max = 400)
    private String resonA02;

    private LocalDate dateA02;

    @Size(max = 7)
    private String yndA03;

    @Size(max = 400)
    private String resonA03;

    private LocalDate dateA03;

    @Size(max = 7)
    private String yndA04;

    @Size(max = 400)
    private String resonA04;

    private LocalDate dateA04;

    @Size(max = 7)
    private String yndA05;

    @Size(max = 400)
    private String resonA05;

    private LocalDate dateA05;

    @Size(max = 7)
    private String yndA06;

    @Size(max = 400)
    private String resonA06;

    private LocalDate dateA06;

    @Size(max = 7)
    private String yndA07;

    @Size(max = 400)
    private String resonA07;

    private LocalDate dateA07;

    @Size(max = 7)
    private String yndA08;

    @Size(max = 400)
    private String resonA08;

    private LocalDate dateA08;

    @Size(max = 7)
    private String yndA09;

    @Size(max = 400)
    private String resonA09;

    private LocalDate dateA09;

    @Size(max = 7)
    private String yndA10;

    @Size(max = 400)
    private String resonA10;

    private LocalDate dateA10;

    @Size(max = 7)
    private String yndA11;

    @Size(max = 400)
    private String resonA11;

    private LocalDate dateA11;

    @Size(max = 7)
    private String yndA12;

    @Size(max = 400)
    private String resonA12;

    private LocalDate dateA12;

    @Size(max = 7)
    private String yndA13;

    @Size(max = 400)
    private String resonA13;

    private LocalDate dateA13;

    @Size(max = 7)
    private String yndA14;

    @Size(max = 400)
    private String resonA14;

    private LocalDate dateA14;

    @Size(max = 7)
    private String yndA15;

    @Size(max = 400)
    private String resonA15;

    private LocalDate dateA15;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 1)
    private String dsts;

    @Size(max = 7)
    private String yndA16;

    @Size(max = 400)
    private String resonA16;

    private LocalDate dateA16;

    @Size(max = 7)
    private String yndB01;

    @Size(max = 400)
    private String resonB01;

    private LocalDate dateB01;

    @Size(max = 7)
    private String yndB02;

    @Size(max = 400)
    private String resonB02;

    private LocalDate dateB02;

    @Size(max = 7)
    private String yndB03;

    @Size(max = 400)
    private String resonB03;

    private LocalDate dateB03;

    @Size(max = 7)
    private String yndB04;

    @Size(max = 400)
    private String resonB04;

    private LocalDate dateB04;

    @Size(max = 7)
    private String yndB05;

    @Size(max = 400)
    private String resonB05;

    private LocalDate dateB05;

    @Size(max = 7)
    private String yndB06;

    @Size(max = 400)
    private String resonB06;

    private LocalDate dateB06;

    @Size(max = 7)
    private String yndB07;

    @Size(max = 400)
    private String resonB07;

    private LocalDate dateB07;

    @Size(max = 180)
    private String name;

    private BigDecimal bdgt1;

    public BidPrjStcPrimaryKey getId() {
        return new BidPrjStcPrimaryKey(this.wkut,this.prjno,this.checkDate);
    }

    public void setId(BidPrjStcPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.checkDate = id.getCheckDate();
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

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public Long getAdmFileId() {
        return admFileId;
    }

    public void setAdmFileId(Long admFileId) {
        this.admFileId = admFileId;
    }

    public String getYndA01() {
        return yndA01;
    }

    public void setYndA01(String yndA01) {
        this.yndA01 = yndA01;
    }

    public String getResonA01() {
        return resonA01;
    }

    public void setResonA01(String resonA01) {
        this.resonA01 = resonA01;
    }

    public LocalDate getDateA01() {
        return dateA01;
    }

    public void setDateA01(LocalDate dateA01) {
        this.dateA01 = dateA01;
    }

    public String getYndA02() {
        return yndA02;
    }

    public void setYndA02(String yndA02) {
        this.yndA02 = yndA02;
    }

    public String getResonA02() {
        return resonA02;
    }

    public void setResonA02(String resonA02) {
        this.resonA02 = resonA02;
    }

    public LocalDate getDateA02() {
        return dateA02;
    }

    public void setDateA02(LocalDate dateA02) {
        this.dateA02 = dateA02;
    }

    public String getYndA03() {
        return yndA03;
    }

    public void setYndA03(String yndA03) {
        this.yndA03 = yndA03;
    }

    public String getResonA03() {
        return resonA03;
    }

    public void setResonA03(String resonA03) {
        this.resonA03 = resonA03;
    }

    public LocalDate getDateA03() {
        return dateA03;
    }

    public void setDateA03(LocalDate dateA03) {
        this.dateA03 = dateA03;
    }

    public String getYndA04() {
        return yndA04;
    }

    public void setYndA04(String yndA04) {
        this.yndA04 = yndA04;
    }

    public String getResonA04() {
        return resonA04;
    }

    public void setResonA04(String resonA04) {
        this.resonA04 = resonA04;
    }

    public LocalDate getDateA04() {
        return dateA04;
    }

    public void setDateA04(LocalDate dateA04) {
        this.dateA04 = dateA04;
    }

    public String getYndA05() {
        return yndA05;
    }

    public void setYndA05(String yndA05) {
        this.yndA05 = yndA05;
    }

    public String getResonA05() {
        return resonA05;
    }

    public void setResonA05(String resonA05) {
        this.resonA05 = resonA05;
    }

    public LocalDate getDateA05() {
        return dateA05;
    }

    public void setDateA05(LocalDate dateA05) {
        this.dateA05 = dateA05;
    }

    public String getYndA06() {
        return yndA06;
    }

    public void setYndA06(String yndA06) {
        this.yndA06 = yndA06;
    }

    public String getResonA06() {
        return resonA06;
    }

    public void setResonA06(String resonA06) {
        this.resonA06 = resonA06;
    }

    public LocalDate getDateA06() {
        return dateA06;
    }

    public void setDateA06(LocalDate dateA06) {
        this.dateA06 = dateA06;
    }

    public String getYndA07() {
        return yndA07;
    }

    public void setYndA07(String yndA07) {
        this.yndA07 = yndA07;
    }

    public String getResonA07() {
        return resonA07;
    }

    public void setResonA07(String resonA07) {
        this.resonA07 = resonA07;
    }

    public LocalDate getDateA07() {
        return dateA07;
    }

    public void setDateA07(LocalDate dateA07) {
        this.dateA07 = dateA07;
    }

    public String getYndA08() {
        return yndA08;
    }

    public void setYndA08(String yndA08) {
        this.yndA08 = yndA08;
    }

    public String getResonA08() {
        return resonA08;
    }

    public void setResonA08(String resonA08) {
        this.resonA08 = resonA08;
    }

    public LocalDate getDateA08() {
        return dateA08;
    }

    public void setDateA08(LocalDate dateA08) {
        this.dateA08 = dateA08;
    }

    public String getYndA09() {
        return yndA09;
    }

    public void setYndA09(String yndA09) {
        this.yndA09 = yndA09;
    }

    public String getResonA09() {
        return resonA09;
    }

    public void setResonA09(String resonA09) {
        this.resonA09 = resonA09;
    }

    public LocalDate getDateA09() {
        return dateA09;
    }

    public void setDateA09(LocalDate dateA09) {
        this.dateA09 = dateA09;
    }

    public String getYndA10() {
        return yndA10;
    }

    public void setYndA10(String yndA10) {
        this.yndA10 = yndA10;
    }

    public String getResonA10() {
        return resonA10;
    }

    public void setResonA10(String resonA10) {
        this.resonA10 = resonA10;
    }

    public LocalDate getDateA10() {
        return dateA10;
    }

    public void setDateA10(LocalDate dateA10) {
        this.dateA10 = dateA10;
    }

    public String getYndA11() {
        return yndA11;
    }

    public void setYndA11(String yndA11) {
        this.yndA11 = yndA11;
    }

    public String getResonA11() {
        return resonA11;
    }

    public void setResonA11(String resonA11) {
        this.resonA11 = resonA11;
    }

    public LocalDate getDateA11() {
        return dateA11;
    }

    public void setDateA11(LocalDate dateA11) {
        this.dateA11 = dateA11;
    }

    public String getYndA12() {
        return yndA12;
    }

    public void setYndA12(String yndA12) {
        this.yndA12 = yndA12;
    }

    public String getResonA12() {
        return resonA12;
    }

    public void setResonA12(String resonA12) {
        this.resonA12 = resonA12;
    }

    public LocalDate getDateA12() {
        return dateA12;
    }

    public void setDateA12(LocalDate dateA12) {
        this.dateA12 = dateA12;
    }

    public String getYndA13() {
        return yndA13;
    }

    public void setYndA13(String yndA13) {
        this.yndA13 = yndA13;
    }

    public String getResonA13() {
        return resonA13;
    }

    public void setResonA13(String resonA13) {
        this.resonA13 = resonA13;
    }

    public LocalDate getDateA13() {
        return dateA13;
    }

    public void setDateA13(LocalDate dateA13) {
        this.dateA13 = dateA13;
    }

    public String getYndA14() {
        return yndA14;
    }

    public void setYndA14(String yndA14) {
        this.yndA14 = yndA14;
    }

    public String getResonA14() {
        return resonA14;
    }

    public void setResonA14(String resonA14) {
        this.resonA14 = resonA14;
    }

    public LocalDate getDateA14() {
        return dateA14;
    }

    public void setDateA14(LocalDate dateA14) {
        this.dateA14 = dateA14;
    }

    public String getYndA15() {
        return yndA15;
    }

    public void setYndA15(String yndA15) {
        this.yndA15 = yndA15;
    }

    public String getResonA15() {
        return resonA15;
    }

    public void setResonA15(String resonA15) {
        this.resonA15 = resonA15;
    }

    public LocalDate getDateA15() {
        return dateA15;
    }

    public void setDateA15(LocalDate dateA15) {
        this.dateA15 = dateA15;
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

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getYndA16() {
        return yndA16;
    }

    public void setYndA16(String yndA16) {
        this.yndA16 = yndA16;
    }

    public String getResonA16() {
        return resonA16;
    }

    public void setResonA16(String resonA16) {
        this.resonA16 = resonA16;
    }

    public LocalDate getDateA16() {
        return dateA16;
    }

    public void setDateA16(LocalDate dateA16) {
        this.dateA16 = dateA16;
    }

    public String getYndB01() {
        return yndB01;
    }

    public void setYndB01(String yndB01) {
        this.yndB01 = yndB01;
    }

    public String getResonB01() {
        return resonB01;
    }

    public void setResonB01(String resonB01) {
        this.resonB01 = resonB01;
    }

    public LocalDate getDateB01() {
        return dateB01;
    }

    public void setDateB01(LocalDate dateB01) {
        this.dateB01 = dateB01;
    }

    public String getYndB02() {
        return yndB02;
    }

    public void setYndB02(String yndB02) {
        this.yndB02 = yndB02;
    }

    public String getResonB02() {
        return resonB02;
    }

    public void setResonB02(String resonB02) {
        this.resonB02 = resonB02;
    }

    public LocalDate getDateB02() {
        return dateB02;
    }

    public void setDateB02(LocalDate dateB02) {
        this.dateB02 = dateB02;
    }

    public String getYndB03() {
        return yndB03;
    }

    public void setYndB03(String yndB03) {
        this.yndB03 = yndB03;
    }

    public String getResonB03() {
        return resonB03;
    }

    public void setResonB03(String resonB03) {
        this.resonB03 = resonB03;
    }

    public LocalDate getDateB03() {
        return dateB03;
    }

    public void setDateB03(LocalDate dateB03) {
        this.dateB03 = dateB03;
    }

    public String getYndB04() {
        return yndB04;
    }

    public void setYndB04(String yndB04) {
        this.yndB04 = yndB04;
    }

    public String getResonB04() {
        return resonB04;
    }

    public void setResonB04(String resonB04) {
        this.resonB04 = resonB04;
    }

    public LocalDate getDateB04() {
        return dateB04;
    }

    public void setDateB04(LocalDate dateB04) {
        this.dateB04 = dateB04;
    }

    public String getYndB05() {
        return yndB05;
    }

    public void setYndB05(String yndB05) {
        this.yndB05 = yndB05;
    }

    public String getResonB05() {
        return resonB05;
    }

    public void setResonB05(String resonB05) {
        this.resonB05 = resonB05;
    }

    public LocalDate getDateB05() {
        return dateB05;
    }

    public void setDateB05(LocalDate dateB05) {
        this.dateB05 = dateB05;
    }

    public String getYndB06() {
        return yndB06;
    }

    public void setYndB06(String yndB06) {
        this.yndB06 = yndB06;
    }

    public String getResonB06() {
        return resonB06;
    }

    public void setResonB06(String resonB06) {
        this.resonB06 = resonB06;
    }

    public LocalDate getDateB06() {
        return dateB06;
    }

    public void setDateB06(LocalDate dateB06) {
        this.dateB06 = dateB06;
    }

    public String getYndB07() {
        return yndB07;
    }

    public void setYndB07(String yndB07) {
        this.yndB07 = yndB07;
    }

    public String getResonB07() {
        return resonB07;
    }

    public void setResonB07(String resonB07) {
        this.resonB07 = resonB07;
    }

    public LocalDate getDateB07() {
        return dateB07;
    }

    public void setDateB07(LocalDate dateB07) {
        this.dateB07 = dateB07;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBdgt1() {
        return bdgt1;
    }

    public void setBdgt1(BigDecimal bdgt1) {
        this.bdgt1 = bdgt1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjStcDTO)) {
            return false;
        }

        BidPrjStcDTO that = (BidPrjStcDTO) o;

        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,checkDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjStcDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", checkDate='" + getCheckDate() + "'" +
            ", admFileId=" + getAdmFileId() +
            ", yndA01='" + getYndA01() + "'" +
            ", resonA01='" + getResonA01() + "'" +
            ", dateA01='" + getDateA01() + "'" +
            ", yndA02='" + getYndA02() + "'" +
            ", resonA02='" + getResonA02() + "'" +
            ", dateA02='" + getDateA02() + "'" +
            ", yndA03='" + getYndA03() + "'" +
            ", resonA03='" + getResonA03() + "'" +
            ", dateA03='" + getDateA03() + "'" +
            ", yndA04='" + getYndA04() + "'" +
            ", resonA04='" + getResonA04() + "'" +
            ", dateA04='" + getDateA04() + "'" +
            ", yndA05='" + getYndA05() + "'" +
            ", resonA05='" + getResonA05() + "'" +
            ", dateA05='" + getDateA05() + "'" +
            ", yndA06='" + getYndA06() + "'" +
            ", resonA06='" + getResonA06() + "'" +
            ", dateA06='" + getDateA06() + "'" +
            ", yndA07='" + getYndA07() + "'" +
            ", resonA07='" + getResonA07() + "'" +
            ", dateA07='" + getDateA07() + "'" +
            ", yndA08='" + getYndA08() + "'" +
            ", resonA08='" + getResonA08() + "'" +
            ", dateA08='" + getDateA08() + "'" +
            ", yndA09='" + getYndA09() + "'" +
            ", resonA09='" + getResonA09() + "'" +
            ", dateA09='" + getDateA09() + "'" +
            ", yndA10='" + getYndA10() + "'" +
            ", resonA10='" + getResonA10() + "'" +
            ", dateA10='" + getDateA10() + "'" +
            ", yndA11='" + getYndA11() + "'" +
            ", resonA11='" + getResonA11() + "'" +
            ", dateA11='" + getDateA11() + "'" +
            ", yndA12='" + getYndA12() + "'" +
            ", resonA12='" + getResonA12() + "'" +
            ", dateA12='" + getDateA12() + "'" +
            ", yndA13='" + getYndA13() + "'" +
            ", resonA13='" + getResonA13() + "'" +
            ", dateA13='" + getDateA13() + "'" +
            ", yndA14='" + getYndA14() + "'" +
            ", resonA14='" + getResonA14() + "'" +
            ", dateA14='" + getDateA14() + "'" +
            ", yndA15='" + getYndA15() + "'" +
            ", resonA15='" + getResonA15() + "'" +
            ", dateA15='" + getDateA15() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", yndA16='" + getYndA16() + "'" +
            ", resonA16='" + getResonA16() + "'" +
            ", dateA16='" + getDateA16() + "'" +
            ", yndB01='" + getYndB01() + "'" +
            ", resonB01='" + getResonB01() + "'" +
            ", dateB01='" + getDateB01() + "'" +
            ", yndB02='" + getYndB02() + "'" +
            ", resonB02='" + getResonB02() + "'" +
            ", dateB02='" + getDateB02() + "'" +
            ", yndB03='" + getYndB03() + "'" +
            ", resonB03='" + getResonB03() + "'" +
            ", dateB03='" + getDateB03() + "'" +
            ", yndB04='" + getYndB04() + "'" +
            ", resonB04='" + getResonB04() + "'" +
            ", dateB04='" + getDateB04() + "'" +
            ", yndB05='" + getYndB05() + "'" +
            ", resonB05='" + getResonB05() + "'" +
            ", dateB05='" + getDateB05() + "'" +
            ", yndB06='" + getYndB06() + "'" +
            ", resonB06='" + getResonB06() + "'" +
            ", dateB06='" + getDateB06() + "'" +
            ", yndB07='" + getYndB07() + "'" +
            ", resonB07='" + getResonB07() + "'" +
            ", dateB07='" + getDateB07() + "'" +
            ", name='" + getName() + "'" +
            ", bdgt1='" + getBdgt1() + "'" +
            "}";
    }
}
