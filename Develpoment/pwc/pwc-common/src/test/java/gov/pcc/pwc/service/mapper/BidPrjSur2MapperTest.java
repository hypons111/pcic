package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjSur2MapperTest {

    private BidPrjSur2Mapper bidPrjSur2Mapper;

    @BeforeEach
    public void setUp() {
        bidPrjSur2Mapper = new BidPrjSur2MapperImpl();
    }
}
