package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCapacidadeInstaladaGeracaoEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCapacidadeInstaladaGeracaoEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCapacidadeInstaladaGeracaoEntity.class);
        OnsCapacidadeInstaladaGeracaoEntity onsCapacidadeInstaladaGeracaoEntity1 = getOnsCapacidadeInstaladaGeracaoEntitySample1();
        OnsCapacidadeInstaladaGeracaoEntity onsCapacidadeInstaladaGeracaoEntity2 = new OnsCapacidadeInstaladaGeracaoEntity();
        assertThat(onsCapacidadeInstaladaGeracaoEntity1).isNotEqualTo(onsCapacidadeInstaladaGeracaoEntity2);

        onsCapacidadeInstaladaGeracaoEntity2.setId(onsCapacidadeInstaladaGeracaoEntity1.getId());
        assertThat(onsCapacidadeInstaladaGeracaoEntity1).isEqualTo(onsCapacidadeInstaladaGeracaoEntity2);

        onsCapacidadeInstaladaGeracaoEntity2 = getOnsCapacidadeInstaladaGeracaoEntitySample2();
        assertThat(onsCapacidadeInstaladaGeracaoEntity1).isNotEqualTo(onsCapacidadeInstaladaGeracaoEntity2);
    }
}
