package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;
import gov.pcc.pwc.domain.BidPrjFrwkrPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjFrwkrDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjFrwkrDTO.class);
        BidPrjFrwkrDTO bidPrjFrwkrDTO1 = new BidPrjFrwkrDTO();
        bidPrjFrwkrDTO1.setId(new BidPrjFrwkrPrimaryKey("12345678","12345678", LocalDate.now()));
        BidPrjFrwkrDTO bidPrjFrwkrDTO2 = new BidPrjFrwkrDTO();
        assertThat(bidPrjFrwkrDTO1).isNotEqualTo(bidPrjFrwkrDTO2);
        bidPrjFrwkrDTO2.setId(bidPrjFrwkrDTO1.getId());
        assertThat(bidPrjFrwkrDTO1).isEqualTo(bidPrjFrwkrDTO2);
        bidPrjFrwkrDTO2.setId(new BidPrjFrwkrPrimaryKey("12345678","12345678", LocalDate.now()));
        assertThat(bidPrjFrwkrDTO1).isNotEqualTo(bidPrjFrwkrDTO2);
        bidPrjFrwkrDTO1.setId(null);
        assertThat(bidPrjFrwkrDTO1).isNotEqualTo(bidPrjFrwkrDTO2);
    }
}
