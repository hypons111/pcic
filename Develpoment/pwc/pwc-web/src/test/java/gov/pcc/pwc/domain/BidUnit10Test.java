package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidUnit10Test {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidUnit10.class);
        BidUnit10 bidUnit101 = new BidUnit10();
        bidUnit101.setCode("id1");
        BidUnit10 bidUnit102 = new BidUnit10();
        bidUnit102.setCode(bidUnit101.getCode());
        assertThat(bidUnit101).isEqualTo(bidUnit102);
        bidUnit102.setCode("id2");
        assertThat(bidUnit101).isNotEqualTo(bidUnit102);
        bidUnit101.setCode(null);
        assertThat(bidUnit101).isNotEqualTo(bidUnit102);
    }
}
