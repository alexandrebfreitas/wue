package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsReservatoriosEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsReservatoriosEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsReservatoriosEntity.class);
        OnsReservatoriosEntity onsReservatoriosEntity1 = getOnsReservatoriosEntitySample1();
        OnsReservatoriosEntity onsReservatoriosEntity2 = new OnsReservatoriosEntity();
        assertThat(onsReservatoriosEntity1).isNotEqualTo(onsReservatoriosEntity2);

        onsReservatoriosEntity2.setId(onsReservatoriosEntity1.getId());
        assertThat(onsReservatoriosEntity1).isEqualTo(onsReservatoriosEntity2);

        onsReservatoriosEntity2 = getOnsReservatoriosEntitySample2();
        assertThat(onsReservatoriosEntity1).isNotEqualTo(onsReservatoriosEntity2);
    }
}
