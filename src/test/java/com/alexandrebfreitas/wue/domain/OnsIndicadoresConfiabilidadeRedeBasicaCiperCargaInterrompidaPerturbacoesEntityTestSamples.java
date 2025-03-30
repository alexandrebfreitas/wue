package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntitySample1() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity()
            .id(1L)
            .dscAgregacao("dscAgregacao1")
            .codCaracteristica("codCaracteristica1")
            .dscCaracteristica("dscCaracteristica1")
            .idPeriodicidade("idPeriodicidade1");
    }

    public static OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntitySample2() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity()
            .id(2L)
            .dscAgregacao("dscAgregacao2")
            .codCaracteristica("codCaracteristica2")
            .dscCaracteristica("dscCaracteristica2")
            .idPeriodicidade("idPeriodicidade2");
    }

    public static OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityRandomSampleGenerator() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity()
            .id(longCount.incrementAndGet())
            .dscAgregacao(UUID.randomUUID().toString())
            .codCaracteristica(UUID.randomUUID().toString())
            .dscCaracteristica(UUID.randomUUID().toString())
            .idPeriodicidade(UUID.randomUUID().toString());
    }
}
