package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TecPageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TecPage.class);
        TecPage tecPage1 = new TecPage();
        tecPage1.setPageId(1L);
        TecPage tecPage2 = new TecPage();
        tecPage2.setPageId(tecPage1.getPageId());
        assertThat(tecPage1).isEqualTo(tecPage2);
        tecPage2.setPageId(2L);
        assertThat(tecPage1).isNotEqualTo(tecPage2);
        tecPage1.setPageId(null);
        assertThat(tecPage1).isNotEqualTo(tecPage2);
    }
}
