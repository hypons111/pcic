package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjSur2Test {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjSur2.class);
        BidPrjSur2 bidPrjSur21 = new BidPrjSur2();
        bidPrjSur21.setId(new BidPrjSur2PrimaryKey("21312","123213","21321"));
        BidPrjSur2 bidPrjSur22 = new BidPrjSur2();
        bidPrjSur22.setId(bidPrjSur21.getId());
        assertThat(bidPrjSur21).isEqualTo(bidPrjSur22);
        bidPrjSur22.setId(new BidPrjSur2PrimaryKey("21weqw","12321wqe","sadqwe21"));
        assertThat(bidPrjSur21).isNotEqualTo(bidPrjSur22);
        bidPrjSur21.setId(null);
        assertThat(bidPrjSur21).isNotEqualTo(bidPrjSur22);
    }
}
