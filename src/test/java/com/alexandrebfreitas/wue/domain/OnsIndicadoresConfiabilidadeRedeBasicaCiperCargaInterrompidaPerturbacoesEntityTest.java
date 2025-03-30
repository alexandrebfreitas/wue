package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.class);
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity1 =
            getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntitySample1();
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity2 =
            new OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity();
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity1).isNotEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity2
        );

        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity2.setId(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity1.getId()
        );
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity1).isEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity2
        );

        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity2 =
            getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntitySample2();
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity1).isNotEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity2
        );
    }
}
