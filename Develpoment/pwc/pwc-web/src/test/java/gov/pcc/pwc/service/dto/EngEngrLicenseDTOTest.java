package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EngEngrLicenseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EngEngrLicenseDTO.class);
        EngEngrLicenseDTO engEngrLicenseDTO1 = new EngEngrLicenseDTO();
        engEngrLicenseDTO1.setEngEngrLicenseId("id1");
        EngEngrLicenseDTO engEngrLicenseDTO2 = new EngEngrLicenseDTO();
        assertThat(engEngrLicenseDTO1).isNotEqualTo(engEngrLicenseDTO2);
        engEngrLicenseDTO2.setEngEngrLicenseId(engEngrLicenseDTO1.getEngEngrLicenseId());
        assertThat(engEngrLicenseDTO1).isEqualTo(engEngrLicenseDTO2);
        engEngrLicenseDTO2.setEngEngrLicenseId("id2");
        assertThat(engEngrLicenseDTO1).isNotEqualTo(engEngrLicenseDTO2);
        engEngrLicenseDTO1.setEngEngrLicenseId(null);
        assertThat(engEngrLicenseDTO1).isNotEqualTo(engEngrLicenseDTO2);
    }
}
