package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.class);
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity1 =
            getOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntitySample1();
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity2 =
            new OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity();
        assertThat(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity1).isNotEqualTo(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity2);

        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity2.setId(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity1.getId());
        assertThat(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity1).isEqualTo(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity2);

        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity2 = getOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntitySample2();
        assertThat(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity1).isNotEqualTo(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity2);
    }
}
