package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcDownloadRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcDownloadRoleDTO.class);
        PwcDownloadRoleDTO pwcDownloadRoleDTO1 = new PwcDownloadRoleDTO();
        pwcDownloadRoleDTO1.setProDownloadRoleId(1L);
        PwcDownloadRoleDTO pwcDownloadRoleDTO2 = new PwcDownloadRoleDTO();
        assertThat(pwcDownloadRoleDTO1).isNotEqualTo(pwcDownloadRoleDTO2);
        pwcDownloadRoleDTO2.setProDownloadRoleId(pwcDownloadRoleDTO1.getProDownloadRoleId());
        assertThat(pwcDownloadRoleDTO1).isEqualTo(pwcDownloadRoleDTO2);
        pwcDownloadRoleDTO2.setProDownloadRoleId(2L);
        assertThat(pwcDownloadRoleDTO1).isNotEqualTo(pwcDownloadRoleDTO2);
        pwcDownloadRoleDTO1.setProDownloadRoleId(null);
        assertThat(pwcDownloadRoleDTO1).isNotEqualTo(pwcDownloadRoleDTO2);
    }
}
