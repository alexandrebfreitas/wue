package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAllPropertiesEquals(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity expected,
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity actual
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAutoGeneratedPropertiesEquals(expected, actual);
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAllUpdatablePropertiesEquals(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity expected,
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity actual
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityUpdatableFieldsEquals(expected, actual);
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAutoGeneratedPropertiesEquals(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity expected,
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityUpdatableFieldsEquals(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity expected,
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity relevant properties")
            .satisfies(a -> assertThat(a.getDscAgregacao()).as("check dscAgregacao").isEqualTo(expected.getDscAgregacao()))
            .satisfies(a -> assertThat(a.getCodCaracteristica()).as("check codCaracteristica").isEqualTo(expected.getCodCaracteristica()))
            .satisfies(a -> assertThat(a.getDscCaracteristica()).as("check dscCaracteristica").isEqualTo(expected.getDscCaracteristica()))
            .satisfies(a -> assertThat(a.getIdPeriodicidade()).as("check idPeriodicidade").isEqualTo(expected.getIdPeriodicidade()))
            .satisfies(a -> assertThat(a.getDinReferencia()).as("check dinReferencia").isEqualTo(expected.getDinReferencia()))
            .satisfies(a -> assertThat(a.getValSm1()).as("check valSm1").isEqualTo(expected.getValSm1()))
            .satisfies(a -> assertThat(a.getValSm2()).as("check valSm2").isEqualTo(expected.getValSm2()))
            .satisfies(a -> assertThat(a.getValSm3()).as("check valSm3").isEqualTo(expected.getValSm3()))
            .satisfies(a -> assertThat(a.getValSm4()).as("check valSm4").isEqualTo(expected.getValSm4()))
            .satisfies(a -> assertThat(a.getValSm5()).as("check valSm5").isEqualTo(expected.getValSm5()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityUpdatableRelationshipsEquals(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity expected,
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity actual
    ) {
        // empty method
    }
}
