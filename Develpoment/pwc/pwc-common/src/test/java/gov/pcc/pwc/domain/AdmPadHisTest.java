package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmPadHisTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmPadHis.class);
        AdmPadHis admPadHis1 = new AdmPadHis();
        admPadHis1.setId(1L);
        AdmPadHis admPadHis2 = new AdmPadHis();
        admPadHis2.setId(admPadHis1.getId());
        assertThat(admPadHis1).isEqualTo(admPadHis2);
        admPadHis2.setId(2L);
        assertThat(admPadHis1).isNotEqualTo(admPadHis2);
        admPadHis1.setId(null);
        assertThat(admPadHis1).isNotEqualTo(admPadHis2);
    }
}
