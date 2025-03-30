package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsLinhasTransmissaoRedeOperacaoEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsLinhasTransmissaoRedeOperacaoEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsLinhasTransmissaoRedeOperacaoEntity.class);
        OnsLinhasTransmissaoRedeOperacaoEntity onsLinhasTransmissaoRedeOperacaoEntity1 = getOnsLinhasTransmissaoRedeOperacaoEntitySample1();
        OnsLinhasTransmissaoRedeOperacaoEntity onsLinhasTransmissaoRedeOperacaoEntity2 = new OnsLinhasTransmissaoRedeOperacaoEntity();
        assertThat(onsLinhasTransmissaoRedeOperacaoEntity1).isNotEqualTo(onsLinhasTransmissaoRedeOperacaoEntity2);

        onsLinhasTransmissaoRedeOperacaoEntity2.setId(onsLinhasTransmissaoRedeOperacaoEntity1.getId());
        assertThat(onsLinhasTransmissaoRedeOperacaoEntity1).isEqualTo(onsLinhasTransmissaoRedeOperacaoEntity2);

        onsLinhasTransmissaoRedeOperacaoEntity2 = getOnsLinhasTransmissaoRedeOperacaoEntitySample2();
        assertThat(onsLinhasTransmissaoRedeOperacaoEntity1).isNotEqualTo(onsLinhasTransmissaoRedeOperacaoEntity2);
    }
}
