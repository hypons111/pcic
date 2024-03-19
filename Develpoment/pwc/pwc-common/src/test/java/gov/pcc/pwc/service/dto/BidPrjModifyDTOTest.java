package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidPrjModifyPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjModifyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjModifyDTO.class);
        BidPrjModifyDTO bidPrjModifyDTO1 = new BidPrjModifyDTO();
        bidPrjModifyDTO1.setId(new BidPrjModifyPrimaryKey("12345678","12345678", LocalDate.now()));
        BidPrjModifyDTO bidPrjModifyDTO2 = new BidPrjModifyDTO();
        assertThat(bidPrjModifyDTO1).isNotEqualTo(bidPrjModifyDTO2);
        bidPrjModifyDTO2.setId(bidPrjModifyDTO1.getId());
        assertThat(bidPrjModifyDTO1).isEqualTo(bidPrjModifyDTO2);
        bidPrjModifyDTO2.setId(new BidPrjModifyPrimaryKey("22345678","22345678", LocalDate.now()));
        assertThat(bidPrjModifyDTO1).isNotEqualTo(bidPrjModifyDTO2);
        bidPrjModifyDTO1.setId(null);
        assertThat(bidPrjModifyDTO1).isNotEqualTo(bidPrjModifyDTO2);
    }
}
