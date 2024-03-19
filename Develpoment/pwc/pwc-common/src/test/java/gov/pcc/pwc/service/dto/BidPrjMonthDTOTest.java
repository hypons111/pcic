package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjMonthDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjMonthDTO.class);
        BidPrjMonthDTO bidPrjMonthDTO1 = new BidPrjMonthDTO();
        bidPrjMonthDTO1.setId(new BidPrjMonthPrimaryKey("12345678","12345678","111","1"));
        BidPrjMonthDTO bidPrjMonthDTO2 = new BidPrjMonthDTO();
        assertThat(bidPrjMonthDTO1).isNotEqualTo(bidPrjMonthDTO2);
        bidPrjMonthDTO2.setId(bidPrjMonthDTO1.getId());
        assertThat(bidPrjMonthDTO1).isEqualTo(bidPrjMonthDTO2);
        bidPrjMonthDTO2.setId(new BidPrjMonthPrimaryKey("22345678","22345678","112","2"));
        assertThat(bidPrjMonthDTO1).isNotEqualTo(bidPrjMonthDTO2);
        bidPrjMonthDTO1.setId(null);
        assertThat(bidPrjMonthDTO1).isNotEqualTo(bidPrjMonthDTO2);
    }
}
