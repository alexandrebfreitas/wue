package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEnaDiarioReservatorioEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEnaDiarioReservatorioEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEnaDiarioReservatorioEntity.class);
        OnsEnaDiarioReservatorioEntity onsEnaDiarioReservatorioEntity1 = getOnsEnaDiarioReservatorioEntitySample1();
        OnsEnaDiarioReservatorioEntity onsEnaDiarioReservatorioEntity2 = new OnsEnaDiarioReservatorioEntity();
        assertThat(onsEnaDiarioReservatorioEntity1).isNotEqualTo(onsEnaDiarioReservatorioEntity2);

        onsEnaDiarioReservatorioEntity2.setId(onsEnaDiarioReservatorioEntity1.getId());
        assertThat(onsEnaDiarioReservatorioEntity1).isEqualTo(onsEnaDiarioReservatorioEntity2);

        onsEnaDiarioReservatorioEntity2 = getOnsEnaDiarioReservatorioEntitySample2();
        assertThat(onsEnaDiarioReservatorioEntity1).isNotEqualTo(onsEnaDiarioReservatorioEntity2);
    }
}
