package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidPrjQcPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjQcDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjQcDTO.class);
        BidPrjQcDTO bidPrjQcDTO1 = new BidPrjQcDTO();
        bidPrjQcDTO1.setPrjQcKey(new BidPrjQcPrimaryKey("123123","1232565431","1232541", LocalDate.parse("2022-02-21")));
        BidPrjQcDTO bidPrjQcDTO2 = new BidPrjQcDTO();
        assertThat(bidPrjQcDTO1).isNotEqualTo(bidPrjQcDTO2);
        bidPrjQcDTO2.setPrjQcKey(bidPrjQcDTO1.getPrjQcKey());
        assertThat(bidPrjQcDTO1).isEqualTo(bidPrjQcDTO2);
        bidPrjQcDTO2.setPrjQcKey(new BidPrjQcPrimaryKey("123123","1232565431","1232541", LocalDate.parse("2022-03-21")));
        assertThat(bidPrjQcDTO1).isNotEqualTo(bidPrjQcDTO2);
        bidPrjQcDTO1.setPrjQcKey(null);
        assertThat(bidPrjQcDTO1).isNotEqualTo(bidPrjQcDTO2);
    }
}
