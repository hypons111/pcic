package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwcInformationModuleMapperTest {

    private PwcInformationModuleMapper pwcInformationModuleMapper;

    @BeforeEach
    public void setUp() {
        pwcInformationModuleMapper = new PwcInformationModuleMapperImpl();
    }
}
