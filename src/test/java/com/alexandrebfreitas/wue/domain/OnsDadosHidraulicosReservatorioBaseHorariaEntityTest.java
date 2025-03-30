package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseHorariaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosHidraulicosReservatorioBaseHorariaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosHidraulicosReservatorioBaseHorariaEntity.class);
        OnsDadosHidraulicosReservatorioBaseHorariaEntity onsDadosHidraulicosReservatorioBaseHorariaEntity1 =
            getOnsDadosHidraulicosReservatorioBaseHorariaEntitySample1();
        OnsDadosHidraulicosReservatorioBaseHorariaEntity onsDadosHidraulicosReservatorioBaseHorariaEntity2 =
            new OnsDadosHidraulicosReservatorioBaseHorariaEntity();
        assertThat(onsDadosHidraulicosReservatorioBaseHorariaEntity1).isNotEqualTo(onsDadosHidraulicosReservatorioBaseHorariaEntity2);

        onsDadosHidraulicosReservatorioBaseHorariaEntity2.setId(onsDadosHidraulicosReservatorioBaseHorariaEntity1.getId());
        assertThat(onsDadosHidraulicosReservatorioBaseHorariaEntity1).isEqualTo(onsDadosHidraulicosReservatorioBaseHorariaEntity2);

        onsDadosHidraulicosReservatorioBaseHorariaEntity2 = getOnsDadosHidraulicosReservatorioBaseHorariaEntitySample2();
        assertThat(onsDadosHidraulicosReservatorioBaseHorariaEntity1).isNotEqualTo(onsDadosHidraulicosReservatorioBaseHorariaEntity2);
    }
}
