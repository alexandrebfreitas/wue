package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDescontinuadoPrecipitacaoDiariaObservadaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDescontinuadoPrecipitacaoDiariaObservadaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDescontinuadoPrecipitacaoDiariaObservadaEntity.class);
        OnsDescontinuadoPrecipitacaoDiariaObservadaEntity onsDescontinuadoPrecipitacaoDiariaObservadaEntity1 =
            getOnsDescontinuadoPrecipitacaoDiariaObservadaEntitySample1();
        OnsDescontinuadoPrecipitacaoDiariaObservadaEntity onsDescontinuadoPrecipitacaoDiariaObservadaEntity2 =
            new OnsDescontinuadoPrecipitacaoDiariaObservadaEntity();
        assertThat(onsDescontinuadoPrecipitacaoDiariaObservadaEntity1).isNotEqualTo(onsDescontinuadoPrecipitacaoDiariaObservadaEntity2);

        onsDescontinuadoPrecipitacaoDiariaObservadaEntity2.setId(onsDescontinuadoPrecipitacaoDiariaObservadaEntity1.getId());
        assertThat(onsDescontinuadoPrecipitacaoDiariaObservadaEntity1).isEqualTo(onsDescontinuadoPrecipitacaoDiariaObservadaEntity2);

        onsDescontinuadoPrecipitacaoDiariaObservadaEntity2 = getOnsDescontinuadoPrecipitacaoDiariaObservadaEntitySample2();
        assertThat(onsDescontinuadoPrecipitacaoDiariaObservadaEntity1).isNotEqualTo(onsDescontinuadoPrecipitacaoDiariaObservadaEntity2);
    }
}
