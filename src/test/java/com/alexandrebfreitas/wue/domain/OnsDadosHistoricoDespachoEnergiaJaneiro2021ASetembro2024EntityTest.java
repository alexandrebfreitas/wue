package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.class);
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity1 =
            getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntitySample1();
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity2 =
            new OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity();
        assertThat(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity1).isNotEqualTo(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity2
        );

        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity2.setId(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity1.getId()
        );
        assertThat(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity1).isEqualTo(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity2
        );

        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity2 =
            getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntitySample2();
        assertThat(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity1).isNotEqualTo(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity2
        );
    }
}
