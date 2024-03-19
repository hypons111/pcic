package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProNoCommunicateListMapperTest {

    private ProNoCommunicateListMapper proNoCommunicateListMapper;

    @BeforeEach
    public void setUp() {
        proNoCommunicateListMapper = new ProNoCommunicateListMapperImpl();
    }
}
