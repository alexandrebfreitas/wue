package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsCapacidadeInstaladaGeracaoEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCapacidadeInstaladaGeracaoEntityAllPropertiesEquals(
        OnsCapacidadeInstaladaGeracaoEntity expected,
        OnsCapacidadeInstaladaGeracaoEntity actual
    ) {
        assertOnsCapacidadeInstaladaGeracaoEntityAutoGeneratedPropertiesEquals(expected, actual);
        assertOnsCapacidadeInstaladaGeracaoEntityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCapacidadeInstaladaGeracaoEntityAllUpdatablePropertiesEquals(
        OnsCapacidadeInstaladaGeracaoEntity expected,
        OnsCapacidadeInstaladaGeracaoEntity actual
    ) {
        assertOnsCapacidadeInstaladaGeracaoEntityUpdatableFieldsEquals(expected, actual);
        assertOnsCapacidadeInstaladaGeracaoEntityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCapacidadeInstaladaGeracaoEntityAutoGeneratedPropertiesEquals(
        OnsCapacidadeInstaladaGeracaoEntity expected,
        OnsCapacidadeInstaladaGeracaoEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsCapacidadeInstaladaGeracaoEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCapacidadeInstaladaGeracaoEntityUpdatableFieldsEquals(
        OnsCapacidadeInstaladaGeracaoEntity expected,
        OnsCapacidadeInstaladaGeracaoEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsCapacidadeInstaladaGeracaoEntity relevant properties")
            .satisfies(a -> assertThat(a.getIdSubsistema()).as("check idSubsistema").isEqualTo(expected.getIdSubsistema()))
            .satisfies(a -> assertThat(a.getNomSubsistema()).as("check nomSubsistema").isEqualTo(expected.getNomSubsistema()))
            .satisfies(a -> assertThat(a.getIdEstado()).as("check idEstado").isEqualTo(expected.getIdEstado()))
            .satisfies(a -> assertThat(a.getNomEstado()).as("check nomEstado").isEqualTo(expected.getNomEstado()))
            .satisfies(a ->
                assertThat(a.getNomModalidadeoperacao()).as("check nomModalidadeoperacao").isEqualTo(expected.getNomModalidadeoperacao())
            )
            .satisfies(a ->
                assertThat(a.getNomAgenteproprietario()).as("check nomAgenteproprietario").isEqualTo(expected.getNomAgenteproprietario())
            )
            .satisfies(a -> assertThat(a.getNomTipousina()).as("check nomTipousina").isEqualTo(expected.getNomTipousina()))
            .satisfies(a -> assertThat(a.getNomUsina()).as("check nomUsina").isEqualTo(expected.getNomUsina()))
            .satisfies(a -> assertThat(a.getCeg()).as("check ceg").isEqualTo(expected.getCeg()))
            .satisfies(a -> assertThat(a.getNomUnidadegeradora()).as("check nomUnidadegeradora").isEqualTo(expected.getNomUnidadegeradora())
            )
            .satisfies(a -> assertThat(a.getCodEquipamento()).as("check codEquipamento").isEqualTo(expected.getCodEquipamento()))
            .satisfies(a -> assertThat(a.getNumUnidadegeradora()).as("check numUnidadegeradora").isEqualTo(expected.getNumUnidadegeradora())
            )
            .satisfies(a -> assertThat(a.getNomCombustivel()).as("check nomCombustivel").isEqualTo(expected.getNomCombustivel()))
            .satisfies(a -> assertThat(a.getDatEntradateste()).as("check datEntradateste").isEqualTo(expected.getDatEntradateste()))
            .satisfies(a -> assertThat(a.getDatEntradaoperacao()).as("check datEntradaoperacao").isEqualTo(expected.getDatEntradaoperacao())
            )
            .satisfies(a -> assertThat(a.getDatDesativacao()).as("check datDesativacao").isEqualTo(expected.getDatDesativacao()))
            .satisfies(a -> assertThat(a.getValPotenciaefetiva()).as("check valPotenciaefetiva").isEqualTo(expected.getValPotenciaefetiva())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsCapacidadeInstaladaGeracaoEntityUpdatableRelationshipsEquals(
        OnsCapacidadeInstaladaGeracaoEntity expected,
        OnsCapacidadeInstaladaGeracaoEntity actual
    ) {
        // empty method
    }
}
