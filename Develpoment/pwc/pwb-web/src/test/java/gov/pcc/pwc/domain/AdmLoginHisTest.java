package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmLoginHisTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmLoginHis.class);
        AdmLoginHis admLoginHis1 = new AdmLoginHis();
        admLoginHis1.setId(1L);
        AdmLoginHis admLoginHis2 = new AdmLoginHis();
        admLoginHis2.setId(admLoginHis1.getId());
        assertThat(admLoginHis1).isEqualTo(admLoginHis2);
        admLoginHis2.setId(2L);
        assertThat(admLoginHis1).isNotEqualTo(admLoginHis2);
        admLoginHis1.setId(null);
        assertThat(admLoginHis1).isNotEqualTo(admLoginHis2);
    }
}
