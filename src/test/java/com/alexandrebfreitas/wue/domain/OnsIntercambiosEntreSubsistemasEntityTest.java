package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsIntercambiosEntreSubsistemasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsIntercambiosEntreSubsistemasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsIntercambiosEntreSubsistemasEntity.class);
        OnsIntercambiosEntreSubsistemasEntity onsIntercambiosEntreSubsistemasEntity1 = getOnsIntercambiosEntreSubsistemasEntitySample1();
        OnsIntercambiosEntreSubsistemasEntity onsIntercambiosEntreSubsistemasEntity2 = new OnsIntercambiosEntreSubsistemasEntity();
        assertThat(onsIntercambiosEntreSubsistemasEntity1).isNotEqualTo(onsIntercambiosEntreSubsistemasEntity2);

        onsIntercambiosEntreSubsistemasEntity2.setId(onsIntercambiosEntreSubsistemasEntity1.getId());
        assertThat(onsIntercambiosEntreSubsistemasEntity1).isEqualTo(onsIntercambiosEntreSubsistemasEntity2);

        onsIntercambiosEntreSubsistemasEntity2 = getOnsIntercambiosEntreSubsistemasEntitySample2();
        assertThat(onsIntercambiosEntreSubsistemasEntity1).isNotEqualTo(onsIntercambiosEntreSubsistemasEntity2);
    }
}
