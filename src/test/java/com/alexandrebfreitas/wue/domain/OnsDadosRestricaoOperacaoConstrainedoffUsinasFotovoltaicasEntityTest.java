package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.class);
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity1 =
            getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntitySample1();
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity2 =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity();
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity1).isNotEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity2
        );

        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity2.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity1.getId()
        );
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity1).isEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity2
        );

        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity2 =
            getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntitySample2();
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity1).isNotEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity2
        );
    }
}
