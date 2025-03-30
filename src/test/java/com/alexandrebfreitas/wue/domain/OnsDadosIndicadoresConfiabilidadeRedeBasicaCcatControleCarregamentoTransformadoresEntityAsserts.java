package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAllPropertiesEquals(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity expected,
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity actual
    ) {
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAutoGeneratedPropertiesEquals(
            expected,
            actual
        );
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAllUpdatablePropertiesEquals(
            expected,
            actual
        );
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAllUpdatablePropertiesEquals(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity expected,
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity actual
    ) {
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityUpdatableFieldsEquals(
            expected,
            actual
        );
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityUpdatableRelationshipsEquals(
            expected,
            actual
        );
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAutoGeneratedPropertiesEquals(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity expected,
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityUpdatableFieldsEquals(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity expected,
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity relevant properties")
            .satisfies(a -> assertThat(a.getCodTipoagregacao()).as("check codTipoagregacao").isEqualTo(expected.getCodTipoagregacao()))
            .satisfies(a -> assertThat(a.getIdPeriodicidade()).as("check idPeriodicidade").isEqualTo(expected.getIdPeriodicidade()))
            .satisfies(a -> assertThat(a.getNomAgregacao()).as("check nomAgregacao").isEqualTo(expected.getNomAgregacao()))
            .satisfies(a -> assertThat(a.getDinReferencia()).as("check dinReferencia").isEqualTo(expected.getDinReferencia()))
            .satisfies(a ->
                assertThat(a.getNumTransformadoresoperacao())
                    .as("check numTransformadoresoperacao")
                    .isEqualTo(expected.getNumTransformadoresoperacao())
            )
            .satisfies(a ->
                assertThat(a.getNumTransformadoresviolados())
                    .as("check numTransformadoresviolados")
                    .isEqualTo(expected.getNumTransformadoresviolados())
            )
            .satisfies(a -> assertThat(a.getValCcat()).as("check valCcat").isEqualTo(expected.getValCcat()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityUpdatableRelationshipsEquals(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity expected,
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity actual
    ) {
        // empty method
    }
}
