package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjEquip} entity.
 */
public class BidPrjEquipDTO implements Serializable {

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
    @Size(max = 2)
    private String month;

    @Size(max = 5)
    private String eCode;

    @Size(max = 20)
    private String eCnt;

    @Size(max = 200)
    private String eRemark;

    @Size(max = 60)
    private String uIp;

    @Size(max = 10)
    private String supCcut;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @NotNull
    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    public BidPrjEquipPrimaryKey getId() {
        return new BidPrjEquipPrimaryKey(
                this.wkut,this.prjno,this.year,this.month,this.eCode
        );
    }

    public void setId(BidPrjEquipPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.year = id.getYear();
        this.month = id.getMonth();
        this.eCode = id.geteCode();
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public String geteCnt() {
        return eCnt;
    }

    public void seteCnt(String eCnt) {
        this.eCnt = eCnt;
    }

    public String geteRemark() {
        return eRemark;
    }

    public void seteRemark(String eRemark) {
        this.eRemark = eRemark;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getSupCcut() {
        return supCcut;
    }

    public void setSupCcut(String supCcut) {
        this.supCcut = supCcut;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjEquipDTO)) return false;
        BidPrjEquipDTO that = (BidPrjEquipDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && year.equals(that.year) && month.equals(that.month)&& eCode.equals(that.eCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, month,eCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjEquipDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", year='" + getYear() + "'" +
            ", month='" + getMonth() + "'" +
            ", eCode='" + geteCode() + "'" +
            ", eCnt='" + geteCnt() + "'" +
            ", eRemark='" + geteRemark() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", supCcut='" + getSupCcut() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
