package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsImportacaoEnergiaNaModalidadeComercialBlocoEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsImportacaoEnergiaNaModalidadeComercialBlocoEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.class);
        OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity onsImportacaoEnergiaNaModalidadeComercialBlocoEntity1 =
            getOnsImportacaoEnergiaNaModalidadeComercialBlocoEntitySample1();
        OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity onsImportacaoEnergiaNaModalidadeComercialBlocoEntity2 =
            new OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity();
        assertThat(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity1).isNotEqualTo(
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity2
        );

        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity2.setId(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity1.getId());
        assertThat(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity1).isEqualTo(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity2);

        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity2 = getOnsImportacaoEnergiaNaModalidadeComercialBlocoEntitySample2();
        assertThat(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity1).isNotEqualTo(
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity2
        );
    }
}
