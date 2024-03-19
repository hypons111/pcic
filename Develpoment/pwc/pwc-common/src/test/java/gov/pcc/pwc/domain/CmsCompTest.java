package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CmsCompTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CmsComp.class);
        CmsComp cmsComp1 = new CmsComp();
        cmsComp1.setId("id1");
        CmsComp cmsComp2 = new CmsComp();
        cmsComp2.setId(cmsComp1.getId());
        assertThat(cmsComp1).isEqualTo(cmsComp2);
        cmsComp2.setId("id2");
        assertThat(cmsComp1).isNotEqualTo(cmsComp2);
        cmsComp1.setId(null);
        assertThat(cmsComp1).isNotEqualTo(cmsComp2);
    }
}
