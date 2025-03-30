package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsGeracaoTermicaMotivoDespachoEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsGeracaoTermicaMotivoDespachoEntity getOnsGeracaoTermicaMotivoDespachoEntitySample1() {
        return new OnsGeracaoTermicaMotivoDespachoEntity()
            .id(1L)
            .nomTipopatamar("nomTipopatamar1")
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .nomUsina("nomUsina1")
            .codUsinaplanejamento(1)
            .ceg("ceg1")
            .valAtendsatisfatoriorpo(1)
            .tipRestricaoeletrica(1);
    }

    public static OnsGeracaoTermicaMotivoDespachoEntity getOnsGeracaoTermicaMotivoDespachoEntitySample2() {
        return new OnsGeracaoTermicaMotivoDespachoEntity()
            .id(2L)
            .nomTipopatamar("nomTipopatamar2")
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .nomUsina("nomUsina2")
            .codUsinaplanejamento(2)
            .ceg("ceg2")
            .valAtendsatisfatoriorpo(2)
            .tipRestricaoeletrica(2);
    }

    public static OnsGeracaoTermicaMotivoDespachoEntity getOnsGeracaoTermicaMotivoDespachoEntityRandomSampleGenerator() {
        return new OnsGeracaoTermicaMotivoDespachoEntity()
            .id(longCount.incrementAndGet())
            .nomTipopatamar(UUID.randomUUID().toString())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .codUsinaplanejamento(intCount.incrementAndGet())
            .ceg(UUID.randomUUID().toString())
            .valAtendsatisfatoriorpo(intCount.incrementAndGet())
            .tipRestricaoeletrica(intCount.incrementAndGet());
    }
}
