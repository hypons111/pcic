package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProOrgSuggestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProOrgSuggestion.class);
        ProOrgSuggestion proOrgSuggestion1 = new ProOrgSuggestion();
        proOrgSuggestion1.setProOrgSuggestionId(1L);
        ProOrgSuggestion proOrgSuggestion2 = new ProOrgSuggestion();
        proOrgSuggestion2.setProOrgSuggestionId(proOrgSuggestion1.getProOrgSuggestionId());
        assertThat(proOrgSuggestion1).isEqualTo(proOrgSuggestion2);
        proOrgSuggestion2.setProOrgSuggestionId(2L);
        assertThat(proOrgSuggestion1).isNotEqualTo(proOrgSuggestion2);
        proOrgSuggestion1.setProOrgSuggestionId(null);
        assertThat(proOrgSuggestion1).isNotEqualTo(proOrgSuggestion2);
    }
}
