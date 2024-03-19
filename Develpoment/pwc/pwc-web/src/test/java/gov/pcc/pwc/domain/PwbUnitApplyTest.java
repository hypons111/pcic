package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbUnitApplyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbUnitApply.class);
        PwbUnitApply pwbUnitApply1 = new PwbUnitApply();
        pwbUnitApply1.setId(1L);
        PwbUnitApply pwbUnitApply2 = new PwbUnitApply();
        pwbUnitApply2.setId(pwbUnitApply1.getId());
        assertThat(pwbUnitApply1).isEqualTo(pwbUnitApply2);
        pwbUnitApply2.setId(2L);
        assertThat(pwbUnitApply1).isNotEqualTo(pwbUnitApply2);
        pwbUnitApply1.setId(null);
        assertThat(pwbUnitApply1).isNotEqualTo(pwbUnitApply2);
    }
}
