package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsFatorCapacidadeGeracaoEolicaESolarEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsFatorCapacidadeGeracaoEolicaESolarEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsFatorCapacidadeGeracaoEolicaESolarEntity.class);
        OnsFatorCapacidadeGeracaoEolicaESolarEntity onsFatorCapacidadeGeracaoEolicaESolarEntity1 =
            getOnsFatorCapacidadeGeracaoEolicaESolarEntitySample1();
        OnsFatorCapacidadeGeracaoEolicaESolarEntity onsFatorCapacidadeGeracaoEolicaESolarEntity2 =
            new OnsFatorCapacidadeGeracaoEolicaESolarEntity();
        assertThat(onsFatorCapacidadeGeracaoEolicaESolarEntity1).isNotEqualTo(onsFatorCapacidadeGeracaoEolicaESolarEntity2);

        onsFatorCapacidadeGeracaoEolicaESolarEntity2.setId(onsFatorCapacidadeGeracaoEolicaESolarEntity1.getId());
        assertThat(onsFatorCapacidadeGeracaoEolicaESolarEntity1).isEqualTo(onsFatorCapacidadeGeracaoEolicaESolarEntity2);

        onsFatorCapacidadeGeracaoEolicaESolarEntity2 = getOnsFatorCapacidadeGeracaoEolicaESolarEntitySample2();
        assertThat(onsFatorCapacidadeGeracaoEolicaESolarEntity1).isNotEqualTo(onsFatorCapacidadeGeracaoEolicaESolarEntity2);
    }
}
