package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.class);
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity1 =
            getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntitySample1();
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity2 =
            new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity();
        assertThat(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity1).isNotEqualTo(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity2
        );

        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity2.setId(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity1.getId()
        );
        assertThat(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity1).isEqualTo(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity2
        );

        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity2 =
            getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntitySample2();
        assertThat(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity1).isNotEqualTo(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity2
        );
    }
}
