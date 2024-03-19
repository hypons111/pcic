package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmMailSendTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmMailSend.class);
        AdmMailSend admMailSend1 = new AdmMailSend();
        admMailSend1.setId(1L);
        AdmMailSend admMailSend2 = new AdmMailSend();
        admMailSend2.setId(admMailSend1.getId());
        assertThat(admMailSend1).isEqualTo(admMailSend2);
        admMailSend2.setId(2L);
        assertThat(admMailSend1).isNotEqualTo(admMailSend2);
        admMailSend1.setId(null);
        assertThat(admMailSend1).isNotEqualTo(admMailSend2);
    }
}
