package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsCargaEnergiaProgramadaEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCargaEnergiaProgramadaEntityAllPropertiesEquals(
        OnsCargaEnergiaProgramadaEntity expected,
        OnsCargaEnergiaProgramadaEntity actual
    ) {
        assertOnsCargaEnergiaProgramadaEntityAutoGeneratedPropertiesEquals(expected, actual);
        assertOnsCargaEnergiaProgramadaEntityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCargaEnergiaProgramadaEntityAllUpdatablePropertiesEquals(
        OnsCargaEnergiaProgramadaEntity expected,
        OnsCargaEnergiaProgramadaEntity actual
    ) {
        assertOnsCargaEnergiaProgramadaEntityUpdatableFieldsEquals(expected, actual);
        assertOnsCargaEnergiaProgramadaEntityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCargaEnergiaProgramadaEntityAutoGeneratedPropertiesEquals(
        OnsCargaEnergiaProgramadaEntity expected,
        OnsCargaEnergiaProgramadaEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsCargaEnergiaProgramadaEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCargaEnergiaProgramadaEntityUpdatableFieldsEquals(
        OnsCargaEnergiaProgramadaEntity expected,
        OnsCargaEnergiaProgramadaEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsCargaEnergiaProgramadaEntity relevant properties")
            .satisfies(a -> assertThat(a.getCodAreacarga()).as("check codAreacarga").isEqualTo(expected.getCodAreacarga()))
            .satisfies(a -> assertThat(a.getDatReferencia()).as("check datReferencia").isEqualTo(expected.getDatReferencia()))
            .satisfies(a -> assertThat(a.getDinReferenciautc()).as("check dinReferenciautc").isEqualTo(expected.getDinReferenciautc()))
            .satisfies(a ->
                assertThat(a.getValCargaglobalprogramada())
                    .as("check valCargaglobalprogramada")
                    .isEqualTo(expected.getValCargaglobalprogramada())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCargaEnergiaProgramadaEntityUpdatableRelationshipsEquals(
        OnsCargaEnergiaProgramadaEntity expected,
        OnsCargaEnergiaProgramadaEntity actual
    ) {
        // empty method
    }
}
