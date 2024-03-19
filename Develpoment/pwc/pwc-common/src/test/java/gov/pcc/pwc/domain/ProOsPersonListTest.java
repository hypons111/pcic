package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProOsPersonListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProOsPersonList.class);
        ProOsPersonList proOsPersonList1 = new ProOsPersonList();
        proOsPersonList1.setProOsPersonListId(1L);
        ProOsPersonList proOsPersonList2 = new ProOsPersonList();
        proOsPersonList2.setProOsPersonListId(proOsPersonList1.getProOsPersonListId());
        assertThat(proOsPersonList1).isEqualTo(proOsPersonList2);
        proOsPersonList2.setProOsPersonListId(2L);
        assertThat(proOsPersonList1).isNotEqualTo(proOsPersonList2);
        proOsPersonList1.setProOsPersonListId(null);
        assertThat(proOsPersonList1).isNotEqualTo(proOsPersonList2);
    }
}
