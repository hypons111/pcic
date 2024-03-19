package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EngEngrCertificateDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EngEngrCertificateDTO.class);
        EngEngrCertificateDTO engEngrCertificateDTO1 = new EngEngrCertificateDTO();
        engEngrCertificateDTO1.setEngEngrCertificateId("id1");
        EngEngrCertificateDTO engEngrCertificateDTO2 = new EngEngrCertificateDTO();
        assertThat(engEngrCertificateDTO1).isNotEqualTo(engEngrCertificateDTO2);
        engEngrCertificateDTO2.setEngEngrCertificateId(engEngrCertificateDTO1.getEngEngrCertificateId());
        assertThat(engEngrCertificateDTO1).isEqualTo(engEngrCertificateDTO2);
        engEngrCertificateDTO2.setEngEngrCertificateId("id2");
        assertThat(engEngrCertificateDTO1).isNotEqualTo(engEngrCertificateDTO2);
        engEngrCertificateDTO1.setEngEngrCertificateId(null);
        assertThat(engEngrCertificateDTO1).isNotEqualTo(engEngrCertificateDTO2);
    }
}
