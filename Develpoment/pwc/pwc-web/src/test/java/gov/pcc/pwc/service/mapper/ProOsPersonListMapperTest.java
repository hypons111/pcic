package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProOsPersonListMapperTest {

    private ProOsPersonListMapper proOsPersonListMapper;

    @BeforeEach
    public void setUp() {
        proOsPersonListMapper = new ProOsPersonListMapperImpl();
    }
}
