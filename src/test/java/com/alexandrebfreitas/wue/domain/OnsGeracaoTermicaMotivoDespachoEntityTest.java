package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsGeracaoTermicaMotivoDespachoEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsGeracaoTermicaMotivoDespachoEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsGeracaoTermicaMotivoDespachoEntity.class);
        OnsGeracaoTermicaMotivoDespachoEntity onsGeracaoTermicaMotivoDespachoEntity1 = getOnsGeracaoTermicaMotivoDespachoEntitySample1();
        OnsGeracaoTermicaMotivoDespachoEntity onsGeracaoTermicaMotivoDespachoEntity2 = new OnsGeracaoTermicaMotivoDespachoEntity();
        assertThat(onsGeracaoTermicaMotivoDespachoEntity1).isNotEqualTo(onsGeracaoTermicaMotivoDespachoEntity2);

        onsGeracaoTermicaMotivoDespachoEntity2.setId(onsGeracaoTermicaMotivoDespachoEntity1.getId());
        assertThat(onsGeracaoTermicaMotivoDespachoEntity1).isEqualTo(onsGeracaoTermicaMotivoDespachoEntity2);

        onsGeracaoTermicaMotivoDespachoEntity2 = getOnsGeracaoTermicaMotivoDespachoEntitySample2();
        assertThat(onsGeracaoTermicaMotivoDespachoEntity1).isNotEqualTo(onsGeracaoTermicaMotivoDespachoEntity2);
    }
}
