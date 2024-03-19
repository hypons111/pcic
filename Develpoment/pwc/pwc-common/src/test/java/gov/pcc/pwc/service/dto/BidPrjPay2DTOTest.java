package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidPrjPay2PrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjPay2DTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjPay2DTO.class);
        BidPrjPay2DTO bidPrjPay2DTO1 = new BidPrjPay2DTO();
        bidPrjPay2DTO1.setId(new BidPrjPay2PrimaryKey("r3251","fdg4351","102","04"));
        BidPrjPay2DTO bidPrjPay2DTO2 = new BidPrjPay2DTO();
        assertThat(bidPrjPay2DTO1).isNotEqualTo(bidPrjPay2DTO2);
        bidPrjPay2DTO2.setId(bidPrjPay2DTO1.getId());
        assertThat(bidPrjPay2DTO1).isEqualTo(bidPrjPay2DTO2);
        bidPrjPay2DTO2.setId(new BidPrjPay2PrimaryKey("afhgfy65","5745gfhd","104","07"));
        assertThat(bidPrjPay2DTO1).isNotEqualTo(bidPrjPay2DTO2);
        bidPrjPay2DTO1.setId(null);
        assertThat(bidPrjPay2DTO1).isNotEqualTo(bidPrjPay2DTO2);
    }
}
