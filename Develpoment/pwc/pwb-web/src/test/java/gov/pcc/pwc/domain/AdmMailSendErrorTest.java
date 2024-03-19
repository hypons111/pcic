package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.AdmMailSendError;
import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmMailSendErrorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmMailSendError.class);
        AdmMailSendError admMailSendError1 = new AdmMailSendError();
        admMailSendError1.setId(1L);
        AdmMailSendError admMailSendError2 = new AdmMailSendError();
        admMailSendError2.setId(admMailSendError1.getId());
        assertThat(admMailSendError1).isEqualTo(admMailSendError2);
        admMailSendError2.setId(2L);
        assertThat(admMailSendError1).isNotEqualTo(admMailSendError2);
        admMailSendError1.setId(null);
        assertThat(admMailSendError1).isNotEqualTo(admMailSendError2);
    }
}
