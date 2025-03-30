package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.class);
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity1 =
            getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntitySample1();
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity2 =
            new OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity();
        assertThat(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity1).isNotEqualTo(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity2
        );

        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity2.setId(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity1.getId()
        );
        assertThat(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity1).isEqualTo(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity2
        );

        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity2 = getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntitySample2();
        assertThat(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity1).isNotEqualTo(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity2
        );
    }
}
