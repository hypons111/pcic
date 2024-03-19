package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProInnovativeProductTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProInnovativeProduct.class);
        ProInnovativeProduct proInnovativeProduct1 = new ProInnovativeProduct();
        proInnovativeProduct1.setProInnovativeProductId("id1");
        ProInnovativeProduct proInnovativeProduct2 = new ProInnovativeProduct();
        proInnovativeProduct2.setProInnovativeProductId(proInnovativeProduct1.getProInnovativeProductId());
        assertThat(proInnovativeProduct1).isEqualTo(proInnovativeProduct2);
        proInnovativeProduct2.setProInnovativeProductId("id2");
        assertThat(proInnovativeProduct1).isNotEqualTo(proInnovativeProduct2);
        proInnovativeProduct1.setProInnovativeProductId(null);
        assertThat(proInnovativeProduct1).isNotEqualTo(proInnovativeProduct2);
    }
}
