package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCargaEnergiaProgramadaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCargaEnergiaProgramadaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCargaEnergiaProgramadaEntity.class);
        OnsCargaEnergiaProgramadaEntity onsCargaEnergiaProgramadaEntity1 = getOnsCargaEnergiaProgramadaEntitySample1();
        OnsCargaEnergiaProgramadaEntity onsCargaEnergiaProgramadaEntity2 = new OnsCargaEnergiaProgramadaEntity();
        assertThat(onsCargaEnergiaProgramadaEntity1).isNotEqualTo(onsCargaEnergiaProgramadaEntity2);

        onsCargaEnergiaProgramadaEntity2.setId(onsCargaEnergiaProgramadaEntity1.getId());
        assertThat(onsCargaEnergiaProgramadaEntity1).isEqualTo(onsCargaEnergiaProgramadaEntity2);

        onsCargaEnergiaProgramadaEntity2 = getOnsCargaEnergiaProgramadaEntitySample2();
        assertThat(onsCargaEnergiaProgramadaEntity1).isNotEqualTo(onsCargaEnergiaProgramadaEntity2);
    }
}
