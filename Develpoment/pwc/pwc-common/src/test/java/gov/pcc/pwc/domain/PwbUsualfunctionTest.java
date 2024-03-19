package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbUsualfunctionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbUsualfunction.class);
        PwbUsualfunction pwbUsualfunction1 = new PwbUsualfunction();
        pwbUsualfunction1.setId(1L);
        PwbUsualfunction pwbUsualfunction2 = new PwbUsualfunction();
        pwbUsualfunction2.setId(pwbUsualfunction1.getId());
        assertThat(pwbUsualfunction1).isEqualTo(pwbUsualfunction2);
        pwbUsualfunction2.setId(2L);
        assertThat(pwbUsualfunction1).isNotEqualTo(pwbUsualfunction2);
        pwbUsualfunction1.setId(null);
        assertThat(pwbUsualfunction1).isNotEqualTo(pwbUsualfunction2);
    }
}
