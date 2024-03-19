package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjScequipPrimaryKey;
import gov.pcc.pwc.domain.BidPrjSubcoPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjScequip} entity.
 */
public class BidPrjScequipDTO implements Serializable {

//    private Long id;

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @Size(max = 20)
    private String subCcut;

    @Size(max = 5)
    private String eCode;

    @Size(max = 20)
    private String eCnt;

    @Size(max = 2000)
    private String eRemark;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 1)
    private String confA;

    @Size(max = 1)
    private String confB;

    @Size(max = 60)
    private String uIp;

    private List<BidPrjScequipDTO> scequips ;

    public BidPrjScequipPrimaryKey getId() {
        return new BidPrjScequipPrimaryKey(this.wkut, this.prjno, this.subCcut,this.eCode);
    }

    public void setId(BidPrjScequipPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.subCcut = id.getSubCcut();
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

    public String getSubCcut() {
        return subCcut;
    }

    public void setSubCcut(String subCcut) {
        this.subCcut = subCcut;
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

    public String getConfA() {
        return confA;
    }

    public void setConfA(String confA) {
        this.confA = confA;
    }

    public String getConfB() {
        return confB;
    }

    public void setConfB(String confB) {
        this.confB = confB;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public List<BidPrjScequipDTO> getScequips() {
        return scequips;
    }

    public void setScequips(List<BidPrjScequipDTO> scequips) {
        this.scequips = scequips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjScequipDTO)) {
            return false;
        }

        BidPrjScequipDTO that = (BidPrjScequipDTO) o;
        return (
                wkut.equals(that.wkut) &&
                        prjno.equals(that.prjno) &&
                        subCcut.equals(that.subCcut) &&
                        eCode.equals(that.eCode)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, subCcut, eCode);
    }


    @Override
    public String toString() {
        return "BidPrjScequipDTO{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", subCcut='" + subCcut + '\'' +
                ", eCode='" + eCode + '\'' +
                ", eCnt='" + eCnt + '\'' +
                ", eRemark='" + eRemark + '\'' +
                ", createDate=" + createDate +
                ", createUser='" + createUser + '\'' +
                ", updateDate=" + updateDate +
                ", updateUser='" + updateUser + '\'' +
                ", confA='" + confA + '\'' +
                ", confB='" + confB + '\'' +
                ", uIp='" + uIp + '\'' +
                ", scequips=" + scequips +
                '}';
    }
}
