package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjStc.
 */

@IdClass(BidPrjStcPrimaryKey.class)
@Entity
@Table(name = "bid_prj_stc")
public class BidPrjStc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;

    @Id
    @Size(max = 40)
    @Column(name = "prjno", length = 40)
    private String prjno;

    @Id
    @Column(name = "check_date")
    private LocalDate checkDate;

    @Column(name = "adm_file_id")
    private Long admFileId;

    @Size(max = 7)
    @Column(name = "ynd_a01", length = 7)
    private String yndA01;

    @Size(max = 400)
    @Column(name = "reson_a01", length = 400)
    private String resonA01;

    @Column(name = "date_a01")
    private LocalDate dateA01;

    @Size(max = 7)
    @Column(name = "ynd_a02", length = 7)
    private String yndA02;

    @Size(max = 400)
    @Column(name = "reson_a02", length = 400)
    private String resonA02;

    @Column(name = "date_a02")
    private LocalDate dateA02;

    @Size(max = 7)
    @Column(name = "ynd_a03", length = 7)
    private String yndA03;

    @Size(max = 400)
    @Column(name = "reson_a03", length = 400)
    private String resonA03;

    @Column(name = "date_a03")
    private LocalDate dateA03;

    @Size(max = 7)
    @Column(name = "ynd_a04", length = 7)
    private String yndA04;

    @Size(max = 400)
    @Column(name = "reson_a04", length = 400)
    private String resonA04;

    @Column(name = "date_a04")
    private LocalDate dateA04;

    @Size(max = 7)
    @Column(name = "ynd_a05", length = 7)
    private String yndA05;

    @Size(max = 400)
    @Column(name = "reson_a05", length = 400)
    private String resonA05;

    @Column(name = "date_a05")
    private LocalDate dateA05;

    @Size(max = 7)
    @Column(name = "ynd_a06", length = 7)
    private String yndA06;

    @Size(max = 400)
    @Column(name = "reson_a06", length = 400)
    private String resonA06;

    @Column(name = "date_a06")
    private LocalDate dateA06;

    @Size(max = 7)
    @Column(name = "ynd_a07", length = 7)
    private String yndA07;

    @Size(max = 400)
    @Column(name = "reson_a07", length = 400)
    private String resonA07;

    @Column(name = "date_a07")
    private LocalDate dateA07;

    @Size(max = 7)
    @Column(name = "ynd_a08", length = 7)
    private String yndA08;

    @Size(max = 400)
    @Column(name = "reson_a08", length = 400)
    private String resonA08;

    @Column(name = "date_a08")
    private LocalDate dateA08;

    @Size(max = 7)
    @Column(name = "ynd_a09", length = 7)
    private String yndA09;

    @Size(max = 400)
    @Column(name = "reson_a09", length = 400)
    private String resonA09;

    @Column(name = "date_a09")
    private LocalDate dateA09;

    @Size(max = 7)
    @Column(name = "ynd_a10", length = 7)
    private String yndA10;

    @Size(max = 400)
    @Column(name = "reson_a10", length = 400)
    private String resonA10;

    @Column(name = "date_a10")
    private LocalDate dateA10;

    @Size(max = 7)
    @Column(name = "ynd_a11", length = 7)
    private String yndA11;

    @Size(max = 400)
    @Column(name = "reson_a11", length = 400)
    private String resonA11;

    @Column(name = "date_a11")
    private LocalDate dateA11;

    @Size(max = 7)
    @Column(name = "ynd_a12", length = 7)
    private String yndA12;

    @Size(max = 400)
    @Column(name = "reson_a12", length = 400)
    private String resonA12;

    @Column(name = "date_a12")
    private LocalDate dateA12;

    @Size(max = 7)
    @Column(name = "ynd_a13", length = 7)
    private String yndA13;

    @Size(max = 400)
    @Column(name = "reson_a13", length = 400)
    private String resonA13;

    @Column(name = "date_a13")
    private LocalDate dateA13;

    @Size(max = 7)
    @Column(name = "ynd_a14", length = 7)
    private String yndA14;

    @Size(max = 400)
    @Column(name = "reson_a14", length = 400)
    private String resonA14;

    @Column(name = "date_a14")
    private LocalDate dateA14;

    @Size(max = 7)
    @Column(name = "ynd_a15", length = 7)
    private String yndA15;

    @Size(max = 400)
    @Column(name = "reson_a15", length = 400)
    private String resonA15;

    @Column(name = "date_a15")
    private LocalDate dateA15;

    @Column(name = "create_date")
    private Instant createDate;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Size(max = 7)
    @Column(name = "ynd_a16", length = 7)
    private String yndA16;

    @Size(max = 400)
    @Column(name = "reson_a16", length = 400)
    private String resonA16;

    @Column(name = "date_a16")
    private LocalDate dateA16;

    @Size(max = 7)
    @Column(name = "ynd_b01", length = 7)
    private String yndB01;

    @Size(max = 400)
    @Column(name = "reson_b01", length = 400)
    private String resonB01;

    @Column(name = "date_b01")
    private LocalDate dateB01;

    @Size(max = 7)
    @Column(name = "ynd_b02", length = 7)
    private String yndB02;

    @Size(max = 400)
    @Column(name = "reson_b02", length = 400)
    private String resonB02;

    @Column(name = "date_b02")
    private LocalDate dateB02;

    @Size(max = 7)
    @Column(name = "ynd_b03", length = 7)
    private String yndB03;

    @Size(max = 400)
    @Column(name = "reson_b03", length = 400)
    private String resonB03;

    @Column(name = "date_b03")
    private LocalDate dateB03;

    @Size(max = 7)
    @Column(name = "ynd_b04", length = 7)
    private String yndB04;

    @Size(max = 400)
    @Column(name = "reson_b04", length = 400)
    private String resonB04;

    @Column(name = "date_b04")
    private LocalDate dateB04;

    @Size(max = 7)
    @Column(name = "ynd_b05", length = 7)
    private String yndB05;

    @Size(max = 400)
    @Column(name = "reson_b05", length = 400)
    private String resonB05;

    @Column(name = "date_b05")
    private LocalDate dateB05;

    @Size(max = 7)
    @Column(name = "ynd_b06", length = 7)
    private String yndB06;

    @Size(max = 400)
    @Column(name = "reson_b06", length = 400)
    private String resonB06;

    @Column(name = "date_b06")
    private LocalDate dateB06;

    @Size(max = 7)
    @Column(name = "ynd_b07", length = 7)
    private String yndB07;

    @Size(max = 400)
    @Column(name = "reson_b07", length = 400)
    private String resonB07;

    @Column(name = "date_b07")
    private LocalDate dateB07;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjStcPrimaryKey getId() {
        return new BidPrjStcPrimaryKey(wkut,prjno,checkDate);
    }

    public BidPrjStc id(BidPrjStcPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjStcPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.checkDate = id.getCheckDate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjStc wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjStc prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getCheckDate() {
        return this.checkDate;
    }

    public BidPrjStc checkDate(LocalDate checkDate) {
        this.setCheckDate(checkDate);
        return this;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public Long getAdmFileId() {
        return this.admFileId;
    }

    public BidPrjStc admFileId(Long admFileId) {
        this.setAdmFileId(admFileId);
        return this;
    }

    public void setAdmFileId(Long admFileId) {
        this.admFileId = admFileId;
    }

    public String getYndA01() {
        return this.yndA01;
    }

    public BidPrjStc yndA01(String yndA01) {
        this.setYndA01(yndA01);
        return this;
    }

    public void setYndA01(String yndA01) {
        this.yndA01 = yndA01;
    }

    public String getResonA01() {
        return this.resonA01;
    }

    public BidPrjStc resonA01(String resonA01) {
        this.setResonA01(resonA01);
        return this;
    }

    public void setResonA01(String resonA01) {
        this.resonA01 = resonA01;
    }

    public LocalDate getDateA01() {
        return this.dateA01;
    }

    public BidPrjStc dateA01(LocalDate dateA01) {
        this.setDateA01(dateA01);
        return this;
    }

    public void setDateA01(LocalDate dateA01) {
        this.dateA01 = dateA01;
    }

    public String getYndA02() {
        return this.yndA02;
    }

    public BidPrjStc yndA02(String yndA02) {
        this.setYndA02(yndA02);
        return this;
    }

    public void setYndA02(String yndA02) {
        this.yndA02 = yndA02;
    }

    public String getResonA02() {
        return this.resonA02;
    }

    public BidPrjStc resonA02(String resonA02) {
        this.setResonA02(resonA02);
        return this;
    }

    public void setResonA02(String resonA02) {
        this.resonA02 = resonA02;
    }

    public LocalDate getDateA02() {
        return this.dateA02;
    }

    public BidPrjStc dateA02(LocalDate dateA02) {
        this.setDateA02(dateA02);
        return this;
    }

    public void setDateA02(LocalDate dateA02) {
        this.dateA02 = dateA02;
    }

    public String getYndA03() {
        return this.yndA03;
    }

    public BidPrjStc yndA03(String yndA03) {
        this.setYndA03(yndA03);
        return this;
    }

    public void setYndA03(String yndA03) {
        this.yndA03 = yndA03;
    }

    public String getResonA03() {
        return this.resonA03;
    }

    public BidPrjStc resonA03(String resonA03) {
        this.setResonA03(resonA03);
        return this;
    }

    public void setResonA03(String resonA03) {
        this.resonA03 = resonA03;
    }

    public LocalDate getDateA03() {
        return this.dateA03;
    }

    public BidPrjStc dateA03(LocalDate dateA03) {
        this.setDateA03(dateA03);
        return this;
    }

    public void setDateA03(LocalDate dateA03) {
        this.dateA03 = dateA03;
    }

    public String getYndA04() {
        return this.yndA04;
    }

    public BidPrjStc yndA04(String yndA04) {
        this.setYndA04(yndA04);
        return this;
    }

    public void setYndA04(String yndA04) {
        this.yndA04 = yndA04;
    }

    public String getResonA04() {
        return this.resonA04;
    }

    public BidPrjStc resonA04(String resonA04) {
        this.setResonA04(resonA04);
        return this;
    }

    public void setResonA04(String resonA04) {
        this.resonA04 = resonA04;
    }

    public LocalDate getDateA04() {
        return this.dateA04;
    }

    public BidPrjStc dateA04(LocalDate dateA04) {
        this.setDateA04(dateA04);
        return this;
    }

    public void setDateA04(LocalDate dateA04) {
        this.dateA04 = dateA04;
    }

    public String getYndA05() {
        return this.yndA05;
    }

    public BidPrjStc yndA05(String yndA05) {
        this.setYndA05(yndA05);
        return this;
    }

    public void setYndA05(String yndA05) {
        this.yndA05 = yndA05;
    }

    public String getResonA05() {
        return this.resonA05;
    }

    public BidPrjStc resonA05(String resonA05) {
        this.setResonA05(resonA05);
        return this;
    }

    public void setResonA05(String resonA05) {
        this.resonA05 = resonA05;
    }

    public LocalDate getDateA05() {
        return this.dateA05;
    }

    public BidPrjStc dateA05(LocalDate dateA05) {
        this.setDateA05(dateA05);
        return this;
    }

    public void setDateA05(LocalDate dateA05) {
        this.dateA05 = dateA05;
    }

    public String getYndA06() {
        return this.yndA06;
    }

    public BidPrjStc yndA06(String yndA06) {
        this.setYndA06(yndA06);
        return this;
    }

    public void setYndA06(String yndA06) {
        this.yndA06 = yndA06;
    }

    public String getResonA06() {
        return this.resonA06;
    }

    public BidPrjStc resonA06(String resonA06) {
        this.setResonA06(resonA06);
        return this;
    }

    public void setResonA06(String resonA06) {
        this.resonA06 = resonA06;
    }

    public LocalDate getDateA06() {
        return this.dateA06;
    }

    public BidPrjStc dateA06(LocalDate dateA06) {
        this.setDateA06(dateA06);
        return this;
    }

    public void setDateA06(LocalDate dateA06) {
        this.dateA06 = dateA06;
    }

    public String getYndA07() {
        return this.yndA07;
    }

    public BidPrjStc yndA07(String yndA07) {
        this.setYndA07(yndA07);
        return this;
    }

    public void setYndA07(String yndA07) {
        this.yndA07 = yndA07;
    }

    public String getResonA07() {
        return this.resonA07;
    }

    public BidPrjStc resonA07(String resonA07) {
        this.setResonA07(resonA07);
        return this;
    }

    public void setResonA07(String resonA07) {
        this.resonA07 = resonA07;
    }

    public LocalDate getDateA07() {
        return this.dateA07;
    }

    public BidPrjStc dateA07(LocalDate dateA07) {
        this.setDateA07(dateA07);
        return this;
    }

    public void setDateA07(LocalDate dateA07) {
        this.dateA07 = dateA07;
    }

    public String getYndA08() {
        return this.yndA08;
    }

    public BidPrjStc yndA08(String yndA08) {
        this.setYndA08(yndA08);
        return this;
    }

    public void setYndA08(String yndA08) {
        this.yndA08 = yndA08;
    }

    public String getResonA08() {
        return this.resonA08;
    }

    public BidPrjStc resonA08(String resonA08) {
        this.setResonA08(resonA08);
        return this;
    }

    public void setResonA08(String resonA08) {
        this.resonA08 = resonA08;
    }

    public LocalDate getDateA08() {
        return this.dateA08;
    }

    public BidPrjStc dateA08(LocalDate dateA08) {
        this.setDateA08(dateA08);
        return this;
    }

    public void setDateA08(LocalDate dateA08) {
        this.dateA08 = dateA08;
    }

    public String getYndA09() {
        return this.yndA09;
    }

    public BidPrjStc yndA09(String yndA09) {
        this.setYndA09(yndA09);
        return this;
    }

    public void setYndA09(String yndA09) {
        this.yndA09 = yndA09;
    }

    public String getResonA09() {
        return this.resonA09;
    }

    public BidPrjStc resonA09(String resonA09) {
        this.setResonA09(resonA09);
        return this;
    }

    public void setResonA09(String resonA09) {
        this.resonA09 = resonA09;
    }

    public LocalDate getDateA09() {
        return this.dateA09;
    }

    public BidPrjStc dateA09(LocalDate dateA09) {
        this.setDateA09(dateA09);
        return this;
    }

    public void setDateA09(LocalDate dateA09) {
        this.dateA09 = dateA09;
    }

    public String getYndA10() {
        return this.yndA10;
    }

    public BidPrjStc yndA10(String yndA10) {
        this.setYndA10(yndA10);
        return this;
    }

    public void setYndA10(String yndA10) {
        this.yndA10 = yndA10;
    }

    public String getResonA10() {
        return this.resonA10;
    }

    public BidPrjStc resonA10(String resonA10) {
        this.setResonA10(resonA10);
        return this;
    }

    public void setResonA10(String resonA10) {
        this.resonA10 = resonA10;
    }

    public LocalDate getDateA10() {
        return this.dateA10;
    }

    public BidPrjStc dateA10(LocalDate dateA10) {
        this.setDateA10(dateA10);
        return this;
    }

    public void setDateA10(LocalDate dateA10) {
        this.dateA10 = dateA10;
    }

    public String getYndA11() {
        return this.yndA11;
    }

    public BidPrjStc yndA11(String yndA11) {
        this.setYndA11(yndA11);
        return this;
    }

    public void setYndA11(String yndA11) {
        this.yndA11 = yndA11;
    }

    public String getResonA11() {
        return this.resonA11;
    }

    public BidPrjStc resonA11(String resonA11) {
        this.setResonA11(resonA11);
        return this;
    }

    public void setResonA11(String resonA11) {
        this.resonA11 = resonA11;
    }

    public LocalDate getDateA11() {
        return this.dateA11;
    }

    public BidPrjStc dateA11(LocalDate dateA11) {
        this.setDateA11(dateA11);
        return this;
    }

    public void setDateA11(LocalDate dateA11) {
        this.dateA11 = dateA11;
    }

    public String getYndA12() {
        return this.yndA12;
    }

    public BidPrjStc yndA12(String yndA12) {
        this.setYndA12(yndA12);
        return this;
    }

    public void setYndA12(String yndA12) {
        this.yndA12 = yndA12;
    }

    public String getResonA12() {
        return this.resonA12;
    }

    public BidPrjStc resonA12(String resonA12) {
        this.setResonA12(resonA12);
        return this;
    }

    public void setResonA12(String resonA12) {
        this.resonA12 = resonA12;
    }

    public LocalDate getDateA12() {
        return this.dateA12;
    }

    public BidPrjStc dateA12(LocalDate dateA12) {
        this.setDateA12(dateA12);
        return this;
    }

    public void setDateA12(LocalDate dateA12) {
        this.dateA12 = dateA12;
    }

    public String getYndA13() {
        return this.yndA13;
    }

    public BidPrjStc yndA13(String yndA13) {
        this.setYndA13(yndA13);
        return this;
    }

    public void setYndA13(String yndA13) {
        this.yndA13 = yndA13;
    }

    public String getResonA13() {
        return this.resonA13;
    }

    public BidPrjStc resonA13(String resonA13) {
        this.setResonA13(resonA13);
        return this;
    }

    public void setResonA13(String resonA13) {
        this.resonA13 = resonA13;
    }

    public LocalDate getDateA13() {
        return this.dateA13;
    }

    public BidPrjStc dateA13(LocalDate dateA13) {
        this.setDateA13(dateA13);
        return this;
    }

    public void setDateA13(LocalDate dateA13) {
        this.dateA13 = dateA13;
    }

    public String getYndA14() {
        return this.yndA14;
    }

    public BidPrjStc yndA14(String yndA14) {
        this.setYndA14(yndA14);
        return this;
    }

    public void setYndA14(String yndA14) {
        this.yndA14 = yndA14;
    }

    public String getResonA14() {
        return this.resonA14;
    }

    public BidPrjStc resonA14(String resonA14) {
        this.setResonA14(resonA14);
        return this;
    }

    public void setResonA14(String resonA14) {
        this.resonA14 = resonA14;
    }

    public LocalDate getDateA14() {
        return this.dateA14;
    }

    public BidPrjStc dateA14(LocalDate dateA14) {
        this.setDateA14(dateA14);
        return this;
    }

    public void setDateA14(LocalDate dateA14) {
        this.dateA14 = dateA14;
    }

    public String getYndA15() {
        return this.yndA15;
    }

    public BidPrjStc yndA15(String yndA15) {
        this.setYndA15(yndA15);
        return this;
    }

    public void setYndA15(String yndA15) {
        this.yndA15 = yndA15;
    }

    public String getResonA15() {
        return this.resonA15;
    }

    public BidPrjStc resonA15(String resonA15) {
        this.setResonA15(resonA15);
        return this;
    }

    public void setResonA15(String resonA15) {
        this.resonA15 = resonA15;
    }

    public LocalDate getDateA15() {
        return this.dateA15;
    }

    public BidPrjStc dateA15(LocalDate dateA15) {
        this.setDateA15(dateA15);
        return this;
    }

    public void setDateA15(LocalDate dateA15) {
        this.dateA15 = dateA15;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjStc createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjStc createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjStc updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjStc updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjStc dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getYndA16() {
        return this.yndA16;
    }

    public BidPrjStc yndA16(String yndA16) {
        this.setYndA16(yndA16);
        return this;
    }

    public void setYndA16(String yndA16) {
        this.yndA16 = yndA16;
    }

    public String getResonA16() {
        return this.resonA16;
    }

    public BidPrjStc resonA16(String resonA16) {
        this.setResonA16(resonA16);
        return this;
    }

    public void setResonA16(String resonA16) {
        this.resonA16 = resonA16;
    }

    public LocalDate getDateA16() {
        return this.dateA16;
    }

    public BidPrjStc dateA16(LocalDate dateA16) {
        this.setDateA16(dateA16);
        return this;
    }

    public void setDateA16(LocalDate dateA16) {
        this.dateA16 = dateA16;
    }

    public String getYndB01() {
        return this.yndB01;
    }

    public BidPrjStc yndB01(String yndB01) {
        this.setYndB01(yndB01);
        return this;
    }

    public void setYndB01(String yndB01) {
        this.yndB01 = yndB01;
    }

    public String getResonB01() {
        return this.resonB01;
    }

    public BidPrjStc resonB01(String resonB01) {
        this.setResonB01(resonB01);
        return this;
    }

    public void setResonB01(String resonB01) {
        this.resonB01 = resonB01;
    }

    public LocalDate getDateB01() {
        return this.dateB01;
    }

    public BidPrjStc dateB01(LocalDate dateB01) {
        this.setDateB01(dateB01);
        return this;
    }

    public void setDateB01(LocalDate dateB01) {
        this.dateB01 = dateB01;
    }

    public String getYndB02() {
        return this.yndB02;
    }

    public BidPrjStc yndB02(String yndB02) {
        this.setYndB02(yndB02);
        return this;
    }

    public void setYndB02(String yndB02) {
        this.yndB02 = yndB02;
    }

    public String getResonB02() {
        return this.resonB02;
    }

    public BidPrjStc resonB02(String resonB02) {
        this.setResonB02(resonB02);
        return this;
    }

    public void setResonB02(String resonB02) {
        this.resonB02 = resonB02;
    }

    public LocalDate getDateB02() {
        return this.dateB02;
    }

    public BidPrjStc dateB02(LocalDate dateB02) {
        this.setDateB02(dateB02);
        return this;
    }

    public void setDateB02(LocalDate dateB02) {
        this.dateB02 = dateB02;
    }

    public String getYndB03() {
        return this.yndB03;
    }

    public BidPrjStc yndB03(String yndB03) {
        this.setYndB03(yndB03);
        return this;
    }

    public void setYndB03(String yndB03) {
        this.yndB03 = yndB03;
    }

    public String getResonB03() {
        return this.resonB03;
    }

    public BidPrjStc resonB03(String resonB03) {
        this.setResonB03(resonB03);
        return this;
    }

    public void setResonB03(String resonB03) {
        this.resonB03 = resonB03;
    }

    public LocalDate getDateB03() {
        return this.dateB03;
    }

    public BidPrjStc dateB03(LocalDate dateB03) {
        this.setDateB03(dateB03);
        return this;
    }

    public void setDateB03(LocalDate dateB03) {
        this.dateB03 = dateB03;
    }

    public String getYndB04() {
        return this.yndB04;
    }

    public BidPrjStc yndB04(String yndB04) {
        this.setYndB04(yndB04);
        return this;
    }

    public void setYndB04(String yndB04) {
        this.yndB04 = yndB04;
    }

    public String getResonB04() {
        return this.resonB04;
    }

    public BidPrjStc resonB04(String resonB04) {
        this.setResonB04(resonB04);
        return this;
    }

    public void setResonB04(String resonB04) {
        this.resonB04 = resonB04;
    }

    public LocalDate getDateB04() {
        return this.dateB04;
    }

    public BidPrjStc dateB04(LocalDate dateB04) {
        this.setDateB04(dateB04);
        return this;
    }

    public void setDateB04(LocalDate dateB04) {
        this.dateB04 = dateB04;
    }

    public String getYndB05() {
        return this.yndB05;
    }

    public BidPrjStc yndB05(String yndB05) {
        this.setYndB05(yndB05);
        return this;
    }

    public void setYndB05(String yndB05) {
        this.yndB05 = yndB05;
    }

    public String getResonB05() {
        return this.resonB05;
    }

    public BidPrjStc resonB05(String resonB05) {
        this.setResonB05(resonB05);
        return this;
    }

    public void setResonB05(String resonB05) {
        this.resonB05 = resonB05;
    }

    public LocalDate getDateB05() {
        return this.dateB05;
    }

    public BidPrjStc dateB05(LocalDate dateB05) {
        this.setDateB05(dateB05);
        return this;
    }

    public void setDateB05(LocalDate dateB05) {
        this.dateB05 = dateB05;
    }

    public String getYndB06() {
        return this.yndB06;
    }

    public BidPrjStc yndB06(String yndB06) {
        this.setYndB06(yndB06);
        return this;
    }

    public void setYndB06(String yndB06) {
        this.yndB06 = yndB06;
    }

    public String getResonB06() {
        return this.resonB06;
    }

    public BidPrjStc resonB06(String resonB06) {
        this.setResonB06(resonB06);
        return this;
    }

    public void setResonB06(String resonB06) {
        this.resonB06 = resonB06;
    }

    public LocalDate getDateB06() {
        return this.dateB06;
    }

    public BidPrjStc dateB06(LocalDate dateB06) {
        this.setDateB06(dateB06);
        return this;
    }

    public void setDateB06(LocalDate dateB06) {
        this.dateB06 = dateB06;
    }

    public String getYndB07() {
        return this.yndB07;
    }

    public BidPrjStc yndB07(String yndB07) {
        this.setYndB07(yndB07);
        return this;
    }

    public void setYndB07(String yndB07) {
        this.yndB07 = yndB07;
    }

    public String getResonB07() {
        return this.resonB07;
    }

    public BidPrjStc resonB07(String resonB07) {
        this.setResonB07(resonB07);
        return this;
    }

    public void setResonB07(String resonB07) {
        this.resonB07 = resonB07;
    }

    public LocalDate getDateB07() {
        return this.dateB07;
    }

    public BidPrjStc dateB07(LocalDate dateB07) {
        this.setDateB07(dateB07);
        return this;
    }

    public void setDateB07(LocalDate dateB07) {
        this.dateB07 = dateB07;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjStc)) {
            return false;
        }
        BidPrjStc that = (BidPrjStc) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return Objects.hash(wkut,prjno,checkDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjStc{" +
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
            "}";
    }
}
