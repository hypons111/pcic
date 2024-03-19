package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjModifyMapperTest {

    private BidPrjModifyMapper bidPrjModifyMapper;

    @BeforeEach
    public void setUp() {
        bidPrjModifyMapper = new BidPrjModifyMapperImpl();
    }
}
