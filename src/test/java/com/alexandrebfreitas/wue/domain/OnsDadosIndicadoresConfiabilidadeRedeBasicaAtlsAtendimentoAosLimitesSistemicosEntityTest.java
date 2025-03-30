package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.class);
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity1 =
            getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntitySample1();
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity2 =
            new OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity();
        assertThat(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity1).isNotEqualTo(
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity2
        );

        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity2.setId(
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity1.getId()
        );
        assertThat(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity1).isEqualTo(
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity2
        );

        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity2 =
            getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntitySample2();
        assertThat(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity1).isNotEqualTo(
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity2
        );
    }
}
