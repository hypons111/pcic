package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjEnviDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjEnviDTO.class);
        BidPrjEnviDTO bidPrjEnviDTO1 = new BidPrjEnviDTO();
        bidPrjEnviDTO1.setWkut("id1");
        BidPrjEnviDTO bidPrjEnviDTO2 = new BidPrjEnviDTO();
        assertThat(bidPrjEnviDTO1).isNotEqualTo(bidPrjEnviDTO2);
        bidPrjEnviDTO2.setWkut(bidPrjEnviDTO1.getWkut());
        assertThat(bidPrjEnviDTO1).isEqualTo(bidPrjEnviDTO2);
        bidPrjEnviDTO2.setWkut("id2");
        assertThat(bidPrjEnviDTO1).isNotEqualTo(bidPrjEnviDTO2);
        bidPrjEnviDTO1.setWkut(null);
        assertThat(bidPrjEnviDTO1).isNotEqualTo(bidPrjEnviDTO2);
    }
}
