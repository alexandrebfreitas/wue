package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsModalidadeOperacaoUsinasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsModalidadeOperacaoUsinasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsModalidadeOperacaoUsinasEntity.class);
        OnsModalidadeOperacaoUsinasEntity onsModalidadeOperacaoUsinasEntity1 = getOnsModalidadeOperacaoUsinasEntitySample1();
        OnsModalidadeOperacaoUsinasEntity onsModalidadeOperacaoUsinasEntity2 = new OnsModalidadeOperacaoUsinasEntity();
        assertThat(onsModalidadeOperacaoUsinasEntity1).isNotEqualTo(onsModalidadeOperacaoUsinasEntity2);

        onsModalidadeOperacaoUsinasEntity2.setId(onsModalidadeOperacaoUsinasEntity1.getId());
        assertThat(onsModalidadeOperacaoUsinasEntity1).isEqualTo(onsModalidadeOperacaoUsinasEntity2);

        onsModalidadeOperacaoUsinasEntity2 = getOnsModalidadeOperacaoUsinasEntitySample2();
        assertThat(onsModalidadeOperacaoUsinasEntity1).isNotEqualTo(onsModalidadeOperacaoUsinasEntity2);
    }
}
