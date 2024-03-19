package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidPrjSur2PrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjSur2DTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjSur2DTO.class);
        BidPrjSur2DTO bidPrjSur2DTO1 = new BidPrjSur2DTO();
        bidPrjSur2DTO1.setId(new BidPrjSur2PrimaryKey("2124323as2","1asdf13","2123411"));
        BidPrjSur2DTO bidPrjSur2DTO2 = new BidPrjSur2DTO();
        assertThat(bidPrjSur2DTO1).isNotEqualTo(bidPrjSur2DTO2);
        bidPrjSur2DTO2.setId(bidPrjSur2DTO1.getId());
        assertThat(bidPrjSur2DTO1).isEqualTo(bidPrjSur2DTO2);
        bidPrjSur2DTO2.setId(new BidPrjSur2PrimaryKey("saqwe213","1232qwe13","233258"));
        assertThat(bidPrjSur2DTO1).isNotEqualTo(bidPrjSur2DTO2);
        bidPrjSur2DTO1.setId(null);
        assertThat(bidPrjSur2DTO1).isNotEqualTo(bidPrjSur2DTO2);
    }
}
