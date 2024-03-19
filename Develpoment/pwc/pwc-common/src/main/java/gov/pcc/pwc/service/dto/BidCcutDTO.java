package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidProjectUnitPrimaryKey;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidProjectUnit} entity.
 */
public class BidCcutDTO implements Serializable {

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    @Size(max = 2)
    private String type;

    @Size(max = 21)
    private String scut;

    @Size(max = 21)
    private String ccut;

    @Size(max = 60)
    private String name;

//BidProject
    @Size(max = 21)
    private String pcmut;

//BidSubco
    @Size(max = 20)
    private String subCcut;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScut() {return scut;}

    public void setScut(String scut) {this.scut = scut;}

    public String getCcut() {return ccut;}

    public void setCcut(String ccut) {this.ccut = ccut;}

    public String getPcmut() {return pcmut;}

    public void setPcmut(String pcmut) {this.pcmut = pcmut;}

    public String getSubCcut() {return subCcut;}

    public void setSubCcut(String subCcut) {this.subCcut = subCcut;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidCcutDTO)) return false;
        BidCcutDTO that = (BidCcutDTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(type, that.type) && Objects.equals(scut, that.scut) && Objects.equals(ccut, that.ccut) && Objects.equals(name, that.name) && Objects.equals(pcmut, that.pcmut) && Objects.equals(subCcut, that.subCcut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, type, scut, ccut, name, pcmut, subCcut);
    }

    @Override
    public String toString() {
        return "BidCcutDTO{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", type='" + type + '\'' +
                ", scut='" + scut + '\'' +
                ", ccut='" + ccut + '\'' +
                ", name='" + name + '\'' +
                ", pcmut='" + pcmut + '\'' +
                ", subCcut='" + subCcut + '\'' +
                '}';
    }
}
