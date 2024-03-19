package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjEquipMapperTest {

    private BidPrjEquipMapper bidPrjEquipMapper;

    @BeforeEach
    public void setUp() {
        bidPrjEquipMapper = new BidPrjEquipMapperImpl();
    }
}
