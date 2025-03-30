package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEnaDiarioBaciaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEnaDiarioBaciaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEnaDiarioBaciaEntity.class);
        OnsEnaDiarioBaciaEntity onsEnaDiarioBaciaEntity1 = getOnsEnaDiarioBaciaEntitySample1();
        OnsEnaDiarioBaciaEntity onsEnaDiarioBaciaEntity2 = new OnsEnaDiarioBaciaEntity();
        assertThat(onsEnaDiarioBaciaEntity1).isNotEqualTo(onsEnaDiarioBaciaEntity2);

        onsEnaDiarioBaciaEntity2.setId(onsEnaDiarioBaciaEntity1.getId());
        assertThat(onsEnaDiarioBaciaEntity1).isEqualTo(onsEnaDiarioBaciaEntity2);

        onsEnaDiarioBaciaEntity2 = getOnsEnaDiarioBaciaEntitySample2();
        assertThat(onsEnaDiarioBaciaEntity1).isNotEqualTo(onsEnaDiarioBaciaEntity2);
    }
}
