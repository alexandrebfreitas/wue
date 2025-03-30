package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEarDiarioSubsistemaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEarDiarioSubsistemaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEarDiarioSubsistemaEntity.class);
        OnsEarDiarioSubsistemaEntity onsEarDiarioSubsistemaEntity1 = getOnsEarDiarioSubsistemaEntitySample1();
        OnsEarDiarioSubsistemaEntity onsEarDiarioSubsistemaEntity2 = new OnsEarDiarioSubsistemaEntity();
        assertThat(onsEarDiarioSubsistemaEntity1).isNotEqualTo(onsEarDiarioSubsistemaEntity2);

        onsEarDiarioSubsistemaEntity2.setId(onsEarDiarioSubsistemaEntity1.getId());
        assertThat(onsEarDiarioSubsistemaEntity1).isEqualTo(onsEarDiarioSubsistemaEntity2);

        onsEarDiarioSubsistemaEntity2 = getOnsEarDiarioSubsistemaEntitySample2();
        assertThat(onsEarDiarioSubsistemaEntity1).isNotEqualTo(onsEarDiarioSubsistemaEntity2);
    }
}
