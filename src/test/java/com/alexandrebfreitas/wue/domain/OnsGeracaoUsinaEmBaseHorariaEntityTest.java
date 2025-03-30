package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsGeracaoUsinaEmBaseHorariaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsGeracaoUsinaEmBaseHorariaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsGeracaoUsinaEmBaseHorariaEntity.class);
        OnsGeracaoUsinaEmBaseHorariaEntity onsGeracaoUsinaEmBaseHorariaEntity1 = getOnsGeracaoUsinaEmBaseHorariaEntitySample1();
        OnsGeracaoUsinaEmBaseHorariaEntity onsGeracaoUsinaEmBaseHorariaEntity2 = new OnsGeracaoUsinaEmBaseHorariaEntity();
        assertThat(onsGeracaoUsinaEmBaseHorariaEntity1).isNotEqualTo(onsGeracaoUsinaEmBaseHorariaEntity2);

        onsGeracaoUsinaEmBaseHorariaEntity2.setId(onsGeracaoUsinaEmBaseHorariaEntity1.getId());
        assertThat(onsGeracaoUsinaEmBaseHorariaEntity1).isEqualTo(onsGeracaoUsinaEmBaseHorariaEntity2);

        onsGeracaoUsinaEmBaseHorariaEntity2 = getOnsGeracaoUsinaEmBaseHorariaEntitySample2();
        assertThat(onsGeracaoUsinaEmBaseHorariaEntity1).isNotEqualTo(onsGeracaoUsinaEmBaseHorariaEntity2);
    }
}
