package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.class);
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity1 =
            getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntitySample1();
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity2 =
            new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity();
        assertThat(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity1).isNotEqualTo(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity2
        );

        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity2.setId(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity1.getId()
        );
        assertThat(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity1).isEqualTo(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity2
        );

        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity2 =
            getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntitySample2();
        assertThat(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity1).isNotEqualTo(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity2
        );
    }
}
