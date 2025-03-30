package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaDessemEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsBalancoEnergiaDessemEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsBalancoEnergiaDessemEntity.class);
        OnsBalancoEnergiaDessemEntity onsBalancoEnergiaDessemEntity1 = getOnsBalancoEnergiaDessemEntitySample1();
        OnsBalancoEnergiaDessemEntity onsBalancoEnergiaDessemEntity2 = new OnsBalancoEnergiaDessemEntity();
        assertThat(onsBalancoEnergiaDessemEntity1).isNotEqualTo(onsBalancoEnergiaDessemEntity2);

        onsBalancoEnergiaDessemEntity2.setId(onsBalancoEnergiaDessemEntity1.getId());
        assertThat(onsBalancoEnergiaDessemEntity1).isEqualTo(onsBalancoEnergiaDessemEntity2);

        onsBalancoEnergiaDessemEntity2 = getOnsBalancoEnergiaDessemEntitySample2();
        assertThat(onsBalancoEnergiaDessemEntity1).isNotEqualTo(onsBalancoEnergiaDessemEntity2);
    }
}
