package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjPay2Test {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjPay2.class);
        BidPrjPay2 bidPrjPay21 = new BidPrjPay2();
        bidPrjPay21.setId(new BidPrjPay2PrimaryKey("123124","4153214","111","03"));
        BidPrjPay2 bidPrjPay22 = new BidPrjPay2();
        bidPrjPay22.setId(bidPrjPay21.getId());
        assertThat(bidPrjPay21).isEqualTo(bidPrjPay22);
        bidPrjPay22.setId(new BidPrjPay2PrimaryKey("3253","57456","110","05"));
        assertThat(bidPrjPay21).isNotEqualTo(bidPrjPay22);
        bidPrjPay21.setId(null);
        assertThat(bidPrjPay21).isNotEqualTo(bidPrjPay22);
    }
}
