package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsCmoSemanalEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCmoSemanalEntityAllPropertiesEquals(OnsCmoSemanalEntity expected, OnsCmoSemanalEntity actual) {
        assertOnsCmoSemanalEntityAutoGeneratedPropertiesEquals(expected, actual);
        assertOnsCmoSemanalEntityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCmoSemanalEntityAllUpdatablePropertiesEquals(OnsCmoSemanalEntity expected, OnsCmoSemanalEntity actual) {
        assertOnsCmoSemanalEntityUpdatableFieldsEquals(expected, actual);
        assertOnsCmoSemanalEntityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCmoSemanalEntityAutoGeneratedPropertiesEquals(OnsCmoSemanalEntity expected, OnsCmoSemanalEntity actual) {
        assertThat(actual)
            .as("Verify OnsCmoSemanalEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCmoSemanalEntityUpdatableFieldsEquals(OnsCmoSemanalEntity expected, OnsCmoSemanalEntity actual) {
        assertThat(actual)
            .as("Verify OnsCmoSemanalEntity relevant properties")
            .satisfies(a -> assertThat(a.getIdSubsistema()).as("check idSubsistema").isEqualTo(expected.getIdSubsistema()))
            .satisfies(a -> assertThat(a.getNomSubsistema()).as("check nomSubsistema").isEqualTo(expected.getNomSubsistema()))
            .satisfies(a -> assertThat(a.getDinInstante()).as("check dinInstante").isEqualTo(expected.getDinInstante()))
            .satisfies(a -> assertThat(a.getValCmomediasemanal()).as("check valCmomediasemanal").isEqualTo(expected.getValCmomediasemanal())
            )
            .satisfies(a -> assertThat(a.getValCmoleve()).as("check valCmoleve").isEqualTo(expected.getValCmoleve()))
            .satisfies(a -> assertThat(a.getValCmomedia()).as("check valCmomedia").isEqualTo(expected.getValCmomedia()))
            .satisfies(a -> assertThat(a.getValCmopesada()).as("check valCmopesada").isEqualTo(expected.getValCmopesada()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCmoSemanalEntityUpdatableRelationshipsEquals(OnsCmoSemanalEntity expected, OnsCmoSemanalEntity actual) {
        // empty method
    }
}
