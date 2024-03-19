package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmPostAddrTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmPostAddr.class);
        AdmPostAddr admPostAddr1 = new AdmPostAddr();
        admPostAddr1.setId(1L);
        AdmPostAddr admPostAddr2 = new AdmPostAddr();
        admPostAddr2.setId(admPostAddr1.getId());
        assertThat(admPostAddr1).isEqualTo(admPostAddr2);
        admPostAddr2.setId(2L);
        assertThat(admPostAddr1).isNotEqualTo(admPostAddr2);
        admPostAddr1.setId(null);
        assertThat(admPostAddr1).isNotEqualTo(admPostAddr2);
    }
}
