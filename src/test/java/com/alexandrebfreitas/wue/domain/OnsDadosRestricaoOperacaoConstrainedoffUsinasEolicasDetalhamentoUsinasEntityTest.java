package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.class);
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity1 =
            getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntitySample1();
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity2 =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity();
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity1).isNotEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity2
        );

        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity2.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity1.getId()
        );
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity1).isEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity2
        );

        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity2 =
            getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntitySample2();
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity1).isNotEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity2
        );
    }
}
