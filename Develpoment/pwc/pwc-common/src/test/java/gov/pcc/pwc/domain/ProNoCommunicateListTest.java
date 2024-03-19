package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProNoCommunicateListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNoCommunicateList.class);
        ProNoCommunicateList proNoCommunicateList1 = new ProNoCommunicateList();
        proNoCommunicateList1.setProNoCommunicateListId(1L);
        ProNoCommunicateList proNoCommunicateList2 = new ProNoCommunicateList();
        proNoCommunicateList2.setProNoCommunicateListId(proNoCommunicateList1.getProNoCommunicateListId());
        assertThat(proNoCommunicateList1).isEqualTo(proNoCommunicateList2);
        proNoCommunicateList2.setProNoCommunicateListId(2L);
        assertThat(proNoCommunicateList1).isNotEqualTo(proNoCommunicateList2);
        proNoCommunicateList1.setProNoCommunicateListId(null);
        assertThat(proNoCommunicateList1).isNotEqualTo(proNoCommunicateList2);
    }
}
