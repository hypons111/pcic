package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcInformationRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcInformationRoleDTO.class);
        PwcInformationRoleDTO pwcInformationRoleDTO1 = new PwcInformationRoleDTO();
        pwcInformationRoleDTO1.setProInformationRoleId(1L);
        PwcInformationRoleDTO pwcInformationRoleDTO2 = new PwcInformationRoleDTO();
        assertThat(pwcInformationRoleDTO1).isNotEqualTo(pwcInformationRoleDTO2);
        pwcInformationRoleDTO2.setProInformationRoleId(pwcInformationRoleDTO1.getProInformationRoleId());
        assertThat(pwcInformationRoleDTO1).isEqualTo(pwcInformationRoleDTO2);
        pwcInformationRoleDTO2.setProInformationRoleId(2L);
        assertThat(pwcInformationRoleDTO1).isNotEqualTo(pwcInformationRoleDTO2);
        pwcInformationRoleDTO1.setProInformationRoleId(null);
        assertThat(pwcInformationRoleDTO1).isNotEqualTo(pwcInformationRoleDTO2);
    }
}
