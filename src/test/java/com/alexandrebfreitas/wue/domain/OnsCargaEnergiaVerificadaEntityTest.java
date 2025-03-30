package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCargaEnergiaVerificadaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCargaEnergiaVerificadaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCargaEnergiaVerificadaEntity.class);
        OnsCargaEnergiaVerificadaEntity onsCargaEnergiaVerificadaEntity1 = getOnsCargaEnergiaVerificadaEntitySample1();
        OnsCargaEnergiaVerificadaEntity onsCargaEnergiaVerificadaEntity2 = new OnsCargaEnergiaVerificadaEntity();
        assertThat(onsCargaEnergiaVerificadaEntity1).isNotEqualTo(onsCargaEnergiaVerificadaEntity2);

        onsCargaEnergiaVerificadaEntity2.setId(onsCargaEnergiaVerificadaEntity1.getId());
        assertThat(onsCargaEnergiaVerificadaEntity1).isEqualTo(onsCargaEnergiaVerificadaEntity2);

        onsCargaEnergiaVerificadaEntity2 = getOnsCargaEnergiaVerificadaEntitySample2();
        assertThat(onsCargaEnergiaVerificadaEntity1).isNotEqualTo(onsCargaEnergiaVerificadaEntity2);
    }
}
