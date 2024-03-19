package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmContactTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmContact.class);
        AdmContact admContact1 = new AdmContact();
        admContact1.setUserId("id1");
        AdmContact admContact2 = new AdmContact();
        admContact2.setUserId(admContact1.getUserId());
        assertThat(admContact1).isEqualTo(admContact2);
        admContact2.setUserId("id2");
        assertThat(admContact1).isNotEqualTo(admContact2);
        admContact1.setUserId(null);
        assertThat(admContact1).isNotEqualTo(admContact2);
    }
}
