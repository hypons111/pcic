package gov.pcc.pwc.domain;

import com.google.common.base.Objects;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class EngEngrCertificatePK implements Serializable {
    private String engEngrCertificateId;
    private BigDecimal engEngrCertificateVersion;

    public EngEngrCertificatePK() {};

    public EngEngrCertificatePK(String engEngrCertificateId, BigDecimal engEngrCertificateVersion) {
        this.engEngrCertificateId = engEngrCertificateId;
        this.engEngrCertificateVersion = engEngrCertificateVersion;
    }

    public String getEngEngrCertificateId() {
        return engEngrCertificateId;
    }

    public void setEngEngrCertificateId(String engEngrCertificateId) {
        this.engEngrCertificateId = engEngrCertificateId;
    }

    public BigDecimal getEngEngrCertificateVersion() {
        return engEngrCertificateVersion;
    }

    public void setEngEngrCertificateVersion(BigDecimal engEngrCertificateVersion) {
        this.engEngrCertificateVersion = engEngrCertificateVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngEngrCertificatePK that = (EngEngrCertificatePK) o;
        return Objects.equal(engEngrCertificateId, that.engEngrCertificateId) && Objects.equal(engEngrCertificateVersion, that.engEngrCertificateVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(engEngrCertificateId, engEngrCertificateVersion);
    }
}
