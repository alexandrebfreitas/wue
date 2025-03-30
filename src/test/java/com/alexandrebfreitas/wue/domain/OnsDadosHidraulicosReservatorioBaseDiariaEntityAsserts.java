package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsDadosHidraulicosReservatorioBaseDiariaEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosHidraulicosReservatorioBaseDiariaEntityAllPropertiesEquals(
        OnsDadosHidraulicosReservatorioBaseDiariaEntity expected,
        OnsDadosHidraulicosReservatorioBaseDiariaEntity actual
    ) {
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityAutoGeneratedPropertiesEquals(expected, actual);
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosHidraulicosReservatorioBaseDiariaEntityAllUpdatablePropertiesEquals(
        OnsDadosHidraulicosReservatorioBaseDiariaEntity expected,
        OnsDadosHidraulicosReservatorioBaseDiariaEntity actual
    ) {
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityUpdatableFieldsEquals(expected, actual);
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosHidraulicosReservatorioBaseDiariaEntityAutoGeneratedPropertiesEquals(
        OnsDadosHidraulicosReservatorioBaseDiariaEntity expected,
        OnsDadosHidraulicosReservatorioBaseDiariaEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsDadosHidraulicosReservatorioBaseDiariaEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosHidraulicosReservatorioBaseDiariaEntityUpdatableFieldsEquals(
        OnsDadosHidraulicosReservatorioBaseDiariaEntity expected,
        OnsDadosHidraulicosReservatorioBaseDiariaEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsDadosHidraulicosReservatorioBaseDiariaEntity relevant properties")
            .satisfies(a -> assertThat(a.getValNivelmontante()).as("check valNivelmontante").isEqualTo(expected.getValNivelmontante()))
            .satisfies(a -> assertThat(a.getValNiveljusante()).as("check valNiveljusante").isEqualTo(expected.getValNiveljusante()))
            .satisfies(a -> assertThat(a.getValVolumeutilcon()).as("check valVolumeutilcon").isEqualTo(expected.getValVolumeutilcon()))
            .satisfies(a -> assertThat(a.getValVazaoafluente()).as("check valVazaoafluente").isEqualTo(expected.getValVazaoafluente()))
            .satisfies(a -> assertThat(a.getValVazaoturbinada()).as("check valVazaoturbinada").isEqualTo(expected.getValVazaoturbinada()))
            .satisfies(a -> assertThat(a.getValVazaovertida()).as("check valVazaovertida").isEqualTo(expected.getValVazaovertida()))
            .satisfies(a ->
                assertThat(a.getValVazaooutrasestruturas())
                    .as("check valVazaooutrasestruturas")
                    .isEqualTo(expected.getValVazaooutrasestruturas())
            )
            .satisfies(a -> assertThat(a.getValVazaodefluente()).as("check valVazaodefluente").isEqualTo(expected.getValVazaodefluente()))
            .satisfies(a ->
                assertThat(a.getValVazaotransferida()).as("check valVazaotransferida").isEqualTo(expected.getValVazaotransferida())
            )
            .satisfies(a -> assertThat(a.getValVazaonatural()).as("check valVazaonatural").isEqualTo(expected.getValVazaonatural()))
            .satisfies(a -> assertThat(a.getValVazaoartificial()).as("check valVazaoartificial").isEqualTo(expected.getValVazaoartificial())
            )
            .satisfies(a ->
                assertThat(a.getValVazaoincremental()).as("check valVazaoincremental").isEqualTo(expected.getValVazaoincremental())
            )
            .satisfies(a ->
                assertThat(a.getValVazaoevaporacaoliquida())
                    .as("check valVazaoevaporacaoliquida")
                    .isEqualTo(expected.getValVazaoevaporacaoliquida())
            )
            .satisfies(a ->
                assertThat(a.getValVazaousoconsuntivo()).as("check valVazaousoconsuntivo").isEqualTo(expected.getValVazaousoconsuntivo())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsDadosHidraulicosReservatorioBaseDiariaEntityUpdatableRelationshipsEquals(
        OnsDadosHidraulicosReservatorioBaseDiariaEntity expected,
        OnsDadosHidraulicosReservatorioBaseDiariaEntity actual
    ) {
        // empty method
    }
}
