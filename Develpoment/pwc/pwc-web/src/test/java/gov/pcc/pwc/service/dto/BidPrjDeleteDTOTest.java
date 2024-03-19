package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjDeleteDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjDeleteDTO.class);
        BidPrjDeleteDTO bidPrjDeleteDTO1 = new BidPrjDeleteDTO();
        bidPrjDeleteDTO1.setId(new BidProjectPrimaryKey("1234","56789"));
        BidPrjDeleteDTO bidPrjDeleteDTO2 = new BidPrjDeleteDTO();
        assertThat(bidPrjDeleteDTO1).isNotEqualTo(bidPrjDeleteDTO2);
        bidPrjDeleteDTO2.setId(bidPrjDeleteDTO1.getId());
        assertThat(bidPrjDeleteDTO1).isEqualTo(bidPrjDeleteDTO2);
        bidPrjDeleteDTO2.setId(new BidProjectPrimaryKey("2234","56789"));
        assertThat(bidPrjDeleteDTO1).isNotEqualTo(bidPrjDeleteDTO2);
        bidPrjDeleteDTO1.setId(null);
        assertThat(bidPrjDeleteDTO1).isNotEqualTo(bidPrjDeleteDTO2);
    }
}
