package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidPrjMilestonePrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjMilestoneDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjMilestoneDTO.class);
        BidPrjMilestoneDTO bidPrjMilestoneDTO1 = new BidPrjMilestoneDTO();
        bidPrjMilestoneDTO1.setId(new BidPrjMilestonePrimaryKey("213456","4546452","415464"));
        BidPrjMilestoneDTO bidPrjMilestoneDTO2 = new BidPrjMilestoneDTO();
        assertThat(bidPrjMilestoneDTO1).isNotEqualTo(bidPrjMilestoneDTO2);
        bidPrjMilestoneDTO2.setId(bidPrjMilestoneDTO1.getId());
        assertThat(bidPrjMilestoneDTO1).isEqualTo(bidPrjMilestoneDTO2);
        bidPrjMilestoneDTO2.setId(new BidPrjMilestonePrimaryKey("2121146","7945696","542543"));
        assertThat(bidPrjMilestoneDTO1).isNotEqualTo(bidPrjMilestoneDTO2);
        bidPrjMilestoneDTO1.setId(null);
        assertThat(bidPrjMilestoneDTO1).isNotEqualTo(bidPrjMilestoneDTO2);
    }
}
