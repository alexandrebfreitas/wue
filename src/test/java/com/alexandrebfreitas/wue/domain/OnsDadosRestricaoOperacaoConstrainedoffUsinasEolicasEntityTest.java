package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.class);
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity1 =
            getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntitySample1();
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity2 =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity();
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity1).isNotEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity2
        );

        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity2.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity1.getId()
        );
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity1).isEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity2
        );

        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity2 =
            getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntitySample2();
        assertThat(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity1).isNotEqualTo(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity2
        );
    }
}
