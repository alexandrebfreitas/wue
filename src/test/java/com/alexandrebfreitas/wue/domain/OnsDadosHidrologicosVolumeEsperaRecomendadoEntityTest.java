package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosHidrologicosVolumeEsperaRecomendadoEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosHidrologicosVolumeEsperaRecomendadoEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosHidrologicosVolumeEsperaRecomendadoEntity.class);
        OnsDadosHidrologicosVolumeEsperaRecomendadoEntity onsDadosHidrologicosVolumeEsperaRecomendadoEntity1 =
            getOnsDadosHidrologicosVolumeEsperaRecomendadoEntitySample1();
        OnsDadosHidrologicosVolumeEsperaRecomendadoEntity onsDadosHidrologicosVolumeEsperaRecomendadoEntity2 =
            new OnsDadosHidrologicosVolumeEsperaRecomendadoEntity();
        assertThat(onsDadosHidrologicosVolumeEsperaRecomendadoEntity1).isNotEqualTo(onsDadosHidrologicosVolumeEsperaRecomendadoEntity2);

        onsDadosHidrologicosVolumeEsperaRecomendadoEntity2.setId(onsDadosHidrologicosVolumeEsperaRecomendadoEntity1.getId());
        assertThat(onsDadosHidrologicosVolumeEsperaRecomendadoEntity1).isEqualTo(onsDadosHidrologicosVolumeEsperaRecomendadoEntity2);

        onsDadosHidrologicosVolumeEsperaRecomendadoEntity2 = getOnsDadosHidrologicosVolumeEsperaRecomendadoEntitySample2();
        assertThat(onsDadosHidrologicosVolumeEsperaRecomendadoEntity1).isNotEqualTo(onsDadosHidrologicosVolumeEsperaRecomendadoEntity2);
    }
}
