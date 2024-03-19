package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmFunctionUsedRecordTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmFunctionUsedRecord.class);
        AdmFunctionUsedRecord admFunctionUsedRecord1 = new AdmFunctionUsedRecord();
        admFunctionUsedRecord1.setId(1L);
        AdmFunctionUsedRecord admFunctionUsedRecord2 = new AdmFunctionUsedRecord();
        admFunctionUsedRecord2.setId(admFunctionUsedRecord1.getId());
        assertThat(admFunctionUsedRecord1).isEqualTo(admFunctionUsedRecord2);
        admFunctionUsedRecord2.setId(2L);
        assertThat(admFunctionUsedRecord1).isNotEqualTo(admFunctionUsedRecord2);
        admFunctionUsedRecord1.setId(null);
        assertThat(admFunctionUsedRecord1).isNotEqualTo(admFunctionUsedRecord2);
    }
}
