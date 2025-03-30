package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsEquipamentosControleReativosEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsEquipamentosControleReativosEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsEquipamentosControleReativosEntity.class);
        OnsEquipamentosControleReativosEntity onsEquipamentosControleReativosEntity1 = getOnsEquipamentosControleReativosEntitySample1();
        OnsEquipamentosControleReativosEntity onsEquipamentosControleReativosEntity2 = new OnsEquipamentosControleReativosEntity();
        assertThat(onsEquipamentosControleReativosEntity1).isNotEqualTo(onsEquipamentosControleReativosEntity2);

        onsEquipamentosControleReativosEntity2.setId(onsEquipamentosControleReativosEntity1.getId());
        assertThat(onsEquipamentosControleReativosEntity1).isEqualTo(onsEquipamentosControleReativosEntity2);

        onsEquipamentosControleReativosEntity2 = getOnsEquipamentosControleReativosEntitySample2();
        assertThat(onsEquipamentosControleReativosEntity1).isNotEqualTo(onsEquipamentosControleReativosEntity2);
    }
}
