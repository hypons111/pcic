package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CmsCompDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CmsCompDTO.class);
        CmsCompDTO cmsCompDTO1 = new CmsCompDTO();
        cmsCompDTO1.setId("id1");
        CmsCompDTO cmsCompDTO2 = new CmsCompDTO();
        assertThat(cmsCompDTO1).isNotEqualTo(cmsCompDTO2);
        cmsCompDTO2.setId(cmsCompDTO1.getId());
        assertThat(cmsCompDTO1).isEqualTo(cmsCompDTO2);
        cmsCompDTO2.setId("id2");
        assertThat(cmsCompDTO1).isNotEqualTo(cmsCompDTO2);
        cmsCompDTO1.setId(null);
        assertThat(cmsCompDTO1).isNotEqualTo(cmsCompDTO2);
    }
}
