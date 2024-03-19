package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProOrgSuggestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProOrgSuggestionDTO.class);
        ProOrgSuggestionDTO proOrgSuggestionDTO1 = new ProOrgSuggestionDTO();
        proOrgSuggestionDTO1.setProOrgSuggestionId(1L);
        ProOrgSuggestionDTO proOrgSuggestionDTO2 = new ProOrgSuggestionDTO();
        assertThat(proOrgSuggestionDTO1).isNotEqualTo(proOrgSuggestionDTO2);
        proOrgSuggestionDTO2.setProOrgSuggestionId(proOrgSuggestionDTO1.getProOrgSuggestionId());
        assertThat(proOrgSuggestionDTO1).isEqualTo(proOrgSuggestionDTO2);
        proOrgSuggestionDTO2.setProOrgSuggestionId(2L);
        assertThat(proOrgSuggestionDTO1).isNotEqualTo(proOrgSuggestionDTO2);
        proOrgSuggestionDTO1.setProOrgSuggestionId(null);
        assertThat(proOrgSuggestionDTO1).isNotEqualTo(proOrgSuggestionDTO2);
    }
}
