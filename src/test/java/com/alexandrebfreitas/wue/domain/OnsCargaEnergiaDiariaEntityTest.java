package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCargaEnergiaDiariaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCargaEnergiaDiariaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCargaEnergiaDiariaEntity.class);
        OnsCargaEnergiaDiariaEntity onsCargaEnergiaDiariaEntity1 = getOnsCargaEnergiaDiariaEntitySample1();
        OnsCargaEnergiaDiariaEntity onsCargaEnergiaDiariaEntity2 = new OnsCargaEnergiaDiariaEntity();
        assertThat(onsCargaEnergiaDiariaEntity1).isNotEqualTo(onsCargaEnergiaDiariaEntity2);

        onsCargaEnergiaDiariaEntity2.setId(onsCargaEnergiaDiariaEntity1.getId());
        assertThat(onsCargaEnergiaDiariaEntity1).isEqualTo(onsCargaEnergiaDiariaEntity2);

        onsCargaEnergiaDiariaEntity2 = getOnsCargaEnergiaDiariaEntitySample2();
        assertThat(onsCargaEnergiaDiariaEntity1).isNotEqualTo(onsCargaEnergiaDiariaEntity2);
    }
}
