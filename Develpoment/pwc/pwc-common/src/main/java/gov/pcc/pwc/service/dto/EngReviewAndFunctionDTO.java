package gov.pcc.pwc.service.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class EngReviewAndFunctionDTO implements Serializable {

    private EngReviewLogDTO engReviewLogDTO;

    private EngEngrCertificateDTO engEngrCertificateDTO;

    public EngReviewLogDTO getEngReviewLogDTO() {
        return engReviewLogDTO;
    }

    public void setEngReviewLogDTO(EngReviewLogDTO engReviewLogDTO) {
        this.engReviewLogDTO = engReviewLogDTO;
    }

    public EngEngrCertificateDTO getEngEngrCertificateDTO() {
        return engEngrCertificateDTO;
    }

    public void setEngEngrCertificateDTO(EngEngrCertificateDTO engEngrCertificateDTO) {
        this.engEngrCertificateDTO = engEngrCertificateDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngReviewAndFunctionDTO that = (EngReviewAndFunctionDTO) o;
        return Objects.equal(engReviewLogDTO, that.engReviewLogDTO) && Objects.equal(engEngrCertificateDTO, that.engEngrCertificateDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(engReviewLogDTO, engEngrCertificateDTO);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("engReviewLogDTO", engReviewLogDTO)
                .add("engEngrCertificateDTO", engEngrCertificateDTO)
                .toString();
    }
}
