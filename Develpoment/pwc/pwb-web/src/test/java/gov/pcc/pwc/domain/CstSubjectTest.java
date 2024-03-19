package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CstSubjectTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CstSubject.class);
        CstSubject cstSubject1 = new CstSubject();
        cstSubject1.setId(1L);
        CstSubject cstSubject2 = new CstSubject();
        cstSubject2.setId(cstSubject1.getId());
        assertThat(cstSubject1).isEqualTo(cstSubject2);
        cstSubject2.setId(2L);
        assertThat(cstSubject1).isNotEqualTo(cstSubject2);
        cstSubject1.setId(null);
        assertThat(cstSubject1).isNotEqualTo(cstSubject2);
    }
}
