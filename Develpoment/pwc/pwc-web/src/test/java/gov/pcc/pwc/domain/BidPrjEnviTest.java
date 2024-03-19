package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjEnviTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjEnvi.class);
        BidPrjEnvi bidPrjEnvi1 = new BidPrjEnvi();
        bidPrjEnvi1.setWkut("id1");
        BidPrjEnvi bidPrjEnvi2 = new BidPrjEnvi();
        bidPrjEnvi2.setWkut(bidPrjEnvi1.getWkut());
        assertThat(bidPrjEnvi1).isEqualTo(bidPrjEnvi2);
        bidPrjEnvi2.setWkut("id2");
        assertThat(bidPrjEnvi1).isNotEqualTo(bidPrjEnvi2);
        bidPrjEnvi1.setWkut(null);
        assertThat(bidPrjEnvi1).isNotEqualTo(bidPrjEnvi2);
    }
}
