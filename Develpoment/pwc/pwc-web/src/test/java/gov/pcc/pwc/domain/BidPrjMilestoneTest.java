package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjMilestoneTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjMilestone.class);
        BidPrjMilestone bidPrjMilestone1 = new BidPrjMilestone();
        bidPrjMilestone1.setId(new BidPrjMilestonePrimaryKey("2636347","877687654","87678567"));
        BidPrjMilestone bidPrjMilestone2 = new BidPrjMilestone();
        bidPrjMilestone2.setId(bidPrjMilestone1.getId());
        assertThat(bidPrjMilestone1).isEqualTo(bidPrjMilestone2);
        bidPrjMilestone2.setId(new BidPrjMilestonePrimaryKey("4564545","7687654","7868767"));
        assertThat(bidPrjMilestone1).isNotEqualTo(bidPrjMilestone2);
        bidPrjMilestone1.setId(null);
        assertThat(bidPrjMilestone1).isNotEqualTo(bidPrjMilestone2);
    }
}
