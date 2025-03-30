package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEnaDiarioSubsistemaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEnaDiarioSubsistemaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEnaDiarioSubsistemaEntity.class);
        OnsEnaDiarioSubsistemaEntity onsEnaDiarioSubsistemaEntity1 = getOnsEnaDiarioSubsistemaEntitySample1();
        OnsEnaDiarioSubsistemaEntity onsEnaDiarioSubsistemaEntity2 = new OnsEnaDiarioSubsistemaEntity();
        assertThat(onsEnaDiarioSubsistemaEntity1).isNotEqualTo(onsEnaDiarioSubsistemaEntity2);

        onsEnaDiarioSubsistemaEntity2.setId(onsEnaDiarioSubsistemaEntity1.getId());
        assertThat(onsEnaDiarioSubsistemaEntity1).isEqualTo(onsEnaDiarioSubsistemaEntity2);

        onsEnaDiarioSubsistemaEntity2 = getOnsEnaDiarioSubsistemaEntitySample2();
        assertThat(onsEnaDiarioSubsistemaEntity1).isNotEqualTo(onsEnaDiarioSubsistemaEntity2);
    }
}
