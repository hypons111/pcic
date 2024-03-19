package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmSysParameterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmSysParameter.class);
        AdmSysParameter admSysParameter1 = new AdmSysParameter();
        admSysParameter1.setSysName("aaa");
        admSysParameter1.setSysType("aaa");
        AdmSysParameter admSysParameter2 = new AdmSysParameter();
        admSysParameter2.setSysName(admSysParameter1.getSysName());
        admSysParameter2.setSysType(admSysParameter1.getSysType());
        assertThat(admSysParameter1).isEqualTo(admSysParameter2);
        admSysParameter2.setSysName("bbb");
        assertThat(admSysParameter1).isNotEqualTo(admSysParameter2);
        admSysParameter1.setSysName(null);
        assertThat(admSysParameter1).isNotEqualTo(admSysParameter2);
    }
}
