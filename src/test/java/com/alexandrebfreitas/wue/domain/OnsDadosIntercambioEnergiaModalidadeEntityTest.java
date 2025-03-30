package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosIntercambioEnergiaModalidadeEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosIntercambioEnergiaModalidadeEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosIntercambioEnergiaModalidadeEntity.class);
        OnsDadosIntercambioEnergiaModalidadeEntity onsDadosIntercambioEnergiaModalidadeEntity1 =
            getOnsDadosIntercambioEnergiaModalidadeEntitySample1();
        OnsDadosIntercambioEnergiaModalidadeEntity onsDadosIntercambioEnergiaModalidadeEntity2 =
            new OnsDadosIntercambioEnergiaModalidadeEntity();
        assertThat(onsDadosIntercambioEnergiaModalidadeEntity1).isNotEqualTo(onsDadosIntercambioEnergiaModalidadeEntity2);

        onsDadosIntercambioEnergiaModalidadeEntity2.setId(onsDadosIntercambioEnergiaModalidadeEntity1.getId());
        assertThat(onsDadosIntercambioEnergiaModalidadeEntity1).isEqualTo(onsDadosIntercambioEnergiaModalidadeEntity2);

        onsDadosIntercambioEnergiaModalidadeEntity2 = getOnsDadosIntercambioEnergiaModalidadeEntitySample2();
        assertThat(onsDadosIntercambioEnergiaModalidadeEntity1).isNotEqualTo(onsDadosIntercambioEnergiaModalidadeEntity2);
    }
}
