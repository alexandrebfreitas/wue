package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.class);
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity1 =
            getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntitySample1();
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity2 =
            new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity();
        assertThat(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity1).isNotEqualTo(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity2
        );

        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity2.setId(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity1.getId()
        );
        assertThat(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity1).isEqualTo(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity2
        );

        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity2 =
            getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntitySample2();
        assertThat(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity1).isNotEqualTo(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity2
        );
    }
}
