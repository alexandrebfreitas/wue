package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEarDiarioReeReservatorioEquivalenteEnergiaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEarDiarioReeReservatorioEquivalenteEnergiaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.class);
        OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity onsEarDiarioReeReservatorioEquivalenteEnergiaEntity1 =
            getOnsEarDiarioReeReservatorioEquivalenteEnergiaEntitySample1();
        OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity onsEarDiarioReeReservatorioEquivalenteEnergiaEntity2 =
            new OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity();
        assertThat(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity1).isNotEqualTo(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity2);

        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity2.setId(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity1.getId());
        assertThat(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity1).isEqualTo(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity2);

        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity2 = getOnsEarDiarioReeReservatorioEquivalenteEnergiaEntitySample2();
        assertThat(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity1).isNotEqualTo(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity2);
    }
}
