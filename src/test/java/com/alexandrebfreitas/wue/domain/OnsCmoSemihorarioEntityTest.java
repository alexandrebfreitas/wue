package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCmoSemihorarioEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCmoSemihorarioEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCmoSemihorarioEntity.class);
        OnsCmoSemihorarioEntity onsCmoSemihorarioEntity1 = getOnsCmoSemihorarioEntitySample1();
        OnsCmoSemihorarioEntity onsCmoSemihorarioEntity2 = new OnsCmoSemihorarioEntity();
        assertThat(onsCmoSemihorarioEntity1).isNotEqualTo(onsCmoSemihorarioEntity2);

        onsCmoSemihorarioEntity2.setId(onsCmoSemihorarioEntity1.getId());
        assertThat(onsCmoSemihorarioEntity1).isEqualTo(onsCmoSemihorarioEntity2);

        onsCmoSemihorarioEntity2 = getOnsCmoSemihorarioEntitySample2();
        assertThat(onsCmoSemihorarioEntity1).isNotEqualTo(onsCmoSemihorarioEntity2);
    }
}
