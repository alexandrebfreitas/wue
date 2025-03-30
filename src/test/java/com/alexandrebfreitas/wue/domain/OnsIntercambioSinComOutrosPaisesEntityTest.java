package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsIntercambioSinComOutrosPaisesEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsIntercambioSinComOutrosPaisesEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsIntercambioSinComOutrosPaisesEntity.class);
        OnsIntercambioSinComOutrosPaisesEntity onsIntercambioSinComOutrosPaisesEntity1 = getOnsIntercambioSinComOutrosPaisesEntitySample1();
        OnsIntercambioSinComOutrosPaisesEntity onsIntercambioSinComOutrosPaisesEntity2 = new OnsIntercambioSinComOutrosPaisesEntity();
        assertThat(onsIntercambioSinComOutrosPaisesEntity1).isNotEqualTo(onsIntercambioSinComOutrosPaisesEntity2);

        onsIntercambioSinComOutrosPaisesEntity2.setId(onsIntercambioSinComOutrosPaisesEntity1.getId());
        assertThat(onsIntercambioSinComOutrosPaisesEntity1).isEqualTo(onsIntercambioSinComOutrosPaisesEntity2);

        onsIntercambioSinComOutrosPaisesEntity2 = getOnsIntercambioSinComOutrosPaisesEntitySample2();
        assertThat(onsIntercambioSinComOutrosPaisesEntity1).isNotEqualTo(onsIntercambioSinComOutrosPaisesEntity2);
    }
}
