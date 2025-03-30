package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.class);
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity1 =
            getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntitySample1();
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity2 =
            new OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity();
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity1).isNotEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity2
        );

        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity2.setId(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity1.getId()
        );
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity1).isEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity2
        );

        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity2 =
            getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntitySample2();
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity1).isNotEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity2
        );
    }
}
