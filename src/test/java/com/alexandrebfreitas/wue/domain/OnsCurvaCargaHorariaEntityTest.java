package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsCurvaCargaHorariaEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsCurvaCargaHorariaEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsCurvaCargaHorariaEntity.class);
        OnsCurvaCargaHorariaEntity onsCurvaCargaHorariaEntity1 = getOnsCurvaCargaHorariaEntitySample1();
        OnsCurvaCargaHorariaEntity onsCurvaCargaHorariaEntity2 = new OnsCurvaCargaHorariaEntity();
        assertThat(onsCurvaCargaHorariaEntity1).isNotEqualTo(onsCurvaCargaHorariaEntity2);

        onsCurvaCargaHorariaEntity2.setId(onsCurvaCargaHorariaEntity1.getId());
        assertThat(onsCurvaCargaHorariaEntity1).isEqualTo(onsCurvaCargaHorariaEntity2);

        onsCurvaCargaHorariaEntity2 = getOnsCurvaCargaHorariaEntitySample2();
        assertThat(onsCurvaCargaHorariaEntity1).isNotEqualTo(onsCurvaCargaHorariaEntity2);
    }
}
