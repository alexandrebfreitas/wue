package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosValoresProgramacaoDiariaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosValoresProgramacaoDiariaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosValoresProgramacaoDiariaEntity.class);
        OnsDadosValoresProgramacaoDiariaEntity onsDadosValoresProgramacaoDiariaEntity1 = getOnsDadosValoresProgramacaoDiariaEntitySample1();
        OnsDadosValoresProgramacaoDiariaEntity onsDadosValoresProgramacaoDiariaEntity2 = new OnsDadosValoresProgramacaoDiariaEntity();
        assertThat(onsDadosValoresProgramacaoDiariaEntity1).isNotEqualTo(onsDadosValoresProgramacaoDiariaEntity2);

        onsDadosValoresProgramacaoDiariaEntity2.setId(onsDadosValoresProgramacaoDiariaEntity1.getId());
        assertThat(onsDadosValoresProgramacaoDiariaEntity1).isEqualTo(onsDadosValoresProgramacaoDiariaEntity2);

        onsDadosValoresProgramacaoDiariaEntity2 = getOnsDadosValoresProgramacaoDiariaEntitySample2();
        assertThat(onsDadosValoresProgramacaoDiariaEntity1).isNotEqualTo(onsDadosValoresProgramacaoDiariaEntity2);
    }
}
