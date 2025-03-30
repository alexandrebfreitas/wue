package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.class);
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity1 =
            getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntitySample1();
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity2 =
            new OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity();
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity1).isNotEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity2
        );

        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity2.setId(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity1.getId());
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity1).isEqualTo(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity2);

        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity2 = getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntitySample2();
        assertThat(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity1).isNotEqualTo(
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity2
        );
    }
}
