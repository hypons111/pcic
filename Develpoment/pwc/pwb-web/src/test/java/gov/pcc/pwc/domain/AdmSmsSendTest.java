package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.AdmSmsSend;
import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmSmsSendTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmSmsSend.class);
        AdmSmsSend admSmsSend1 = new AdmSmsSend();
        admSmsSend1.setId(1L);
        AdmSmsSend admSmsSend2 = new AdmSmsSend();
        admSmsSend2.setId(admSmsSend1.getId());
        assertThat(admSmsSend1).isEqualTo(admSmsSend2);
        admSmsSend2.setId(2L);
        assertThat(admSmsSend1).isNotEqualTo(admSmsSend2);
        admSmsSend1.setId(null);
        assertThat(admSmsSend1).isNotEqualTo(admSmsSend2);
    }
}
