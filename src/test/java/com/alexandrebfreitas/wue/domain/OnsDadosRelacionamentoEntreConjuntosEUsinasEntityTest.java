package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreConjuntosEUsinasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosRelacionamentoEntreConjuntosEUsinasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosRelacionamentoEntreConjuntosEUsinasEntity.class);
        OnsDadosRelacionamentoEntreConjuntosEUsinasEntity onsDadosRelacionamentoEntreConjuntosEUsinasEntity1 =
            getOnsDadosRelacionamentoEntreConjuntosEUsinasEntitySample1();
        OnsDadosRelacionamentoEntreConjuntosEUsinasEntity onsDadosRelacionamentoEntreConjuntosEUsinasEntity2 =
            new OnsDadosRelacionamentoEntreConjuntosEUsinasEntity();
        assertThat(onsDadosRelacionamentoEntreConjuntosEUsinasEntity1).isNotEqualTo(onsDadosRelacionamentoEntreConjuntosEUsinasEntity2);

        onsDadosRelacionamentoEntreConjuntosEUsinasEntity2.setId(onsDadosRelacionamentoEntreConjuntosEUsinasEntity1.getId());
        assertThat(onsDadosRelacionamentoEntreConjuntosEUsinasEntity1).isEqualTo(onsDadosRelacionamentoEntreConjuntosEUsinasEntity2);

        onsDadosRelacionamentoEntreConjuntosEUsinasEntity2 = getOnsDadosRelacionamentoEntreConjuntosEUsinasEntitySample2();
        assertThat(onsDadosRelacionamentoEntreConjuntosEUsinasEntity1).isNotEqualTo(onsDadosRelacionamentoEntreConjuntosEUsinasEntity2);
    }
}
