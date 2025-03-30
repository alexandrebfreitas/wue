package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCvuUsinaTermicasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsCvuUsinaTermicasEntity getOnsCvuUsinaTermicasEntitySample1() {
        return new OnsCvuUsinaTermicasEntity()
            .id(1L)
            .anoReferencia(1)
            .mesReferencia(1)
            .numRevisao(1)
            .nomSemanaoperativa("nomSemanaoperativa1")
            .codModelos(1)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .nomUsina("nomUsina1");
    }

    public static OnsCvuUsinaTermicasEntity getOnsCvuUsinaTermicasEntitySample2() {
        return new OnsCvuUsinaTermicasEntity()
            .id(2L)
            .anoReferencia(2)
            .mesReferencia(2)
            .numRevisao(2)
            .nomSemanaoperativa("nomSemanaoperativa2")
            .codModelos(2)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .nomUsina("nomUsina2");
    }

    public static OnsCvuUsinaTermicasEntity getOnsCvuUsinaTermicasEntityRandomSampleGenerator() {
        return new OnsCvuUsinaTermicasEntity()
            .id(longCount.incrementAndGet())
            .anoReferencia(intCount.incrementAndGet())
            .mesReferencia(intCount.incrementAndGet())
            .numRevisao(intCount.incrementAndGet())
            .nomSemanaoperativa(UUID.randomUUID().toString())
            .codModelos(intCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString());
    }
}
