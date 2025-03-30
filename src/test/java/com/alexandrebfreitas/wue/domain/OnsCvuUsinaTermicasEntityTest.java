package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCvuUsinaTermicasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCvuUsinaTermicasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCvuUsinaTermicasEntity.class);
        OnsCvuUsinaTermicasEntity onsCvuUsinaTermicasEntity1 = getOnsCvuUsinaTermicasEntitySample1();
        OnsCvuUsinaTermicasEntity onsCvuUsinaTermicasEntity2 = new OnsCvuUsinaTermicasEntity();
        assertThat(onsCvuUsinaTermicasEntity1).isNotEqualTo(onsCvuUsinaTermicasEntity2);

        onsCvuUsinaTermicasEntity2.setId(onsCvuUsinaTermicasEntity1.getId());
        assertThat(onsCvuUsinaTermicasEntity1).isEqualTo(onsCvuUsinaTermicasEntity2);

        onsCvuUsinaTermicasEntity2 = getOnsCvuUsinaTermicasEntitySample2();
        assertThat(onsCvuUsinaTermicasEntity1).isNotEqualTo(onsCvuUsinaTermicasEntity2);
    }
}
