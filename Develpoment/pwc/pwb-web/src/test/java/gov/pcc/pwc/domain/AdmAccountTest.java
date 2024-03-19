package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmAccountTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmAccount.class);
        AdmAccount admAccount1 = new AdmAccount();
        admAccount1.setUserId("id1");
        AdmAccount admAccount2 = new AdmAccount();
        admAccount2.setUserId(admAccount1.getUserId());
        assertThat(admAccount1).isEqualTo(admAccount2);
        admAccount2.setUserId("id2");
        assertThat(admAccount1).isNotEqualTo(admAccount2);
        admAccount1.setUserId(null);
        assertThat(admAccount1).isNotEqualTo(admAccount2);
    }
}
