package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEarDiarioBaciaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEarDiarioBaciaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEarDiarioBaciaEntity.class);
        OnsEarDiarioBaciaEntity onsEarDiarioBaciaEntity1 = getOnsEarDiarioBaciaEntitySample1();
        OnsEarDiarioBaciaEntity onsEarDiarioBaciaEntity2 = new OnsEarDiarioBaciaEntity();
        assertThat(onsEarDiarioBaciaEntity1).isNotEqualTo(onsEarDiarioBaciaEntity2);

        onsEarDiarioBaciaEntity2.setId(onsEarDiarioBaciaEntity1.getId());
        assertThat(onsEarDiarioBaciaEntity1).isEqualTo(onsEarDiarioBaciaEntity2);

        onsEarDiarioBaciaEntity2 = getOnsEarDiarioBaciaEntitySample2();
        assertThat(onsEarDiarioBaciaEntity1).isNotEqualTo(onsEarDiarioBaciaEntity2);
    }
}
