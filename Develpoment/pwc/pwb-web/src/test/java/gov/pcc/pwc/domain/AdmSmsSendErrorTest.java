package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.AdmSmsSendError;
import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmSmsSendErrorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmSmsSendError.class);
        AdmSmsSendError admSmsSendError1 = new AdmSmsSendError();
        admSmsSendError1.setId(1L);
        AdmSmsSendError admSmsSendError2 = new AdmSmsSendError();
        admSmsSendError2.setId(admSmsSendError1.getId());
        assertThat(admSmsSendError1).isEqualTo(admSmsSendError2);
        admSmsSendError2.setId(2L);
        assertThat(admSmsSendError1).isNotEqualTo(admSmsSendError2);
        admSmsSendError1.setId(null);
        assertThat(admSmsSendError1).isNotEqualTo(admSmsSendError2);
    }
}
