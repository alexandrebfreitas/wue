package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEarDiarioReservatorioEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEarDiarioReservatorioEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEarDiarioReservatorioEntity.class);
        OnsEarDiarioReservatorioEntity onsEarDiarioReservatorioEntity1 = getOnsEarDiarioReservatorioEntitySample1();
        OnsEarDiarioReservatorioEntity onsEarDiarioReservatorioEntity2 = new OnsEarDiarioReservatorioEntity();
        assertThat(onsEarDiarioReservatorioEntity1).isNotEqualTo(onsEarDiarioReservatorioEntity2);

        onsEarDiarioReservatorioEntity2.setId(onsEarDiarioReservatorioEntity1.getId());
        assertThat(onsEarDiarioReservatorioEntity1).isEqualTo(onsEarDiarioReservatorioEntity2);

        onsEarDiarioReservatorioEntity2 = getOnsEarDiarioReservatorioEntitySample2();
        assertThat(onsEarDiarioReservatorioEntity1).isNotEqualTo(onsEarDiarioReservatorioEntity2);
    }
}
