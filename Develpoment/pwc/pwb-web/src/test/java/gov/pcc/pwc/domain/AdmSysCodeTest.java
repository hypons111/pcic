package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmSysCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmSysCode.class);
        AdmSysCode admSysCode1 = new AdmSysCode();
        admSysCode1.setModuleType("aaa");
        admSysCode1.setDataType("bbb");
        admSysCode1.setDataKey("ccc");
        AdmSysCode admSysCode2 = new AdmSysCode();
        admSysCode2.setModuleType(admSysCode1.getModuleType());
        admSysCode2.setDataType(admSysCode1.getDataType());
        admSysCode2.setDataKey(admSysCode1.getDataKey());
        assertThat(admSysCode1).isEqualTo(admSysCode2);

        admSysCode2.setModuleType("ddd");
        assertThat(admSysCode1).isNotEqualTo(admSysCode2);

        admSysCode1.setModuleType(null);
        assertThat(admSysCode1).isNotEqualTo(admSysCode2);
    }
}
