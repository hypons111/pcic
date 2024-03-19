package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EngEngrCertificateMapperTest {

    private EngEngrCertificateMapper engEngrCertificateMapper;

    @BeforeEach
    public void setUp() {
        engEngrCertificateMapper = new EngEngrCertificateMapperImpl();
    }
}
