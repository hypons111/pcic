package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProOsPersonListDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProOsPersonListDTO.class);
        ProOsPersonListDTO proOsPersonListDTO1 = new ProOsPersonListDTO();
        proOsPersonListDTO1.setProOsPersonListId(1L);
        ProOsPersonListDTO proOsPersonListDTO2 = new ProOsPersonListDTO();
        assertThat(proOsPersonListDTO1).isNotEqualTo(proOsPersonListDTO2);
        proOsPersonListDTO2.setProOsPersonListId(proOsPersonListDTO1.getProOsPersonListId());
        assertThat(proOsPersonListDTO1).isEqualTo(proOsPersonListDTO2);
        proOsPersonListDTO2.setProOsPersonListId(2L);
        assertThat(proOsPersonListDTO1).isNotEqualTo(proOsPersonListDTO2);
        proOsPersonListDTO1.setProOsPersonListId(null);
        assertThat(proOsPersonListDTO1).isNotEqualTo(proOsPersonListDTO2);
    }
}
