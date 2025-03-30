package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.class);
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity1 =
            getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntitySample1();
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity2 =
            new OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity();
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity1).isNotEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity2
        );

        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity2.setId(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity1.getId()
        );
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity1).isEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity2
        );

        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity2 =
            getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntitySample2();
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity1).isNotEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity2
        );
    }
}
