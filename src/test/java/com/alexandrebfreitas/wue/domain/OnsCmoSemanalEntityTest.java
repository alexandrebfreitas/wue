package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCmoSemanalEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCmoSemanalEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCmoSemanalEntity.class);
        OnsCmoSemanalEntity onsCmoSemanalEntity1 = getOnsCmoSemanalEntitySample1();
        OnsCmoSemanalEntity onsCmoSemanalEntity2 = new OnsCmoSemanalEntity();
        assertThat(onsCmoSemanalEntity1).isNotEqualTo(onsCmoSemanalEntity2);

        onsCmoSemanalEntity2.setId(onsCmoSemanalEntity1.getId());
        assertThat(onsCmoSemanalEntity1).isEqualTo(onsCmoSemanalEntity2);

        onsCmoSemanalEntity2 = getOnsCmoSemanalEntitySample2();
        assertThat(onsCmoSemanalEntity1).isNotEqualTo(onsCmoSemanalEntity2);
    }
}
