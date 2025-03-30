package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCapacidadeInstaladaGeracaoEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsCapacidadeInstaladaGeracaoEntity getOnsCapacidadeInstaladaGeracaoEntitySample1() {
        return new OnsCapacidadeInstaladaGeracaoEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .nomModalidadeoperacao("nomModalidadeoperacao1")
            .nomAgenteproprietario("nomAgenteproprietario1")
            .nomTipousina("nomTipousina1")
            .nomUsina("nomUsina1")
            .ceg("ceg1")
            .nomUnidadegeradora("nomUnidadegeradora1")
            .codEquipamento("codEquipamento1")
            .numUnidadegeradora("numUnidadegeradora1")
            .nomCombustivel("nomCombustivel1");
    }

    public static OnsCapacidadeInstaladaGeracaoEntity getOnsCapacidadeInstaladaGeracaoEntitySample2() {
        return new OnsCapacidadeInstaladaGeracaoEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .nomModalidadeoperacao("nomModalidadeoperacao2")
            .nomAgenteproprietario("nomAgenteproprietario2")
            .nomTipousina("nomTipousina2")
            .nomUsina("nomUsina2")
            .ceg("ceg2")
            .nomUnidadegeradora("nomUnidadegeradora2")
            .codEquipamento("codEquipamento2")
            .numUnidadegeradora("numUnidadegeradora2")
            .nomCombustivel("nomCombustivel2");
    }

    public static OnsCapacidadeInstaladaGeracaoEntity getOnsCapacidadeInstaladaGeracaoEntityRandomSampleGenerator() {
        return new OnsCapacidadeInstaladaGeracaoEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .nomModalidadeoperacao(UUID.randomUUID().toString())
            .nomAgenteproprietario(UUID.randomUUID().toString())
            .nomTipousina(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString())
            .nomUnidadegeradora(UUID.randomUUID().toString())
            .codEquipamento(UUID.randomUUID().toString())
            .numUnidadegeradora(UUID.randomUUID().toString())
            .nomCombustivel(UUID.randomUUID().toString());
    }
}
