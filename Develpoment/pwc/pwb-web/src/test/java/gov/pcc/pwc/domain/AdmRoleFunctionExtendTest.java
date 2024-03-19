package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmRoleFunctionExtendTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmRoleFunctionExtend.class);
        AdmRoleFunctionExtend admRoleFunctionExtend1 = new AdmRoleFunctionExtend();
        admRoleFunctionExtend1.setId(1L);
        AdmRoleFunctionExtend admRoleFunctionExtend2 = new AdmRoleFunctionExtend();
        admRoleFunctionExtend2.setId(admRoleFunctionExtend1.getId());
        assertThat(admRoleFunctionExtend1).isEqualTo(admRoleFunctionExtend2);
        admRoleFunctionExtend2.setId(2L);
        assertThat(admRoleFunctionExtend1).isNotEqualTo(admRoleFunctionExtend2);
        admRoleFunctionExtend1.setId(null);
        assertThat(admRoleFunctionExtend1).isNotEqualTo(admRoleFunctionExtend2);
    }
}
