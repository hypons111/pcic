package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmCompanyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmCompanyDTO.class);
        AdmCompanyDTO admCompanyDTO1 = new AdmCompanyDTO();
        admCompanyDTO1.setCompIdno("id1");
        AdmCompanyDTO admCompanyDTO2 = new AdmCompanyDTO();
        assertThat(admCompanyDTO1).isNotEqualTo(admCompanyDTO2);
        admCompanyDTO2.setCompIdno(admCompanyDTO1.getCompIdno());
        assertThat(admCompanyDTO1).isEqualTo(admCompanyDTO2);
        admCompanyDTO2.setCompIdno("id2");
        assertThat(admCompanyDTO1).isNotEqualTo(admCompanyDTO2);
        admCompanyDTO1.setCompIdno(null);
        assertThat(admCompanyDTO1).isNotEqualTo(admCompanyDTO2);
    }
}
