package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseDiariaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosHidraulicosReservatorioBaseDiariaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosHidraulicosReservatorioBaseDiariaEntity.class);
        OnsDadosHidraulicosReservatorioBaseDiariaEntity onsDadosHidraulicosReservatorioBaseDiariaEntity1 =
            getOnsDadosHidraulicosReservatorioBaseDiariaEntitySample1();
        OnsDadosHidraulicosReservatorioBaseDiariaEntity onsDadosHidraulicosReservatorioBaseDiariaEntity2 =
            new OnsDadosHidraulicosReservatorioBaseDiariaEntity();
        assertThat(onsDadosHidraulicosReservatorioBaseDiariaEntity1).isNotEqualTo(onsDadosHidraulicosReservatorioBaseDiariaEntity2);

        onsDadosHidraulicosReservatorioBaseDiariaEntity2.setId(onsDadosHidraulicosReservatorioBaseDiariaEntity1.getId());
        assertThat(onsDadosHidraulicosReservatorioBaseDiariaEntity1).isEqualTo(onsDadosHidraulicosReservatorioBaseDiariaEntity2);

        onsDadosHidraulicosReservatorioBaseDiariaEntity2 = getOnsDadosHidraulicosReservatorioBaseDiariaEntitySample2();
        assertThat(onsDadosHidraulicosReservatorioBaseDiariaEntity1).isNotEqualTo(onsDadosHidraulicosReservatorioBaseDiariaEntity2);
    }
}
