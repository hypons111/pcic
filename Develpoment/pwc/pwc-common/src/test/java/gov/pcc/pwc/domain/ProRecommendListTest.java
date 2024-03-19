package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProRecommendListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRecommendList.class);
        ProRecommendList proRecommendList1 = new ProRecommendList();
        proRecommendList1.setProRecommendListId(1L);
        ProRecommendList proRecommendList2 = new ProRecommendList();
        proRecommendList2.setProRecommendListId(proRecommendList1.getProRecommendListId());
        assertThat(proRecommendList1).isEqualTo(proRecommendList2);
        proRecommendList2.setProRecommendListId(2L);
        assertThat(proRecommendList1).isNotEqualTo(proRecommendList2);
        proRecommendList1.setProRecommendListId(null);
        assertThat(proRecommendList1).isNotEqualTo(proRecommendList2);
    }
}
