package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EngEngrCertificateTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EngEngrCertificate.class);
        EngEngrCertificate engEngrCertificate1 = new EngEngrCertificate();
        engEngrCertificate1.setEngEngrCertificateId("id1");
        EngEngrCertificate engEngrCertificate2 = new EngEngrCertificate();
        engEngrCertificate2.setEngEngrCertificateId(engEngrCertificate1.getEngEngrCertificateId());
        assertThat(engEngrCertificate1).isEqualTo(engEngrCertificate2);
        engEngrCertificate2.setEngEngrCertificateId("id2");
        assertThat(engEngrCertificate1).isNotEqualTo(engEngrCertificate2);
        engEngrCertificate1.setEngEngrCertificateId(null);
        assertThat(engEngrCertificate1).isNotEqualTo(engEngrCertificate2);
    }
}
