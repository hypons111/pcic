package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.AdmLineSendError;
import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmLineSendErrorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmLineSendError.class);
        AdmLineSendError admLineSendError1 = new AdmLineSendError();
        admLineSendError1.setId(1L);
        AdmLineSendError admLineSendError2 = new AdmLineSendError();
        admLineSendError2.setId(admLineSendError1.getId());
        assertThat(admLineSendError1).isEqualTo(admLineSendError2);
        admLineSendError2.setId(2L);
        assertThat(admLineSendError1).isNotEqualTo(admLineSendError2);
        admLineSendError1.setId(null);
        assertThat(admLineSendError1).isNotEqualTo(admLineSendError2);
    }
}
