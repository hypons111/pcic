package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcInformationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcInformation.class);
        PwcInformation pwcInformation1 = new PwcInformation();
        pwcInformation1.setId(1L);
        PwcInformation pwcInformation2 = new PwcInformation();
        pwcInformation2.setId(pwcInformation1.getId());
        assertThat(pwcInformation1).isEqualTo(pwcInformation2);
        pwcInformation2.setId(2L);
        assertThat(pwcInformation1).isNotEqualTo(pwcInformation2);
        pwcInformation1.setId(null);
        assertThat(pwcInformation1).isNotEqualTo(pwcInformation2);
    }
}
