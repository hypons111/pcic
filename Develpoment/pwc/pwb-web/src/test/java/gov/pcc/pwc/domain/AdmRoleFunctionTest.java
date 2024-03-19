package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmRoleFunctionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmRoleFunction.class);
        AdmRoleFunction admRoleFunction1 = new AdmRoleFunction();
        admRoleFunction1.setId(1L);
        AdmRoleFunction admRoleFunction2 = new AdmRoleFunction();
        admRoleFunction2.setId(admRoleFunction1.getId());
        assertThat(admRoleFunction1).isEqualTo(admRoleFunction2);
        admRoleFunction2.setId(2L);
        assertThat(admRoleFunction1).isNotEqualTo(admRoleFunction2);
        admRoleFunction1.setId(null);
        assertThat(admRoleFunction1).isNotEqualTo(admRoleFunction2);
    }
}
