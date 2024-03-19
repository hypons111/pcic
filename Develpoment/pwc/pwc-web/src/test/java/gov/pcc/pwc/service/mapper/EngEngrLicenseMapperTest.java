package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EngEngrLicenseMapperTest {

    private EngEngrLicenseMapper engEngrLicenseMapper;

    @BeforeEach
    public void setUp() {
        engEngrLicenseMapper = new EngEngrLicenseMapperImpl();
    }
}
