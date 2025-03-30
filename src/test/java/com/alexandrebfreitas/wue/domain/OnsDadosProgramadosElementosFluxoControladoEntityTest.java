package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosProgramadosElementosFluxoControladoEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosProgramadosElementosFluxoControladoEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosProgramadosElementosFluxoControladoEntity.class);
        OnsDadosProgramadosElementosFluxoControladoEntity onsDadosProgramadosElementosFluxoControladoEntity1 =
            getOnsDadosProgramadosElementosFluxoControladoEntitySample1();
        OnsDadosProgramadosElementosFluxoControladoEntity onsDadosProgramadosElementosFluxoControladoEntity2 =
            new OnsDadosProgramadosElementosFluxoControladoEntity();
        assertThat(onsDadosProgramadosElementosFluxoControladoEntity1).isNotEqualTo(onsDadosProgramadosElementosFluxoControladoEntity2);

        onsDadosProgramadosElementosFluxoControladoEntity2.setId(onsDadosProgramadosElementosFluxoControladoEntity1.getId());
        assertThat(onsDadosProgramadosElementosFluxoControladoEntity1).isEqualTo(onsDadosProgramadosElementosFluxoControladoEntity2);

        onsDadosProgramadosElementosFluxoControladoEntity2 = getOnsDadosProgramadosElementosFluxoControladoEntitySample2();
        assertThat(onsDadosProgramadosElementosFluxoControladoEntity1).isNotEqualTo(onsDadosProgramadosElementosFluxoControladoEntity2);
    }
}
