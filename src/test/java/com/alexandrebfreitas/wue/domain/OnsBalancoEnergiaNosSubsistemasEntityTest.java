package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaNosSubsistemasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsBalancoEnergiaNosSubsistemasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsBalancoEnergiaNosSubsistemasEntity.class);
        OnsBalancoEnergiaNosSubsistemasEntity onsBalancoEnergiaNosSubsistemasEntity1 = getOnsBalancoEnergiaNosSubsistemasEntitySample1();
        OnsBalancoEnergiaNosSubsistemasEntity onsBalancoEnergiaNosSubsistemasEntity2 = new OnsBalancoEnergiaNosSubsistemasEntity();
        assertThat(onsBalancoEnergiaNosSubsistemasEntity1).isNotEqualTo(onsBalancoEnergiaNosSubsistemasEntity2);

        onsBalancoEnergiaNosSubsistemasEntity2.setId(onsBalancoEnergiaNosSubsistemasEntity1.getId());
        assertThat(onsBalancoEnergiaNosSubsistemasEntity1).isEqualTo(onsBalancoEnergiaNosSubsistemasEntity2);

        onsBalancoEnergiaNosSubsistemasEntity2 = getOnsBalancoEnergiaNosSubsistemasEntitySample2();
        assertThat(onsBalancoEnergiaNosSubsistemasEntity1).isNotEqualTo(onsBalancoEnergiaNosSubsistemasEntity2);
    }
}
