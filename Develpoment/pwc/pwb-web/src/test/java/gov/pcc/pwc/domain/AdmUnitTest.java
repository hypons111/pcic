package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmUnitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmUnit.class);
        AdmUnit admUnit1 = new AdmUnit();
        admUnit1.setUnitId("id1");
        AdmUnit admUnit2 = new AdmUnit();
        admUnit2.setUnitId(admUnit1.getUnitId());
        assertThat(admUnit1).isEqualTo(admUnit2);
        admUnit2.setUnitId("id2");
        assertThat(admUnit1).isNotEqualTo(admUnit2);
        admUnit1.setUnitId(null);
        assertThat(admUnit1).isNotEqualTo(admUnit2);
    }
}
