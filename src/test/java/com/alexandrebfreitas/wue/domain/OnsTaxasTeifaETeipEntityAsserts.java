package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsTaxasTeifaETeipEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsTaxasTeifaETeipEntityAllPropertiesEquals(
        OnsTaxasTeifaETeipEntity expected,
        OnsTaxasTeifaETeipEntity actual
    ) {
        assertOnsTaxasTeifaETeipEntityAutoGeneratedPropertiesEquals(expected, actual);
        assertOnsTaxasTeifaETeipEntityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsTaxasTeifaETeipEntityAllUpdatablePropertiesEquals(
        OnsTaxasTeifaETeipEntity expected,
        OnsTaxasTeifaETeipEntity actual
    ) {
        assertOnsTaxasTeifaETeipEntityUpdatableFieldsEquals(expected, actual);
        assertOnsTaxasTeifaETeipEntityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsTaxasTeifaETeipEntityAutoGeneratedPropertiesEquals(
        OnsTaxasTeifaETeipEntity expected,
        OnsTaxasTeifaETeipEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsTaxasTeifaETeipEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsTaxasTeifaETeipEntityUpdatableFieldsEquals(
        OnsTaxasTeifaETeipEntity expected,
        OnsTaxasTeifaETeipEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsTaxasTeifaETeipEntity relevant properties")
            .satisfies(a -> assertThat(a.getNomUsina()).as("check nomUsina").isEqualTo(expected.getNomUsina()))
            .satisfies(a -> assertThat(a.getCodCeg()).as("check codCeg").isEqualTo(expected.getCodCeg()))
            .satisfies(a -> assertThat(a.getIdTipousina()).as("check idTipousina").isEqualTo(expected.getIdTipousina()))
            .satisfies(a -> assertThat(a.getDinMes()).as("check dinMes").isEqualTo(expected.getDinMes()))
            .satisfies(a -> assertThat(a.getNomTaxa()).as("check nomTaxa").isEqualTo(expected.getNomTaxa()))
            .satisfies(a -> assertThat(a.getValTaxa()).as("check valTaxa").isEqualTo(expected.getValTaxa()))
            .satisfies(a -> assertThat(a.getNumVersao()).as("check numVersao").isEqualTo(expected.getNumVersao()))
            .satisfies(a -> assertThat(a.getDinInstante()).as("check dinInstante").isEqualTo(expected.getDinInstante()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsTaxasTeifaETeipEntityUpdatableRelationshipsEquals(
        OnsTaxasTeifaETeipEntity expected,
        OnsTaxasTeifaETeipEntity actual
    ) {
        // empty method
    }
}
