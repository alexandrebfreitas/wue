package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCargaEnergiaMensalEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCargaEnergiaMensalEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCargaEnergiaMensalEntity.class);
        OnsCargaEnergiaMensalEntity onsCargaEnergiaMensalEntity1 = getOnsCargaEnergiaMensalEntitySample1();
        OnsCargaEnergiaMensalEntity onsCargaEnergiaMensalEntity2 = new OnsCargaEnergiaMensalEntity();
        assertThat(onsCargaEnergiaMensalEntity1).isNotEqualTo(onsCargaEnergiaMensalEntity2);

        onsCargaEnergiaMensalEntity2.setId(onsCargaEnergiaMensalEntity1.getId());
        assertThat(onsCargaEnergiaMensalEntity1).isEqualTo(onsCargaEnergiaMensalEntity2);

        onsCargaEnergiaMensalEntity2 = getOnsCargaEnergiaMensalEntitySample2();
        assertThat(onsCargaEnergiaMensalEntity1).isNotEqualTo(onsCargaEnergiaMensalEntity2);
    }
}
