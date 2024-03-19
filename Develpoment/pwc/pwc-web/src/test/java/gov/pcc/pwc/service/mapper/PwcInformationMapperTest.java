package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwcInformationMapperTest {

    private PwcInformationMapper pwcInformationMapper;

    @BeforeEach
    public void setUp() {
        pwcInformationMapper = new PwcInformationMapperImpl();
    }
}
