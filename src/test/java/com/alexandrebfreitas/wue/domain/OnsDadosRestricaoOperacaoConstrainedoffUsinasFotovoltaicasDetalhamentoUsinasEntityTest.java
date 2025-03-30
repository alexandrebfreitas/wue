package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.class);
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity1 =
            getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntitySample1();
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity2 =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity();
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity1).isNotEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity2
        );

        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity2.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity1.getId()
        );
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity1).isEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity2
        );

        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity2 =
            getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntitySample2();
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity1).isNotEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity2
        );
    }
}
