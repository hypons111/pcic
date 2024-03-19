package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjPay2MapperTest {

    private BidPrjPay2Mapper bidPrjPay2Mapper;

    @BeforeEach
    public void setUp() {
        bidPrjPay2Mapper = new BidPrjPay2MapperImpl();
    }
}
