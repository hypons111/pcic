package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmCompanyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmCompany.class);
        AdmCompany admCompany1 = new AdmCompany();
        admCompany1.setCompIdno("id1");
        AdmCompany admCompany2 = new AdmCompany();
        admCompany2.setCompIdno(admCompany1.getCompIdno());
        assertThat(admCompany1).isEqualTo(admCompany2);
        admCompany2.setCompIdno("id2");
        assertThat(admCompany1).isNotEqualTo(admCompany2);
        admCompany1.setCompIdno(null);
        assertThat(admCompany1).isNotEqualTo(admCompany2);
    }
}
