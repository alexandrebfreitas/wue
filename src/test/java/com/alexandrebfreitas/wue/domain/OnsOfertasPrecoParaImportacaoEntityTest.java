package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsOfertasPrecoParaImportacaoEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsOfertasPrecoParaImportacaoEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsOfertasPrecoParaImportacaoEntity.class);
        OnsOfertasPrecoParaImportacaoEntity onsOfertasPrecoParaImportacaoEntity1 = getOnsOfertasPrecoParaImportacaoEntitySample1();
        OnsOfertasPrecoParaImportacaoEntity onsOfertasPrecoParaImportacaoEntity2 = new OnsOfertasPrecoParaImportacaoEntity();
        assertThat(onsOfertasPrecoParaImportacaoEntity1).isNotEqualTo(onsOfertasPrecoParaImportacaoEntity2);

        onsOfertasPrecoParaImportacaoEntity2.setId(onsOfertasPrecoParaImportacaoEntity1.getId());
        assertThat(onsOfertasPrecoParaImportacaoEntity1).isEqualTo(onsOfertasPrecoParaImportacaoEntity2);

        onsOfertasPrecoParaImportacaoEntity2 = getOnsOfertasPrecoParaImportacaoEntitySample2();
        assertThat(onsOfertasPrecoParaImportacaoEntity1).isNotEqualTo(onsOfertasPrecoParaImportacaoEntity2);
    }
}
