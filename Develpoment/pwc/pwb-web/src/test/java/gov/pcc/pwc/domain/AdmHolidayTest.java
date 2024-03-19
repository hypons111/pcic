package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmHolidayTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmHoliday.class);
        AdmHoliday admHoliday1 = new AdmHoliday();
        admHoliday1.setId(1L);
        AdmHoliday admHoliday2 = new AdmHoliday();
        admHoliday2.setId(admHoliday1.getId());
        assertThat(admHoliday1).isEqualTo(admHoliday2);
        admHoliday2.setId(2L);
        assertThat(admHoliday1).isNotEqualTo(admHoliday2);
        admHoliday1.setId(null);
        assertThat(admHoliday1).isNotEqualTo(admHoliday2);
    }
}
