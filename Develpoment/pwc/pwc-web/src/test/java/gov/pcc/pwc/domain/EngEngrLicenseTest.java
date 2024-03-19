package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EngEngrLicenseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EngEngrLicense.class);
        EngEngrLicense engEngrLicense1 = new EngEngrLicense();
        engEngrLicense1.setEngEngrLicenseId("id1");
        EngEngrLicense engEngrLicense2 = new EngEngrLicense();
        engEngrLicense2.setEngEngrLicenseId(engEngrLicense1.getEngEngrLicenseId());
        assertThat(engEngrLicense1).isEqualTo(engEngrLicense2);
        engEngrLicense2.setEngEngrLicenseId("id2");
        assertThat(engEngrLicense1).isNotEqualTo(engEngrLicense2);
        engEngrLicense1.setEngEngrLicenseId(null);
        assertThat(engEngrLicense1).isNotEqualTo(engEngrLicense2);
    }
}
