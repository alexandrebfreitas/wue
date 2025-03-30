package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsGeracaoComercialParaExportacaoInternacionalEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsGeracaoComercialParaExportacaoInternacionalEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsGeracaoComercialParaExportacaoInternacionalEntity.class);
        OnsGeracaoComercialParaExportacaoInternacionalEntity onsGeracaoComercialParaExportacaoInternacionalEntity1 =
            getOnsGeracaoComercialParaExportacaoInternacionalEntitySample1();
        OnsGeracaoComercialParaExportacaoInternacionalEntity onsGeracaoComercialParaExportacaoInternacionalEntity2 =
            new OnsGeracaoComercialParaExportacaoInternacionalEntity();
        assertThat(onsGeracaoComercialParaExportacaoInternacionalEntity1).isNotEqualTo(
            onsGeracaoComercialParaExportacaoInternacionalEntity2
        );

        onsGeracaoComercialParaExportacaoInternacionalEntity2.setId(onsGeracaoComercialParaExportacaoInternacionalEntity1.getId());
        assertThat(onsGeracaoComercialParaExportacaoInternacionalEntity1).isEqualTo(onsGeracaoComercialParaExportacaoInternacionalEntity2);

        onsGeracaoComercialParaExportacaoInternacionalEntity2 = getOnsGeracaoComercialParaExportacaoInternacionalEntitySample2();
        assertThat(onsGeracaoComercialParaExportacaoInternacionalEntity1).isNotEqualTo(
            onsGeracaoComercialParaExportacaoInternacionalEntity2
        );
    }
}
