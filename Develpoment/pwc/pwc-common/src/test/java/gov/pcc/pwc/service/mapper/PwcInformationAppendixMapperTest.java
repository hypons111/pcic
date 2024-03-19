package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwcInformationAppendixMapperTest {

    private PwcInformationAppendixMapper pwcInformationAppendixMapper;

    @BeforeEach
    public void setUp() {
        pwcInformationAppendixMapper = new PwcInformationAppendixMapperImpl();
    }
}
