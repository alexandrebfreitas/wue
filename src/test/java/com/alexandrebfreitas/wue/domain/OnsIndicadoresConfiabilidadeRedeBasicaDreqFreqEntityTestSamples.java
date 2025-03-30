package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntitySample1() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity()
            .id(1L)
            .dscAgregacao("dscAgregacao1")
            .codCaracteristica("codCaracteristica1")
            .dscCaracteristica("dscCaracteristica1")
            .idPeriodicidade("idPeriodicidade1");
    }

    public static OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntitySample2() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity()
            .id(2L)
            .dscAgregacao("dscAgregacao2")
            .codCaracteristica("codCaracteristica2")
            .dscCaracteristica("dscCaracteristica2")
            .idPeriodicidade("idPeriodicidade2");
    }

    public static OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityRandomSampleGenerator() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity()
            .id(longCount.incrementAndGet())
            .dscAgregacao(UUID.randomUUID().toString())
            .codCaracteristica(UUID.randomUUID().toString())
            .dscCaracteristica(UUID.randomUUID().toString())
            .idPeriodicidade(UUID.randomUUID().toString());
    }
}
