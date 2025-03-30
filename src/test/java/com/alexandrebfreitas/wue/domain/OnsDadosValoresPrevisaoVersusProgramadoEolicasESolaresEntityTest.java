package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.class);
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity1 =
            getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntitySample1();
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity2 =
            new OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity();
        assertThat(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity1).isNotEqualTo(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity2
        );

        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity2.setId(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity1.getId()
        );
        assertThat(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity1).isEqualTo(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity2
        );

        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity2 =
            getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntitySample2();
        assertThat(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity1).isNotEqualTo(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity2
        );
    }
}
