package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidUnit10MapperTest {

    private BidUnit10Mapper bidUnit10Mapper;

    @BeforeEach
    public void setUp() {
        bidUnit10Mapper = new BidUnit10MapperImpl();
    }
}
