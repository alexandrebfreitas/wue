package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosDisponibilidadeUsinasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosDisponibilidadeUsinasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosDisponibilidadeUsinasEntity.class);
        OnsDadosDisponibilidadeUsinasEntity onsDadosDisponibilidadeUsinasEntity1 = getOnsDadosDisponibilidadeUsinasEntitySample1();
        OnsDadosDisponibilidadeUsinasEntity onsDadosDisponibilidadeUsinasEntity2 = new OnsDadosDisponibilidadeUsinasEntity();
        assertThat(onsDadosDisponibilidadeUsinasEntity1).isNotEqualTo(onsDadosDisponibilidadeUsinasEntity2);

        onsDadosDisponibilidadeUsinasEntity2.setId(onsDadosDisponibilidadeUsinasEntity1.getId());
        assertThat(onsDadosDisponibilidadeUsinasEntity1).isEqualTo(onsDadosDisponibilidadeUsinasEntity2);

        onsDadosDisponibilidadeUsinasEntity2 = getOnsDadosDisponibilidadeUsinasEntitySample2();
        assertThat(onsDadosDisponibilidadeUsinasEntity1).isNotEqualTo(onsDadosDisponibilidadeUsinasEntity2);
    }
}
