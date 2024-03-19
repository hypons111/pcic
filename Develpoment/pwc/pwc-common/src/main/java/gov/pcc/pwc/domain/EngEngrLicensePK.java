package gov.pcc.pwc.domain;

import com.google.common.base.Objects;
import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class EngEngrLicensePK implements Serializable {
    private String engEngrLicenseId;
    private BigDecimal engEngrLicenseVersion;

    public EngEngrLicensePK(){};

    public EngEngrLicensePK(String engEngrLicenseId, BigDecimal engEngrLicenseVersion) {
        this.engEngrLicenseId = engEngrLicenseId;
        this.engEngrLicenseVersion = engEngrLicenseVersion;
    }

    public String getEngEngrLicenseId() {
        return this.engEngrLicenseId;
    }

    public void setEngEngrLicenseId(String engEngrLicenseId) {
        this.engEngrLicenseId = engEngrLicenseId;
    }

    public BigDecimal getEngEngrLicenseVersion() {
        return this.engEngrLicenseVersion;
    }

    public void setEngEngrLicenseVersion(BigDecimal engEngrLicenseVersion) {
        this.engEngrLicenseVersion = engEngrLicenseVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngEngrLicensePK that = (EngEngrLicensePK) o;
        return Objects.equal(engEngrLicenseId, that.engEngrLicenseId) && Objects.equal(engEngrLicenseVersion, that.engEngrLicenseVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(engEngrLicenseId, engEngrLicenseVersion);
    }

}
