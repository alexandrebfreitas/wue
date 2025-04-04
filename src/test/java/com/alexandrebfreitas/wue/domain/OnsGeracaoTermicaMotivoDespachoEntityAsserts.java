package com.alexandrebfreitas.wue.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OnsGeracaoTermicaMotivoDespachoEntityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsGeracaoTermicaMotivoDespachoEntityAllPropertiesEquals(
        OnsGeracaoTermicaMotivoDespachoEntity expected,
        OnsGeracaoTermicaMotivoDespachoEntity actual
    ) {
        assertOnsGeracaoTermicaMotivoDespachoEntityAutoGeneratedPropertiesEquals(expected, actual);
        assertOnsGeracaoTermicaMotivoDespachoEntityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsGeracaoTermicaMotivoDespachoEntityAllUpdatablePropertiesEquals(
        OnsGeracaoTermicaMotivoDespachoEntity expected,
        OnsGeracaoTermicaMotivoDespachoEntity actual
    ) {
        assertOnsGeracaoTermicaMotivoDespachoEntityUpdatableFieldsEquals(expected, actual);
        assertOnsGeracaoTermicaMotivoDespachoEntityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsGeracaoTermicaMotivoDespachoEntityAutoGeneratedPropertiesEquals(
        OnsGeracaoTermicaMotivoDespachoEntity expected,
        OnsGeracaoTermicaMotivoDespachoEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsGeracaoTermicaMotivoDespachoEntity auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsGeracaoTermicaMotivoDespachoEntityUpdatableFieldsEquals(
        OnsGeracaoTermicaMotivoDespachoEntity expected,
        OnsGeracaoTermicaMotivoDespachoEntity actual
    ) {
        assertThat(actual)
            .as("Verify OnsGeracaoTermicaMotivoDespachoEntity relevant properties")
            .satisfies(a -> assertThat(a.getDinInstante()).as("check dinInstante").isEqualTo(expected.getDinInstante()))
            .satisfies(a -> assertThat(a.getNomTipopatamar()).as("check nomTipopatamar").isEqualTo(expected.getNomTipopatamar()))
            .satisfies(a -> assertThat(a.getIdSubsistema()).as("check idSubsistema").isEqualTo(expected.getIdSubsistema()))
            .satisfies(a -> assertThat(a.getNomSubsistema()).as("check nomSubsistema").isEqualTo(expected.getNomSubsistema()))
            .satisfies(a -> assertThat(a.getNomUsina()).as("check nomUsina").isEqualTo(expected.getNomUsina()))
            .satisfies(a ->
                assertThat(a.getCodUsinaplanejamento()).as("check codUsinaplanejamento").isEqualTo(expected.getCodUsinaplanejamento())
            )
            .satisfies(a -> assertThat(a.getCeg()).as("check ceg").isEqualTo(expected.getCeg()))
            .satisfies(a -> assertThat(a.getValProggeracao()).as("check valProggeracao").isEqualTo(expected.getValProggeracao()))
            .satisfies(a -> assertThat(a.getValProgordemmerito()).as("check valProgordemmerito").isEqualTo(expected.getValProgordemmerito())
            )
            .satisfies(a ->
                assertThat(a.getValProgordemdemeritoref())
                    .as("check valProgordemdemeritoref")
                    .isEqualTo(expected.getValProgordemdemeritoref())
            )
            .satisfies(a ->
                assertThat(a.getValProgordemdemeritoacimadainflex())
                    .as("check valProgordemdemeritoacimadainflex")
                    .isEqualTo(expected.getValProgordemdemeritoacimadainflex())
            )
            .satisfies(a ->
                assertThat(a.getValProginflexibilidade()).as("check valProginflexibilidade").isEqualTo(expected.getValProginflexibilidade())
            )
            .satisfies(a ->
                assertThat(a.getValProginflexembutmerito())
                    .as("check valProginflexembutmerito")
                    .isEqualTo(expected.getValProginflexembutmerito())
            )
            .satisfies(a -> assertThat(a.getValProginflexpura()).as("check valProginflexpura").isEqualTo(expected.getValProginflexpura()))
            .satisfies(a ->
                assertThat(a.getValPrograzaoeletrica()).as("check valPrograzaoeletrica").isEqualTo(expected.getValPrograzaoeletrica())
            )
            .satisfies(a ->
                assertThat(a.getValProggarantiaenergetica())
                    .as("check valProggarantiaenergetica")
                    .isEqualTo(expected.getValProggarantiaenergetica())
            )
            .satisfies(a -> assertThat(a.getValProggfom()).as("check valProggfom").isEqualTo(expected.getValProggfom()))
            .satisfies(a ->
                assertThat(a.getValProgreposicaoperdas()).as("check valProgreposicaoperdas").isEqualTo(expected.getValProgreposicaoperdas())
            )
            .satisfies(a -> assertThat(a.getValProgexportacao()).as("check valProgexportacao").isEqualTo(expected.getValProgexportacao()))
            .satisfies(a ->
                assertThat(a.getValProgreservapotencia()).as("check valProgreservapotencia").isEqualTo(expected.getValProgreservapotencia())
            )
            .satisfies(a -> assertThat(a.getValProggsub()).as("check valProggsub").isEqualTo(expected.getValProggsub()))
            .satisfies(a ->
                assertThat(a.getValProgunitcommitment()).as("check valProgunitcommitment").isEqualTo(expected.getValProgunitcommitment())
            )
            .satisfies(a ->
                assertThat(a.getValProgconstrainedoff()).as("check valProgconstrainedoff").isEqualTo(expected.getValProgconstrainedoff())
            )
            .satisfies(a ->
                assertThat(a.getValProginflexibilidadedessem())
                    .as("check valProginflexibilidadedessem")
                    .isEqualTo(expected.getValProginflexibilidadedessem())
            )
            .satisfies(a -> assertThat(a.getValVerifgeracao()).as("check valVerifgeracao").isEqualTo(expected.getValVerifgeracao()))
            .satisfies(a ->
                assertThat(a.getValVerifordemmerito()).as("check valVerifordemmerito").isEqualTo(expected.getValVerifordemmerito())
            )
            .satisfies(a ->
                assertThat(a.getValVerifordemdemeritoacimadainflex())
                    .as("check valVerifordemdemeritoacimadainflex")
                    .isEqualTo(expected.getValVerifordemdemeritoacimadainflex())
            )
            .satisfies(a ->
                assertThat(a.getValVerifinflexibilidade())
                    .as("check valVerifinflexibilidade")
                    .isEqualTo(expected.getValVerifinflexibilidade())
            )
            .satisfies(a ->
                assertThat(a.getValVerifinflexembutmerito())
                    .as("check valVerifinflexembutmerito")
                    .isEqualTo(expected.getValVerifinflexembutmerito())
            )
            .satisfies(a -> assertThat(a.getValVerifinflexpura()).as("check valVerifinflexpura").isEqualTo(expected.getValVerifinflexpura())
            )
            .satisfies(a ->
                assertThat(a.getValVerifrazaoeletrica()).as("check valVerifrazaoeletrica").isEqualTo(expected.getValVerifrazaoeletrica())
            )
            .satisfies(a ->
                assertThat(a.getValVerifgarantiaenergetica())
                    .as("check valVerifgarantiaenergetica")
                    .isEqualTo(expected.getValVerifgarantiaenergetica())
            )
            .satisfies(a -> assertThat(a.getValVerifgfom()).as("check valVerifgfom").isEqualTo(expected.getValVerifgfom()))
            .satisfies(a ->
                assertThat(a.getValVerifreposicaoperdas())
                    .as("check valVerifreposicaoperdas")
                    .isEqualTo(expected.getValVerifreposicaoperdas())
            )
            .satisfies(a -> assertThat(a.getValVerifexportacao()).as("check valVerifexportacao").isEqualTo(expected.getValVerifexportacao())
            )
            .satisfies(a -> assertThat(a.getValFdexp()).as("check valFdexp").isEqualTo(expected.getValFdexp()))
            .satisfies(a ->
                assertThat(a.getValVerifreservapotencia())
                    .as("check valVerifreservapotencia")
                    .isEqualTo(expected.getValVerifreservapotencia())
            )
            .satisfies(a ->
                assertThat(a.getValAtendsatisfatoriorpo())
                    .as("check valAtendsatisfatoriorpo")
                    .isEqualTo(expected.getValAtendsatisfatoriorpo())
            )
            .satisfies(a -> assertThat(a.getValVerifgsub()).as("check valVerifgsub").isEqualTo(expected.getValVerifgsub()))
            .satisfies(a ->
                assertThat(a.getValVerifunitcommitment()).as("check valVerifunitcommitment").isEqualTo(expected.getValVerifunitcommitment())
            )
            .satisfies(a ->
                assertThat(a.getValVerifconstrainedoff()).as("check valVerifconstrainedoff").isEqualTo(expected.getValVerifconstrainedoff())
            )
            .satisfies(a ->
                assertThat(a.getTipRestricaoeletrica()).as("check tipRestricaoeletrica").isEqualTo(expected.getTipRestricaoeletrica())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOnsGeracaoTermicaMotivoDespachoEntityUpdatableRelationshipsEquals(
        OnsGeracaoTermicaMotivoDespachoEntity expected,
        OnsGeracaoTermicaMotivoDespachoEntity actual
    ) {
        // empty method
    }
}
