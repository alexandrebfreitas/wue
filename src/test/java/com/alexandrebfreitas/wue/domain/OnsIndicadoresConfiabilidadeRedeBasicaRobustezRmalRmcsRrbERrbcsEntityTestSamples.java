package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntitySample1() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity()
            .id(1L)
            .codIndicador("codIndicador1")
            .dscAgregacao("dscAgregacao1")
            .codCaracteristica("codCaracteristica1")
            .dscCaracteristica("dscCaracteristica1")
            .idPeriodicidade("idPeriodicidade1");
    }

    public static OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntitySample2() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity()
            .id(2L)
            .codIndicador("codIndicador2")
            .dscAgregacao("dscAgregacao2")
            .codCaracteristica("codCaracteristica2")
            .dscCaracteristica("dscCaracteristica2")
            .idPeriodicidade("idPeriodicidade2");
    }

    public static OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityRandomSampleGenerator() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity()
            .id(longCount.incrementAndGet())
            .codIndicador(UUID.randomUUID().toString())
            .dscAgregacao(UUID.randomUUID().toString())
            .codCaracteristica(UUID.randomUUID().toString())
            .dscCaracteristica(UUID.randomUUID().toString())
            .idPeriodicidade(UUID.randomUUID().toString());
    }
}
