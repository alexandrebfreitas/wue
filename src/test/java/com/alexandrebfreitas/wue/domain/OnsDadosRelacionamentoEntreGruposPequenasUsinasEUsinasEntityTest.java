package com.alexandrebfreitas.wue.domain;

import static com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexandrebfreitas.wue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.class);
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity1 =
            getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntitySample1();
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity2 =
            new OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity();
        assertThat(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity1).isNotEqualTo(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity2
        );

        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity2.setId(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity1.getId()
        );
        assertThat(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity1).isEqualTo(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity2
        );

        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity2 =
            getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntitySample2();
        assertThat(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity1).isNotEqualTo(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity2
        );
    }
}
