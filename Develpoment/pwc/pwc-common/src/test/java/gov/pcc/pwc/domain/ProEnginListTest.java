package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProEnginListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProEnginList.class);
        ProEnginList proEnginList1 = new ProEnginList();
        proEnginList1.setProEnginListId(1L);
        ProEnginList proEnginList2 = new ProEnginList();
        proEnginList2.setProEnginListId(proEnginList1.getProEnginListId());
        assertThat(proEnginList1).isEqualTo(proEnginList2);
        proEnginList2.setProEnginListId(2L);
        assertThat(proEnginList1).isNotEqualTo(proEnginList2);
        proEnginList1.setProEnginListId(null);
        assertThat(proEnginList1).isNotEqualTo(proEnginList2);
    }
}
