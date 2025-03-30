package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsTaxasTeifaETeipEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsTaxasTeifaETeipEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsTaxasTeifaETeipEntity.class);
        OnsTaxasTeifaETeipEntity onsTaxasTeifaETeipEntity1 = getOnsTaxasTeifaETeipEntitySample1();
        OnsTaxasTeifaETeipEntity onsTaxasTeifaETeipEntity2 = new OnsTaxasTeifaETeipEntity();
        assertThat(onsTaxasTeifaETeipEntity1).isNotEqualTo(onsTaxasTeifaETeipEntity2);

        onsTaxasTeifaETeipEntity2.setId(onsTaxasTeifaETeipEntity1.getId());
        assertThat(onsTaxasTeifaETeipEntity1).isEqualTo(onsTaxasTeifaETeipEntity2);

        onsTaxasTeifaETeipEntity2 = getOnsTaxasTeifaETeipEntitySample2();
        assertThat(onsTaxasTeifaETeipEntity1).isNotEqualTo(onsTaxasTeifaETeipEntity2);
    }
}
