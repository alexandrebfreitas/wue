package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAllPropertiesEquals(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity expected,
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity actual
    ) {
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAutoGeneratedPropertiesEquals(expected, actual);
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAllUpdatablePropertiesEquals(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity expected,
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity actual
    ) {
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableFieldsEquals(expected, actual);
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAutoGeneratedPropertiesEquals(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity expected,
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableFieldsEquals(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity expected,
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity relevant properties")
            .satisfies(a ->
                assertThat(a.getEnaArmazenavelReePercentualmlt())
                    .as("check enaArmazenavelReePercentualmlt")
                    .isEqualTo(expected.getEnaArmazenavelReePercentualmlt())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableRelationshipsEquals(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity expected,
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity actual
    ) {
        // empty method
    }
}
