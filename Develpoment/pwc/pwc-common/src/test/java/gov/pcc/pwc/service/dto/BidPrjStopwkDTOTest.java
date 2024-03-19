package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidPrjStopwkPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjStopwkDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjStopwkDTO.class);
        BidPrjStopwkDTO bidPrjStopwkDTO1 = new BidPrjStopwkDTO();
        bidPrjStopwkDTO1.setId(new BidPrjStopwkPrimaryKey("22345678","22345678", LocalDate.now()));
        BidPrjStopwkDTO bidPrjStopwkDTO2 = new BidPrjStopwkDTO();
        assertThat(bidPrjStopwkDTO1).isNotEqualTo(bidPrjStopwkDTO2);
        bidPrjStopwkDTO2.setId(bidPrjStopwkDTO1.getId());
        assertThat(bidPrjStopwkDTO1).isEqualTo(bidPrjStopwkDTO2);
        bidPrjStopwkDTO2.setId(new BidPrjStopwkPrimaryKey("22345678","22345678", LocalDate.now()));
        assertThat(bidPrjStopwkDTO1).isNotEqualTo(bidPrjStopwkDTO2);
        bidPrjStopwkDTO1.setId(null);
        assertThat(bidPrjStopwkDTO1).isNotEqualTo(bidPrjStopwkDTO2);
    }
}
