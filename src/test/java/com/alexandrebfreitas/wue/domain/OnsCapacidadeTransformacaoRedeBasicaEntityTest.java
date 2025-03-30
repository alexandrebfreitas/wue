package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCapacidadeTransformacaoRedeBasicaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCapacidadeTransformacaoRedeBasicaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCapacidadeTransformacaoRedeBasicaEntity.class);
        OnsCapacidadeTransformacaoRedeBasicaEntity onsCapacidadeTransformacaoRedeBasicaEntity1 =
            getOnsCapacidadeTransformacaoRedeBasicaEntitySample1();
        OnsCapacidadeTransformacaoRedeBasicaEntity onsCapacidadeTransformacaoRedeBasicaEntity2 =
            new OnsCapacidadeTransformacaoRedeBasicaEntity();
        assertThat(onsCapacidadeTransformacaoRedeBasicaEntity1).isNotEqualTo(onsCapacidadeTransformacaoRedeBasicaEntity2);

        onsCapacidadeTransformacaoRedeBasicaEntity2.setId(onsCapacidadeTransformacaoRedeBasicaEntity1.getId());
        assertThat(onsCapacidadeTransformacaoRedeBasicaEntity1).isEqualTo(onsCapacidadeTransformacaoRedeBasicaEntity2);

        onsCapacidadeTransformacaoRedeBasicaEntity2 = getOnsCapacidadeTransformacaoRedeBasicaEntitySample2();
        assertThat(onsCapacidadeTransformacaoRedeBasicaEntity1).isNotEqualTo(onsCapacidadeTransformacaoRedeBasicaEntity2);
    }
}
