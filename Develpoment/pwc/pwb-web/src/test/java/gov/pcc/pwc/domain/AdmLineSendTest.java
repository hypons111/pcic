package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.AdmLineSend;
import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmLineSendTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmLineSend.class);
        AdmLineSend admLineSend1 = new AdmLineSend();
        admLineSend1.setId(1L);
        AdmLineSend admLineSend2 = new AdmLineSend();
        admLineSend2.setId(admLineSend1.getId());
        assertThat(admLineSend1).isEqualTo(admLineSend2);
        admLineSend2.setId(2L);
        assertThat(admLineSend1).isNotEqualTo(admLineSend2);
        admLineSend1.setId(null);
        assertThat(admLineSend1).isNotEqualTo(admLineSend2);
    }
}
