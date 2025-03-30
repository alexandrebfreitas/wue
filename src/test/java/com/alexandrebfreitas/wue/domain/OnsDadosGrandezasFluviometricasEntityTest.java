package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosGrandezasFluviometricasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosGrandezasFluviometricasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosGrandezasFluviometricasEntity.class);
        OnsDadosGrandezasFluviometricasEntity onsDadosGrandezasFluviometricasEntity1 = getOnsDadosGrandezasFluviometricasEntitySample1();
        OnsDadosGrandezasFluviometricasEntity onsDadosGrandezasFluviometricasEntity2 = new OnsDadosGrandezasFluviometricasEntity();
        assertThat(onsDadosGrandezasFluviometricasEntity1).isNotEqualTo(onsDadosGrandezasFluviometricasEntity2);

        onsDadosGrandezasFluviometricasEntity2.setId(onsDadosGrandezasFluviometricasEntity1.getId());
        assertThat(onsDadosGrandezasFluviometricasEntity1).isEqualTo(onsDadosGrandezasFluviometricasEntity2);

        onsDadosGrandezasFluviometricasEntity2 = getOnsDadosGrandezasFluviometricasEntitySample2();
        assertThat(onsDadosGrandezasFluviometricasEntity1).isNotEqualTo(onsDadosGrandezasFluviometricasEntity2);
    }
}
