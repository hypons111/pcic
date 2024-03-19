package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TecContentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TecContent.class);
        TecContent tecContent1 = new TecContent();
        tecContent1.setContentId(1L);
        TecContent tecContent2 = new TecContent();
        tecContent2.setContentId(tecContent1.getContentId());
        assertThat(tecContent1).isEqualTo(tecContent2);
        tecContent2.setContentId(2L);
        assertThat(tecContent1).isNotEqualTo(tecContent2);
        tecContent1.setContentId(null);
        assertThat(tecContent1).isNotEqualTo(tecContent2);
    }
}
